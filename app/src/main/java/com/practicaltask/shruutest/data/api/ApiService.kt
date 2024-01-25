package com.practicaltask.shruutest.data.api


import com.practicaltask.shruutest.data.model.ProductResponse
import com.practicaltask.shruutest.data.model.ProductsItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("products")
   suspend fun getProducts(): Response<ProductResponse>
}