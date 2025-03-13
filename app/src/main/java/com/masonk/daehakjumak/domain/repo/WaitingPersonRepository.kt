package com.masonk.daehakjumak.domain.repo

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.WaitingPerson
import kotlinx.coroutines.flow.Flow

interface WaitingPersonRepository {
    fun getWaitingPersonList(): Flow<DataResource<List<WaitingPerson>>>
}