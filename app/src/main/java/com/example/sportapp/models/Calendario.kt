package com.example.sportapp.models

import java.io.Serializable

data class Calendario (
    val ids:String,
    val fecha:String,
    val actividad: String,
    val detalle: String,
    val state: String,

): Serializable