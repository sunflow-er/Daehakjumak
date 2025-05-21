package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Table
import com.masonk.daehakjumak.domain.repo.TableRepository
import com.masonk.daehakjumak.domain.usecase.AddTableUseCase
import kotlinx.coroutines.flow.Flow

class AddTableUseCaseImpl(
    private val tableRepository: TableRepository
) : AddTableUseCase {
    override fun invoke(table: Table): Flow<DataResource<Unit>> {
        return tableRepository.addTable(table)
    }
}