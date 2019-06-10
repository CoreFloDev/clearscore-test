package io.coreflodev.exampleapplication.score.repo

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.coreflodev.exampleapplication.common.network.ClearScoreApi
import io.coreflodev.exampleapplication.common.network.credit.Credit
import io.coreflodev.exampleapplication.common.network.credit.CreditReportInfo
import io.reactivex.Single
import org.junit.Test

class ClearScoreRankRepositoryTest {

    private val mockApi: ClearScoreApi = mock()

    private val repo = ClearScoreRankRepository(mockApi)

    @Test
    fun `given score is requested when everything is fine then a score is returned`() {
        whenever(mockApi.getCredit()).thenReturn(Single.just(CREDIT))

        repo.getCreditScoreInfos()
            .test()
            .assertValue(
                RankRepository.CreditScoreInfo(
                    max = CREDIT_REPORT_INFO.maxScoreValue,
                    percent = CREDIT_REPORT_INFO.score,
                    current = CREDIT_REPORT_INFO.score
                )
            )
    }

    @Test
    fun `given score is requested when an error is happen then the error is returned`() {
        val expectedError = Throwable()
        whenever(mockApi.getCredit()).thenReturn(Single.error(expectedError))

        repo.getCreditScoreInfos()
            .test()
            .assertError(expectedError)
    }

    companion object {

        private val CREDIT_REPORT_INFO = CreditReportInfo(
            maxScoreValue = 100,
            minScoreValue = 0,
            score = 85
        )

        private val CREDIT = Credit(
            creditReportInfo = CREDIT_REPORT_INFO
        )
    }
}