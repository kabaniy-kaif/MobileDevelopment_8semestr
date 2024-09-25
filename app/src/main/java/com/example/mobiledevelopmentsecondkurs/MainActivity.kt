package com.example.mobiledevelopmentsecondkurs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.data.repository.NewsRepositoryImpl
import com.example.mobiledevelopmentsecondkurs.ui.theme.MobileDevelopmentSecondKursTheme
import com.example.presentation.ui.MyGLSurfaceView
import com.example.presentation.ui.NewsScreen
import com.example.presentation.viewmodel.NewsViewModel
import com.example.presentation.viewmodel.NewsViewModelFactory

class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileDevelopmentSecondKursTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(newsViewModel = newsViewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(newsViewModel: NewsViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        composable("menu") { MenuScreen(navController) }
        composable("lab1") { NewsScreen(newsViewModel = newsViewModel) }
        composable("lab2") { Lab2Screen() }
    }
}

@Composable
fun MenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("lab1") }) {
            Text(text = "Lab 1")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("lab2") }) {
            Text(text = "Lab 2")
        }
    }
}

@Composable
fun Lab2Screen() {
    AndroidView(
        factory = { context ->
            MyGLSurfaceView(context)
        },
        modifier = Modifier.fillMaxSize()
    )
}
