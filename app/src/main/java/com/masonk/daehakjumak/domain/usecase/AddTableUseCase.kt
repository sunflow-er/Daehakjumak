package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Table

interface AddTableUseCase {
    suspend operator fun invoke(table: Table): DataResource<Unit>
}