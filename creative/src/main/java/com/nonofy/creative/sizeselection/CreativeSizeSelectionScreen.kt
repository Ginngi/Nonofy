package com.nonofy.creative.sizeselection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nonofy.creative.buildCreativeDrawRoute
import com.nonofy.ui.components.Screen

@Composable
fun CreativeSizeSelectionScreen(navController: NavController) {
    Screen {
        Column {
            Button(
                onClick = {
                    val route = buildCreativeDrawRoute(5)
                    navController.navigate(route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(text = "5X5")
            }

            Button(
                onClick = { navController.navigate(buildCreativeDrawRoute(10)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(text = "10X10")
            }

            Button(
                onClick = { navController.navigate(buildCreativeDrawRoute(15)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(text = "15X15")
            }
        }
    }
}