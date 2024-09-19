package com.sena.matsystemandroidview.Recycler.Animation

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
                    val center = recyclerView.height / 1
                    for (i in 0 until recyclerView.childCount) {
                        val child = recyclerView.getChildAt(i)
                        val childCenter = (child.top + child.bottom)
                        val distanceFromCenter = Math.abs(center - childCenter)
                        val scale = 1 - (distanceFromCenter / (recyclerView.width.toFloat() * 2))
                        child.scaleX = scale
                        child.scaleY = scale
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
                    for (i in 0 until recyclerView.childCount) {
                        val child = recyclerView.getChildAt(i)
                        val childCenter = (child.left + child.right) / 2
                        val distanceFromCenter = Math.abs(center - childCenter)
                        val scale = 1 - (distanceFromCenter / (recyclerView.width.toFloat() * 2))
                        child.scaleX = scale
                        child.scaleY = scale
                    }
                }
            })
        }
    }
}