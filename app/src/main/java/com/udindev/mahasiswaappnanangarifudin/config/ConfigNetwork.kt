package com.udindev.mahasiswaappnanangarifudin.config

import com.google.gson.GsonBuilder
import com.udindev.mahasiswaappnanangarifudin.model.action.ResponseAction
import com.udindev.mahasiswaappnanangarifudin.model.getdata.ResponseGetData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {

    private val service: MahasiswaService
    companion object {
        const val BASE_URL = "http://192.168.42.220/mentoring_kotlin_week4/"
    }
    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        service = retrofit.create(MahasiswaService::class.java)
    }

    fun getResultData(): Call<ResponseGetData> {
        return service.getData()
    }

    fun insertData(nama: String, nohp: String, alamat: String
    ): Call<ResponseAction> {
        return service.insertData(nama, nohp, alamat)
    }

    fun updateData(id : String, nama: String, nohp: String, alamat: String
    ) : Call<ResponseAction> {
        return service.updateData(id, nama, nohp, alamat)
    }

    fun deleteData(id : String) : Call<ResponseAction> {
        return service.deleteData(id)
    }

}