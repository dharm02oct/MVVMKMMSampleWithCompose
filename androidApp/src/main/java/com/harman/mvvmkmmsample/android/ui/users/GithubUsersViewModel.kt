package com.harman.mvvmkmmsample.android.ui.users


import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.harman.mvvmkmmsample.domain.interactor.GetUsers
import com.harman.mvvmkmmsample.domain.model.User
import com.harman.mvvmkmmsample.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow


class GithubUsersViewModel constructor(
    private val getUsers: GetUsers
) : BaseViewModel() {

    val users: Flow<PagingData<User>> =
        flow {
               emitAll(getUsers.execute(null).cachedIn(viewModelScope))
        }
}