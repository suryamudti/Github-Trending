package com.surya.githubtrending.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.surya.githubtrending.R
import com.surya.githubtrending.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by suryamudti on 15/11/2019.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)


    @Before fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test fun scrollAndClickTest(){
        onView(ViewMatchers.withId(R.id.recycler_view_trend)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.recycler_view_trend)).perform(ViewActions.swipeUp())
        onView(ViewMatchers.withId(R.id.recycler_view_trend)).perform(ViewActions.swipeDown())
        onView(ViewMatchers.withId(R.id.recycler_view_trend)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(1,click()))
        onView(ViewMatchers.withId(R.id.recycler_view_trend)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(3,click()))
    }

    @Test fun sortByStarsTest(){
        onView(ViewMatchers.withId(R.id.recycler_view_trend)).check(matches(isDisplayed()))
        Espresso.openContextualActionModeOverflowMenu()
        onView(ViewMatchers.withText("Sort by stars")).perform(click())
    }

    @Test fun sortByNameTest(){
        onView(ViewMatchers.withId(R.id.recycler_view_trend)).check(matches(isDisplayed()))
        Espresso.openContextualActionModeOverflowMenu()
        onView(ViewMatchers.withText("Sort by name")).perform(click())
    }

    @Test fun swipeRefreshTest(){
        onView(ViewMatchers.withId(R.id.recycler_view_trend)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.swipe_refresh)).perform(ViewActions.swipeDown())
    }

}