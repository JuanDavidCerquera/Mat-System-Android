package com.sena.matsystemandroidview.Recycler.Specific.Service

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sena.MatSystemAndoidView.Recycler.Generic.Adapter.GenericAdapter
import com.sena.matsystemandroidview.R
import com.sena.matsystemandroidview.Recycler.Specific.Holder.HolderInscription
import org.json.JSONArray
import org.json.JSONObject

class SInscription(val view: View, val context: Context, val fragment: FragmentManager) {
    fun renderRecycler(){
        val holder ={view: View,fragmentManager: FragmentManager -> HolderInscription(view,fragmentManager) }
        // Inicializar el JSONArray
        val data = JSONArray()

        // Crear y agregar JSONObject al JSONArray
        for (i in 0 until 4) {
            val json = JSONObject()
            json.put("Inscription", "juan")
            data.put(json)
        }

        val recyclerInscription = view.findViewById<RecyclerView>(R.id.RecyclerInscription)
        recyclerInscription.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = object : GenericAdapter<HolderInscription>(data,holder, R.layout.item_inscription,fragment){}
        recyclerInscription.adapter =adapter


    }
}