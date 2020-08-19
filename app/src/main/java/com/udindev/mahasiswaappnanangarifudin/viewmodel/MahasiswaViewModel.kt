package com.udindev.mahasiswaappnanangarifudin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udindev.mahasiswaappnanangarifudin.config.ConfigNetwork
import com.udindev.mahasiswaappnanangarifudin.model.action.ResponseAction
import com.udindev.mahasiswaappnanangarifudin.model.getdata.ResponseGetData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MahasiswaViewModel : ViewModel() {
    var result: MutableLiveData<ResponseGetData> = MutableLiveData()
    var resultError: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    private val configNetwork: ConfigNetwork = ConfigNetwork()

    fun getResults(): LiveData<ResponseGetData> = result
    fun getError(): LiveData<Boolean> = resultError
    fun getLoading(): LiveData<Boolean> = loading

    fun loadResults() {
        loading.value = true
        val call = configNetwork.getResultData()
        call.enqueue(object : Callback<ResponseGetData> {
            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                resultError.value = true
                loading.value = false
                Log.e("error server", t.message!!)
            }

            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {
                response.isSuccessful.let {
                    loading.value = false
                    val resultList = ResponseGetData(response.body()?.data ?: emptyList())
                    result.value = resultList
                }
            }

        })
    }

    fun insertData(nama: String?, nohp: String?, alamat: String?) {
        val input = configNetwork.insertData(nama!!, nohp!!, alamat!!)
        input.enqueue(object : Callback<ResponseAction> {
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                resultError.value = true
                Log.e("error server", t.message!!)
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Log.d(
                    "Input Data",
                    "onResponse: succes add nama : $nama , nohp : $nohp, alamat : $alamat"
                )
            }

        })
    }

    fun updateData(id: String?, nama: String?, nohp: String?, alamat: String?) {
        val update = configNetwork.updateData(id!!, nama!!, nohp!!, alamat!!)
        update.enqueue(object : Callback<ResponseAction> {
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                resultError.value = true
                Log.e("error server", t.message!!)
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Log.d(
                    "Update Data",
                    "onResponse: succes update nama : id : $id, $nama , nohp : $nohp, alamat : $alamat"
                )
            }

        })
    }

    fun deleteData(id: String?) {
        val delete = configNetwork.deleteData(id!!)
        delete.enqueue(object : Callback<ResponseAction> {
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                resultError.value = true
                Log.e("error server", t.message!!)
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Log.d(
                    "Delete Data",
                    "onResponse: succes delete data dengan id : $id"
                )
            }

        })
    }

}