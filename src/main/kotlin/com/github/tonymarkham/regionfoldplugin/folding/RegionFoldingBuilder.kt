package com.github.tonymarkham.regionfoldplugin.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiComment
import com.intellij.psi.util.PsiTreeUtil

class RegionFoldingBuilder : FoldingBuilder {

    override fun buildFoldRegions(node: ASTNode, document: Document): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val element = node.psi

        // Find all region start comments
        val allComments = PsiTreeUtil.findChildrenOfType(element, PsiComment::class.java)
        val regionStarts = mutableListOf<PsiComment>()

        for (comment in allComments) {
            val text = comment.text.trim()
            // Only match the exact pattern: starts with "/* #region" and ends with "*/"
            if (text.startsWith("/* #region") && text.endsWith("*/")) {
                regionStarts.add(comment)
            }
        }

        // For each region start, find the corresponding end
        for (regionStart in regionStarts) {
            val regionEnd = findRegionEnd(regionStart, allComments)
            if (regionEnd != null) {
                val startOffset = regionStart.textRange.startOffset
                val endOffset = regionEnd.textRange.endOffset

                if (startOffset < endOffset) {
                    val textRange = TextRange(startOffset, endOffset)
                    descriptors.add(FoldingDescriptor(regionStart.node, textRange))
                }
            }
        }

        return descriptors.toTypedArray()
    }

    private fun findRegionEnd(regionStart: PsiComment, allComments: Collection<PsiComment>): PsiComment? {
        val startOffset = regionStart.textRange.startOffset

        // Find the closest endregion comment after this region start
        var closestEnd: PsiComment? = null
        var closestDistance = Int.MAX_VALUE

        for (comment in allComments) {
            val text = comment.text.trim()
            // Only match the exact pattern: starts with "/* #endregion" and ends with "*/"
            if (text.startsWith("/* #endregion") && text.endsWith("*/") &&
                comment.textRange.startOffset > startOffset) {

                val distance = comment.textRange.startOffset - startOffset
                if (distance < closestDistance) {
                    closestDistance = distance
                    closestEnd = comment
                }
            }
        }

        return closestEnd
    }

    override fun getPlaceholderText(node: ASTNode): String {
        val comment = node.psi as? PsiComment
        if (comment != null) {
            val text = comment.text.trim()
            // Extract region name if available
            val regionMatch = Regex("""/\* #region\s+(.+?)\s*\*/""").find(text)
            if (regionMatch != null) {
                val regionName = regionMatch.groupValues[1].trim()
                return "/* #region $regionName ... */"
            }
        }
        return "/* #region ... */"
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return false
    }
}