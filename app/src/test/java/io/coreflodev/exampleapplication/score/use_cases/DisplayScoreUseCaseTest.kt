package io.coreflodev.exampleapplication.score.use_cases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.coreflodev.exampleapplication.score.repo.RankRepository
import io.reactivex.Observable
import org.junit.Test

class DisplayScoreUseCaseTest {

    private val repo: RankRepository = mock()
    private val useCase = DisplayScoreUseCase(repo)

    @Test
    fun `given initial action received when repo is empty then a loading state is received`() {
        whenever(repo.getCreditScoreInfos()).thenReturn(Observable.empty())

        Observable.just(Action.InitialAction)
            .compose(useCase())
            .test()
            .assertValue(Result.UiUpdate.Loading)
    }

    @Test
    fun `given initial action is received when repo return an error then a loading and an error state is received`() {
        whenever(repo.getCreditScoreInfos()).thenReturn(Observable.error(Throwable()))

        Observable.just(Action.InitialAction)
            .compose(useCase())
            .test()
            .assertValues(Result.UiUpdate.Loading, Result.UiUpdate.Error)
    }

    @Test
    fun `given initial action is received when repo return a result then a loading and a display state is returned`() {
        whenever(repo.getCreditScoreInfos()).thenReturn(Observable.just(A_SCORE))

        Observable.just(Action.InitialAction)
            .compose(useCase())
            .test()
            .assertValues(Result.UiUpdate.Loading, Result.UiUpdate.Display(A_SCORE))
    }

    companion object {

        private val A_SCORE = RankRepository.CreditScoreInfo(
            min = 1,
            max = 2,
            current = 3
        )
    }
}