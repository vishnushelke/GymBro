package com.pamu.gymbro.domain.usecase.stats

import com.pamu.gymbro.domain.repository.DailyStatsRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddWaterUseCase @Inject constructor(
    private val repository: DailyStatsRepository
) {
    suspend operator fun invoke(amountMl: Int) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateKey = dateFormat.format(Date())
        repository.addWater(dateKey, amountMl)
    }
}
