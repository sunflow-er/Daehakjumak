package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.Coordinate
import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.repo.TableRepository
import com.masonk.daehakjumak.domain.usecase.UpdateTableCoordUseCase

class UpdateTableCoordUseCaseImpl(
    private val tableRepository: TableRepository
)  : UpdateTableCoordUseCase {
    override suspend fun invoke(tableId: String, coordinate: Coordinate): DataResource<Unit> {
        return tableRepository.updateTableCoord(tableId, coordinate)
    }
}