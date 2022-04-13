package com.project.marvelapplication.data.model

import com.google.gson.annotations.SerializedName


data class Thumbnail (

  @SerializedName("path")
  var path: String? = null,

  @SerializedName("extension")
  var extension: String? = null

){
  fun changePath(): String? {
    return path?.replace("http", "https")
  }
}