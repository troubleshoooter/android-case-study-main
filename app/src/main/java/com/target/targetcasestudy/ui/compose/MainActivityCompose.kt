package com.target.targetcasestudy.ui.compose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.compose.theme.TargetTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivityCompose : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var currentRoute by remember {
                mutableStateOf("")
            }
            LaunchedEffect(Unit) {
                navController.currentBackStackEntryFlow.collectLatest {
                    currentRoute = it.destination.route.orEmpty()
                }
            }
            TargetTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {

                            Text(
                                text =
                                getTitleByRoute(
                                    LocalContext.current,
                                    currentRoute
                                )
                            )
                        }, navigationIcon = {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        })
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "dealsList",
                        modifier = Modifier.padding(it)
                    ) {
                        composable("dealsList") {
                            DealList()
                        }
                        composable("dealInfo") {
                            DealInfo()
                        }
                    }
                }
            }
        }
    }
}

fun getTitleByRoute(context: Context, route: String): String {
    return when (route) {
        "dealsList" -> context.getString(R.string.title_screen_1)
        "dealInfo" -> context.getString(R.string.title_screen_2)
        else -> context.getString(R.string.title_screen_1)
    }
}

