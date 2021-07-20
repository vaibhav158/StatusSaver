package com.mystikcoder.statussaver.repository.instagram

import com.mystikcoder.statussaver.model.instagram.FullDetailModel
import com.mystikcoder.statussaver.networking.ApiService
import com.mystikcoder.statussaver.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class FullDetailsInfoApiRespository @Inject constructor(
    private val apiService: ApiService
) : FullDetailsInfoApiInterface {
    override suspend fun getFullDetailInfoApi(
        value: String,
        cookie: String,
        userAgent: String
    ): Resource<FullDetailModel> {

        val response: Response<FullDetailModel> =
            apiService.getFullDetailInfoApi(value, cookie, userAgent)
        val result = response.body()

        return try {
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }
        }catch (e: Exception){
            return Resource.Error(e.message ?: "An error occurred")
        }
    }
}