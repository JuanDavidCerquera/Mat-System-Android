package com.sena.MatSystemAndoidView.Recycler.Generic.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sena.matsystemandroidview.Recycler.Generic.Holder.GenericHolder
import org.json.JSONArray

abstract class GenericAdapter<T : GenericHolder>(var data:  JSONArray, private val viewHolderFactory: (View,FragmentManager) -> T, private val  viewItems : Int,private val fragmentManager: FragmentManager ) : RecyclerView.Adapter<T>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T  {
        val view = LayoutInflater.from(parent.context).inflate(viewItems, parent, false)
        return viewHolderFactory(view,fragmentManager)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        val item = data.getJSONObject(position)
        holder.render(item)
        // Enlaza los datos del libro con las vistas del ítem
    }

    override fun getItemCount(): Int {
        return data.length()
    }

}