package com.sadapay.assessment.presentation.fragments

import androidx.test.espresso.matcher.ViewMatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GitHubListFragmentTest {

    @Inject
    lateinit var someString: String

    @Before
    fun init() {
        someString = "this is a test string"
    }

    @Test
    fun hiltTest() {
        ViewMatchers.assertThat(someString, CoreMatchers.containsString("test string"))
    }


}


