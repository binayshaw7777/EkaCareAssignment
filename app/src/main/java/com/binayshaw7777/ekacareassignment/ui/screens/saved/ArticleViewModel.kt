package com.binayshaw7777.ekacareassignment.ui.screens.saved

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binayshaw7777.ekacareassignment.data.local.model.ArticleEntity
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import com.binayshaw7777.ekacareassignment.domain.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {
    val savedArticles: StateFlow<List<ArticleEntity>> = repository.getArticles()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    var savedRowIdLiveData = MutableLiveData<Long?>()
        private set

    fun saveArticle(article: Article) =
        viewModelScope.launch(Dispatchers.IO) {
            val savedRowId = repository.saveArticle(article = article)
            savedRowIdLiveData.postValue(savedRowId)
        }

    fun deleteArticle(article: ArticleEntity, onDelete: (Boolean) -> Unit) = viewModelScope.launch {
        val success = repository.deleteArticle(article)
        onDelete(success)
    }

    fun clearSaveRowId() {
        savedRowIdLiveData.value = null
    }
}