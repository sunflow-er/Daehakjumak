package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.Coordinate
import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.repo.TableRepository
import com.masonk.daehakjumak.domain.usecase.UpdateTableCoordUseCase
import kotlinx.coroutines.flow.Flow

class UpdateTableCoordUseCaseImpl(
    private val tableRepository: TableRepository
)  : UpdateTableCoordUseCase {
    override fun invoke(tableId: String, coordinate: Coordinate): Flow<DataResource<Unit>> {
        return tableRepository.updateTableCoord(tableId, coordinate)
    }
}