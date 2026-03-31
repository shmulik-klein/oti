package klein.shmulik.oti

import kotlinx.browser.window
import kotlinx.coroutines.*
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

    @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
    private fun fetch(url: String): dynamic = window.asDynamic().fetch(url).unsafeCast<dynamic>()

    fun loadStoriesIndex(onComplete: () -> Unit, onError: (String) -> Unit) {
        scope.launch {
            try {
                val response = fetch("stories/stories-index.json")
                val text = response.text().await()
                if (!response.ok) {
                    onError("Failed to load stories index: ${response.status}")
                    return@launch
                }
                val json: dynamic = JSON.parse(text)
                storyIndex = js("[]").unsafeCast<Array<dynamic>>().let {
                    val arr = json.unsafeCast<Array<dynamic>>()
                    arr.map { entry ->
                        StoryInfo(
                            id = entry.id.unsafeCast<Int>(),
                            title = entry.title.unsafeCast<String>(),
                            file = entry.file.unsafeCast<String>(),
                            preview = "" 
                        )
                    }
                }
                loaded = true
                onComplete()
            } catch (e: dynamic) {
                onError("Error loading stories: ${e.message}")
            }
        }
    }

    fun getStoryCount(): Int = storyIndex.size

    fun getStoryInfo(index: Int): StoryInfo? {
        return if (index in storyIndex.indices) storyIndex[index] else null
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
                val response = fetch("stories/${storyInfo.file}")
                val text = response.text().await()
                if (!response.ok) {
                    onError("Failed to load story: ${response.status}")
                    onComplete(null)
                    return@launch
                }
                val json: dynamic = JSON.parse(text)
                val story = Story(
                    id = json.id.unsafeCast<Int>(),
                    title = json.title.unsafeCast<String>(),
                    pages = js("[]").unsafeCast<Array<dynamic>>().let { arr ->
                        arr.map { page ->
                            StoryPage(
                                hebrew = page.hebrew.unsafeCast<String>(),
                                translation = page.translation.unsafeCast<String>(),
                                imageUrl = page.imageUrl.unsafeCast<String>().let { if (it == null) "" else it }
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