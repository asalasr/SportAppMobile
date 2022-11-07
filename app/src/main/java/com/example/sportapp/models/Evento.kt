package com.example.sportapp.models

import java.io.Serializable


data class MisEventos(
    val id:String,
    val user_id:String,
    val register_date:String,
    val event: Eventos,
): Serializable


data class Eventos(
    val id:String,
    val title:String,
    val body:String,
    val url_image:String,
    val datatime:String,
    val state:String,
): Serializable
