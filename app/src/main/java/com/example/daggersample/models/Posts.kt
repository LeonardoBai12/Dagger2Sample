package com.example.daggersample.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Post(@field:SerializedName("userId")
                 var userId : Int,

                 @field:SerializedName("id")
                  var id : Int,

                  @field:SerializedName("title")
                  var title : String,

                  @field:SerializedName("body")
                  var body : String ) : Serializable {
    constructor() : this(0,0,"","")
}