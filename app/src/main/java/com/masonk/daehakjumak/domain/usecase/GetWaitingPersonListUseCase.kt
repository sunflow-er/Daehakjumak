package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.WaitingPerson
import kotlinx.coroutines.flow.Flow

interface GetWaitingPersonListUseCase {
    operator fun invoke(): Flow<DataResource<List<WaitingPerson>>>
}