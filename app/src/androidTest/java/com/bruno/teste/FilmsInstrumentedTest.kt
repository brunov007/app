package com.bruno.teste

import android.content.Context
import android.net.wifi.WifiManager
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.bruno.teste.core.utils.EspressoIdlingResource
import com.bruno.teste.view.activities.MainActivity
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
class FilmsInstrumentedTest {

    @Rule
    @JvmField
    var mLoginOptionsActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        //ApplicationProperties.setMockEnabledToTests("true")
        Intents.init();
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun after() {
        //ApplicationProperties.setMockEnabledToTests("false")
        Intents.release()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    //DEFAULT
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.bruno.teste", appContext.packageName)
    }

    @Test
    fun test_recyclerIsScrollabe(){
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(RecyclerViewChildActions.actionOnChild(
                scrollTo()!!,
                2
            ))
    }

    @Test
    fun test_clickOnFirstItemShouldDisplayInfoAboutFilm(){
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(RecyclerViewChildActions.actionOnChild(
                clickButton()!!,
                1
            ))

        Espresso.onView(ViewMatchers.withId(R.id.card))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_clickFirstItemOfRecycler(){
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(RecyclerViewChildActions.actionOnChild(
                clickButton()!!,
                4
            ))
    }

    @Test
    fun test_isNetworkRunning(){
        if(!checkConnection()){
            Assert.fail("Sem Conexão com a internet")
        }

        Assert.assertTrue(true)
    }

    @Test
    fun test_isFirstFragmentVisible_onAppLaunch(){
            Espresso.onView(
                AllOf.allOf(
                    ViewMatchers.withId(R.id.recyclerView),
                    ViewMatchers.isDisplayed()
                )
            )
    }

    private fun scrollTo(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View?>? {
                return ViewMatchers.isEnabled()
            }

            override fun getDescription(): String? {
                return "Click Plus Button"
            }

            override fun perform(uiController: UiController?, view: View) {
                view.scrollTo(0, 10)
            }
        }
    }

    private fun clickButton(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View?>? {
                return ViewMatchers.isEnabled()
            }

            override fun getDescription(): String? {
                return "Click Plus Button"
            }

            override fun perform(uiController: UiController?, view: View) {
                view.performClick()
            }
        }
    }

    private fun checkConnection() : Boolean{
        return getWifi().isWifiEnabled
    }

    private fun getWifi(): WifiManager {
        return mLoginOptionsActivityTestRule.activity.applicationContext
            .getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    fun getResourceString(id: Int): String? {
        val targetContext: Context = mLoginOptionsActivityTestRule.activity.applicationContext
        return targetContext.resources.getString(id)
    }
}