package com.sadapay.assessment.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sadapay.R
import com.sadapay.assessment.di.AppModule.KEY_PREF_THEME
import com.sadapay.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @set:Inject
    internal var sharedPreferences: SharedPreferences? = null

    lateinit var binding: ActivityMainBinding

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

        if (sharedPreferences?.getBoolean(KEY_PREF_THEME, false) == true) {
            setTheme(R.style.AppThemeDark)
        } else {
            setTheme(R.style.AppThemeLight)

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
                if (sharedPreferences?.getBoolean(KEY_PREF_THEME, false) == true) {

                    sharedPreferences?.edit()
                        ?.putBoolean(
                            KEY_PREF_THEME,
                            false
                        )?.apply()

                    recreate()
                }
                true
            }

            R.id.menu_dark -> {

                if (sharedPreferences?.getBoolean(KEY_PREF_THEME, false) == false) {
                    sharedPreferences?.edit()
                        ?.putBoolean(
                            KEY_PREF_THEME,
                            true
                        )?.apply()
                    recreate()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}