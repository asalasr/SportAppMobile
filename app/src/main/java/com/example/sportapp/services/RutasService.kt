package com.example.sportapp.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportapp.models.Rutas
import org.json.JSONArray
import org.json.JSONObject

class RutasService constructor(context: Context)  {

    companion object{
        const val BASE_URL= "https://trainer-date-v2.azurewebsites.net/api/v1/"
        var instance: RutasService? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: RutasService(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getRutas(onComplete:(resp:List<Rutas>)->Unit, onError: (error: VolleyError)->Unit) {
        Log.i("RutasService","Llego getRutas")

        requestQueue.add(getRequest("routes",
            Response.Listener<String> { response ->
                Log.d("tagb", response)

                val resp = JSONArray(response)
                val list = mutableListOf<Rutas>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)

                    list.add(i, Rutas(
                        ids = item.getString("id"),
                        title = item.getString("title"),
                        linkGoogle = item.getString("link_google"),
                        urlImage = item.getString("url_image")
                        )
                    )
                }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
                Log.d("Error get Rutas", it.message.toString())
            },
        ))

    }


    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
        /*val stringRequest = object: StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["user_id"] = "9920d31e-efa4-4b35-8d28-762bea2b6610"
                return headers
            }
        }
        return stringRequest*/
    }

    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}