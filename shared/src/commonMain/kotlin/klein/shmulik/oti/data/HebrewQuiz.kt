package klein.shmulik.oti.data

data class QuizQuestion(
    val questionText: String,
    val correctAnswer: String,
    val options: List<String>,
    val type: QuizType
)

enum class QuizType {
    LETTER_TO_NAME,
    NAME_TO_LETTER,
    LETTER_TO_SOUND
}

object HebrewQuiz {
    fun generateLetterToNameQuiz(): List<QuizQuestion> {
        val letters = HebrewAlphabet.letters.shuffled()
        return letters.take(10).map { letter ->
            val wrongOptions = HebrewAlphabet.letters
                .filter { it.order != letter.order }
                .shuffled()
                .take(3)
                .map { it.name }
            val options = (wrongOptions + letter.name).shuffled()
            QuizQuestion(
                questionText = letter.character,
                correctAnswer = letter.name,
                options = options,
                type = QuizType.LETTER_TO_NAME
            )
        }
    }

    fun generateNameToLetterQuiz(): List<QuizQuestion> {
        val letters = HebrewAlphabet.letters.shuffled()
        return letters.take(10).map { letter ->
            val wrongOptions = HebrewAlphabet.letters
                .filter { it.order != letter.order }
                .shuffled()
                .take(3)
                .map { it.character }
            val options = (wrongOptions + letter.character).shuffled()
            QuizQuestion(
                questionText = letter.name,
                correctAnswer = letter.character,
                options = options,
                type = QuizType.NAME_TO_LETTER
            )
        }
    }
}
