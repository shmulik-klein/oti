package klein.shmulik.oti.data

object StoriesData {
    val storiesIndex = """
        [
          {"id": 1, "title": "הנזל וגרטל", "file": "hansel-gretel.json"},
          {"id": 2, "title": "כובע אדום", "file": "red-riding-hood.json"},
          {"id": 3, "title": "שלושה חזירונים", "file": "three-pigs.json"},
          {"id": 4, "title": "הרועה והגמדים", "file": "shepherd-dwarfs.json"},
          {"id": 5, "title": "הדייג ואשתו", "file": "fisherman-wife.json"}
        ]
    """.trimIndent()

    val storyData = mapOf(
        "hansel-gretel.json" to """
            {
              "id": 1,
              "title": "הנזל וגרטל",
              "pages": [
                {"hebrew": "יְלָדִים הָיוּ בַּכֹּפֶר.", "translation": "There were children in the village."},
                {"hebrew": "שֵׁם הַיָּלָד הָאָחִים הָיָה הַנְּזָל וְגִרְטֵל.", "translation": "The children's names were Hansel and Gretel."},
                {"hebrew": "אָבָא וְאֵם הָיוּ עֲנִיִּים.", "translation": "Father and mother were poor."},
                {"hebrew": "בַּיּוֹם הָרִאשׁוֹן הָלְכוּ הַיְּלָדִים לַיָּעַר.", "translation": "On the first day, the children went to the forest."},
                {"hebrew": "הֵם מָצְאוּ בַּיִת קָטָן מִסּוֹכֶר.", "translation": "They found a small house made of candy."},
                {"hebrew": "אִמָּה הָרְעָה גַּרָה בַּבַּיִת.", "translation": "The evil stepmother lived in the house."},
                {"hebrew": "הִיא לָקְחָה אֶת גִרְטֵל לַתּוֹךְ הַבַּיִת.", "translation": "She took Gretel inside the house."},
                {"hebrew": "הַנְּזָל בָּרַח מִן הַבַּיִת.", "translation": "Hansel ran away from the house."},
                {"hebrew": "הֵם מָצְאוּ אֶת הַדֶּרֶךְ הַבַּיְתָה.", "translation": "They found the way home."},
                {"hebrew": "הַמִּשְׁפָּחָה הָיְתָה שָׂמֵחָה.", "translation": "The family was happy."}
              ]
            }
        """.trimIndent(),
        "red-riding-hood.json" to """
            {
              "id": 2,
              "title": "כובע אדום",
              "pages": [
                {"hebrew": "יַלְדָּה קְטַנָּה גָּרָה בַּכֹּפֶר.", "translation": "A little girl lived in the village."},
                {"hebrew": "סָבְתָא נָתְנָה לָהּ כּוֹבַע אָדוּם יָפֶה.", "translation": "Grandmother gave her a beautiful red hat."},
                {"hebrew": "יוֹם אֶחָד הַלְּכָה הַיַּלְדָּה לְבַקֵּר אֶת סָבְתָא.", "translation": "One day, the girl went to visit grandmother."},
                {"hebrew": "בַּדֶּרֶךְ פָּגְשָׁה זְאֵב גָּדוֹל.", "translation": "On the way, she met a big wolf."},
                {"hebrew": "\"לְאָן אַתְּ הוֹלֶכֶת?\" שָׁאַל הַזְּאֵב.", "translation": "\"Where are you going?\" asked the wolf."},
                {"hebrew": "\"אֲנִי הוֹלֶכֶת לְבַקֵּר אֶת סָבְתָא,\" עָנְתָה הַיַּלְדָּה.", "translation": "\"I'm going to visit grandmother,\" answered the girl."},
                {"hebrew": "הַזְּאֵב אָכַל אֶת הַסָּבָה.", "translation": "The wolf ate the grandmother."},
                {"hebrew": "צַיָּד בָּא לִתְרוֹץ אֶת הַזְּאֵב.", "translation": "A hunter came to save the wolf."},
                {"hebrew": "הַיַּלְדָּה נִצְלָה.", "translation": "The girl was saved."},
                {"hebrew": "מֵאָז הִיא תָּמִיד שָׁמְרָה עַל עַצְמָהּ.", "translation": "Since then, she always took care of herself."}
              ]
            }
        """.trimIndent(),
        "three-pigs.json" to """
            {
              "id": 3,
              "title": "שלושה חזירונים",
              "pages": [
                {"hebrew": "שְׁלוֹשָׁה חֲזִירוֹנִים גָּרוּ בַּיָּעַר.", "translation": "Three little pigs lived in the forest."},
                {"hebrew": "הֵם הֶחֱלִיטוּ לִבְנוֹת בָּתִּים.", "translation": "They decided to build houses."},
                {"hebrew": "הָרִאשׁוֹן בָּנָה בַּיִת מִקַּשׁ.", "translation": "The first built a house of straw."},
                {"hebrew": "הַשֵּׁנִי בָּנָה בַּיִת מִקּוֹרוֹת עֵץ.", "translation": "The second built a house of wood."},
                {"hebrew": "הַשְּׁלִישִׁי בָּנָה בַּיִת מֵאֲבָנִים.", "translation": "The third built a house of stones."},
                {"hebrew": "זְאֵב גָּדוֹל בָּא לְהַפְחִיד אוֹתָם.", "translation": "A big wolf came to scare them."},
                {"hebrew": "הַזְּאֵב נָפַח עַל בֵּית הַקַּשׁ, וְהוּא נָפָל.", "translation": "The wolf blew on the straw house, and it fell."},
                {"hebrew": "הַזְּאֵב נָפַח עַל בֵּית הַקּוֹרוֹת, וְהוּא נָפָל.", "translation": "The wolf blew on the wood house, and it fell."},
                {"hebrew": "הַזְּאֵב לֹא יָכוֹל לְהַפִּיל אֶת בֵּית הָאֲבָנִים.", "translation": "The wolf could not blow down the stone house."},
                {"hebrew": "הַחֲזִירוֹנִים הָיוּ בְּטוּחִים בְּבֵיתָם.", "translation": "The little pigs were safe in their house."}
              ]
            }
        """.trimIndent(),
        "shepherd-dwarfs.json" to """
            {
              "id": 4,
              "title": "הרועה והגמדים",
              "pages": [
                {"hebrew": "רוֹעֶה צֹאן הָלַךְ בַּהָרִים.", "translation": "A shepherd walked in the mountains."},
                {"hebrew": "הוּא מָצָא מְעָרָה קְטַנָּה בַּסֶּלַע.", "translation": "He found a small cave in the rock."},
                {"hebrew": "בִּמְעָרָה גָּרוּ גַּמְדִים קְטַנִּים.", "translation": "Little dwarfs lived in the cave."},
                {"hebrew": "הַגַּמְדִים עָבְדוּ כָּל הַיּוֹם.", "translation": "The dwarfs worked all day."},
                {"hebrew": "הֵם זָהָב לְמַלֵךְ עָשׂוּ.", "translation": "They made gold for the king."},
                {"hebrew": "יוֹם אֶחָד הַמֶּלֶךְ גִּירֵשׁ אֶת הָרוֹעֶה.", "translation": "One day, the king chased the shepherd away."},
                {"hebrew": "הָרוֹעֶה חָזַר לִמְעָרָה.", "translation": "The shepherd returned to the cave."},
                {"hebrew": "הַגַּמְדִים נְתָנוּ לוֹ כֶּסֶף הַרְבֵּה.", "translation": "The dwarfs gave him a lot of money."},
                {"hebrew": "הָרוֹעֶה הָיָה עַתָּה עָשִׁיר.", "translation": "The shepherd was now rich."},
                {"hebrew": "הוּא חָיָה בְּאֹשֶׁר לְעוֹלָם.", "translation": "He lived happily forever."}
              ]
            }
        """.trimIndent(),
        "fisherman-wife.json" to """
            {
              "id": 5,
              "title": "הדייג ואשתו",
              "pages": [
                {"hebrew": "דַּיָּג אֶחָד גָּר לְיַד הַיָּם.", "translation": "A fisherman lived by the sea."},
                {"hebrew": "אִשְׁתּוֹ הָיְתָה עֲנִיָּה מְאוֹד.", "translation": "His wife was very poor."},
                {"hebrew": "יוֹם אֶחָד תָּפַס דָּג קָסָם.", "translation": "One day, he caught a magic fish."},
                {"hebrew": "הַדָּג אָמַר: \"אֶתָּנְךָ כָּל מָה שֶׁתִּרְצֶה!\"", "translation": "The fish said: \"I will give you everything you want!\""},
                {"hebrew": "הַדַּיָּג שָׁחַר אוֹתוֹ וְהָלַךְ הַבַּיְתָה.", "translation": "The fisherman released him and went home."},
                {"hebrew": "אִשְׁתּוֹ רָצְתָה בַּיִת גָּדוֹל.", "translation": "His wife wanted a big house."},
                {"hebrew": "הַדַּיָּג חָזַר לַיָּם וּבִקֵּשׁ.", "translation": "The fisherman went back to the sea and asked."},
                {"hebrew": "הַבַּיִת הֻגְדַּל פִּתְאוֹם.", "translation": "The house suddenly became big."},
                {"hebrew": "אֲבָל הַדַּיָּג לֹא רָצָה יוֹתֵר.", "translation": "But the fisherman didn't want more."},
                {"hebrew": "הוּא וְאִשְׁתּוֹ חָיוּ בְּשָׁלוֹם.", "translation": "He and his wife lived in peace."}
              ]
            }
        """.trimIndent()
    )
}