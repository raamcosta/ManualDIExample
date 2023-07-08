package com.ramcosta.manualdiexample.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.manualdiexample.ui.theme.ManualDIExampleTheme

@Composable
fun MyScreen(
    viewModel: MyViewModel = viewModel()
) {
    MyScreen(viewModel.uiState)
}

@Composable
private fun MyScreen(
    state: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $state!",
        modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ManualDIExampleTheme {
        MyScreen("Android")
    }
}