package com.github.tonymarkham.regionfoldplugin.highlighting

import com.github.tonymarkham.regionfoldplugin.settings.RegionFoldingSettings
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import java.awt.Font

class UniversalRegionAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is PsiWhiteSpace) return

        val text = element.text?.trim() ?: return

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

        if (containsRegionMarker(text)) {
            findAndHighlightRegionMarkers(element, holder, text)
        }
    }

    private fun isRegionMarker(text: String): Boolean {
        return text.matches(Regex("""/\*\s*#region.*\*/""")) ||
                text.matches(Regex("""/\*\s*#endregion.*\*/"""))
    }

    private fun containsRegionMarker(text: String): Boolean {
        return text.contains(Regex("""/\*\s*#region.*\*/""")) ||
                text.contains(Regex("""/\*\s*#endregion.*\*/"""))
    }

    private fun findAndHighlightRegionMarkers(element: PsiElement, holder: AnnotationHolder, text: String) {
        val settings = RegionFoldingSettings.getInstance()
        val textAttributes = TextAttributes(
            settings.regionHighlightColor,
            null,
            null,
            null,
            Font.BOLD
        )

        val regionMatches = Regex("""/\*\s*#(region|endregion).*?\*/""").findAll(text)

        for (match in regionMatches) {
            val startOffset = element.textRange.startOffset + match.range.first
            val endOffset = element.textRange.startOffset + match.range.last + 1
            val markerRange = com.intellij.openapi.util.TextRange(startOffset, endOffset)

            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(markerRange)
                .enforcedTextAttributes(textAttributes)
                .create()
        }
    }
}