package com.danielceinos.imgram.usecases


import com.danielceinos.imgram.domain.image.ImageRepository
import com.danielceinos.imgram.data.imgurapi.Image

class DetailImageUseCase(private val imageRepository: ImageRepository) {
    suspend fun execute(ImageId: String): List<Image> = imageRepository.getAlbumImages(ImageId)
}
