package com.dicoding.bajp.ui.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.bajp.R
import com.dicoding.bajp.utlis.Simplify.convertDpToPixel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    private var flagPast: Int = 1
    private var actionBarSize: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavigationMenu()
        setActionBarSize()
        setSelectedMenu()
    }

    private fun setNavigationMenu() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movie,
                R.id.navigation_tv,
                R.id.navigation_favorite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setActionBarSize() {
        actionBarSize = convertDpToPixel(60f)
    }

    private fun setSelectedMenu() {
        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_movie -> {
                    bottomMenuAnimate(1)
                    flagPast = 1
                }
                R.id.navigation_tv -> {
                    bottomMenuAnimate(2)
                    flagPast = 2
                }
                R.id.navigation_favorite -> {
                    bottomMenuAnimate(3)
                    flagPast = 3
                }
                else -> null
            } != null
        }
    }

    private fun bottomMenuAnimate(flagCurrent: Int) {
        if (flagPast == flagCurrent) return

        setMenuAnimate(300, 0)

        val timer = 400 + (100 * (flagCurrent - flagPast).absoluteValue)

        Handler().postDelayed({
            setMenuAnimate(500, actionBarSize)
        }, timer.toLong())
    }

    private fun setMenuAnimate(time: Long, size: Int) {
        ValueAnimator.ofInt(my_light.measuredHeight, size).apply {
            addUpdateListener { valueAnimator ->
                val layoutParams = my_light.layoutParams
                layoutParams.height = valueAnimator.animatedValue as Int
                my_light.layoutParams = layoutParams
            }
            duration = time
            start()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("position", flagPast)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        flagPast = savedInstanceState.getInt("position")
    }
}
