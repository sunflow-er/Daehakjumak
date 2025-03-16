package com.masonk.daehakjumak.domain.repo

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Table
import kotlinx.coroutines.flow.Flow

interface TableRepository {
    fun getTableList(): Flow<DataResource<List<Table>>>
}