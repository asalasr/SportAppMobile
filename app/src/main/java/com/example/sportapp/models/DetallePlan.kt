package com.example.sportapp.models

import java.io.Serializable

data class DetallePlan(
    val id:String,
    val idDeportista:String,
    val idDetallePlan:String,
    val numdia: String,
    val marcaStreet: String,
    val estado: String,
    val fechaInicio: String,
    val fechaFin: String,
    val calorias: String,
    val velocidadMaxima: String,
    val distanciaRecorrida: String,
    val AsignarPlan: AsignarPlan,
): Serializable

data class AsignarPlan (
    val id:String,
    val idDeportista:String,
    val fechaInicio:String,
    val fechaFin: String,
    val estado: String,
): Serializable