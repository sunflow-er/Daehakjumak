package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Table
import kotlinx.coroutines.flow.Flow

interface AddTableUseCase {
   operator fun invoke(table: Table): Flow<DataResource<Unit>>
}