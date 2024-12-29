package com.lockermanwxlf.filemanager.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import com.lockermanwxlf.filemanager.viewmodels.MainViewModel
import com.lockermanwxlf.filemanager.viewmodels.SearchStatus
import filemanager.composeapp.generated.resources.Res
import filemanager.composeapp.generated.resources.arrow_back
import filemanager.composeapp.generated.resources.arrow_upward
import filemanager.composeapp.generated.resources.check
import filemanager.composeapp.generated.resources.menu
import filemanager.composeapp.generated.resources.x
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopBar(vm: MainViewModel) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                enabled = (vm.previousPath != null),
                onClick = {
                    vm.navigateBack()
                }
            ) {
                Icon(painterResource(Res.drawable.arrow_back), contentDescription = null)
            }
            IconButton(
                enabled = (vm.currentPath.parent != null),
                onClick = {
                    vm.navigateUp()
                }
            ) {
                Icon(painterResource(Res.drawable.arrow_upward), contentDescription = null)
            }

            SearchBar(vm)

            IconButton(
                enabled = (vm.previousPath != null),
                onClick = {
                    vm.navigateBack()
                }
            ) {
                Icon(painterResource(Res.drawable.menu), contentDescription = null)
            }
        }
    }
}

@Composable
private fun RowScope.SearchBar(vm: MainViewModel) {
    val trailingIcon: @Composable (() -> Unit)? = when (vm.searchStatus) {
        SearchStatus.OK -> {{
            Icon(painterResource(Res.drawable.check), contentDescription = null)
        }}
        SearchStatus.BAD -> {{
            Icon(painterResource(Res.drawable.x), contentDescription = null)
        }}
        SearchStatus.NONE -> null
    }

    OutlinedTextField(
        value = vm.currentTypedInPath,
        onValueChange = vm::setTypedInPath,
        singleLine = true,
        label = { Text(vm.currentPath.toString()) },
        isError = vm.searchStatus == SearchStatus.BAD,
        trailingIcon = trailingIcon,
        modifier = Modifier.weight(1f).padding(5.dp).onKeyEvent { key ->
            if (key.key == Key.Enter) {
                vm.navigate()
                return@onKeyEvent true
            }
            return@onKeyEvent false
        }
    )
}