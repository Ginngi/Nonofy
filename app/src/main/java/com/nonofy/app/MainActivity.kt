package com.nonofy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.nonofy.navigation.NavigatorManager
import com.nonofy.navigation.NonofyNavHost
import com.nonofy.ui.theme.NonofyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigatorManager: NavigatorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NonofyTheme {
                val navController = rememberNavController()
                navigatorManager.init(navController)
                NonofyNavHost(navController)
            }
        }
    }
}