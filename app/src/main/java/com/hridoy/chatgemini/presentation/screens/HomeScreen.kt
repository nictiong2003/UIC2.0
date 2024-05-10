
package com.hridoy.chatgemini.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hridoy.chatgemini.common.components.TemplatePreview
import com.hridoy.chatgemini.navigation.ScreenDestinations

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Hello User!",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Button(
                modifier = Modifier
                    .size(width = 200.dp, height = 40.dp),
                onClick = {
                    navController.navigate(ScreenDestinations.ViewScreen.route) {
                        popUpTo(ScreenDestinations.HomeScreen.route) {
                            inclusive = false
                        }
                    }
                },
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxSize().padding(0.dp),
                    text = "Lets Start with UIC!",
                    color = MaterialTheme.colorScheme.background,
                )
            }
        }
    }
}

@TemplatePreview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
