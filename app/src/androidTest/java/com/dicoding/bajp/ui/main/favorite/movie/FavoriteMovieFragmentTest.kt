package com.dicoding.bajp.ui.main.favorite.movie

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.bajp.R
import org.junit.Test

class FavoriteMovieFragmentTest {

    @Test
    fun favoriteMovieFragment() {
        launchFragmentInContainer<FavoriteMovieFragment>()
        onView(withId(R.id.favorite_recycler)).apply {
            check(matches(isDisplayed()))
            perform(ViewActions.swipeLeft())
            perform(ViewActions.swipeRight())
        }
    }
}