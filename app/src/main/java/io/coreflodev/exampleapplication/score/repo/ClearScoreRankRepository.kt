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
                    min = it.creditReportInfo.minScoreValue,
                    current = it.creditReportInfo.score
                )
            }

}
