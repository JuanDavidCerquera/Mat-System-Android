package com.sena.matsystemandroidview.Services

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sena.matsystemandroidview.Config.Urls

class SMunicipio {
    companion object{
        val urlMunicipio = Urls.urlMunicipio
        lateinit var context: Context

        fun init(requiredContext: Context){
            context = requiredContext
        }

        fun CargarSelect(onComplete: (List<String>, HashMap<String, Int>) -> Unit)  {
            val listaNombres = mutableListOf<String>()
            val idMap = HashMap<String, Int>()
            try {

                val request = JsonObjectRequest(
                    Request.Method.GET,//metodo
                    "$urlMunicipio/all",//url
                    null,//parametros
                    { response ->//respuesta correcta
                        val data = response.getJSONArray("data")
                        for (i in 0 until data.length()) {
                            val item = data.getJSONObject(i) // Obtener cada objeto del array
                            val id = item.getInt("id") // Obtener el campo 'id'
                            val string = item.getString("nombre") // Obtener el campo 'nombre'
                            listaNombres.add(string)
                            idMap[string] = id
                        }
                        onComplete(listaNombres,idMap )
                    },
                    { error ->//respuesta es incorrecta o no es la respuesta esperada
                        Toast.makeText(context, "Se genero un error", Toast.LENGTH_SHORT).show()
                    })
                val queue = Volley.newRequestQueue(context)
                queue.add(request)
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                println(e)
            }
        }
    }
}