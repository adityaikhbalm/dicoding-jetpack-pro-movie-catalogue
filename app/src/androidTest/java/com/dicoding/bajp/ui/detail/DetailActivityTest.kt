package com.dicoding.bajp.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.dicoding.bajp.R
import com.dicoding.bajp.data.remote.response.ApiResponse
import com.dicoding.bajp.utlis.FakeDataDummy
import com.google.gson.Gson
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {

    private val dummyMovie =
        Gson().fromJson(FakeDataDummy.jsonDiscoverMovie, ApiResponse::class.java).results[0]
//    private val dummyTv =
//        Gson().fromJson(FakeDataDummy.jsonDiscoverTv, ApiResponse::class.java).results[0]

    @get:Rule
    val activityRuleMovie =
        object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val target = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(target, DetailActivity::class.java)
                dummyMovie.type = "movie"
                result.putExtra(DetailActivity.MOVIE_DATA, dummyMovie)
//            dummyTv.type = "tv"
//            result.putExtra(DetailActivity.MOVIE_DATA, dummyTv)
                return result
            }
        }

    @Test
    fun loadMovie() {
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dummyMovie.title)))

        onView(withId(R.id.detail_release)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_release)).check(matches(withText(dummyMovie.releaseDate)))
    }

//    @Test
//    fun loadTv() {
//        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
//        onView(withId(R.id.detail_title)).check(matches(withText(dummyMovie.name)))
//
//        onView(withId(R.id.detail_release)).check(matches(isDisplayed()))
//        onView(withId(R.id.detail_release)).check(matches(withText(dummyMovie.firstAirDate)))
//    }

    @Test
    fun loadMovieTv() {
        onView(withId(R.id.detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating)).check(matches(withText(dummyMovie.voteCount.toString())))

        onView(withId(R.id.detail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_language)).check(matches(withText(dummyMovie.originalLanguage)))

        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview)).check(matches(withText(dummyMovie.overview)))

        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.favorite_menu)).perform(click())
    }
}