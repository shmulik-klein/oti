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
            title = "הנזל וגרטל",
            pages = listOf(
                StoryPage(
                    "יְלָדִים הָיוּ בַּכֹּפֶר.",
                    "There were children in the village."
                ),
                StoryPage(
                    "שֵׁם הַיָּלָד הָאָחִים הָיָה הַנְּזָל וְגִרְטֵל.",
                    "The children's names were Hansel and Gretel."
                ),
                StoryPage(
                    "אָבָא וְאֵם הָיוּ עֲנִיִּים.",
                    "Father and mother were poor."
                ),
                StoryPage(
                    "בַּיּוֹם הָרִאשׁוֹן הָלְכוּ הַיְּלָדִים לַיָּעַר.",
                    "On the first day, the children went to the forest."
                ),
                StoryPage(
                    "הֵם מָצְאוּ בַּיִת קָטָן מִסּוֹכֶר.",
                    "They found a small house made of candy."
                ),
                StoryPage(
                    "אִמָּה הָרְעָה גַּרָה בַּבַּיִת.",
                    "The evil stepmother lived in the house."
                ),
                StoryPage(
                    "הִיא לָקְחָה אֶת גִרְטֵל לַתּוֹךְ הַבַּיִת.",
                    "She took Gretel inside the house."
                ),
                StoryPage(
                    "הַנְּזָל בָּרַח מִן הַבַּיִת.",
                    "Hansel ran away from the house."
                ),
                StoryPage(
                    "הֵם מָצְאוּ אֶת הַדֶּרֶךְ הַבַּיְתָה.",
                    "They found the way home."
                ),
                StoryPage(
                    "הַמִּשְׁפָּחָה הָיְתָה שָׂמֵחָה.",
                    "The family was happy."
                )
            )
        ),
        Story(
            id = 2,
            title = "כובע אדום",
            pages = listOf(
                StoryPage(
                    "יַלְדָּה קְטַנָּה גָּרָה בַּכֹּפֶר.",
                    "A little girl lived in the village."
                ),
                StoryPage(
                    "סָבְתָא נָתְנָה לָהּ כּוֹבַע אָדוּם יָפֶה.",
                    "Grandmother gave her a beautiful red hat."
                ),
                StoryPage(
                    "יוֹם אֶחָד הַלְּכָה הַיַּלְדָּה לְבַקֵּר אֶת סָבְתָא.",
                    "One day, the girl went to visit grandmother."
                ),
                StoryPage(
                    "בַּדֶּרֶךְ פָּגְשָׁה זְאֵב גָּדוֹל.",
                    "On the way, she met a big wolf."
                ),
                StoryPage(
                    "\"לְאָן אַתְּ הוֹלֶכֶת?\" שָׁאַל הַזְּאֵב.",
                    "\"Where are you going?\" asked the wolf."
                ),
                StoryPage(
                    "\"אֲנִי הוֹלֶכֶת לְבַקֵּר אֶת סָבְתָא,\" עָנְתָה הַיַּלְדָּה.",
                    "\"I'm going to visit grandmother,\" answered the girl."
                ),
                StoryPage(
                    "הַזְּאֵב אָכַל אֶת הַסָּבָה.",
                    "The wolf ate the grandmother."
                ),
                StoryPage(
                    "צַיָּד בָּא לִתְרוֹץ אֶת הַזְּאֵב.",
                    "A hunter came to save the wolf."
                ),
                StoryPage(
                    "הַיַּלְדָּה נִצְלָה.",
                    "The girl was saved."
                ),
                StoryPage(
                    "מֵאָז הִיא תָּמִיד שָׁמְרָה עַל עַצְמָהּ.",
                    "Since then, she always took care of herself."
                )
            )
        ),
        Story(
            id = 3,
            title = "שלושה חזירונים",
            pages = listOf(
                StoryPage(
                    "שְׁלוֹשָׁה חֲזִירוֹנִים גָּרוּ בַּיָּעַר.",
                    "Three little pigs lived in the forest."
                ),
                StoryPage(
                    "הֵם הֶחֱלִיטוּ לִבְנוֹת בָּתִּים.",
                    "They decided to build houses."
                ),
                StoryPage(
                    "הָרִאשׁוֹן בָּנָה בַּיִת מִקַּשׁ.",
                    "The first built a house of straw."
                ),
                StoryPage(
                    "הַשֵּׁנִי בָּנָה בַּיִת מִקּוֹרוֹת עֵץ.",
                    "The second built a house of wood."
                ),
                StoryPage(
                    "הַשְּׁלִישִׁי בָּנָה בַּיִת מֵאֲבָנִים.",
                    "The third built a house of stones."
                ),
                StoryPage(
                    "זְאֵב גָּדוֹל בָּא לְהַפְחִיד אוֹתָם.",
                    "A big wolf came to scare them."
                ),
                StoryPage(
                    "הַזְּאֵב נָפַח עַל בֵּית הַקַּשׁ, וְהוּא נָפָל.",
                    "The wolf blew on the straw house, and it fell."
                ),
                StoryPage(
                    "הַזְּאֵב נָפַח עַל בֵּית הַקּוֹרוֹת, וְהוּא נָפָל.",
                    "The wolf blew on the wood house, and it fell."
                ),
                StoryPage(
                    "הַזְּאֵב לֹא יָכוֹל לְהַפִּיל אֶת בֵּית הָאֲבָנִים.",
                    "The wolf could not blow down the stone house."
                ),
                StoryPage(
                    "הַחֲזִירוֹנִים הָיוּ בְּטוּחִים בְּבֵיתָם.",
                    "The little pigs were safe in their house."
                )
            )
        ),
        Story(
            id = 4,
            title = "הרועה והגמדים",
            pages = listOf(
                StoryPage(
                    "רוֹעֶה צֹאן הָלַךְ בַּהָרִים.",
                    "A shepherd walked in the mountains."
                ),
                StoryPage(
                    "הוּא מָצָא מְעָרָה קְטַנָּה בַּסֶּלַע.",
                    "He found a small cave in the rock."
                ),
                StoryPage(
                    "בִּמְעָרָה גָּרוּ גַּמְדִים קְטַנִּים.",
                    "Little dwarfs lived in the cave."
                ),
                StoryPage(
                    "הַגַּמְדִים עָבְדוּ כָּל הַיּוֹם.",
                    "The dwarfs worked all day."
                ),
                StoryPage(
                    "הֵם זָהָב לְמַלֵךְ עָשׂוּ.",
                    "They made gold for the king."
                ),
                StoryPage(
                    "יוֹם אֶחָד הַמֶּלֶךְ גִּירֵשׁ אֶת הָרוֹעֶה.",
                    "One day, the king chased the shepherd away."
                ),
                StoryPage(
                    "הָרוֹעֶה חָזַר לִמְעָרָה.",
                    "The shepherd returned to the cave."
                ),
                StoryPage(
                    "הַגַּמְדִים נְתָנוּ לוֹ כֶּסֶף הַרְבֵּה.",
                    "The dwarfs gave him a lot of money."
                ),
                StoryPage(
                    "הָרוֹעֶה הָיָה עַתָּה עָשִׁיר.",
                    "The shepherd was now rich."
                ),
                StoryPage(
                    "הוּא חָיָה בְּאֹשֶׁר לְעוֹלָם.",
                    "He lived happily forever."
                )
            )
        ),
        Story(
            id = 5,
            title = "הדייג ואשתו",
            pages = listOf(
                StoryPage(
                    "דַּיָּג אֶחָד גָּר לְיַד הַיָּם.",
                    "A fisherman lived by the sea."
                ),
                StoryPage(
                    "אִשְׁתּוֹ הָיְתָה עֲנִיָּה מְאוֹד.",
                    "His wife was very poor."
                ),
                StoryPage(
                    "יוֹם אֶחָד תָּפַס דָּג קָסָם.",
                    "One day, he caught a magic fish."
                ),
                StoryPage(
                    "הַדָּג אָמַר: \"אֶתָּנְךָ כָּל מָה שֶׁתִּרְצֶה!\"",
                    "The fish said: \"I will give you everything you want!\""
                ),
                StoryPage(
                    "הַדַּיָּג שָׁחַר אוֹתוֹ וְהָלַךְ הַבַּיְתָה.",
                    "The fisherman released him and went home."
                ),
                StoryPage(
                    "אִשְׁתּוֹ רָצְתָה בַּיִת גָּדוֹל.",
                    "His wife wanted a big house."
                ),
                StoryPage(
                    "הַדַּיָּג חָזַר לַיָּם וּבִקֵּשׁ.",
                    "The fisherman went back to the sea and asked."
                ),
                StoryPage(
                    "הַבַּיִת הֻגְדַּל פִּתְאוֹם.",
                    "The house suddenly became big."
                ),
                StoryPage(
                    "אֲבָל הַדַּיָּג לֹא רָצָה יוֹתֵר.",
                    "But the fisherman didn't want more."
                ),
                StoryPage(
                    "הוּא וְאִשְׁתּוֹ חָיוּ בְּשָׁלוֹם.",
                    "He and his wife lived in peace."
                )
            )
        )
    )
}