package com.taewan.gptmobile.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taewan.gptmobile.data.database.entity.ChatRoomV2Entity
import com.taewan.gptmobile.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _chats = MutableStateFlow<List<ChatRoomV2Entity>>(emptyList())
    val chats: StateFlow<List<ChatRoomV2Entity>> = _chats.asStateFlow()

    init {
        loadChats()
    }

    private fun loadChats() {
        viewModelScope.launch {
            chatRepository.getAllChatsFlow().collect { list ->
                _chats.value = list
            }
        }
    }

    fun toggleFavorite(chatId: String, currentFavorite: Boolean) {
        viewModelScope.launch {
            chatRepository.setChatFavoriteV2(chatId, !currentFavorite)
        }
    }
}
