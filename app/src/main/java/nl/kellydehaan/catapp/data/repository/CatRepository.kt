package nl.kellydehaan.catapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.kellydehaan.catapp.data.api.CatApiService
import nl.kellydehaan.catapp.data.api.NetworkClient
import nl.kellydehaan.catapp.data.model.CatBreed

class CatRepository(val api: CatApiService = NetworkClient.catApiService) {
    suspend fun getBreeds(page: Int = 0, limit: Int = 20): Result<List<CatBreed>> =
        withContext(Dispatchers.IO) {
            runCatching {
                api.getBreeds(limit = limit, page = page)
            }
        }
}