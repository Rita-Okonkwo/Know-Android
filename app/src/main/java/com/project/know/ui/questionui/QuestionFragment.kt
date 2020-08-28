package com.project.know.ui.questionui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

    private lateinit var questionBinding: FragmentQuestionBinding
    val questionViewModel: QuestionViewModel by viewModels()
    private lateinit var answers: List<Answer>
    var scores = 0


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        questionBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)

        questionBinding.lifecycleOwner = this
        questionBinding.viewModel = questionViewModel

        questionViewModel.questions.observe(viewLifecycleOwner, Observer { questions ->
            setQuestion(questions)
            questionBinding.next.setOnClickListener {
                questionViewModel.updateIndex()
            }
        })

        questionViewModel.score.observe(viewLifecycleOwner, Observer { score ->
            questionBinding.scoreValue.text = score.toString()
            scores = score
        })

        questionViewModel.time.observe(viewLifecycleOwner, Observer { time ->
            questionBinding.timeValue.text = time.toString()
        })

        questionViewModel.gameFinished.observe(viewLifecycleOwner, Observer { finished ->
            if (finished && scores < 50) {
                view?.findNavController()?.navigate(QuestionFragmentDirections.actionQuestionFragmentToLoseFragment(scores))
            } else if (finished && scores >= 50) {
                view?.findNavController()?.navigate(QuestionFragmentDirections.actionQuestionFragmentToWinFragment(scores))
            }
        })

        return questionBinding.root
    }

    private fun setQuestion(questions: List<QuestionsItem>) {
        questionViewModel.questionIndex.observe(viewLifecycleOwner, Observer { questionIndex ->
            if (questionIndex == questions.size) {
                questionViewModel.gameFinished()
            } else {
                questionBinding.firstAnswer.setBackgroundColor(
                        ContextCompat.getColor(
                                requireContext(),
                                R.color.textViewColor
                        )
                )
                questionBinding.secondAnswer.setBackgroundColor(
                        ContextCompat.getColor(
                                requireContext(),
                                R.color.textViewColor
                        )
                )
                questionBinding.thirdAnswer.setBackgroundColor(
                        ContextCompat.getColor(
                                requireContext(),
                                R.color.textViewColor
                        )
                )
                questionBinding.fourthAnswer.setBackgroundColor(
                        ContextCompat.getColor(
                                requireContext(),
                                R.color.textViewColor
                        )
                )
                questionBinding.question.text = questions[questionIndex].question
                answers = questions[questionIndex].answers
                questionBinding.firstAnswer.text = answers[0].answer
                questionBinding.secondAnswer.text = answers[1].answer
                questionBinding.thirdAnswer.text = answers[2].answer
                questionBinding.fourthAnswer.text = answers[3].answer
                setListener()
            }
        })


    }

    private fun setListener() {
        questionBinding.firstAnswer.setOnClickListener {
            if (answers[0].correct) {
                it.setBackgroundColor(Color.GREEN)
                questionViewModel.updateScore()
            } else {
                it.setBackgroundColor(Color.RED)
                when {
                    answers[1].correct -> {
                        questionBinding.secondAnswer.setBackgroundColor(Color.GREEN)
                    }
                    answers[2].correct -> {
                        questionBinding.thirdAnswer.setBackgroundColor(Color.GREEN)
                    }
                    answers[3].correct -> {
                        questionBinding.fourthAnswer.setBackgroundColor(Color.GREEN)
                    }
                }
            }
            questionBinding.secondAnswer.isClickable = false
            questionBinding.thirdAnswer.isClickable = false
            questionBinding.fourthAnswer.isClickable = false
        }

        questionBinding.secondAnswer.setOnClickListener {
            if (answers[1].correct) {
                it.setBackgroundColor(Color.GREEN)
                questionViewModel.updateScore()
            } else {
                it.setBackgroundColor(Color.RED)
                when {
                    answers[0].correct -> {
                        questionBinding.firstAnswer.setBackgroundColor(Color.GREEN)
                    }
                    answers[2].correct -> {
                        questionBinding.thirdAnswer.setBackgroundColor(Color.GREEN)
                    }
                    answers[3].correct -> {
                        questionBinding.fourthAnswer.setBackgroundColor(Color.GREEN)
                    }
                }
            }
            questionBinding.firstAnswer.isClickable = false
            questionBinding.thirdAnswer.isClickable = false
            questionBinding.fourthAnswer.isClickable = false
        }

        questionBinding.thirdAnswer.setOnClickListener {
            if (answers[2].correct) {
                questionViewModel.updateScore()
                it.setBackgroundColor(Color.GREEN)
            } else {
                it.setBackgroundColor(Color.RED)
                when {
                    answers[0].correct -> {
                        questionBinding.firstAnswer.setBackgroundColor(Color.GREEN)
                    }
                    answers[1].correct -> {
                        questionBinding.secondAnswer.setBackgroundColor(Color.GREEN)
                    }
                    answers[3].correct -> {
                        questionBinding.fourthAnswer.setBackgroundColor(Color.GREEN)
                    }
                }
            }
            questionBinding.secondAnswer.isClickable = false
            questionBinding.firstAnswer.isClickable = false
            questionBinding.fourthAnswer.isClickable = false
        }

        questionBinding.fourthAnswer.setOnClickListener {
            if (answers[3].correct) {
                it.setBackgroundColor(Color.GREEN)
                questionViewModel.updateScore()
            } else {
                it.setBackgroundColor(Color.RED)
                when {
                    answers[0].correct -> {
                        questionBinding.firstAnswer.setBackgroundColor(Color.GREEN)
                    }
                    answers[1].correct -> {
                        questionBinding.secondAnswer.setBackgroundColor(Color.GREEN)
                    }
                    answers[2].correct -> {
                        questionBinding.thirdAnswer.setBackgroundColor(Color.GREEN)
                    }
                }
            }
            questionBinding.secondAnswer.isClickable = false
            questionBinding.thirdAnswer.isClickable = false
            questionBinding.firstAnswer.isClickable = false
        }

    }

}