package com.masonk.daehakjumak.domain.repo

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Menu
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenuList(): Flow<DataResource<List<Menu>>>
}