package com.lockermanwxlf.filemanager.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lockermanwxlf.filemanager.viewmodels.MainViewModel
import filemanager.composeapp.generated.resources.Res
import filemanager.composeapp.generated.resources.folder
import filemanager.composeapp.generated.resources.menu
import org.jetbrains.compose.resources.painterResource
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileDisplay(vm: MainViewModel, file: File) {
    val painter = if (file.isDirectory) {
        painterResource(Res.drawable.folder)
    } else {
        painterResource(Res.drawable.menu)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(3.dp)
            .combinedClickable(
                onClick = {

                },
                onDoubleClick = {
                    vm.setTypedInPath(file.absolutePath)
                    vm.navigate()
                    if (file.isFile) {
                        ProcessBuilder("alacritty", "-e", "/usr/, file.absolutePath).start()
                    }
                },
                onLongClick = {

                }
            ),
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).fillMaxSize(0.7f),
            tint = Color(0f, 0f, 0f, if (file.isHidden) 0.5f else 1f)
        )
        Text(
            text = file.name,
            modifier = Modifier.align(Alignment.BottomCenter),
            maxLines = 1
        )
    }
}