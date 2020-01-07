package com.dicoding.bajp.ui.main

import android.content.Intent
import androidx.navigation.NavDeepLinkBuilder
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.dicoding.bajp.R
import com.dicoding.bajp.utlis.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseNavigationTest {

    @get:Rule
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    internal lateinit var navigator: Intent

    @Before
    fun init() {
        navigator = NavDeepLinkBuilder(InstrumentationRegistry.getInstrumentation().targetContext)
            .setGraph(R.navigation.mobile_navigation)
            .setComponentName(MainActivity::class.java)
            .setDestination(destination())
            .createTaskStackBuilder().intents[0]

        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    abstract fun destination(): Int
}