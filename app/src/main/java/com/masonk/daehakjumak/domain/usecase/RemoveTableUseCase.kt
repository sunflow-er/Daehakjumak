package com.masonk.daehakjumak.domain.usecase

import com.masonk.daehakjumak.core.dataresource.DataResource

interface RemoveTableUseCase {
    suspend operator fun invoke(tableId: String): DataResource<Unit>
}