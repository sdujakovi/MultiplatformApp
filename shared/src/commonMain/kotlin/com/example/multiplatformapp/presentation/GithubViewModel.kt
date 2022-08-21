package com.example.multiplatformapp.presentation

import com.example.multiplatformapp.data.repository.GithubRepository
import com.example.multiplatformapp.data.utils.MainDispatcher
import com.example.multiplatformapp.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GithubViewModel(private val query: String) : MainDispatcher(), KoinComponent {
    private val githubRepository: GithubRepository by inject()

    private val _repositories = MutableStateFlow<List<Repository>>(emptyList())
    val repositories: StateFlow<List<Repository>> = _repositories

    private val _anyUseCaseInProgress = MutableStateFlow(false)
    val anyUseCaseInProgress: StateFlow<Boolean> = _anyUseCaseInProgress

    private val _anyUseCaseFailure = MutableStateFlow(Throwable())
    val anyUseCaseFailure: StateFlow<Throwable> = _anyUseCaseFailure

    init {
        getGithubRepositories(query)
    }

    fun getGithubRepositories(query: String) {
        coroutineScope.launch {
            try {
                _anyUseCaseInProgress.value = true
                _repositories.value = githubRepository.getGithubRepositories(query)
            } catch (cause: Throwable) {
                _anyUseCaseFailure.value = cause
            } finally {
                _anyUseCaseInProgress.value = false
            }
        }
    }

    fun observeGithubRepositories(onChange: (List<Repository>) -> Unit) {
        repositories.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }

    fun observeAnyUseCaseInProgress(onChange: (Boolean) -> Unit) {
        anyUseCaseInProgress.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }

    fun observeAnyUseCaseFailure(onChange: (Throwable) -> Unit) {
        anyUseCaseFailure.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }
}