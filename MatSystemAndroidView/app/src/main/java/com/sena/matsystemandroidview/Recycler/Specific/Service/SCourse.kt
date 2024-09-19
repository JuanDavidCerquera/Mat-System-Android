package com.sena.matsystemandroidview.Recycler.Specific.Service

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sena.MatSystemAndoidView.Recycler.Generic.Adapter.GenericAdapter
import com.sena.matsystemandroidview.R
import com.sena.matsystemandroidview.Recycler.Animations.AnimationCoverFlow
import com.sena.matsystemandroidview.Recycler.Specific.Holder.HolderCourse
import org.json.JSONArray
import org.json.JSONObject

class SCourse(val view: View, val context: Context, val fragment: FragmentManager) {

    fun renderRecycler(){
        val holder ={view: View,fragmentManager: FragmentManager -> HolderCourse(view,fragmentManager) }
        // Inicializar el JSONArray
        val data = JSONArray()

        // Crear y agregar JSONObject al JSONArray
        for (i in 0 until 10) {
            val json = JSONObject()
            json.put("course", "ADSO")
            data.put(json)
        }

        val recyclerCourse = view.findViewById<RecyclerView>(R.id.RecyclerCourseList)
        recyclerCourse.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = object : GenericAdapter<HolderCourse>(data,holder, R.layout.item_course,fragment){}
        recyclerCourse.adapter =adapter

       // AnimationCoverFlow.animationHorizontal(recyclerCourse)

    }
}