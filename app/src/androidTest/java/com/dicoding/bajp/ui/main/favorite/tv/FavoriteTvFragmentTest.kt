package com.dicoding.bajp.ui.main.favorite.tv

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.dicoding.bajp.R
import com.dicoding.bajp.ui.main.favorite.movie.FavoriteMovieFragment
import org.junit.Test

class FavoriteTvFragmentTest {

    @Test
    fun favoriteMovieFragment() {
        launchFragmentInContainer<FavoriteMovieFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.favorite_recycler)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            perform(ViewActions.swipeLeft())
            perform(ViewActions.swipeRight())
        }
    }
}