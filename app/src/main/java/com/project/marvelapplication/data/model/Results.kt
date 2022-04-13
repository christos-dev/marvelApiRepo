package com.project.marvelapplication.data.model

import com.google.gson.annotations.SerializedName
import com.project.marvelapplication.data.database.CharacterRoom


data class Results (

  @SerializedName("id")
  var id: String? = null,

  @SerializedName("name")
  var name: String? = null,

  @SerializedName("description")
  var description: String? = null,

  @SerializedName("modified")
  var modified: String? = null,

  @SerializedName("resourceURI")
  var resourceURI: String? = null,

  @SerializedName("urls")
  var urls: ArrayList<Urls> = arrayListOf(),

  @SerializedName("thumbnail")
  var thumbnail: Thumbnail? = Thumbnail(),

  @SerializedName("comics")
  var comics: Comics? = Comics(),

  @SerializedName("stories")
  var stories: Stories? = Stories(),

  @SerializedName("events")
  var events: Events? = Events(),

  @SerializedName("series")
  var series: Series? = Series()

){
  fun changeToCharacterRoom(): CharacterRoom {
    val path = "${thumbnail?.changePath()}/detail.${thumbnail?.extension}"
    return CharacterRoom(Integer.parseInt(id!!), name!!, path, description!!)
  }
}