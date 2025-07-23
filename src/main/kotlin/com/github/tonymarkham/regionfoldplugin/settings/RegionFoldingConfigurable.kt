package com.github.tonymarkham.regionfoldplugin.settings

import com.intellij.openapi.options.Configurable
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JColorChooser
import javax.swing.JComponent
import javax.swing.JPanel

class RegionFoldingConfigurable : Configurable {

    private lateinit var colorButton: JButton
    private var selectedColor: Color = Color.GREEN
    private val settings = RegionFoldingSettings.getInstance()

    override fun getDisplayName(): String = "Region Folding"

    override fun createComponent(): JComponent {
        selectedColor = settings.regionHighlightColor

        colorButton = JButton().apply {
            background = selectedColor
            isOpaque = true
            text = "Choose Color"
            addActionListener(object : ActionListener {
                override fun actionPerformed(e: ActionEvent) {
                    val newColor = JColorChooser.showDialog(
                        this@apply,
                        "Choose Region Highlight Color",
                        selectedColor
                    )
                    if (newColor != null) {
                        selectedColor = newColor
                        background = newColor
                    }
                }
            })
        }

        return FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Region highlight color:"), colorButton, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    override fun isModified(): Boolean {
        return selectedColor != settings.regionHighlightColor
    }

    override fun apply() {
        settings.regionHighlightColor = selectedColor
    }

    override fun reset() {
        selectedColor = settings.regionHighlightColor
        colorButton.background = selectedColor
    }
}