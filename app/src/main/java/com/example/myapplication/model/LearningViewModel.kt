package com.example.myapplication.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LearningViewModel : ViewModel() {
    private val learnings = MutableStateFlow(listOf<LearningDetails>())
    val learningList: StateFlow<List<LearningDetails>> get() = learnings

    init {
        getLearnings()
    }

    private fun getLearnings() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                learnings.emit(Learnings.learningsList)
            }
        }
    }
}