package klein.shmulik.oti

import klein.shmulik.oti.domain.LetterUseCase
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLAudioElement
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

fun main() {
    val useCase = LetterUseCase()
    var currentIndex = 0
    var isNikudMode = false

    val letterElement = document.getElementById("letter") as HTMLElement
    val nameElement = document.getElementById("name") as HTMLElement
    val nikudNameElement = document.getElementById("nikud-name") as HTMLElement
    val transliterationElement = document.getElementById("transliteration") as HTMLElement
    val currentElement = document.getElementById("current") as HTMLElement
    val totalElement = document.getElementById("total") as HTMLElement
    val prevButton = document.getElementById("prev") as HTMLButtonElement
    val nextButton = document.getElementById("next") as HTMLButtonElement
    val letterDisplay = document.getElementById("letter-display") as HTMLElement
    val modeLettersBtn = document.getElementById("mode-letters") as HTMLButtonElement
    val modeNikudBtn = document.getElementById("mode-nikud") as HTMLButtonElement

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
        if (isNikudMode) {
            val entry = useCase.getNikudEntry(currentIndex)
            entry?.let {
                if (it.audioFile.isNotEmpty()) {
                    playAudio(it.audioFile)
                } else {
                    speakFallback(it.audioText)
                }
            }
        } else {
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

    fun updateDisplay() {
        if (isNikudMode) {
            val entry = useCase.getNikudEntry(currentIndex)
            entry?.let {
                letterElement.textContent = it.combined
                nameElement.textContent = "${it.letter.name} + ${it.nikud.name}"
                nikudNameElement.textContent = it.nikud.name
                transliterationElement.textContent = "${it.letter.transliteration} (${it.nikud.pronunciation})"
                currentElement.textContent = (currentIndex + 1).toString()
                totalElement.textContent = useCase.getNikudCount().toString()
            }
        } else {
            val letter = useCase.getLetter(currentIndex)
            letter?.let {
                letterElement.textContent = it.character
                nameElement.textContent = it.name
                nikudNameElement.textContent = ""
                transliterationElement.textContent = it.transliteration
                currentElement.textContent = (currentIndex + 1).toString()
                totalElement.textContent = useCase.getLetterCount().toString()
            }
        }
    }

    letterDisplay.addEventListener("click", { _: Event ->
        speakCurrent()
    })

    prevButton.addEventListener("click", { _: Event ->
        currentIndex = if (isNikudMode) useCase.getPreviousNikud(currentIndex) else useCase.getPreviousLetter(currentIndex)
        updateDisplay()
    })

    nextButton.addEventListener("click", { _: Event ->
        currentIndex = if (isNikudMode) useCase.getNextNikud(currentIndex) else useCase.getNextLetter(currentIndex)
        updateDisplay()
    })

    modeLettersBtn.addEventListener("click", { _: Event ->
        isNikudMode = false
        currentIndex = 0
        modeLettersBtn.classList.add("active")
        modeNikudBtn.classList.remove("active")
        updateDisplay()
    })

    modeNikudBtn.addEventListener("click", { _: Event ->
        isNikudMode = true
        currentIndex = 0
        modeNikudBtn.classList.add("active")
        modeLettersBtn.classList.remove("active")
        updateDisplay()
    })

    window.addEventListener("keydown", { event: Event ->
        val keyEvent = event as KeyboardEvent
        when (keyEvent.key) {
            "ArrowLeft" -> {
                currentIndex = if (isNikudMode) useCase.getNextNikud(currentIndex) else useCase.getNextLetter(currentIndex)
                updateDisplay()
            }
            "ArrowRight" -> {
                currentIndex = if (isNikudMode) useCase.getPreviousNikud(currentIndex) else useCase.getPreviousLetter(currentIndex)
                updateDisplay()
            }
            " " -> {
                speakCurrent()
            }
        }
    })

    updateDisplay()
}
