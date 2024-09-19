package com.sena.matsystemandroidview.Services

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sena.MatSystemAndoidView.Recycler.Generic.Adapter.GenericAdapter
import com.sena.matsystemandroidview.Config.Urls
import com.sena.matsystemandroidview.R
import com.sena.matsystemandroidview.Recycler.Specific.Holder.HolderVideo

class SMultimedia {
    companion object {

        private lateinit var view: View
        private lateinit var context: Context
        private lateinit var fragment :FragmentManager


        val holder = { view: View,fragmentManager: FragmentManager -> HolderVideo/*Holder*/(view,fragmentManager) }
        val url = Urls.urlMultimedia//URL
        val layout = R.layout.item_video//Item


        fun initView(view: View, context: Context?,fragment :FragmentManager) {
            this.view = view
            this.context = context!!
            this.fragment = fragment
        }


        fun cargar() {
            try {
                val request = JsonObjectRequest(
                    Request.Method.GET,//metodo
                    "$url/all",//url
                    null,//parametros
                    { response ->//respuesta correcta
                        val data = response.getJSONArray("data")
                        println(data)

                        val recycler: RecyclerView = view.findViewById(R.id.RecyclerVideo/*Recycler*/)
                        recycler.layoutManager = LinearLayoutManager(context)
                        val adapter = object :
                            GenericAdapter<HolderVideo>(data, holder,layout,fragment){}
                        recycler.adapter = adapter

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