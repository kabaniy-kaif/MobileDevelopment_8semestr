package com.example.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.domain.models.News
import com.example.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.delay

@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {
    val newsList by newsViewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            newsViewModel.getRandomNewsForAll()

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                NewsCard(news = newsList.getOrNull(0), onLikeClick = { newsViewModel.likeNews(newsList[0]) })
            }
            Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                NewsCard(news = newsList.getOrNull(1), onLikeClick = { newsViewModel.likeNews(newsList[1]) })
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                NewsCard(news = newsList.getOrNull(2), onLikeClick = { newsViewModel.likeNews(newsList[2]) })
            }
            Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                NewsCard(news = newsList.getOrNull(3), onLikeClick = { newsViewModel.likeNews(newsList[3]) })
            }
        }
    }
}

@Composable
fun NewsCard(news: News?, onLikeClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier.fillMaxSize(), // Карточка будет заполнять доступное пространство
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = news?.content ?: "Loading...",
                modifier = Modifier
                    .weight(0.85f)
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Button(
                onClick = onLikeClick,
                modifier = Modifier
                    .weight(0.15f)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Likes: ${news?.likes ?: 0}")
            }
        }
    }
}
