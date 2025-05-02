package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.repo.TableRepository
import com.masonk.daehakjumak.domain.usecase.RemoveTableUseCase

class RemoveTableUseCaseImpl(private val tableRepository: TableRepository): RemoveTableUseCase {
    override suspend fun invoke(tableId: String): DataResource<Unit> {
        return tableRepository.removeTable(tableId)
    }
}