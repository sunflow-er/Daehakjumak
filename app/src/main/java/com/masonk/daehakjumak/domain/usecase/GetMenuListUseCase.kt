package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Menu
import kotlinx.coroutines.flow.Flow

interface GetMenuListUseCase {
    operator fun invoke() : Flow<DataResource<List<Menu>>>
}