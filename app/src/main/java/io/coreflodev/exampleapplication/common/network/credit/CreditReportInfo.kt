package io.coreflodev.exampleapplication.common.network.credit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditReportInfo(
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val score: Int
)
