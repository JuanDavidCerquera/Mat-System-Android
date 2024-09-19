package com.sena.matsystemandroidview.Recycler.Specific.Holder

import android.net.Uri
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.FragmentManager
import com.sena.matsystemandroidview.R
import com.sena.matsystemandroidview.Recycler.Generic.Holder.GenericHolder
import org.json.JSONObject

class HolderVideo(view : View, private val fragmentManager: FragmentManager): GenericHolder(view) {

    val webView: WebView = view.findViewById(R.id.webView)

    override fun render(data: JSONObject) {
        val videoId = data.getString("url")
        webView.loadData(videoId, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

    }
}