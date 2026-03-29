package klein.shmulik.oti.data

data class HebrewLetter(
    val character: String,
    val name: String,
    val transliteration: String,
    val order: Int
)

data class NikudMark(
    val symbol: String,
    val name: String,
    val pronunciation: String
)

data class NikudEntry(
    val letter: HebrewLetter,
    val nikud: NikudMark,
    val combined: String,
    val audioText: String
)

object HebrewAlphabet {
    val letters = listOf(
        HebrewLetter("א", "אלף", "alef", 1),
        HebrewLetter("ב", "בית", "bet", 2),
        HebrewLetter("ג", "גימל", "gimel", 3),
        HebrewLetter("ד", "דלת", "dalet", 4),
        HebrewLetter("ה", "הא", "he", 5),
        HebrewLetter("ו", "וו", "vav", 6),
        HebrewLetter("ז", "זין", "zayin", 7),
        HebrewLetter("ח", "חית", "het", 8),
        HebrewLetter("ט", "טית", "tet", 9),
        HebrewLetter("י", "יוד", "yod", 10),
        HebrewLetter("כ", "כף", "kaf", 11),
        HebrewLetter("ל", "למד", "lamed", 12),
        HebrewLetter("מ", "מם", "mem", 13),
        HebrewLetter("נ", "נון", "nun", 14),
        HebrewLetter("ס", "סמך", "samech", 15),
        HebrewLetter("ע", "עיין", "ayin", 16),
        HebrewLetter("פ", "פא", "pe", 17),
        HebrewLetter("צ", "צדי", "tsade", 18),
        HebrewLetter("ק", "קוף", "kof", 19),
        HebrewLetter("ר", "ריש", "resh", 20),
        HebrewLetter("ש", "שין", "shin", 21),
        HebrewLetter("ת", "תאו", "tav", 22)
    )

    val nikudMarks = listOf(
        NikudMark("ַ", "פַּתַח", "a"),
        NikudMark("ָ", "קָמַץ", "a"),
        NikudMark("ֶ", "סֶגוֹל", "e"),
        NikudMark("ִ", "חִירֵק", "i"),
        NikudMark("ֵ", "צֵירֵי", "e"),
        NikudMark("ְ", "שְׁוָא", "e"),
        NikudMark("וּ", "שׁוּרוּק", "u"),
        NikudMark("וֹ", "חוֹלָם", "o")
    )

    fun generateNikudEntries(): List<NikudEntry> {
        val entries = mutableListOf<NikudEntry>()
        val vowels = listOf("ַ", "ָ", "ֶ", "ִ", "ֵ", "וּ", "וֹ")
        val vowelSounds = mapOf(
            "ַ" to "אַ",
            "ָ" to "אָ",
            "ֶ" to "אֶ",
            "ִ" to "אִ",
            "ֵ" to "אֵ",
            "וּ" to "אוּ",
            "וֹ" to "אוֹ"
        )
        
        for (letter in letters) {
            for (nikud in nikudMarks) {
                val combined = letter.character + nikud.symbol
                val audioText = vowelSounds[nikud.symbol] ?: (letter.character + nikud.pronunciation)
                entries.add(NikudEntry(letter, nikud, combined, audioText))
            }
        }
        return entries
    }
}
