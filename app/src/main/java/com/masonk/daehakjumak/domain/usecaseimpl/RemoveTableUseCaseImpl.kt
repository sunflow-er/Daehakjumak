package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.repo.TableRepository
import com.masonk.daehakjumak.domain.usecase.RemoveTableUseCase
import kotlinx.coroutines.flow.Flow

class RemoveTableUseCaseImpl(private val tableRepository: TableRepository): RemoveTableUseCase {
    override fun invoke(tableId: String): Flow<DataResource<Unit>> {
        return tableRepository.removeTable(tableId)
    }
}