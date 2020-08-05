package com.project.know.ui.questionui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.project.know.repository.KnowRepository
import kotlinx.coroutines.Dispatchers

class QuestionViewModel : ViewModel(){
    val repository: KnowRepository = KnowRepository()

    val questions: LiveData<List<QuestionsItem>> = liveData(Dispatchers.IO) {
        val retrivedQuestions = repository.getQuestions()
        emit(retrivedQuestions)
    }
    val _questionIndex = MutableLiveData<Int>()
    val questionIndex : LiveData<Int>
        get() = _questionIndex
    var nextQuestion : Boolean = true
    init {
        _questionIndex.value = 0
    }


    fun updateIndex(){
        if (_questionIndex.value!! != questions.value!!.size){
            _questionIndex.value = _questionIndex.value?.plus(1)
        } else {
            nextQuestion = false
        }

    }

}