package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource
import kotlinx.coroutines.flow.Flow

interface RemoveTableUseCase {
    operator fun invoke(tableId: String): Flow<DataResource<Unit>>
}