package com.udindev.mahasiswaappnanangarifudin.model.getdata

import com.google.gson.annotations.SerializedName

data class ResponseGetData(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)