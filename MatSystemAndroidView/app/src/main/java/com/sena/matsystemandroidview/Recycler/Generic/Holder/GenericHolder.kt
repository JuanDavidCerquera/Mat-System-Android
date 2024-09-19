package com.sena.matsystemandroidview.Recycler.Generic.Holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject


abstract class GenericHolder(view : View): RecyclerView.ViewHolder(view) {
    open fun render(data: JSONObject){
}

}
