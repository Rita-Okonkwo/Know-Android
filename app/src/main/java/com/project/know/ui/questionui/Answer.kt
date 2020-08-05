package com.project.know.ui.questionui

data class Answer(
    val answer: String,
    val correct: Boolean,
    val id: Int,
    val question_id: Int
)