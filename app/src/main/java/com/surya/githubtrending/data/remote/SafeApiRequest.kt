package com.surya.githubtrending.data.remote

import com.surya.githubtrending.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by suryamudti on 12/11/2019.
 */

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()

            val message = StringBuilder()
            error?.let{
                try{
                    message.append(JSONObject(it).getString("message"))
                }catch(e: JSONException){ e.printStackTrace()}
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}