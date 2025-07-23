package com.github.tonymarkham.regionfoldplugin.highlighting

import com.github.tonymarkham.regionfoldplugin.settings.RegionFoldingSettings
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.psi.PsiElement
import java.awt.Font

class TextBasedRegionAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val text = element.text?.trim() ?: return

        // Check if this element contains region markers
        if (isRegionMarker(text)) {
            val settings = RegionFoldingSettings.getInstance()
            val textAttributes = TextAttributes(
                settings.regionHighlightColor,
                null,
                null,
                null,
                Font.BOLD
            )

            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.textRange)
                .enforcedTextAttributes(textAttributes)
                .create()
        }
    }

    private fun isRegionMarker(text: String): Boolean {
        return (text.startsWith("/* #region") && text.endsWith("*/")) ||
                (text.startsWith("/* #endregion") && text.endsWith("*/"))
    }
}