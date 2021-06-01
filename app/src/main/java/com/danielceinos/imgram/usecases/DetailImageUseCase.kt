package com.danielceinos.imgram.usecases


import com.danielceinos.imgram.data.imgurapi.Image
import com.danielceinos.imgram.domain.image.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailImageUseCase(private val imageRepository: ImageRepository) {
    suspend fun execute(ImageId: String): List<Image> = withContext(Dispatchers.IO) { imageRepository.getAlbumImages(ImageId) }
}
