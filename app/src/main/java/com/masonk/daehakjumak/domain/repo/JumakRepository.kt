package com.masonk.daehakjumak.domain.repo

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Jumak
import kotlinx.coroutines.flow.Flow

interface JumakRepository {
    fun getJumakInfo(): Flow<DataResource<Jumak>>

    fun setJumakOpenClosedStatus(status: Boolean): Flow<DataResource<Unit>>
}