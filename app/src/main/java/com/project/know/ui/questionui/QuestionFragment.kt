package com.project.know.ui.questionui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.know.R
import com.project.know.databinding.FragmentQuestionBinding
import kotlin.properties.Delegates

/**
 * @author: Rita Okonkwo.
 * A simple [Fragment] subclass.
 * The QuestionFragment for Know app.
 */
class QuestionFragment : Fragment() {

    private lateinit var questionBinding : FragmentQuestionBinding
    val questionViewModel: QuestionViewModel by viewModels()
    private lateinit var questions : List<QuestionsItem>
    private var questionIndex by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        questionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        questions = questionViewModel.questions.value!!
        questionIndex = questionViewModel.questionIndex.value!!
        setQuestion()

        return questionBinding.root
    }

    fun setQuestion(){
            questionBinding.question.text = questions[questionIndex].question
            val answers = questions[questionIndex].answers
            questionBinding.firstAnswer.text = answers[0].answer
            questionBinding.secondAnswer.text = answers[1].answer
            questionBinding.thirdAnswer.text = answers[2].answer
            questionBinding.fourthAnswer.text = answers[3].answer
    }
}