package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Table
import com.masonk.daehakjumak.domain.repo.TableRepository
import com.masonk.daehakjumak.domain.usecase.GetTableListUseCase
import kotlinx.coroutines.flow.Flow

class GetTableListUseCaseImpl(private val tableRepository: TableRepository) : GetTableListUseCase {
    override fun invoke(): Flow<DataResource<List<Table>>> {
        return tableRepository.getTableList()
    }
}