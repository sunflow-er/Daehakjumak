package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.Coordinate
import com.masonk.daehakjumak.core.dataresource.DataResource
import kotlinx.coroutines.flow.Flow

interface UpdateTableCoordUseCase {
    operator fun invoke(tableId: String, coordinate: Coordinate): Flow<DataResource<Unit>>
}