package io.coreflodev.exampleapplication

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.coreflodev.exampleapplication.core.HttpServerRule
import io.coreflodev.exampleapplication.score.ui.ScoreActivity
import okhttp3.mockwebserver.MockResponse
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsTest {

    private val activity = ActivityTestRule<ScoreActivity>(ScoreActivity::class.java, false, false)
    private val httpServerRule = HttpServerRule()

    @get:Rule
    val rule: RuleChain = RuleChain.outerRule(activity)
        .around(httpServerRule)


    @Test
    fun givenDisplayStateReceived_WhenEverythingIsFine_thenDisplayStateIsShowed() {
        val expectedMessage = "a message"

        httpServerRule.mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(
                    """
{"accountIDVStatus":"PASS","creditReportInfo":{"score":514,"scoreBand":4,"clientRef":"CS-SED-655426-708782","status":"MATCH","maxScoreValue":700,"minScoreValue":0,"monthsSinceLastDefaulted":-1,"hasEverDefaulted":false,"monthsSinceLastDelinquent":1,"hasEverBeenDelinquent":true,"percentageCreditUsed":44,"percentageCreditUsedDirectionFlag":1,"changedScore":0,"currentShortTermDebt":13758,"currentShortTermNonPromotionalDebt":13758,"currentShortTermCreditLimit":30600,"currentShortTermCreditUtilisation":44,"changeInShortTermDebt":549,"currentLongTermDebt":24682,"currentLongTermNonPromotionalDebt":24682,"currentLongTermCreditLimit":null,"currentLongTermCreditUtilisation":null,"changeInLongTermDebt":-327,"numPositiveScoreFactors":9,"numNegativeScoreFactors":0,"equifaxScoreBand":4,"equifaxScoreBandDescription":"Excellent","daysUntilNextReport":9},"dashboardStatus":"PASS","personaType":"INEXPERIENCED","coachingSummary":{"activeTodo":false,"activeChat":true,"numberOfTodoItems":0,"numberOfCompletedTodoItems":0,"selected":true},"augmentedCreditScore":null}
            """.trimIndent()
                )
        )

        activity.launchActivity(Intent())


        onView(withText(expectedMessage)).check(matches(isDisplayed()))
    }
}
