package klein.shmulik.oti

import klein.shmulik.oti.data.QuizQuestion
import klein.shmulik.oti.data.QuizType
import klein.shmulik.oti.domain.LetterUseCase
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLAudioElement
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

enum class DisplayMode { LETTERS, NIKUD, WORDS, QUIZ }

fun main() {
    val useCase = LetterUseCase()
    var currentIndex = 0
    var currentMode = DisplayMode.LETTERS
    var quizQuestions: List<QuizQuestion> = emptyList()
    var quizScore = 0
    var quizAnswered = false
    var selectedQuizType = QuizType.LETTER_TO_NAME

    val letterElement = document.getElementById("letter") as HTMLElement
    val nameElement = document.getElementById("name") as HTMLElement
    val nikudNameElement = document.getElementById("nikud-name") as HTMLElement
    val transliterationElement = document.getElementById("transliteration") as HTMLElement
    val translationElement = document.getElementById("translation") as HTMLElement
    val categoryElement = document.getElementById("category") as HTMLElement
    val currentElement = document.getElementById("current") as HTMLElement
    val totalElement = document.getElementById("total") as HTMLElement
    val prevButton = document.getElementById("prev") as HTMLButtonElement
    val nextButton = document.getElementById("next") as HTMLButtonElement
    val letterDisplay = document.getElementById("letter-display") as HTMLElement
    val modeLettersBtn = document.getElementById("mode-letters") as HTMLButtonElement
    val modeNikudBtn = document.getElementById("mode-nikud") as HTMLButtonElement
    val modeWordsBtn = document.getElementById("mode-words") as HTMLButtonElement
    val modeQuizBtn = document.getElementById("mode-quiz") as HTMLButtonElement
    val learnContainer = document.getElementById("learn-container") as HTMLElement
    val quizContainer = document.getElementById("quiz-container") as HTMLElement
    val quizTypeContainer = document.getElementById("quiz-type-container") as HTMLElement
    val quizQuestionElement = document.getElementById("quiz-question") as HTMLElement
    val quizHintElement = document.getElementById("quiz-hint") as HTMLElement
    val quizOptionsElement = document.getElementById("quiz-options") as HTMLElement
    val quizScoreElement = document.getElementById("quiz-score") as HTMLElement
    val quizNextButton = document.getElementById("quiz-next") as HTMLButtonElement
    val quizLetterNameBtn = document.getElementById("quiz-letter-name") as HTMLButtonElement
    val quizNameLetterBtn = document.getElementById("quiz-name-letter") as HTMLButtonElement

    fun playAudio(fileName: String) {
        if (fileName.isNotEmpty()) {
            val audioPath = "audio/${fileName}"
            @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
            val audio = window.asDynamic().Audio(audioPath) as HTMLAudioElement
            audio.play()
        }
    }

    fun speakFallback(text: String) {
        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
        val synth = window.asDynamic().speechSynthesis
        if (synth != undefined) {
            synth.cancel()
            val utterance = window.asDynamic().SpeechSynthesisUtterance(text)
            utterance.lang = "he-IL"
            synth.speak(utterance)
        }
    }

    fun speakCurrent() {
        when (currentMode) {
            DisplayMode.NIKUD -> {
                val entry = useCase.getNikudEntry(currentIndex)
                entry?.let {
                    if (it.audioFile.isNotEmpty()) {
                        playAudio(it.audioFile)
                    } else {
                        speakFallback(it.audioText)
                    }
                }
            }
            DisplayMode.WORDS -> {
                val word = useCase.getWord(currentIndex)
                word?.let {
                    if (it.audioFile.isNotEmpty()) {
                        playAudio(it.audioFile)
                    } else {
                        speakFallback(it.text)
                    }
                }
            }
            else -> {
                val letter = useCase.getLetter(currentIndex)
                letter?.let {
                    if (it.audioFile.isNotEmpty()) {
                        playAudio(it.audioFile)
                    } else {
                        speakFallback(it.name)
                    }
                }
            }
        }
    }

    fun getNextIndex(): Int = when (currentMode) {
        DisplayMode.NIKUD -> useCase.getNextNikud(currentIndex)
        DisplayMode.WORDS -> useCase.getNextWord(currentIndex)
        else -> useCase.getNextLetter(currentIndex)
    }

    fun getPreviousIndex(): Int = when (currentMode) {
        DisplayMode.NIKUD -> useCase.getPreviousNikud(currentIndex)
        DisplayMode.WORDS -> useCase.getPreviousWord(currentIndex)
        else -> useCase.getPreviousLetter(currentIndex)
    }

    fun updateDisplay() {
        when (currentMode) {
            DisplayMode.NIKUD -> {
                val entry = useCase.getNikudEntry(currentIndex)
                entry?.let {
                    letterElement.textContent = it.combined
                    nameElement.textContent = "${it.letter.name} + ${it.nikud.name}"
                    nikudNameElement.textContent = it.nikud.name
                    transliterationElement.textContent = "${it.letter.transliteration} (${it.nikud.pronunciation})"
                    translationElement.textContent = ""
                    categoryElement.textContent = ""
                    currentElement.textContent = (currentIndex + 1).toString()
                    totalElement.textContent = useCase.getNikudCount().toString()
                }
            }
            DisplayMode.WORDS -> {
                val word = useCase.getWord(currentIndex)
                word?.let {
                    letterElement.textContent = it.text
                    nameElement.textContent = it.name
                    nikudNameElement.textContent = ""
                    transliterationElement.textContent = ""
                    translationElement.textContent = it.translation
                    categoryElement.textContent = it.category
                    currentElement.textContent = (currentIndex + 1).toString()
                    totalElement.textContent = useCase.getWordCount().toString()
                }
            }
            else -> {
                val letter = useCase.getLetter(currentIndex)
                letter?.let {
                    letterElement.textContent = it.character
                    nameElement.textContent = it.name
                    nikudNameElement.textContent = ""
                    transliterationElement.textContent = it.transliteration
                    translationElement.textContent = ""
                    categoryElement.textContent = ""
                    currentElement.textContent = (currentIndex + 1).toString()
                    totalElement.textContent = useCase.getLetterCount().toString()
                }
            }
        }
    }

    fun showQuizQuestion() {
        if (currentIndex >= quizQuestions.size) {
            quizQuestionElement.textContent = "Done!"
            quizHintElement.textContent = "Score: $quizScore / ${quizQuestions.size}"
            quizOptionsElement.innerHTML = ""
            quizNextButton.textContent = "Play Again"
            return
        }

        val question = quizQuestions[currentIndex]
        quizScoreElement.textContent = "Question ${currentIndex + 1} / ${quizQuestions.size}"
        quizQuestionElement.textContent = question.questionText
        quizHintElement.textContent = "Choose the correct answer"
        
        quizOptionsElement.innerHTML = ""
        quizAnswered = false
        
        for (option in question.options) {
            val btn = document.createElement("button")
            btn.textContent = option
            btn.className = "quiz-option"
            btn.addEventListener("click", { _: Event ->
                if (!quizAnswered) {
                    quizAnswered = true
                    if (option == question.correctAnswer) {
                        btn.classList.add("correct")
                        quizScore++
                    } else {
                        btn.classList.add("wrong")
                        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
                        val children = quizOptionsElement.asDynamic().children
                        for (i in 0 until children.length) {
                            val child = children[i] as HTMLElement
                            if (child.textContent == question.correctAnswer) {
                                child.classList.add("correct")
                            }
                        }
                    }
                    quizNextButton.textContent = "Next"
                }
            })
            quizOptionsElement.appendChild(btn)
        }
    }

    fun startQuiz(type: QuizType) {
        selectedQuizType = type
        quizQuestions = useCase.startQuiz(type)
        currentIndex = 0
        quizScore = 0
        quizAnswered = false
        showQuizQuestion()
    }

    letterDisplay.addEventListener("click", { _: Event ->
        speakCurrent()
    })

    prevButton.addEventListener("click", { _: Event ->
        currentIndex = getPreviousIndex()
        updateDisplay()
    })

    nextButton.addEventListener("click", { _: Event ->
        currentIndex = getNextIndex()
        updateDisplay()
    })

    quizNextButton.addEventListener("click", { _: Event ->
        if (quizNextButton.textContent == "Play Again") {
            startQuiz(selectedQuizType)
        } else {
            currentIndex++
            showQuizQuestion()
        }
    })

    quizLetterNameBtn.addEventListener("click", { _: Event ->
        quizLetterNameBtn.classList.add("active")
        quizNameLetterBtn.classList.remove("active")
        startQuiz(QuizType.LETTER_TO_NAME)
    })

    quizNameLetterBtn.addEventListener("click", { _: Event ->
        quizNameLetterBtn.classList.add("active")
        quizLetterNameBtn.classList.remove("active")
        startQuiz(QuizType.NAME_TO_LETTER)
    })

    fun setMode(mode: DisplayMode) {
        currentMode = mode
        currentIndex = 0
        modeLettersBtn.classList.remove("active")
        modeNikudBtn.classList.remove("active")
        modeWordsBtn.classList.remove("active")
        modeQuizBtn.classList.remove("active")
        
        when (mode) {
            DisplayMode.LETTERS -> {
                modeLettersBtn.classList.add("active")
                learnContainer.classList.remove("hidden")
                quizContainer.classList.remove("active")
                quizTypeContainer.style.display = "none"
                updateDisplay()
            }
            DisplayMode.NIKUD -> {
                modeNikudBtn.classList.add("active")
                learnContainer.classList.remove("hidden")
                quizContainer.classList.remove("active")
                quizTypeContainer.style.display = "none"
                updateDisplay()
            }
            DisplayMode.WORDS -> {
                modeWordsBtn.classList.add("active")
                learnContainer.classList.remove("hidden")
                quizContainer.classList.remove("active")
                quizTypeContainer.style.display = "none"
                updateDisplay()
            }
            DisplayMode.QUIZ -> {
                modeQuizBtn.classList.add("active")
                learnContainer.classList.add("hidden")
                quizContainer.classList.add("active")
                quizTypeContainer.style.display = "flex"
                startQuiz(QuizType.LETTER_TO_NAME)
            }
        }
    }

    modeLettersBtn.addEventListener("click", { _: Event ->
        setMode(DisplayMode.LETTERS)
    })

    modeNikudBtn.addEventListener("click", { _: Event ->
        setMode(DisplayMode.NIKUD)
    })

    modeWordsBtn.addEventListener("click", { _: Event ->
        setMode(DisplayMode.WORDS)
    })

    modeQuizBtn.addEventListener("click", { _: Event ->
        setMode(DisplayMode.QUIZ)
    })

    window.addEventListener("keydown", { event: Event ->
        if (currentMode == DisplayMode.QUIZ) return@addEventListener
        
        val keyEvent = event as KeyboardEvent
        when (keyEvent.key) {
            "ArrowLeft" -> {
                currentIndex = getNextIndex()
                updateDisplay()
            }
            "ArrowRight" -> {
                currentIndex = getPreviousIndex()
                updateDisplay()
            }
            " " -> {
                speakCurrent()
            }
        }
    })

    updateDisplay()
}
