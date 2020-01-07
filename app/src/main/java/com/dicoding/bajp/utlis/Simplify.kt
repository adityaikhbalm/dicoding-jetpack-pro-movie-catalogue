package com.dicoding.bajp.utlis

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible

object Simplify {
    private val metrics = Resources.getSystem().displayMetrics

    fun convertDpToPixel(dp: Float) =
        (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()

    fun calculateNoOfColumns(columnWidthDp: Float): Int {
        val screenWidthDp = metrics.widthPixels / metrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }

    fun View.showIt() {
        this.isVisible = true
    }

    fun View.hideIt() {
        this.isVisible = false
    }

    fun Context.toastShow(message: Int) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun getYear(date: String) = date.substring(0, 4)
}