package io.coreflodev.exampleapplication.common.network.credit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Credit(
    val creditReportInfo: CreditReportInfo
)