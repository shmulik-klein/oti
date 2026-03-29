package klein.shmulik.oti.data

data class Story(
    val id: Int,
    val title: String,
    val pages: List<StoryPage>
)

data class StoryPage(
    val hebrew: String,
    val translation: String,
    val imageUrl: String = ""
)

object HebrewStories {
    val stories = listOf(
        Story(
            id = 1,
            title = "הַיָּם הַגָּדוֹל",
            pages = listOf(
                StoryPage(
                    "יֵשׁ יָם גָּדוֹל.",
                    "There is a big sea."
                ),
                StoryPage(
                    "בַּיָּם יֵשׁ דָּגִים רַבִּים.",
                    "In the sea there are many fish."
                ),
                StoryPage(
                    "הַדָּג שָׁחֶה בַּמַּיִם.",
                    "The fish swims in the water."
                ),
                StoryPage(
                    "הַחֲתוּל שָׁם עַל הַסֶּלַע.",
                    "The cat sits on the rock."
                ),
                StoryPage(
                    "שָׁלוֹם לַיָּם!",
                    "Peace to the sea!"
                )
            )
        ),
        Story(
            id = 2,
            title = "הַבַּיִת שֶׁלִּי",
            pages = listOf(
                StoryPage(
                    "זֶה הַבַּיִת שֶׁלִּי.",
                    "This is my house."
                ),
                StoryPage(
                    "בַּבַּיִת יֵשׁ דֶּלֶת וְחַלּוֹן.",
                    "In the house there is a door and a window."
                ),
                StoryPage(
                    "אָבָא בַּבָּיִת.",
                    "Father is in the house."
                ),
                StoryPage(
                    "אֵם בַּבָּיִת.",
                    "Mother is in the house."
                ),
                StoryPage(
                    "אֲנִי אוֹהֵב אֶת הַבַּיִת.",
                    "I love the house."
                )
            )
        ),
        Story(
            id = 3,
            title = "בַּגָּן",
            pages = listOf(
                StoryPage(
                    "אֲנִי בַּגָּן.",
                    "I am in the garden."
                ),
                StoryPage(
                    "בַּגָּן יֵשׁ עֵץ גָּדוֹל.",
                    "In the garden there is a big tree."
                ),
                StoryPage(
                    "עַל הָעֵץ יֵשׁ צִפּוֹר.",
                    "On the tree there is a bird."
                ),
                StoryPage(
                    "הַצִפּוֹר שָׁרָה שִׁיר.",
                    "The bird sings a song."
                ),
                StoryPage(
                    "הַפֶּרַח יָפֶה.",
                    "The flower is beautiful."
                )
            )
        ),
        Story(
            id = 4,
            title = "הַכֶּלֶב וְהַחֲתוּל",
            pages = listOf(
                StoryPage(
                    "יֵשׁ לִי כֶּלֶב קָטָן.",
                    "I have a small dog."
                ),
                StoryPage(
                    "שֵׁם הַכֶּלֶב רֵי.",
                    "The dog's name is Roy."
                ),
                StoryPage(
                    "הַכֶּלֶב אוֹהֵב לָרוּץ.",
                    "The dog loves to run."
                ),
                StoryPage(
                    "גַּם הַחֲתוּל שָׁם.",
                    "The cat is there too."
                ),
                StoryPage(
                    "הֵם חֲבֵרִים טוֹבִים.",
                    "They are good friends."
                )
            )
        ),
        Story(
            id = 5,
            title = "יוֹם טוֹב",
            pages = listOf(
                StoryPage(
                    "בּוֹקֶר טוֹב!",
                    "Good morning!"
                ),
                StoryPage(
                    "הַשֶּׁמֶשׁ זוֹרַחַת.",
                    "The sun is rising."
                ),
                StoryPage(
                    "אֲנִי אוֹכֵל לֶחֶם.",
                    "I eat bread."
                ),
                StoryPage(
                    "אֲנִי שׁוֹתֶה מַיִם.",
                    "I drink water."
                ),
                StoryPage(
                    "יוֹם טוֹב!",
                    "Good day!"
                )
            )
        )
    )
}
