package com.masonk.daehakjumak.domain.repo

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getOrderList() : Flow<DataResource<List<Order>>>
}