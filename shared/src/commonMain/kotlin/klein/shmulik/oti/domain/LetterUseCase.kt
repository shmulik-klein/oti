package klein.shmulik.oti.domain

import klein.shmulik.oti.data.HebrewAlphabet
import klein.shmulik.oti.data.HebrewLetter
import klein.shmulik.oti.data.NikudEntry

class LetterUseCase {
    private val nikudEntries = HebrewAlphabet.generateNikudEntries()
    
    fun getAllLetters(): List<HebrewLetter> = HebrewAlphabet.letters
    
    fun getNikudEntries(): List<NikudEntry> = nikudEntries

    fun getLetter(index: Int): HebrewLetter? {
        val letters = HebrewAlphabet.letters
        return if (index in letters.indices) letters[index] else null
    }

    fun getNikudEntry(index: Int): NikudEntry? {
        return if (index in nikudEntries.indices) nikudEntries[index] else null
    }

    fun getNextLetter(currentIndex: Int): Int {
        val letters = HebrewAlphabet.letters
        return if (currentIndex < letters.size - 1) currentIndex + 1 else 0
    }

    fun getPreviousLetter(currentIndex: Int): Int {
        val letters = HebrewAlphabet.letters
        return if (currentIndex > 0) currentIndex - 1 else letters.size - 1
    }

    fun getNextNikud(currentIndex: Int): Int {
        return if (currentIndex < nikudEntries.size - 1) currentIndex + 1 else 0
    }

    fun getPreviousNikud(currentIndex: Int): Int {
        return if (currentIndex > 0) currentIndex - 1 else nikudEntries.size - 1
    }

    fun getLetterCount(): Int = HebrewAlphabet.letters.size
    
    fun getNikudCount(): Int = nikudEntries.size
}
