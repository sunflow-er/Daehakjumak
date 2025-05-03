package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource
import kotlinx.coroutines.flow.Flow

interface SetJumakOpenStatusUseCase {
    operator fun invoke(status: Boolean): Flow<DataResource<Unit>>
}