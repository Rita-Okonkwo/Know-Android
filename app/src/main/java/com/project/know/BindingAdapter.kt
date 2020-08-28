package com.project.know

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.project.know.ui.questionui.QuestionApiStatus

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, apiStatus: QuestionApiStatus) {
    when (apiStatus) {
        QuestionApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        QuestionApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        QuestionApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("textVisible")
fun bindStatusText(answerTextView: TextView, apiStatus: QuestionApiStatus) {
    when (apiStatus) {
        QuestionApiStatus.LOADING -> {
            answerTextView.visibility = View.GONE
        }
        QuestionApiStatus.ERROR -> {
            answerTextView.visibility = View.GONE
        }
        QuestionApiStatus.DONE -> {
            answerTextView.visibility = View.VISIBLE
        }
    }
}