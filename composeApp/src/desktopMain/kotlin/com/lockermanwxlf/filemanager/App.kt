package com.lockermanwxlf.filemanager

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lockermanwxlf.filemanager.ui.FilesDisplay
import com.lockermanwxlf.filemanager.ui.SideBar
import com.lockermanwxlf.filemanager.ui.TopBar
import com.lockermanwxlf.filemanager.viewmodels.MainViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    vm: MainViewModel
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopBar(vm)
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                SideBar()
                FilesDisplay(vm)
            }
        }
    }
}