package com.lockermanwxlf.filemanager.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.lockermanwxlf.filemanager.viewmodels.MainViewModel

@Composable
fun FilesDisplay(vm: MainViewModel) {
    val files = if (vm.settings.showHiddenFiles) {
        vm.files
    } else {
        vm.files.filter { !it.isHidden }
    }.groupBy { !it.isDirectory }
        .map { it.value.sortedBy { it.name.lowercase() } }
        .sortedBy { !it[0].isDirectory }
        .flatten()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(vm.settings.fileDisplaySize.dp),
        contentPadding = PaddingValues(5.dp),
    ) {
        items(files) { file ->
            FileDisplay(vm, file)
        }
    }
}