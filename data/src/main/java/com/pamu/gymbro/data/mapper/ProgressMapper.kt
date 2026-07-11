package com.pamu.gymbro.data.mapper

import com.pamu.gymbro.data.local.entity.ProgressEntryEntity
import com.pamu.gymbro.domain.model.ProgressEntry

fun ProgressEntryEntity.toDomain(): ProgressEntry {
    return ProgressEntry(
        id = id,
        date = date,
        weight = weight,
        chest = chest,
        waist = waist,
        hips = hips,
        arms = arms,
        thighs = thighs,
        bodyFat = bodyFat,
        notes = notes
    )
}

fun ProgressEntry.toEntity(): ProgressEntryEntity {
    return ProgressEntryEntity(
        id = id,
        date = date,
        weight = weight,
        chest = chest,
        waist = waist,
        hips = hips,
        arms = arms,
        thighs = thighs,
        bodyFat = bodyFat,
        notes = notes
    )
}
