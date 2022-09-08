package com.example.myapplication.expandablelist.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExpandableListViewModel : ViewModel() {
    private val listItems = MutableStateFlow(listOf<FaqModel>())
    val items: StateFlow<List<FaqModel>> get() = listItems

    private val listItemIds = MutableStateFlow(listOf<Int>())
    val itemIds: StateFlow<List<Int>> get() = listItemIds

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                listItems.emit(Data.items)
            }
        }
    }

    fun onItemClicked(itemId: Int) {
        listItemIds.value = listItemIds.value.toMutableList().also { list ->
            if (list.contains(itemId)) {
                list.remove(itemId)
            } else {
                list.add(itemId)
            }
        }
    }
}