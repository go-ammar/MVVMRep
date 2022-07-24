package com.sadapay.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Singleton


@RunWith(AndroidJUnit4::class)
class TestCase {

    @Inject
    lateinit var someString: String




    @Before
    fun setUp() {
        someString = "Test string"
    }


    @Test
    fun hiltTest() {
        Assert.assertEquals(someString.isNotEmpty(), true)
    }

    @Test
    fun isAvailable() {
//        val x = viewModel.repos.value.toString()
//        Assert.assertEquals(x.isNotEmpty(), true)
    }

}