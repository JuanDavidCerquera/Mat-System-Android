package com.sena.matsystemandroidview.Recycler.Specific.Holder

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.sena.matsystemandroidview.FragmentDocument
import com.sena.matsystemandroidview.R
import com.sena.matsystemandroidview.Recycler.Generic.Holder.GenericHolder
import org.json.JSONObject

class HolderDocument(view : View, private val fragmentManager: FragmentManager): GenericHolder(view) {
//    val card = view.findViewById<CardView>(R.id.cardDoc)

   val  webView: WebView = view.findViewById(R.id.webView);
    init {
        // Configurar WebView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
    }
    override fun render(data: JSONObject) {
        val base64PDF = data.getString("documento") // Reemplaza con tu cadena Base64 real

        if (base64PDF.isNotEmpty()) {
            // Decodificar y cargar el PDF en el WebView
            val pdf = "data:application/pdf;base64,$base64PDF"
            webView.loadDataWithBaseURL(
                null,
                "<iframe width='100%' height='100%' src='$pdf'></iframe>",
                "text/html", "UTF-8", null
            )
        }



//        card.setOnClickListener(){
//            fragmentManager
//                .beginTransaction()
//                .replace(R.id.fragmentContainerView, FragmentDocument())
//                .addToBackStack(null)
//                .commit()
//        }
    }
}