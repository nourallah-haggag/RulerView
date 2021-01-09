package com.rumbl.rumbl_pt.models

import com.rumbl.rumbl_pt.network.response.SessionsResponse

// used with recycler view in home screen to support multiple view models
interface IHomeScreenModel

data class LatestSessionsRequests(
    val sessions: List<SessionsResponse>
) : IHomeScreenModel

object HeaderItem : IHomeScreenModel