package io.coreflodev.exampleapplication.score.use_cases

import io.coreflodev.exampleapplication.score.repo.RankRepository
import io.reactivex.ObservableTransformer

class DisplayScoreUseCase(private val repo: RankRepository) {

    operator fun invoke(): ObservableTransformer<Action.InitialAction, Result> = ObservableTransformer { observable ->
        observable.flatMap {
            repo.getCreditScoreInfos()
                .map { Result.UiUpdate.Display(it) as Result }
                .onErrorReturnItem(Result.UiUpdate.Error)
                .startWith(Result.UiUpdate.Loading)
        }
    }
}
