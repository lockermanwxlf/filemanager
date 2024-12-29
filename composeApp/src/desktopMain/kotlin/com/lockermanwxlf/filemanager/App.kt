package com.lockermanwxlf.filemanager

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
            },
            drawerContent = {
                Column {
                    listOf("hello", "goodbye", "thing here").forEach {
                        Text(it)
                    }
                }
            },
            drawerShape = MaterialTheme.shapes.small
        ) {

        }
    }
}