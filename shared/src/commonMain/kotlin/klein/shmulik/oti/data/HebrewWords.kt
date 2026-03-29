package klein.shmulik.oti.data

data class HebrewWord(
    val text: String,
    val name: String,
    val translation: String,
    val category: String,
    val audioFile: String = ""
)

object HebrewWords {
    val words = listOf(
        HebrewWord("אָב", "אב", "father", "family"),
        HebrewWord("אֵם", "אם", "mother", "family"),
        HebrewWord("בֵּן", "בן", "son", "family"),
        HebrewWord("בַּת", "בת", "daughter", "family"),
        HebrewWord("אָח", "אח", "brother", "family"),
        HebrewWord("אָחוֹת", "אחות", "sister", "family"),
        HebrewWord("שָׁלוֹם", "שלום", "peace/hello", "greeting"),
        HebrewWord("תּוֹדָה", "תודה", "thank you", "greeting"),
        HebrewWord("כֵּן", "כן", "yes", "basic"),
        HebrewWord("לֹא", "לא", "no", "basic"),
        HebrewWord("מַיִם", "מים", "water", "nature"),
        HebrewWord("אָוֶשׁ", "אוויר", "air", "nature"),
        HebrewWord("אֵשׁ", "אש", "fire", "nature"),
        HebrewWord("אָרֶץ", "ארץ", "earth", "nature"),
        HebrewWord("שָׁמַיִם", "שמים", "sky/heaven", "nature"),
        HebrewWord("יָם", "ים", "sea", "nature"),
        HebrewWord("כֶּלֶב", "כלב", "dog", "animal"),
        HebrewWord("חָתוּל", "חתול", "cat", "animal"),
        HebrewWord("צִפּוֹר", "ציפור", "bird", "animal"),
        HebrewWord("דָּג", "דג", "fish", "animal"),
        HebrewWord("תַּפּוּחַ", "תפוח", "apple", "food"),
        HebrewWord("לֶחֶם", "לחם", "bread", "food"),
        HebrewWord("חָלָב", "חלב", "milk", "food"),
        HebrewWord("זַיִת", "זית", "olive", "food"),
        HebrewWord("יָרָק", "ירק", "vegetable", "food"),
        HebrewWord("שֶׁמֶשׁ", "שמש", "sun", "nature"),
        HebrewWord("יָרֵחַ", "ירח", "moon", "nature"),
        HebrewWord("כּוֹכָב", "כוכב", "star", "nature"),
        HebrewWord("עֵץ", "עץ", "tree", "nature"),
        HebrewWord("פֶּרַח", "פרח", "flower", "nature"),
        HebrewWord("בַּיִת", "בית", "house", "place"),
        HebrewWord("דֶּלֶת", "דלת", "door", "place"),
        HebrewWord("חַלוֹן", "חלון", "window", "place"),
        HebrewWord("שֻׁלְחָן", "שולחן", "table", "object"),
        HebrewWord("כִּסֵּא", "כיסא", "chair", "object"),
        HebrewWord("סֵפֶר", "ספר", "book", "object"),
        HebrewWord("עִתּוֹן", "עיתון", "newspaper", "object")
    )
}
