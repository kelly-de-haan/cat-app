package nl.kellydehaan.catapp.data.model

import com.google.gson.annotations.SerializedName

data class CatBreed(
    val id: String,
    val name: String,
    val description: String,
    val temperament: String,
    val origin: String,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("affection_level") val affectionLevel: Int,
    @SerializedName("energy_level") val energyLevel: Int,
    @SerializedName("intelligence") val intelligence: Int,
    @SerializedName("child_friendly") val childFriendly: Int,
    @SerializedName("dog_friendly") val dogFriendly: Int,
    @SerializedName("shedding_level") val sheddingLevel: Int,
    @SerializedName("health_issues") val healthIssues: Int,
    @SerializedName("wikipedia_url") val wikipediaUrl: String?,
    val weight: Weight,
    val image: BreedImage?
)

data class Weight(
    val imperial: String,
    val metric: String
)

data class BreedImage(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)
