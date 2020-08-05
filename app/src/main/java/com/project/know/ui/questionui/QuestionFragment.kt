package com.project.know.ui.questionui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.project.know.R
import com.project.know.databinding.FragmentQuestionBinding
import org.w3c.dom.Text
import kotlin.properties.Delegates

/**
 * @author: Rita Okonkwo.
 * A simple [Fragment] subclass.
 * The QuestionFragment for Know app.
 */
class QuestionFragment : Fragment() {

    private lateinit var questionBinding : FragmentQuestionBinding
    val questionViewModel: QuestionViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        questionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        questionViewModel.questions.observe(viewLifecycleOwner, Observer {
            questions -> setQuestion(questions)
            questionBinding.next.setOnClickListener {
                if (!questionViewModel.nextQuestion) {
                    Toast.makeText(context, "End", Toast.LENGTH_SHORT).show()
                } else {
                    questionViewModel.updateIndex()
                    setQuestion(questions)
                }
            }
        })

        return questionBinding.root
    }

    private fun setQuestion(questions : List<QuestionsItem>){
            questionViewModel.questionIndex.observe(viewLifecycleOwner, Observer {
                questionIndex ->
                questionBinding.question.text = questions[questionIndex].question
                val answers = questions[questionIndex].answers
                questionBinding.firstAnswer.text = answers[0].answer
                questionBinding.secondAnswer.text = answers[1].answer
                questionBinding.thirdAnswer.text = answers[2].answer
                questionBinding.fourthAnswer.text = answers[3].answer
            })

    }

}