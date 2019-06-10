package io.coreflodev.exampleapplication.score.repo

import io.coreflodev.exampleapplication.common.network.ClearScoreApi
import io.reactivex.Observable

class ClearScoreRankRepository(private val clearScoreApi: ClearScoreApi) : RankRepository {

    override fun getCreditScoreInfos(): Observable<RankRepository.CreditScoreInfo> =
        clearScoreApi.getCredit()
            .toObservable()
            .map {
                RankRepository.CreditScoreInfo(
                    max = it.creditReportInfo.maxScoreValue,
                    current = it.creditReportInfo.score,
                    percent =  ((it.creditReportInfo.score - it.creditReportInfo.minScoreValue) * 100) / (it.creditReportInfo.maxScoreValue - it.creditReportInfo.minScoreValue)
                )
            }

}
