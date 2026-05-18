package nl.kellydehaan.catapp.data.repository

import nl.kellydehaan.catapp.data.model.BreedImage
import nl.kellydehaan.catapp.data.model.CatBreed
import nl.kellydehaan.catapp.data.model.Weight

class CatRepository {
    fun getBreeds(page: Int): Result<List<CatBreed>> {
        return if (page == 0) {
            Result.success(
                listOf(
                    CatBreed(
                        id = "test-cat",
                        name = "Test Cat",
                        description = "A test cat",
                        temperament = "Very sweet",
                        origin = "The Netherlands",
                        lifeSpan = "30 years",
                        affectionLevel = 5,
                        energyLevel = 3,
                        intelligence = 3,
                        childFriendly = 2,
                        dogFriendly = 1,
                        sheddingLevel = 3,
                        healthIssues = 5,
                        wikipediaUrl = null,
                        weight = Weight(
                            imperial = "10 pounds",
                            metric = "5 kg",
                        ),
                        image = BreedImage(
                            id = "MTc5NDkzMg",
                            url = "https://cdn2.thecatapi.com/images/MTc5NDkzMg.jpg",
                            width = 1936,
                            height = 2592,
                        ),
                    )
                )
            )
        } else {
            Result.success(emptyList())
        }
    }
}