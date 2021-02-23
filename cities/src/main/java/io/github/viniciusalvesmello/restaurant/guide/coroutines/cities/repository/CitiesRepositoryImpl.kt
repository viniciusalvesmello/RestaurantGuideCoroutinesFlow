package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository

import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.asResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.singleEmit
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

class CitiesRepositoryImpl(
    private val coroutineContext: CoroutineContext
): CitiesRepository {

    override fun getCities(coroutineScope: CoroutineScope): ResourceResponse<List<City>> = singleEmit {
       mutableListOf<City>().apply {
            add(
                City(
                    id = 67,
                    name = "São Paulo",
                    imageUrl = "https://www.localizahertz.com/brasil-site/pt-br/rede-de-agencias/PublishingImages/Sao-Paulo/sao-paulo-cabecalho.jpg"
                )
            )
            add(
                City(
                    id = 66,
                    name = "Brasília",
                    imageUrl = "https://media.melhoresdestinos.com.br/2019/02/passagens-aereas-brasilia-capa2019-01.jpg"
                )
            )
        }
    }.asResourceResponse(coroutineScope = coroutineScope, coroutineContext = coroutineContext)
}