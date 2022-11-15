package com.example.sportapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.sportapp.models.DetallePlan
import com.example.sportapp.repositories.DetallePlanRepository

class DetallePlanViewModel  (application: Application) :  AndroidViewModel(application) {

    //MUTABLES se utilizan para representar la fuente dinámica de datos
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    private var _Loanding = MutableLiveData<Boolean>(false)

    private val _evento = MutableLiveData<List<DetallePlan>>()

    //LIVE DATA representar el valor almacenado de los datos en un momento
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    val loanding: LiveData<Boolean>
        get() = _Loanding

    val eventovm: LiveData<List<DetallePlan>>
        get() = _evento


    //Repository
    private var DetallePlanRepositoryObject = DetallePlanRepository(application)


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

        Log.i("ListCollector", "llego a consultar collector")
        DetallePlanRepositoryObject.refreshDataDetallePlan({
            _evento.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    //CLASS FACTORY crea la instancia del ViewModel
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetallePlanViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetallePlanViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}