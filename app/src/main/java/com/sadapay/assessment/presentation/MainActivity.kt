package com.sadapay.assessment.presentation

import android.app.UiModeManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.sadapay.R
import com.sadapay.assessment.data.PrefsManager
import com.sadapay.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var preferenceStorage: PrefsManager
    private var isDarkMode : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViews()
    }

    private fun setViews() {
        setSupportActionBar(binding.toolbarTop)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        lifecycleScope.launch {
            val isDarkMode = preferenceStorage.getDarkMode()

            AppCompatDelegate.setDefaultNightMode(
                if (isDarkMode)
                    AppCompatDelegate.MODE_NIGHT_YES
                else
                    AppCompatDelegate.MODE_NIGHT_NO
            )

//            if (isDarkMode) {
//                setTheme(R.style.AppThemeLight)
//            } else {
//                setTheme(R.style.AppThemeDark)
//            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_theme, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_light -> {
                if (isDarkMode){
                    lifecycleScope.launch {
                        preferenceStorage.setDarkMode(isDarkMode = false)
                    }

                    recreate()
                }

                true
            }

            R.id.menu_dark -> {

                if (!isDarkMode){
                    lifecycleScope.launch {
                        preferenceStorage.setDarkMode(isDarkMode = true)
                    }
                    recreate()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}