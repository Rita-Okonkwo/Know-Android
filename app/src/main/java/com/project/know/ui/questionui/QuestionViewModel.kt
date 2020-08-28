package com.project.know.ui.questionui

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.project.know.repository.KnowRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

private const val COUNTDOWN_SEC = 60000L
private const val INTERVAL_SEC = 1000L

enum class QuestionApiStatus { LOADING, ERROR, DONE }

class QuestionViewModel : ViewModel() {
    val repository: KnowRepository = KnowRepository()

    private val _apiStatus = MutableLiveData<QuestionApiStatus>()
    val apiStatus: LiveData<QuestionApiStatus>
        get() = _apiStatus

    val questions: LiveData<List<QuestionsItem>> = liveData(Dispatchers.IO) {
        loading()
        try {
            val retrivedQuestions = repository.getQuestions()
            emit(retrivedQuestions)
            done()
        } catch (e: Exception) {
            networkError()
        }
    }


    private val _questionIndex = MutableLiveData<Int>()
    val questionIndex: LiveData<Int>
        get() = _questionIndex
    var nextQuestion: Boolean = true
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    private val _time = MutableLiveData<Int>()
    val time: LiveData<Int>
        get() = _time
    private val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean>
        get() = _gameFinished
    private val timer: CountDownTimer

    init {
        _questionIndex.value = 0
        _score.value = 0
        _gameFinished.value = false
        _apiStatus.value = QuestionApiStatus.LOADING
        timer = object : CountDownTimer(COUNTDOWN_SEC, INTERVAL_SEC) {
            override fun onTick(millisUntilFinished: Long) {
                _time.value = (millisUntilFinished / INTERVAL_SEC).toInt()
            }

            override fun onFinish() {
                gameFinished()
            }
        }
    }

    private suspend fun done() {
        withContext(Main) {
            _apiStatus.value = QuestionApiStatus.DONE
            timer.start()
        }
    }

    private suspend fun loading() {
        withContext(Main) {
            _apiStatus.value = QuestionApiStatus.LOADING
        }
    }

    private suspend fun networkError() {
        withContext(Main) {
            _apiStatus.value = QuestionApiStatus.ERROR
        }
    }

    fun updateIndex() {
        if (_questionIndex.value!! != questions.value!!.size) {
            _questionIndex.value = _questionIndex.value?.plus(1)
        } else {
            nextQuestion = false
        }

    }

    fun updateScore() {
        _score.value = _score.value?.plus(5)
    }

    fun gameFinished() {
        _gameFinished.value = true
        timer.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}