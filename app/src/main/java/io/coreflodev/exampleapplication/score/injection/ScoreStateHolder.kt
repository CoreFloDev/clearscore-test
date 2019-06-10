package io.coreflodev.exampleapplication.score.injection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.coreflodev.exampleapplication.common.ClearScoreApplication

class ScoreStateHolder(app: Application) : AndroidViewModel(app) {

    val scoreComponent: ScoreComponent =
        DaggerScoreComponent.builder()
            .applicationComponent(ClearScoreApplication.applicationComponent(app))
            .build()
}