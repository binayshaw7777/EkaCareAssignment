package com.binayshaw7777.ekacareassignment.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binayshaw7777.ekacareassignment.data.remote.response.Articles
import com.binayshaw7777.ekacareassignment.data.remote.response.NewsResponse
import com.binayshaw7777.ekacareassignment.domain.repository.NewsApiRepository
import com.binayshaw7777.ekacareassignment.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NewsApiRepository) : ViewModel() {

    var newsResponse = MutableStateFlow<NetworkResult<NewsResponse>>(NetworkResult.Clear())
        private set

    var articles = MutableStateFlow<List<Articles>>(emptyList())
        private set

    fun getNews(query: String = "Bitcoin") = viewModelScope.launch(Dispatchers.IO) {
        if (articles.value.isEmpty()) {
            newsResponse.emit(NetworkResult.Loading())
        }
        repository.getNewsByQuery(query).collect { response ->
            newsResponse.emit(response)
            when (response) {
                is NetworkResult.Success -> {
                    Timber.d("API call was success: ${response.data}")
                    articles.emit(response.data.articles)
                }

                is NetworkResult.Error -> {
                    Timber.d("API call was failed due to error: ${response.code} ${response.message}")
                }

                is NetworkResult.Loading -> {
                    Timber.d("API call is loading")
                }

                is NetworkResult.Exception -> {
                    Timber.d("API call was failed due to exception: ${response.e}")
                }

                is NetworkResult.Clear -> {}
            }
        }
    }
}