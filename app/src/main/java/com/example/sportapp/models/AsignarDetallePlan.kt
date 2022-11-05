package com.example.sportapp.models

import java.io.Serializable


data class AsignarDetallePlan(
    val ids:String,
    val idDeportista:String,
    val idDetallePlan:String,
    val numDia:String,
    val marcaStreet:String,
    val estado:String,
    val fechaInicio: String,
    val fechaFin: String,
    val calorias: Double,
    val velocidadMaxima: Double,
    val distanciaRecorrida:Double,
    val idAsignarPlanId:String
): Serializable