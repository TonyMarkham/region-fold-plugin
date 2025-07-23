
package com.github.tonymarkham.regionfoldplugin.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.*
import com.intellij.util.messages.Topic
import java.awt.Color

interface RegionFoldingSettingsListener {
    fun settingsChanged()
}

@State(
    name = "RegionFoldingSettings",
    storages = [Storage("regionFolding.xml")]
)
@Service
class RegionFoldingSettings : PersistentStateComponent<RegionFoldingSettings.State> {

    companion object {
        val SETTINGS_CHANGED_TOPIC = Topic.create("RegionFoldingSettingsChanged", RegionFoldingSettingsListener::class.java)

        fun getInstance(): RegionFoldingSettings =
            ApplicationManager.getApplication().getService(RegionFoldingSettings::class.java)
    }

    data class State(
        var regionHighlightColor: String = "#00FF00" // Default green
    )

    private var myState = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state
    }

    var regionHighlightColor: Color
        get() = Color.decode(myState.regionHighlightColor)
        set(value) {
            myState.regionHighlightColor = String.format("#%06X", value.rgb and 0xFFFFFF)
            notifySettingsChanged()
        }

    private fun notifySettingsChanged() {
        ApplicationManager.getApplication().messageBus
            .syncPublisher(SETTINGS_CHANGED_TOPIC)
            .settingsChanged()
    }
}