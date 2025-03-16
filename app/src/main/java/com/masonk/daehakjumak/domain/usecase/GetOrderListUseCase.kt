package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface GetOrderListUseCase {
    operator fun invoke(): Flow<DataResource<List<Order>>>
}