package com.example.sportapp.models

import java.io.Serializable


data class Evento(
    val id:String,
    val user_id:String,
    val register_date:String,
    val event: Event,
): Serializable


data class Event(
    val id:String,
    val title:String,
    val body:String,
    val url_image:String,
    val datatime:String,
    val state:String,
    val assistants:Array<Assistant>?,
): Serializable

data class Assistant(
    val id:String,
    val user_id:String,
    val register_date:String,
): Serializable