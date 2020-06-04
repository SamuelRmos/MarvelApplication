package com.example.desafio_android_samuel_ramos.view.activity

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.desafio_android_samuel_ramos.R
import com.example.desafio_android_samuel_ramos.base.BaseUITest
import com.example.desafio_android_samuel_ramos.di.generateTestAppComponent
import com.example.desafio_android_samuel_ramos.helpers.recyclerItemAtPosition
import com.example.desafio_android_samuel_ramos.view.adapter.CharacterAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class MainActivityTest: BaseUITest() {

    //region constants

    //end region constants

    //region helper fields

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(
        MainActivity::class.java,
        true,
        false
    )

    private val mNameOne = "A.I.M."
    private val mDescriptionOne =  "AIM is a terrorist organization bent on destroying the world."

    private val mNameTwo = "A-Bomb (HAS)"
    private val mDescriptionTwo =  "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong"

    // end region helper fields


    @Before
    fun start() {
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()))
    }

    @Test
    fun test_recyclerView_elements_for_expected_response() {

        mActivityTestRule.launchActivity(null)
        mockNetworkResponseWithFileContent("data_list", HttpURLConnection.HTTP_OK)

        SystemClock.sleep(1000)

        onView(withId(R.id.recyclerView))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mNameOne))
                    )
                )
            )

//        onView(withId(R.id.recyclerView))
//            .check(
//                matches(
//                    recyclerItemAtPosition(
//                        0,
//                        ViewMatchers.hasDescendant(withText(mDescriptionOne))
//                    )
//                )
//            )

        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<CharacterAdapter.ViewHolder>(19)
        )

        onView(withId(R.id.recyclerView))
            .check(
                matches(
                    recyclerItemAtPosition(
                        19,
                        ViewMatchers.hasDescendant(withText(mNameTwo))
                    )
                )
            )
//
//        onView(withId(R.id.recyclerView))
//            .check(
//                matches(
//                    recyclerItemAtPosition(
//                        19,
//                        ViewMatchers.hasDescendant(withText(mDescriptionTwo))
//                    )
//                )
//            )

    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}