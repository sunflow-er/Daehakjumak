package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.Coordinate
import com.masonk.daehakjumak.core.dataresource.DataResource

interface UpdateTableCoordUseCase {
    suspend operator fun invoke(tableId: String, coordinate: Coordinate): DataResource<Unit>
}