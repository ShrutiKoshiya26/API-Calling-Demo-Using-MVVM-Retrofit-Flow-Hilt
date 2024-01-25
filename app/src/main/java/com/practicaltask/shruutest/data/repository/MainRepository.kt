package com.practicaltask.shruutest.data.repository

import com.practicaltask.shruutest.data.api.ApiService
import com.practicaltask.shruutest.data.model.ProductResponse

import com.practicaltask.shruutest.data.model.ProductsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MainRepository
@Inject constructor(private val apiService: ApiService) {

    fun getProducts(): Flow<Response<ProductResponse>> = flow {
        emit(apiService.getProducts())
    }.flowOn(Dispatchers.IO)

}