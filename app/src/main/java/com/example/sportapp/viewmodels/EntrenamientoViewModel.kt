package com.example.sportapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.sportapp.models.AsignarDetallePlan
import com.example.sportapp.repositories.EntrenamientoRepository

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

        Log.i("ListCollector", "llego a consultar collector")
        entrenamientoRepositoryObject.refreshData({
            _entrenamiento.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
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