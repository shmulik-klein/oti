package klein.shmulik.oti.domain

import klein.shmulik.oti.data.HebrewAlphabet
import klein.shmulik.oti.data.HebrewLetter

class LetterUseCase {
    fun getAllLetters(): List<HebrewLetter> = HebrewAlphabet.letters

    fun getLetter(index: Int): HebrewLetter? {
        val letters = HebrewAlphabet.letters
        return if (index in letters.indices) letters[index] else null
    }

    fun getNextLetter(currentIndex: Int): Int {
        val letters = HebrewAlphabet.letters
        return if (currentIndex < letters.size - 1) currentIndex + 1 else 0
    }

    fun getPreviousLetter(currentIndex: Int): Int {
        val letters = HebrewAlphabet.letters
        return if (currentIndex > 0) currentIndex - 1 else letters.size - 1
    }

    fun getTotalCount(): Int = HebrewAlphabet.letters.size
}