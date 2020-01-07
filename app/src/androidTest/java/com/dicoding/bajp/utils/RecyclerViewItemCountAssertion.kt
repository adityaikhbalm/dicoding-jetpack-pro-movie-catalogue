package com.dicoding.bajp.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import junit.framework.Assert.assertNotNull
import org.hamcrest.core.Is.`is`

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val rv = view as RecyclerView
        val adapter = rv.adapter
        assertNotNull(adapter)
        assertThat(adapter!!.itemCount, `is`(expectedCount))
    }
}