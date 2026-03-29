package klein.shmulik.oti.domain

import klein.shmulik.oti.data.HebrewAlphabet
import klein.shmulik.oti.data.HebrewLetter
import klein.shmulik.oti.data.HebrewQuiz
import klein.shmulik.oti.data.HebrewStories
import klein.shmulik.oti.data.HebrewWord
import klein.shmulik.oti.data.HebrewWords
import klein.shmulik.oti.data.NikudEntry
import klein.shmulik.oti.data.QuizQuestion
import klein.shmulik.oti.data.QuizType
import klein.shmulik.oti.data.Story

class LetterUseCase {
    private val nikudEntries = HebrewAlphabet.generateNikudEntries()
    private var letterToNameQuiz: List<QuizQuestion> = emptyList()
    private var nameToLetterQuiz: List<QuizQuestion> = emptyList()
    private var currentQuizType: QuizType = QuizType.LETTER_TO_NAME
    
    fun getAllLetters(): List<HebrewLetter> = HebrewAlphabet.letters
    fun getNikudEntries(): List<NikudEntry> = nikudEntries
    fun getAllWords(): List<HebrewWord> = HebrewWords.words
    fun getAllStories(): List<Story> = HebrewStories.stories

    fun getLetter(index: Int): HebrewLetter? {
        val letters = HebrewAlphabet.letters
        return if (index in letters.indices) letters[index] else null
    }

    fun getNikudEntry(index: Int): NikudEntry? {
        return if (index in nikudEntries.indices) nikudEntries[index] else null
    }

    fun getWord(index: Int): HebrewWord? {
        val words = HebrewWords.words
        return if (index in words.indices) words[index] else null
    }

    fun getStory(index: Int): Story? {
        val stories = HebrewStories.stories
        return if (index in stories.indices) stories[index] else null
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

    fun getNextWord(currentIndex: Int): Int {
        val words = HebrewWords.words
        return if (currentIndex < words.size - 1) currentIndex + 1 else 0
    }

    fun getPreviousWord(currentIndex: Int): Int {
        val words = HebrewWords.words
        return if (currentIndex > 0) currentIndex - 1 else words.size - 1
    }

    fun getNextStory(currentIndex: Int): Int {
        val stories = HebrewStories.stories
        return if (currentIndex < stories.size - 1) currentIndex + 1 else 0
    }

    fun getPreviousStory(currentIndex: Int): Int {
        val stories = HebrewStories.stories
        return if (currentIndex > 0) currentIndex - 1 else stories.size - 1
    }

    fun getLetterCount(): Int = HebrewAlphabet.letters.size
    fun getNikudCount(): Int = nikudEntries.size
    fun getWordCount(): Int = HebrewWords.words.size
    fun getStoryCount(): Int = HebrewStories.stories.size

    fun startQuiz(type: QuizType): List<QuizQuestion> {
        currentQuizType = type
        letterToNameQuiz = HebrewQuiz.generateLetterToNameQuiz()
        nameToLetterQuiz = HebrewQuiz.generateNameToLetterQuiz()
        return when (type) {
            QuizType.LETTER_TO_NAME -> letterToNameQuiz
            QuizType.NAME_TO_LETTER -> nameToLetterQuiz
            QuizType.LETTER_TO_SOUND -> letterToNameQuiz
        }
    }

    fun getCurrentQuiz(): List<QuizQuestion> = when (currentQuizType) {
        QuizType.LETTER_TO_NAME -> letterToNameQuiz
        QuizType.NAME_TO_LETTER -> nameToLetterQuiz
        QuizType.LETTER_TO_SOUND -> letterToNameQuiz
    }

    fun getCurrentQuizType(): QuizType = currentQuizType
}
