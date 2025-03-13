package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.WaitingPerson
import com.masonk.daehakjumak.domain.repo.WaitingPersonRepository
import com.masonk.daehakjumak.domain.usecase.GetWaitingPersonListUseCase
import kotlinx.coroutines.flow.Flow

class GetWaitingPersonListUseCaseImpl(private val waitingPersonRepository: WaitingPersonRepository) : GetWaitingPersonListUseCase {
    override fun invoke(): Flow<DataResource<List<WaitingPerson>>> {
        return waitingPersonRepository.getWaitingPersonList()
    }
}