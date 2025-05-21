package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.repo.JumakRepository
import com.masonk.daehakjumak.domain.usecase.SetJumakOpenStatusUseCase
import kotlinx.coroutines.flow.Flow

class SetJumakOpenStatusUseCaseImpl(
    private val jumakRepository: JumakRepository
) : SetJumakOpenStatusUseCase {
    override fun invoke(status: Boolean): Flow<DataResource<Unit>> {
        return jumakRepository.setJumakOpenStatus(status)
    }

}