package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Order
import com.masonk.daehakjumak.domain.repo.OrderRepository
import com.masonk.daehakjumak.domain.usecase.GetOrderListUseCase
import kotlinx.coroutines.flow.Flow

class GetOrderListUseCaseImpl(private val orderRepository: OrderRepository) : GetOrderListUseCase {
    override fun invoke(): Flow<DataResource<List<Order>>> {
         return orderRepository.getOrderList()
    }
}