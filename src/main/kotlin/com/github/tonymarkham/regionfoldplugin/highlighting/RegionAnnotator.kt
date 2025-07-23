package com.github.tonymarkham.regionfoldplugin.highlighting

import com.github.tonymarkham.regionfoldplugin.settings.RegionFoldingSettings
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiComment
import java.awt.Font

class RegionAnnotator : Annotator {

    companion object {
        val REGION_ATTRIBUTES = TextAttributesKey.createTextAttributesKey("REGION_COMMENT")
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is PsiComment) {
            val text = element.text

            // Only match the exact pattern specified in requirements
            if (isRegionComment(text)) {
                val settings = RegionFoldingSettings.getInstance()
                val textAttributes = TextAttributes(
                    settings.regionHighlightColor, // foreground color
                    null, // background color
                    null, // effect color
                    null, // effect type
                    Font.BOLD // font type
                )

                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .enforcedTextAttributes(textAttributes)
                    .create()
            }
        }
    }

    private fun isRegionComment(text: String): Boolean {
        val trimmed = text.trim()
        return (trimmed.startsWith("/* #region") && trimmed.endsWith("*/")) ||
                (trimmed.startsWith("/* #endregion") && trimmed.endsWith("*/"))
    }
}