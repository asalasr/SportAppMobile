package com.example.sportapp.models

import java.io.Serializable


data class AsignarDetallePlan(
    val ids:String,
    val idDeportista:String,
    val idDetallePlan:String,
    val numDia:Int,
    val marcaStreet:String,
    val estado:String,
    val fechaInicio: String,
    val fechaFin: String,
    val calorias: Int,
    val velocidadMaxima: Int,
    val distanciaRecorrida:Int,
    val idAsignarPlanId:String
): Serializable