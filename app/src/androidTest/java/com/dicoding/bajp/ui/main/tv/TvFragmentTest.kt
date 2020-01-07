package com.dicoding.bajp.ui.main.tv

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.bajp.R
import com.dicoding.bajp.ui.main.BaseNavigationTest
import com.dicoding.bajp.utils.RecyclerViewItemCountAssertion
import org.junit.Test

class TvFragmentTest : BaseNavigationTest() {

    override fun destination(): Int {
        return R.id.navigation_tv
    }

    @Test
    fun tvFragment() {
        activityRule.launchActivity(navigator)
        onView(withId(R.id.movie_tv_recycler)).apply {
            check(matches(isDisplayed()))
            check(RecyclerViewItemCountAssertion(20))
        }
    }
}