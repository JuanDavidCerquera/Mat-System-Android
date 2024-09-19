package com.sena.matsystemandroidview.Recycler.Specific.Service

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sena.MatSystemAndoidView.Recycler.Generic.Adapter.GenericAdapter
import com.sena.matsystemandroidview.R
import com.sena.matsystemandroidview.Recycler.Specific.Holder.HolderDocument
import org.json.JSONArray
import org.json.JSONObject

class SDocument(val view: View, val context: Context, val fragment: FragmentManager) {
    fun renderRecycler(){
        val holder ={view: View,fragmentManager: FragmentManager -> HolderDocument(view,fragmentManager) }
        // Inicializar el JSONArray
        val data = JSONArray()

        // Crear y agregar JSONObject al JSONArray
        for (i in 0 until 4) {
            val json = JSONObject()
            json.put("Document", "juan")
            data.put(json)
        }

        val recyclerDocument = view.findViewById<RecyclerView>(R.id.RecyclerDocument)
        recyclerDocument.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = object : GenericAdapter<HolderDocument>(data,holder, R.layout.item_document,fragment){}
        recyclerDocument.adapter =adapter


    }
}