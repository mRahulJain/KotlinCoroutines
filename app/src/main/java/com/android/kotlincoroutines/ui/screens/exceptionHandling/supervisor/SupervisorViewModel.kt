package com.android.kotlincoroutines.ui.screens.exceptionHandling.supervisor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.kotlincoroutines.data.api.ApiHelper
import com.android.kotlincoroutines.data.model.ApiUser
import com.android.kotlincoroutines.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class SupervisorViewModel @Inject constructor(
    private val apiHelper: ApiHelper
): ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<ApiUser>>>()
    val uiState: LiveData<UiState<List<ApiUser>>> = _uiState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            // supervisorScope is needed, so that we can ignore error and continue
            // one child job gets failed, we can continue with other.
            supervisorScope {
                _uiState.postValue(UiState.Loading)
                val deferredUserFromApi = async { apiHelper.getUsersWithError() }
                val deferredMoreUserFromApi = async { apiHelper.getMoreUsers() }

                val usersFromApi = try {
                    deferredUserFromApi.await()
                } catch (e: Exception) {
                    emptyList()
                }

                val moreUsersFromApi = try {
                    deferredMoreUserFromApi.await()
                } catch (e: Exception) {
                    emptyList()
                }

                val allUsersFromApi = usersFromApi + moreUsersFromApi

                _uiState.postValue(UiState.Success(allUsersFromApi))
            }
        }
    }
}