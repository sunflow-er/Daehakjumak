package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.repo.JumakRepository
import com.masonk.daehakjumak.domain.usecase.ChangeJumakOpenClosedStatusUseCase
import kotlinx.coroutines.flow.Flow

class ChangeJumakOpenClosedStatusUseCaseImpl(
    private val jumakRepository: JumakRepository
) : ChangeJumakOpenClosedStatusUseCase {
    override fun invoke(status: Boolean): Flow<DataResource<Unit>> {
        return jumakRepository.setJumakOpenClosedStatus(status)
    }

}