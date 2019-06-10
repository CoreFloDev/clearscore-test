package io.coreflodev.exampleapplication.score

import io.coreflodev.exampleapplication.common.arch.ScreenOutput

sealed class ScoreOutput : ScreenOutput {

    data class Display(val data: ScoreViewModel) : ScoreOutput()

    object Loading : ScoreOutput()

    object Error : ScoreOutput()
}