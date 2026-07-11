package com.pamu.gymbro.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "meals",
    foreignKeys = [
        ForeignKey(
            entity = DietPlanEntity::class,
            parentColumns = ["id"],
            childColumns = ["dietPlanId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("dietPlanId")]
)
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dietPlanId: Long,
    val mealType: String,
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int
)
