package com.smilegate.Easel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JoinViewModel : ViewModel() {

    // LiveData를 사용하여 데이터 관리
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _profileImageURL = MutableLiveData<String>()
    val profileImageURL: LiveData<String> get() = _profileImageURL

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> get() = _nickname

    // 값을 설정하는 메서드
    fun setEmailValue(data: String) {
        _email.value = data
    }

    fun setPasswordValue(data: String) {
        _password.value = data
    }

    fun setProfileImageURL(url: String) {
        _profileImageURL.value = url
    }

    fun setUsernameValue(username: String) {
        _username.value = username
    }

    fun setNicknameValue(nickname: String) {
        _nickname.value = nickname
    }
}
