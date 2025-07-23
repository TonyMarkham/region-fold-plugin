package com.github.tonymarkham.regionfoldplugin.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange

class UniversalRegionFoldingBuilder : FoldingBuilder {

    override fun buildFoldRegions(node: ASTNode, document: Document): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val text = document.text

        val regionPairs = findRegionPairs(text)

        for (pair in regionPairs) {
            val textRange = TextRange(pair.startOffset, pair.endOffset)
            descriptors.add(FoldingDescriptor(node, textRange))
        }

        return descriptors.toTypedArray()
    }

    private fun findRegionPairs(text: String): List<RegionPair> {
        val pairs = mutableListOf<RegionPair>()
        val lines = text.split('\n')
        val regionStack = mutableListOf<RegionStart>()

        var currentOffset = 0

        for (line in lines) {
            val trimmed = line.trim()

            when {
                trimmed.matches(Regex("""/\*\s*#region.*\*/""")) -> {
                    val nameMatch = Regex("""/\*\s*#region\s+(.+?)\s*\*/""").find(trimmed)
                    val name = nameMatch?.groupValues?.get(1)?.trim() ?: ""
                    regionStack.add(RegionStart(currentOffset, name))
                }
                trimmed.matches(Regex("""/\*\s*#endregion.*\*/""")) -> {
                    if (regionStack.isNotEmpty()) {
                        val start = regionStack.removeLastOrNull()
                        if (start != null) {
                            val endOffset = currentOffset + line.length
                            pairs.add(RegionPair(start.offset, endOffset, start.name))
                        }
                    }
                }
            }

            currentOffset += line.length + 1 // +1 for newline
        }

        return pairs
    }

    override fun getPlaceholderText(node: ASTNode): String {
        val text = node.text
        val nameMatch = Regex("""/\*\s*#region\s+(.+?)\s*\*/""").find(text)
        return if (nameMatch != null) {
            val name = nameMatch.groupValues[1].trim()
            "/* #region $name ... */"
        } else {
            "/* #region ... */"
        }
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false

    private data class RegionStart(val offset: Int, val name: String)
    private data class RegionPair(val startOffset: Int, val endOffset: Int, val name: String)
}