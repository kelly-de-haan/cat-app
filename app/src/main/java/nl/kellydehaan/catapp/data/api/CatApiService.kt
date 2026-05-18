package nl.kellydehaan.catapp.data.api

import nl.kellydehaan.catapp.data.model.CatBreed
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    /**
     * Fetches a paginated list of cat breeds.
     * The Cat API returns breeds without images by default — attach_image=1 includes them.
     */
    @GET("breeds")
    suspend fun getBreeds(
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int = 0,
        @Query("attach_image") attachImage: Int = 1
    ): List<CatBreed>
}