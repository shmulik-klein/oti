package klein.shmulik.oti

import kotlinx.coroutines.*
import klein.shmulik.oti.data.StoriesData
import klein.shmulik.oti.data.Story
import klein.shmulik.oti.data.StoryPage

data class StoryInfo(
    val id: Int,
    val title: String,
    val file: String,
    val preview: String
)

class StoryLoader {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private var storyIndex: List<StoryInfo> = emptyList()
    private var storyCache: MutableMap<Int, Story> = mutableMapOf()
    private var loaded = false
    private var loadError: String? = null

    fun loadStoriesIndex(onComplete: () -> Unit, onError: (String) -> Unit) {
        scope.launch {
            try {
                val json = JSON.parse<dynamic>(StoriesData.storiesIndex)
                val arr = json.unsafeCast<Array<dynamic>>()
                storyIndex = arr.map { entry ->
                    StoryInfo(
                        id = entry.id.unsafeCast<Int>(),
                        title = entry.title.unsafeCast<String>(),
                        file = entry.file.unsafeCast<String>(),
                        preview = ""
                    )
                }
                loaded = true
                onComplete()
            } catch (e: dynamic) {
                loadError = "Error loading stories: ${e.message}"
                onError(loadError!!)
            }
        }
    }

    fun getStoryCount(): Int = storyIndex.size

    fun getStoryInfo(index: Int): StoryInfo? {
        return if (index >= 0 && index < storyIndex.size) storyIndex[index] else null
    }

    fun loadStory(index: Int, onComplete: (Story?) -> Unit, onError: (String) -> Unit) {
        if (storyCache.containsKey(index)) {
            onComplete(storyCache[index])
            return
        }

        val storyInfo = getStoryInfo(index) ?: run {
            onComplete(null)
            return
        }

        scope.launch {
            try {
                val jsonStr = StoriesData.storyData[storyInfo.file]
                if (jsonStr == null) {
                    onError("Story not found: ${storyInfo.file}")
                    onComplete(null)
                    return@launch
                }
                val json: dynamic = JSON.parse(jsonStr)
                val story = Story(
                    id = json.id.unsafeCast<Int>(),
                    title = json.title.unsafeCast<String>(),
                    pages = js("[]").unsafeCast<Array<dynamic>>().let { arr ->
                        arr.map { page ->
                            StoryPage(
                                hebrew = page.hebrew.unsafeCast<String>(),
                                translation = page.translation.unsafeCast<String>(),
                                imageUrl = if (page.imageUrl != undefined && page.imageUrl != null) page.imageUrl.unsafeCast<String>() else ""
                            )
                        }
                    }
                )
                storyCache[index] = story
                onComplete(story)
            } catch (e: dynamic) {
                onError("Error loading story: ${e.message}")
                onComplete(null)
            }
        }
    }

    fun getCachedStory(index: Int): Story? = storyCache[index]

    fun dispose() {
        scope.cancel()
    }
}