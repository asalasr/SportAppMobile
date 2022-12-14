package com.example.sportapp.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportapp.models.Calendario
import com.example.sportapp.models.Eventos
import com.example.sportapp.models.MisEventos
import org.json.JSONArray

class EventoService constructor(context: Context)  {
    companion object{
        const val BASE_URL= "https://gestorterceros.azurewebsites.net/api/v1/"
        var instance: EventoService? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: EventoService(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getEventos(onComplete:(resp:List<Eventos>)->Unit, onError: (error: VolleyError)->Unit) {
        Log.i("EventoService","LLEGO ENDPOINT getEventos")

        requestQueue.add(getRequest("events",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Eventos>()
                val listEvent = mutableListOf<Eventos>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Eventos(
                        id = item.getString("id"),
                        title = item.getString("title"),
                        body = item.getString("body"),
                        url_image = item.getString("url_image"),
                        datatime = item.getString("datatime"),
                        state = item.getString("state"),
                        )
                    )}
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
                Log.d("Error get Evento", it.message.toString())
            },
        ))
    }


    fun getCalendarioMisEventos(onComplete:(resp:List<Calendario>)->Unit, onError: (error: VolleyError)->Unit) {
        Log.i("EventoService","LLEGO ENDPOINT getCalendarioMisEventos")

        requestQueue.add(getRequest("events/me",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Calendario>()
                val listEvent = mutableListOf<MisEventos>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Calendario(
                        ids = item.getJSONObject("event").getString("id"),
                        fecha = item.getJSONObject("event").getString("datatime"),
                        actividad= "Evento",
                        detalle= item.getJSONObject("event").getString("title"),
                        state = item.getJSONObject("event").getString("state")

                    ))}
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
                Log.d("Error get Evento", it.message.toString())
            },
        ))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        //return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
        val stringRequest = object: StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["user_id"] = "9920d31e-efa4-4b35-8d28-762bea2b6610"
                return headers
            }
        }
        return stringRequest
    }

}