package klein.shmulik.oti

import klein.shmulik.oti.domain.LetterUseCase
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

fun main() {
    val useCase = LetterUseCase()
    var currentIndex = 0

    val letterElement = document.getElementById("letter") as HTMLElement
    val nameElement = document.getElementById("name") as HTMLElement
    val transliterationElement = document.getElementById("transliteration") as HTMLElement
    val currentElement = document.getElementById("current") as HTMLElement
    val totalElement = document.getElementById("total") as HTMLElement
    val prevButton = document.getElementById("prev") as HTMLButtonElement
    val nextButton = document.getElementById("next") as HTMLButtonElement
    val letterDisplay = document.getElementById("letter-display") as HTMLElement

    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    val synth = window.asDynamic().speechSynthesis

    fun speak(text: String) {
        if (synth != undefined) {
            synth.cancel()
            val utterance = js("new SpeechSynthesisUtterance(text)")
            utterance.lang = "he-IL"
            synth.speak(utterance)
        }
    }

    fun updateDisplay() {
        val letter = useCase.getLetter(currentIndex)
        letter?.let {
            letterElement.textContent = it.character
            nameElement.textContent = it.name
            transliterationElement.textContent = it.transliteration
            currentElement.textContent = (currentIndex + 1).toString()
            totalElement.textContent = useCase.getTotalCount().toString()
        }
    }

    letterDisplay.addEventListener("click", { _: Event ->
        val letter = useCase.getLetter(currentIndex)
        letter?.let { speak(it.name) }
    })

    prevButton.addEventListener("click", { _: Event ->
        currentIndex = useCase.getPreviousLetter(currentIndex)
        updateDisplay()
    })

    nextButton.addEventListener("click", { _: Event ->
        currentIndex = useCase.getNextLetter(currentIndex)
        updateDisplay()
    })

    window.addEventListener("keydown", { event: Event ->
        val keyEvent = event as KeyboardEvent
        when (keyEvent.key) {
            "ArrowLeft" -> {
                currentIndex = useCase.getNextLetter(currentIndex)
                updateDisplay()
            }
            "ArrowRight" -> {
                currentIndex = useCase.getPreviousLetter(currentIndex)
                updateDisplay()
            }
            " " -> {
                val letter = useCase.getLetter(currentIndex)
                letter?.let { speak(it.name) }
            }
        }
    })

    updateDisplay()
}
