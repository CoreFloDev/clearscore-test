package io.coreflodev.exampleapplication.score

import io.coreflodev.exampleapplication.common.arch.Screen
import io.coreflodev.exampleapplication.score.use_cases.Action
import io.coreflodev.exampleapplication.score.use_cases.DisplayScoreUseCase
import io.coreflodev.exampleapplication.score.use_cases.Result
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class ScoreScreen(
    private val displayScoreUseCase: DisplayScoreUseCase
) : Screen<ScoreInput, ScoreOutput>() {

    override fun output(): Observable<ScoreOutput> =
        input.compose(convertInputToAction())
            .publish {
                it.ofType(Action.InitialAction::class.java).compose(displayScoreUseCase())
            }
            .compose(convertResultToOutput())

    companion object {
        fun convertInputToAction() = ObservableTransformer<ScoreInput, Action> { observable ->
            observable
                .map {
                    when (it) {
                        ScoreInput.Retry -> Action.InitialAction
                    } as Action
                }
                .startWith(Action.InitialAction)
        }

        fun convertResultToOutput() = ObservableTransformer<Result, ScoreOutput> { observable ->
            observable.ofType(Result.UiUpdate::class.java)
                .compose(reducingUiState())
                .replay(1)
                .autoConnect()
        }

        fun reducingUiState() = ObservableTransformer<Result.UiUpdate, ScoreOutput> { observable ->
            observable.map { uiState ->
                when (uiState) {
                    Result.UiUpdate.Error -> ScoreOutput.Error
                    is Result.UiUpdate.Display -> ScoreOutput.Display(
                        data = ScoreViewModel(
                            percent = uiState.data.percent,
                            maxScore = uiState.data.max,
                            currentScore = uiState.data.current
                        )
                    )
                    Result.UiUpdate.Loading -> ScoreOutput.Loading
                }
            }
        }
    }
}