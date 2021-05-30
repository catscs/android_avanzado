package com.danielceinos.imgram.usecases

import com.danielceinos.imgram.ui.detail.DetailViewModel
import org.kodein.di.*

object UseCasesDIModule {
    fun create() = DI.Module(name = this::class.simpleName!!, allowSilentOverride = false, init = builder)

    private val builder: DI.Builder.() -> Unit = {
        bind<UploadImageUseCase>() with singleton { UploadImageUseCase(instance()) }
        bind<ShareImageUseCase>() with singleton { ShareImageUseCase(instance()) }
        bind<DeleteImageUseCase>() with singleton { DeleteImageUseCase(instance()) }
        bind<DetailViewModel>() with singleton { DetailViewModel(instance()) }
    }
}
