package com.dicoding.bajp.ui.main.favorite

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.bajp.R
import com.dicoding.bajp.ui.main.BaseNavigationTest
import org.junit.Test

class FavoriteFragmentTest : BaseNavigationTest() {

    override fun destination(): Int {
        return R.id.navigation_favorite
    }

    @Test
    fun favoriteFragment() {
        activityRule.launchActivity(navigator)
        onView(withId(R.id.tab_favorites)).apply {
            check(matches(isDisplayed()))
            perform(click())
        }
        onView(withId(R.id.category_favorite_viewpager)).apply {
            check(matches(isDisplayed()))
            perform(swipeLeft())
            perform(swipeRight())
        }
    }
}