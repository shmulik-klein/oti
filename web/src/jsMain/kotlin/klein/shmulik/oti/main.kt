package klein.shmulik.oti

import klein.shmulik.oti.data.QuizQuestion
import klein.shmulik.oti.data.QuizType
import klein.shmulik.oti.data.Story
import klein.shmulik.oti.domain.LetterUseCase
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLAudioElement
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

enum class DisplayMode { LETTERS, NIKUD, WORDS, STORIES, QUIZ }

fun main() {
    val useCase = LetterUseCase()
    val storyLoader = StoryLoader()
    var currentIndex = 0
    var currentMode = DisplayMode.LETTERS
    var quizQuestions: List<QuizQuestion> = emptyList()
    var quizScore = 0
    var quizAnswered = false
    var selectedQuizType = QuizType.LETTER_TO_NAME
    var currentStoryIndex = 0
    var storiesLoaded = false
    var currentStory: Story? = null

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
    val modeStoriesBtn = document.getElementById("mode-stories") as HTMLButtonElement
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
    val storiesContainer = document.getElementById("stories-container") as HTMLElement
    val storiesListElement = document.getElementById("stories-list") as HTMLElement
    val storyReaderElement = document.getElementById("story-reader") as HTMLElement
    val storyFullTextElement = document.getElementById("story-full-text") as HTMLElement
    val storyFullTranslationElement = document.getElementById("story-full-translation") as HTMLElement
    val backToStoriesBtn = document.getElementById("back-to-stories") as HTMLButtonElement

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
            DisplayMode.STORIES -> {
                currentStory?.let {
                    val allText = it.pages.joinToString(" ") { page -> page.hebrew }
                    speakFallback(allText)
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

    fun showStoryReader() {
        storiesListElement.style.display = "none"
        storyReaderElement.style.display = "block"
        
        storyLoader.loadStory(currentStoryIndex, { story ->
            story?.let {
                currentStory = it
                val allHebrew = it.pages.joinToString(" ") { page -> page.hebrew }
                val allTranslation = it.pages.joinToString("\n") { page -> page.translation }
                storyFullTextElement.textContent = allHebrew
                storyFullTranslationElement.textContent = allTranslation
            }
        }, { error ->
            console.error(error)
        })
    }

    fun showStoriesList() {
        storyReaderElement.style.display = "none"
        storiesListElement.style.display = "block"
        storiesListElement.innerHTML = ""
        
        if (!storiesLoaded) {
            storiesListElement.innerHTML = "<div class='story-preview'>Loading stories...</div>"
            storyLoader.loadStoriesIndex({
                storiesLoaded = true
                showStoriesList()
            }, { error ->
                storiesListElement.innerHTML = "<div class='story-preview'>Error loading stories: $error</div>"
            })
            return
        }

        for (index in 0 until storyLoader.getStoryCount()) {
            val storyInfo = storyLoader.getStoryInfo(index)
            storyInfo?.let { info ->
                val card = document.createElement("div")
                card.className = "story-card"
                card.innerHTML = """
                    <div class="story-title">${info.title}</div>
                    <div class="story-preview">Click to load story</div>
                """
                card.addEventListener("click", { _: Event ->
                    currentStoryIndex = index
                    showStoryReader()
                })
                storiesListElement.appendChild(card)
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

    backToStoriesBtn.addEventListener("click", { _: Event ->
        currentStory = null
        showStoriesList()
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
        modeStoriesBtn.classList.remove("active")
        modeQuizBtn.classList.remove("active")
        
        learnContainer.classList.remove("hidden")
        quizContainer.classList.remove("active")
        storiesContainer.classList.remove("active")
        quizTypeContainer.style.display = "none"
        
        when (mode) {
            DisplayMode.LETTERS -> {
                modeLettersBtn.classList.add("active")
                learnContainer.classList.remove("hidden")
                updateDisplay()
            }
            DisplayMode.NIKUD -> {
                modeNikudBtn.classList.add("active")
                learnContainer.classList.remove("hidden")
                updateDisplay()
            }
            DisplayMode.WORDS -> {
                modeWordsBtn.classList.add("active")
                learnContainer.classList.remove("hidden")
                updateDisplay()
            }
            DisplayMode.STORIES -> {
                modeStoriesBtn.classList.add("active")
                storiesContainer.classList.add("active")
                learnContainer.classList.add("hidden")
                showStoriesList()
            }
            DisplayMode.QUIZ -> {
                modeQuizBtn.classList.add("active")
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

    modeStoriesBtn.addEventListener("click", { _: Event ->
        setMode(DisplayMode.STORIES)
    })

    modeQuizBtn.addEventListener("click", { _: Event ->
        setMode(DisplayMode.QUIZ)
    })

    window.addEventListener("keydown", { event: Event ->
        if (currentMode == DisplayMode.QUIZ || currentMode == DisplayMode.STORIES) return@addEventListener
        
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