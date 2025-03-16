package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Jumak
import kotlinx.coroutines.flow.Flow

interface GetJumakInfoUseCase {
    operator fun invoke(): Flow<DataResource<Jumak>>
}