package io.coreflodev.exampleapplication.score.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.coreflodev.exampleapplication.R
import io.coreflodev.exampleapplication.common.arch.Screen
import io.coreflodev.exampleapplication.common.arch.ScreenView
import io.coreflodev.exampleapplication.score.ScoreInput
import io.coreflodev.exampleapplication.score.ScoreOutput
import io.coreflodev.exampleapplication.score.injection.ScoreStateHolder
import kotlinx.android.synthetic.main.activity_score.*
import javax.inject.Inject

class ScoreActivity : AppCompatActivity(), ScreenView<ScoreInput, ScoreOutput> {

    override fun render(output: ScoreOutput) {
        when (output) {
            is ScoreOutput.Display -> {
                error_score_activity.visibility = GONE
                loading_score_activity.visibility = GONE
                content_score_activity.visibility = VISIBLE
                percent_score_activity.setPercent(output.data.percent)
                total_txt_score_activity.text = resources.getString(R.string.max_score, output.data.maxScore)
                score_txt_score_activity.text = output.data.currentScore.toString()
            }
            ScoreOutput.Loading -> {
                error_score_activity.visibility = GONE
                content_score_activity.visibility = GONE
                loading_score_activity.visibility = VISIBLE
            }
            ScoreOutput.Error -> {
                loading_score_activity.visibility = GONE
                content_score_activity.visibility = GONE
                error_score_activity.visibility = VISIBLE
            }
        }
    }

    @Inject
    lateinit var screen: Screen<ScoreInput, ScoreOutput>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_score)

        ViewModelProviders.of(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(ScoreStateHolder::class.java)
            .scoreComponent
            .inject(this)

        screen.attach(this)
    }

    override fun onDestroy() {
        screen.detach()
        super.onDestroy()
    }
}
