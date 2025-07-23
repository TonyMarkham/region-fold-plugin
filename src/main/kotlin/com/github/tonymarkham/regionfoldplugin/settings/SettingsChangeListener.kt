
package com.github.tonymarkham.regionfoldplugin.settings

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.startup.StartupActivity
import com.intellij.psi.PsiManager

@Service
class SettingsChangeListener : StartupActivity {

    override fun runActivity(project: Project) {
        ApplicationManager.getApplication().messageBus.connect()
            .subscribe(RegionFoldingSettings.SETTINGS_CHANGED_TOPIC, object : RegionFoldingSettingsListener {
                override fun settingsChanged() {
                    refreshAllOpenFiles()
                }
            })
    }

    private fun refreshAllOpenFiles() {
        ProjectManager.getInstance().openProjects.forEach { project ->
            ApplicationManager.getApplication().invokeLater {
                val fileEditorManager = FileEditorManager.getInstance(project)
                val openFiles = fileEditorManager.openFiles

                openFiles.forEach { virtualFile ->
                    val psiFile = PsiManager.getInstance(project).findFile(virtualFile)
                    if (psiFile != null) {
                        DaemonCodeAnalyzer.getInstance(project).restart(psiFile)
                    }
                }
            }
        }
    }
}