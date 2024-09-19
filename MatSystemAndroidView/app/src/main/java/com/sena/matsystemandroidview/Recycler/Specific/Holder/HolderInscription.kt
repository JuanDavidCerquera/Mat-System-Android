package com.sena.matsystemandroidview.Recycler.Specific.Holder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.sena.matsystemandroidview.FragmentInscriptionList
import com.sena.matsystemandroidview.FragmentInscriptionState
import com.sena.matsystemandroidview.R
import com.sena.matsystemandroidview.Recycler.Generic.Holder.GenericHolder
import org.json.JSONObject

class HolderInscription(view : View, private val fragmentManager: FragmentManager): GenericHolder(view) {
val b = view.findViewById<TextView>(R.id.textView3)
    override fun render(data: JSONObject) {
        b.setOnClickListener{
            fragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentInscriptionState())
                .addToBackStack(null)
                .commit()
        }
    }
}