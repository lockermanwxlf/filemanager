package com.lockermanwxlf.filemanager.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.math.max
import kotlin.math.min

class MainViewModel : ViewModel() {
    var currentTypedInPath by mutableStateOf(System.getProperty("user.home")!!)
        private set

    var currentPath by mutableStateOf(Path(currentTypedInPath))
        private set

    var previousPath by mutableStateOf<Path?>(null)
        private set

    var searchStatus by mutableStateOf(SearchStatus.OK)
        private set

    var files by mutableStateOf(listOf<File>())
        private set

    val settings = MainSettings()

    private val pathHistory = ArrayDeque<Path>()

    private fun setFiles() {
        files = currentPath.toFile().listFiles()?.toList() ?: listOf()
    }


    init {
        setFiles()
    }

    fun navigateBack() {
        previousPath?.let {
            currentPath = it
            currentTypedInPath = currentPath.toString()
            searchStatus = SearchStatus.OK
            pathHistory.removeLastOrNull()
            previousPath = pathHistory.lastOrNull()
            setFiles()
        }
    }

    fun navigateUp() {
        currentPath.parent?.let {
            currentTypedInPath = it.toString()
            navigate()
        }
    }

    fun navigate() {
        val destination = currentTypedInPath.let {
            if (it.isBlank()) {
                "/"
            } else if (it.first() == '~') {
                System.getProperty("user.home") + it.drop(1)
            } else {
                it
            }
        }

        val path = Path(destination)
        if (path.exists() && path != currentPath) {
            previousPath = currentPath
            pathHistory.addLast(currentPath)
            currentPath = path
            setFiles()
        }
        searchStatus = if (path.exists()) SearchStatus.OK else SearchStatus.BAD
    }

    fun setTypedInPath(value: String) {
        currentTypedInPath = value
        searchStatus = SearchStatus.NONE
    }
}

class MainSettings {
    var fileDisplaySize by mutableStateOf(150)
        private set
    var showHiddenFiles by mutableStateOf(false)
        private set

    fun toggleShowHiddenFiles() {
        showHiddenFiles = !showHiddenFiles
    }

    fun increaseFileDisplaySize() {
        fileDisplaySize = min(fileDisplaySize + 25, 300)
    }

    fun decreaseFileDisplaySize() {
        fileDisplaySize = max(fileDisplaySize - 25, 50)
    }

}

enum class SearchStatus {
    OK,
    BAD,
    NONE
}