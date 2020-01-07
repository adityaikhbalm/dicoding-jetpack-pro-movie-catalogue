package com.dicoding.bajp.ui.main.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.bajp.R
import com.dicoding.bajp.ui.main.BaseNavigationTest
import com.dicoding.bajp.utils.RecyclerViewItemCountAssertion
import org.junit.Test

class MovieFragmentTest : BaseNavigationTest() {

    override fun destination(): Int {
        return R.id.navigation_movie
    }

    @Test
    fun movieFragment() {
        activityRule.launchActivity(navigator)
        onView(withId(R.id.movie_tv_recycler)).apply {
            check(matches(isDisplayed()))
            check(RecyclerViewItemCountAssertion(20))
        }
    }
}