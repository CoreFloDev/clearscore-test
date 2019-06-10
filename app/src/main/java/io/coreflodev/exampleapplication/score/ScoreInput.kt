package io.coreflodev.exampleapplication.score

import io.coreflodev.exampleapplication.common.arch.ScreenInput


sealed class ScoreInput : ScreenInput {
    object Retry: ScoreInput()
}