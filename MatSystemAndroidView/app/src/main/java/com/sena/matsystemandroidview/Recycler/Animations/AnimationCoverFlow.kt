package com.sena.matsystemandroidview.Recycler.Animations

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class AnimationCoverFlow {
    companion object {

        fun animationVertical(recycler: RecyclerView) {
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recycler)

            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val center = recyclerView.height / 2
                    val d0 = 0.0f
                    val d1 = 0.9f * center
                    val mScaleMin = 0.5f // Cambia este valor para hacer que los elementos más alejados se vean más pequeños
                    val mScaleMax = 1.0f

                    for (i in 0 until recyclerView.childCount) {
                        val child = recyclerView.getChildAt(i)
                        val childCenter = (child.top + child.bottom) / 2
                        val distanceFromCenter = Math.abs(center - childCenter).toFloat()

                        // Calcula la escala
                        val scale = mScaleMin + (mScaleMax - mScaleMin) * (1.0f - Math.min(d1, distanceFromCenter) / d1)

                        // Aplica la escala
                        child.scaleX = scale
                        child.scaleY = scale
2
                        // Calcula la rotación en el eje Y
                        val rotationY = (center - childCenter) * 0.1f // Ajusta el factor para más o menos rotación
                        child.rotationX = rotationY
                    }
                }
            })
        }



        fun animationHorizontal(recycler: RecyclerView) {
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recycler)

            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val center = recyclerView.width / 2
                    val d0 = 0.0f
                    val d1 = 0.9f * center
                    val mScaleMin = 0.5f // Cambia este valor para hacer que los elementos más alejados se vean más pequeños
                    val mScaleMax = 1.0f

                    for (i in 0 until recyclerView.childCount) {
                        val child = recyclerView.getChildAt(i)
                        val childCenter = (child.left + child.right) / 2
                        val distanceFromCenter = Math.abs(center - childCenter).toFloat()

                        // Calcula la escala
                        val scale = mScaleMin + (mScaleMax - mScaleMin) * (1.0f - Math.min(d1, distanceFromCenter) / d1)

                        // Aplica la escala
                        child.scaleX = scale
                        child.scaleY = scale

                        // Calcula la rotación en el eje Y
                        val rotationY = (center - childCenter) * 0.1f // Ajusta el factor para más o menos rotación
                        child.rotationY = rotationY
                    }
                }
            })
        }
    }
}