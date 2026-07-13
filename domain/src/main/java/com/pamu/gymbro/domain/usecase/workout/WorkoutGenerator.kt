package com.pamu.gymbro.domain.usecase.workout

import com.pamu.gymbro.domain.model.WorkoutDay
import com.pamu.gymbro.domain.model.WorkoutExercise
import com.pamu.gymbro.domain.model.WorkoutPlan

object WorkoutGenerator {
    
    fun generateDefaultPlan(level: String, goal: String): Pair<WorkoutPlan, List<WorkoutDay>> {
        val planName = "Default $level Plan ($goal)"
        val plan = WorkoutPlan(
            id = 0,
            name = planName,
            level = level,
            goal = goal,
            durationWeeks = 12,
            description = "Scientifically designed $level routine focused on $goal."
        )

        val days = when (level) {
            "Beginner" -> generateBeginnerDays(goal)
            "Intermediate" -> generateIntermediateDays(goal)
            else -> generateExpertDays(goal)
        }

        return plan to days
    }

    private fun generateBeginnerDays(goal: String): List<WorkoutDay> {
        val reps = if (goal == "Weight Loss") "15" else "10"
        val sets = 3
        
        return listOf(
            WorkoutDay(0, 0, 1, "Full Body A", listOf(
                createEx(81, sets, reps), // Bodyweight Squat
                createEx(1, sets, reps),  // Push-Ups
                createEx(18, sets, reps), // Lat Pulldown
                createEx(31, sets, reps), // DB Shoulder Press
                createEx(108, sets, "60s") // Plank
            )),
            WorkoutDay(0, 0, 2, "Rest Day", emptyList()),
            WorkoutDay(0, 0, 3, "Full Body B", listOf(
                createEx(84, sets, reps), // Leg Press
                createEx(11, sets, reps), // Machine Chest Press
                createEx(21, sets, reps), // Seated Row
                createEx(32, sets, reps), // Lateral Raise
                createEx(107, sets, reps) // Crunches
            )),
            WorkoutDay(0, 0, 4, "Rest Day", emptyList()),
            WorkoutDay(0, 0, 5, "Full Body C", listOf(
                createEx(82, sets, reps), // Lunges
                createEx(2, sets, reps),  // Incline Pushups
                createEx(20, sets, reps), // One Arm Row
                createEx(33, sets, reps), // Front Raise
                createEx(111, sets, reps) // Bicycle Crunch
            )),
            WorkoutDay(0, 0, 6, "Active Recovery", listOf(
                createEx(125, 1, "20 min") // Treadmill
            )),
            WorkoutDay(0, 0, 7, "Rest Day", emptyList())
        )
    }

    private fun generateIntermediateDays(goal: String): List<WorkoutDay> {
        val reps = if (goal == "Weight Loss") "12" else "8"
        val sets = 4
        
        return listOf(
            WorkoutDay(0, 0, 1, "Push (Chest/Shoulders/Triceps)", listOf(
                createEx(4, sets, reps),  // Barbell Bench Press
                createEx(5, sets, reps),  // Incline DB Press
                createEx(30, sets, reps), // Overhead Press
                createEx(32, sets, reps), // Lateral Raise
                createEx(57, sets, reps)  // Tricep Pushdown
            )),
            WorkoutDay(0, 0, 2, "Pull (Back/Biceps)", listOf(
                createEx(19, sets, reps), // Barbell Row
                createEx(18, sets, reps), // Lat Pulldown
                createEx(23, sets, reps), // Face Pull
                createEx(46, sets, reps), // Barbell Curl
                createEx(45, sets, reps)  // Hammer Curl
            )),
            WorkoutDay(0, 0, 3, "Legs", listOf(
                createEx(80, sets, reps), // Barbell Squat
                createEx(87, sets, reps), // RDL
                createEx(85, sets, reps), // Leg Extension
                createEx(86, sets, reps), // Leg Curl
                createEx(96, sets, reps)  // Calf Raise
            )),
            WorkoutDay(0, 0, 4, "Push", listOf(
                createEx(9, sets, reps),  // Decline Bench
                createEx(6, sets, reps),  // Chest Fly
                createEx(35, sets, reps), // Arnold Press
                createEx(63, sets, reps), // Diamond Pushups
                createEx(59, sets, reps)  // Skull Crusher
            )),
            WorkoutDay(0, 0, 5, "Pull", listOf(
                createEx(16, sets, reps), // Chin Ups
                createEx(27, sets, reps), // T-Bar Row
                createEx(24, sets, reps), // Shrugs
                createEx(44, sets, reps), // DB Curl
                createEx(70, sets, reps)  // Wrist Curl
            )),
            WorkoutDay(0, 0, 6, "Legs", listOf(
                createEx(84, sets, reps), // Leg Press
                createEx(83, sets, reps), // Bulgarian Split Squat
                createEx(91, sets, reps), // Hack Squat
                createEx(101, sets, reps), // Leg Press Calf
                createEx(110, sets, reps)  // Leg Raise
            )),
            WorkoutDay(0, 0, 7, "Rest Day", emptyList())
        )
    }

    private fun generateExpertDays(goal: String): List<WorkoutDay> {
        val reps = if (goal == "Weight Loss") "12" else "8"
        val sets = 5
        
        return listOf(
            WorkoutDay(0, 0, 1, "Chest & Abs", listOf(
                createEx(4, sets, reps),  // Bench Press
                createEx(5, sets, reps),  // Incline DB
                createEx(7, sets, reps),  // Cable Crossover
                createEx(8, sets, reps),  // Dips
                createEx(114, sets, reps) // Hanging Leg Raise
            )),
            WorkoutDay(0, 0, 2, "Back & Forearms", listOf(
                createEx(17, sets, reps), // Deadlift
                createEx(15, sets, reps), // Pull Ups
                createEx(19, sets, reps), // Barbell Row
                createEx(27, sets, reps), // T-Bar Row
                createEx(72, sets, reps)  // Farmer's Walk
            )),
            WorkoutDay(0, 0, 3, "Shoulders", listOf(
                createEx(30, sets, reps), // Overhead Press
                createEx(40, sets, reps), // Cable Lateral
                createEx(34, sets, reps), // Rear Delt
                createEx(36, sets, reps), // Upright Row
                createEx(43, sets, reps)  // DB Snatch
            )),
            WorkoutDay(0, 0, 4, "Legs (Heavy)", listOf(
                createEx(80, sets, reps), // Squat
                createEx(92, sets, reps), // Sumo Deadlift
                createEx(91, sets, reps), // Hack Squat
                createEx(88, sets, reps), // Hip Thrust
                createEx(104, sets, reps) // Box Jump
            )),
            WorkoutDay(0, 0, 5, "Arms", listOf(
                createEx(47, sets, reps), // Preacher Curl
                createEx(55, 3, "21"),    // 21s
                createEx(59, sets, reps), // Skull Crusher
                createEx(58, sets, reps), // Tricep Dips
                createEx(77, sets, reps)  // Wrist Roller
            )),
            WorkoutDay(0, 0, 6, "Full Body Explosive", listOf(
                createEx(132, sets, reps), // Thrusters
                createEx(131, sets, reps), // KB Swing
                createEx(123, sets, reps), // Burpees
                createEx(128, sets, reps), // Battle Ropes
                createEx(115, sets, reps)  // Ab Wheel
            )),
            WorkoutDay(0, 0, 7, "Rest Day", emptyList())
        )
    }

    private fun createEx(id: Long, sets: Int, reps: String): WorkoutExercise {
        return WorkoutExercise(0, 0, id, sets, reps, 60, null)
    }
}
