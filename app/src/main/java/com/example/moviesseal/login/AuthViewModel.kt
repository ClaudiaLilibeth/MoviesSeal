package com.example.moviesseal.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesseal.login.data.AuthRepository
import com.example.moviesseal.login.data.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    //#firebase 4 flows to use firebase functions inside vm
    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _loginFlow.value = Resource.Success(repository.currentUser!!)

        }
    }

    //iniciar sesiòn
    fun loginUser(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading(true)
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    //registrarse
    fun signupUser(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading(true)
        val result = repository.signup(name, email, password)
        _signupFlow.value = result
    }

    //Cerrar sesiòn
    fun logout() {
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }

}
