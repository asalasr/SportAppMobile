package com.example.sportapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.sportapp.models.AsignarDetallePlan
import com.example.sportapp.repositories.EntrenamientoRepository
import java.util.*

class EntrenamientoViewModel  (application: Application) :  AndroidViewModel(application) {


    //MUTABLES se utilizan para representar la fuente dinámica de datos
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    private var _Loanding = MutableLiveData<Boolean>(false)

    private val _entrenamiento = MutableLiveData<List<AsignarDetallePlan>>()

    //LIVE DATA representar el valor almacenado de los datos en un momento
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    val loanding: LiveData<Boolean>
        get() = _Loanding

    val entrenamientovm: LiveData<List<AsignarDetallePlan>>
        get() = _entrenamiento


    //Repository
    private var entrenamientoRepositoryObject = EntrenamientoRepository(application)


    //Funciones
    init {
        refreshDataFromNetwork()
    }

    //onNetwork
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    //Se encarga de consultar activamente la información de los modelos con el manejador de peticiones de red y actualizar los LiveData respectivos
    private fun refreshDataFromNetwork() {

        Log.i("EntrenamientoViewModel", "refreshData()")
        entrenamientoRepositoryObject.refreshData({
            _entrenamiento.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun putActualizarEntrenamiento(
        idAsignarPlanId: String,
        idDetallePlan: String,
        calorias: Double,
        estado: String,
        fechaFin: String,
        distanciaRecorrida: Double,
        fechaInicio: String,
        idDeportista: String,
        ids: String,
        marcaStreet: String,
        numDia: String,
        velocidadMaxima: Double,
        cbViewSuccess: (resp: Boolean) -> Unit,
        cbViewError: () -> Unit
    ) {
        Log.i("EntrenamientoViewModel", "putActualizarEntrenamiento - entro")
        val asignarDetallePlan = AsignarDetallePlan(
            idAsignarPlanId = idAsignarPlanId,
            idDetallePlan = idDetallePlan,
            calorias = calorias,
            estado = estado,
            fechaFin = fechaFin,
            distanciaRecorrida = distanciaRecorrida,
            fechaInicio = fechaInicio,
            idDeportista = idDeportista,
            ids = ids,
            marcaStreet = marcaStreet,
            numDia = numDia,
            velocidadMaxima = velocidadMaxima
        )

        Log.i("EntrenamientoViewModel", "ids: $ids")
        Log.i("EntrenamientoViewModel", "idDeportista: $idDeportista")
        Log.i("EntrenamientoViewModel", "fechaFin: $fechaFin")
        Log.i("EntrenamientoViewModel", "distanciaRecorrida: $distanciaRecorrida")
        Log.i("EntrenamientoViewModel", "fechaInicio: $fechaInicio")
        Log.i("EntrenamientoViewModel", "velocidadMaxima: $velocidadMaxima")

        entrenamientoRepositoryObject.putAsignarDetallePlan(asignarDetallePlan, {
            Log.i("EntrenamientoViewModel", "Return from Repository: $it")
            cbViewSuccess(it)
        }, {
            Log.i("EntrenamientoViewModel", "Error at Repository")
            cbViewError()
        })
    }


    //CLASS FACTORY crea la instancia del ViewModel
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EntrenamientoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EntrenamientoViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}