package com.example.daggersample.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User( @field:SerializedName("id")
                 var id: Int,

                 @field:SerializedName("username")
                 var username: String,

                 @field:SerializedName("website")
                 var website: String,

                 @field:SerializedName("email")
                 var email: String ) : Serializable {
    constructor() : this(0,"","","")
}