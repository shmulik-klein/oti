package klein.shmulik.oti.data

data class HebrewLetter(
    val character: String,
    val name: String,
    val transliteration: String,
    val order: Int,
    val audioFile: String = ""
)

data class NikudMark(
    val symbol: String,
    val name: String,
    val pronunciation: String,
    val audioFile: String = ""
)

data class NikudEntry(
    val letter: HebrewLetter,
    val nikud: NikudMark,
    val combined: String,
    val audioText: String,
    val audioFile: String = ""
)

object HebrewAlphabet {
    val letters = listOf(
        HebrewLetter("א", "אלף", "alef", 1, "alef.mp3"),
        HebrewLetter("ב", "בית", "bet", 2, "bet.mp3"),
        HebrewLetter("ג", "גימל", "gimel", 3, "gimel.mp3"),
        HebrewLetter("ד", "דלת", "dalet", 4, "dalet.mp3"),
        HebrewLetter("ה", "הא", "he", 5, "he.mp3"),
        HebrewLetter("ו", "וו", "vav", 6, "vav.mp3"),
        HebrewLetter("ז", "זין", "zayin", 7, "zayin.mp3"),
        HebrewLetter("ח", "חית", "het", 8, "het.mp3"),
        HebrewLetter("ט", "טית", "tet", 9, "tet.mp3"),
        HebrewLetter("י", "יוד", "yod", 10, "yod.mp3"),
        HebrewLetter("כ", "כף", "kaf", 11, "kaf.mp3"),
        HebrewLetter("ל", "למד", "lamed", 12, "lamed.mp3"),
        HebrewLetter("מ", "מם", "mem", 13, "mem.mp3"),
        HebrewLetter("נ", "נון", "nun", 14, "nun.mp3"),
        HebrewLetter("ס", "סמך", "samech", 15, "samech.mp3"),
        HebrewLetter("ע", "עיין", "ayin", 16, "ayin.mp3"),
        HebrewLetter("פ", "פא", "pe", 17, "pe.mp3"),
        HebrewLetter("צ", "צדי", "tsade", 18, "tsade.mp3"),
        HebrewLetter("ק", "קוף", "kof", 19, "kof.mp3"),
        HebrewLetter("ר", "ריש", "resh", 20, "resh.mp3"),
        HebrewLetter("ש", "שין", "shin", 21, "shin.mp3"),
        HebrewLetter("ת", "תאו", "tav", 22, "tav.mp3")
    )

    val nikudMarks = listOf(
        NikudMark("ַ", "פַּתַח", "a", "patach.mp3"),
        NikudMark("ָ", "קָמַץ", "a", "kamatz.mp3"),
        NikudMark("ֶ", "סֶגוֹל", "e", "segol.mp3"),
        NikudMark("ִ", "חִירֵק", "i", "hirek.mp3"),
        NikudMark("ֵ", "צֵירֵי", "e", "tsere.mp3"),
        NikudMark("ְ", "שְׁוָא", "e", "shva.mp3"),
        NikudMark("וּ", "שׁוּרוּק", "u", "shuruk.mp3"),
        NikudMark("וֹ", "חוֹלָם", "o", "holam.mp3")
    )

    fun generateNikudEntries(): List<NikudEntry> {
        val entries = mutableListOf<NikudEntry>()
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
                val audioFile = "${letter.transliteration}_${nikud.pronunciation}.mp3"
                entries.add(NikudEntry(letter, nikud, combined, audioText, audioFile))
            }
        }
        return entries
    }
}
