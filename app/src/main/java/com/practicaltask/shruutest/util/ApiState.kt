package com.practicaltask.shruutest.util

import com.practicaltask.shruutest.data.model.ProductResponse
import retrofit2.Response

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data: Response<ProductResponse>) : ApiState()
    object Empty : ApiState()
}
