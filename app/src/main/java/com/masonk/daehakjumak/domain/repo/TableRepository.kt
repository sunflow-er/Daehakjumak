package com.masonk.daehakjumak.domain.repo

import com.masonk.daehakjumak.core.Coordinate
import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Table
import kotlinx.coroutines.flow.Flow

interface TableRepository {
    // 테이블 리스트
    fun getTableList(): Flow<DataResource<List<Table>>>
    
    // 테이블 추가
    fun addTable(table: Table): Flow<DataResource<Unit>>

    // 테이블 삭제
    fun removeTable(tableId: String): Flow<DataResource<Unit>>

    // 테이블 위치(죄표) 업데이트
    fun updateTableCoord(tableId: String, coordinate: Coordinate): Flow<DataResource<Unit>>
}