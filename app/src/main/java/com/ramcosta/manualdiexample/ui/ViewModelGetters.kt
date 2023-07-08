package com.ramcosta.manualdiexample.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.savedstate.SavedStateRegistryOwner
import com.ramcosta.manualdiexample.MyApp
import com.ramcosta.manualdiexample.di.DiContainer

@Composable
inline fun <reified VM : ViewModel> viewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    savedStateRegistryOwner: SavedStateRegistryOwner = LocalSavedStateRegistryOwner.current
): VM {
    val diContainer = (LocalContext.current.applicationContext as MyApp).diContainer
    return diContainer.viewModel(
        VM::class.java,
        viewModelStoreOwner,
        savedStateRegistryOwner,
        (savedStateRegistryOwner as? NavBackStackEntry)?.arguments
    )
}

fun <T : ViewModel> DiContainer.viewModel(
    viewModelClass: Class<T>,
    viewModelStoreOwner: ViewModelStoreOwner,
    savedStateRegistryOwner: SavedStateRegistryOwner,
    defaultArgs: Bundle?,
): T {
    return ViewModelProvider(
        owner = viewModelStoreOwner,
        factory = ViewModelFactory(this, savedStateRegistryOwner, defaultArgs)
    )[viewModelClass]
}

private class ViewModelFactory(
    private val diContainer: DiContainer,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null,
) : AbstractSavedStateViewModelFactory(
    owner,
    defaultArgs
) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return diContainer.createViewModel(modelClass, handle)
    }
}