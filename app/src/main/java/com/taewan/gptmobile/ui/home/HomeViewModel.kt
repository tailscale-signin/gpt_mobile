package com.taewan.gptmobile.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.taewan.gptmobile.data.database.ChatDatabase
import com.taewan.gptmobile.data.database.entity.ChatRoomV2Entity
import com.taewan.gptmobile.data.repository.ChatRepositoryImpl
import com.taewan.gptmobile.domain.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
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

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = ChatDatabase.getDatabase(context.applicationContext)
                    val repository = ChatRepositoryImpl(db.chatRoomV2Dao())
                    return HomeViewModel(repository) as T
                }
            }
    }
}
