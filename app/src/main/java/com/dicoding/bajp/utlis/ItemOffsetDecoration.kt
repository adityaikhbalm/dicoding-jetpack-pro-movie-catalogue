package com.dicoding.bajp.utlis

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class ItemOffsetDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(Simplify.convertDpToPixel(10f), 0, 0, Simplify.convertDpToPixel(15f))
    }
}