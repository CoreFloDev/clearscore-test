package io.coreflodev.exampleapplication.score.use_cases

import io.coreflodev.exampleapplication.score.repo.RankRepository

sealed class Result {

    sealed class UiUpdate : Result() {

        data class Display(val data: RankRepository.CreditScoreInfo) : UiUpdate()

        object Error : UiUpdate()

        object Loading : UiUpdate()
    }
}
