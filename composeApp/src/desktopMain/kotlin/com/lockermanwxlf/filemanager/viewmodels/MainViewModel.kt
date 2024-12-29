package com.lockermanwxlf.filemanager.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.exists

class MainViewModel : ViewModel() {
    var currentTypedInPath by mutableStateOf(System.getProperty("user.home")!!)
        private set

    var currentPath by mutableStateOf(Path(currentTypedInPath))
        private set

    var previousPath by mutableStateOf<Path?>(null)
        private set

    var searchStatus by mutableStateOf(SearchStatus.OK)
        private set

    private val pathHistory = ArrayDeque<Path>()

    fun navigateBack() {
        previousPath?.let {
            currentPath = it
            currentTypedInPath = currentPath.toString()
            searchStatus = SearchStatus.OK
            pathHistory.removeLastOrNull()
            previousPath = pathHistory.lastOrNull()
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
        }
        searchStatus = if (path.exists()) SearchStatus.OK else SearchStatus.BAD
    }

    fun setTypedInPath(value: String) {
        currentTypedInPath = value
        searchStatus = SearchStatus.NONE
    }
}

enum class SearchStatus {
    OK,
    BAD,
    NONE
}