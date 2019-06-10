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
        error_score_activity.visibility = GONE
        loading_score_activity.visibility = GONE

        when (output) {
            is ScoreOutput.Display -> {

            }
            ScoreOutput.Loading -> loading_score_activity.visibility = VISIBLE
            ScoreOutput.Error -> error_score_activity.visibility = VISIBLE
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
