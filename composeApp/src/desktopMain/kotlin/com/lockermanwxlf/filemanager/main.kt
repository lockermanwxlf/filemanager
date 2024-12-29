package com.lockermanwxlf.filemanager

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.lockermanwxlf.filemanager.viewmodels.MainViewModel

fun main() {
    val vm = MainViewModel()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "FileManager",
        ) {
            App(vm)
        }
    }
}