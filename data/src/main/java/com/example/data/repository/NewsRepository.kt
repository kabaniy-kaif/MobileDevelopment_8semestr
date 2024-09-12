package com.example.data.repository

import com.example.data.models.NewsDto
import com.example.domain.models.News
import com.example.domain.repository.NewsRepository

class NewsRepositoryImpl : NewsRepository {

    private val newsList = mutableListOf(
        NewsDto(1, "News 1: SpaceX successfully launches new satellite into orbit.", 0),
        NewsDto(2, "News 2: NASA discovers evidence of water on Mars.", 0),
        NewsDto(3, "News 3: Astronomers spot a rare comet passing through our solar system.", 0),
        NewsDto(4, "News 4: International Space Station celebrates 20 years of continuous human presence.", 0),
        NewsDto(5, "News 5: China lands a rover on the far side of the moon.", 0),
        NewsDto(6, "News 6: Space tourism company announces plans for commercial flights to space.", 0),
        NewsDto(7, "News 7: Scientists capture the first ever image of a black hole.", 0),
        NewsDto(8, "News 8: SpaceX reveals plans for a mission to Mars.", 0),
        NewsDto(9, "News 9: NASA launches new telescope to explore the mysteries of the universe.", 0),
        NewsDto(10, "News 10: Astronauts conduct a successful spacewalk to repair the space station.", 0)
    )

    // Преобразование DTO в доменную модель
    private fun NewsDto.toDomain(): News {
        return News(id, content, likes)
    }

    // Преобразование доменной модели в DTO (чтобы изменить состояние)
    private fun News.toDto(): NewsDto? {
        return newsList.find { it.id == this.id }
    }

    override fun getAllNews(): List<News> {
        return newsList.map { it.toDomain() }
    }

    override fun getRandomNews(): News {
        return newsList.random().toDomain()
    }

    override fun likeNews(news: News) {
        news.toDto()?.let { it.likes++ }
    }
}
