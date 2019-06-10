package io.coreflodev.exampleapplication.score.repo

import io.reactivex.Observable

interface RankRepository {

    fun getCreditScoreInfos(): Observable<CreditScoreInfo>

    data class CreditScoreInfo(
        val max: Int,
        val current: Int,
        val percent: Int
    )
}
