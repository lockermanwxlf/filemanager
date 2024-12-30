package com.lockermanwxlf.filemanager

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.lockermanwxlf.filemanager.viewmodels.MainViewModel

fun main() {
    val vm = MainViewModel()

    fun keyIntercept(event: KeyEvent): Boolean {
        if (!event.isCtrlPressed || event.type != KeyEventType.KeyDown) {
            return false
        }
        when (event.key) {
            Key.Equals -> vm.settings.increaseFileDisplaySize()
            Key.Minus -> vm.settings.decreaseFileDisplaySize()
            Key.H -> vm.settings.toggleShowHiddenFiles()
            else -> return false
        }
        return true
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "FileManager",
            onPreviewKeyEvent = ::keyIntercept
        ) {
            App(vm)
        }
    }
}