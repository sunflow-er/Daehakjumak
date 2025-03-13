package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Menu
import com.masonk.daehakjumak.domain.repo.MenuRepository
import com.masonk.daehakjumak.domain.usecase.GetMenuListUseCase
import kotlinx.coroutines.flow.Flow

class GetMenuListUseCaseImpl(private val menuRepository: MenuRepository) : GetMenuListUseCase {
    override fun invoke(): Flow<DataResource<List<Menu>>> {
        return menuRepository.getMenuList()
    }
}