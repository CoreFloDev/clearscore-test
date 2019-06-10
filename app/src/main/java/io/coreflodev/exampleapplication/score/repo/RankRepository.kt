package io.coreflodev.exampleapplication.score.repo

import io.reactivex.Observable

interface RankRepository {

    fun getCreditScoreInfos(): Observable<CreditScoreInfo>

    data class CreditScoreInfo(
        val max: Int,
        val min: Int,
        val current: Int
    )
}
