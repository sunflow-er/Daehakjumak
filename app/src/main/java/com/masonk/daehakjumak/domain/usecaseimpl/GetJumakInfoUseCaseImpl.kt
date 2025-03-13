package com.masonk.daehakjumak.domain.usecaseimpl

import com.masonk.daehakjumak.core.dataresource.DataResource
import com.masonk.daehakjumak.domain.model.Jumak
import com.masonk.daehakjumak.domain.repo.JumakRepository
import com.masonk.daehakjumak.domain.usecase.GetJumakInfoUseCase
import kotlinx.coroutines.flow.Flow

class GetJumakInfoUseCaseImpl(private val jumakRepository: JumakRepository) : GetJumakInfoUseCase {
    override fun invoke(): Flow<DataResource<Jumak>> {
        return jumakRepository.getJumakInfo()
    }
}