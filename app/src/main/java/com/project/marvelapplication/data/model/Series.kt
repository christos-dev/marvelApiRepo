package com.project.marvelapplication.data.model

import com.google.gson.annotations.SerializedName


data class Series (

  @SerializedName("available")
  var available: String? = null,

  @SerializedName("returned")
  var returned: String? = null,

  @SerializedName("collectionURI")
  var collectionURI: String? = null,

  @SerializedName("items")
  var items: ArrayList<Items> = arrayListOf()

)