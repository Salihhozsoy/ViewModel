package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    private val users = mutableListOf(

        User(
            1,
            "Salih",
            "Özsoy",
            "https://images.unsplash.com/photo-1603415526960-f7e0328c63b1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        User(
            2,
            "Metin",
            "Yıldıran",
            "https://images.unsplash.com/photo-1639747280804-dd2d6b3d88ac?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
        ),
        User(
            3,
            "Oğuzhan",
            "Toruntay",
            "https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        User(
            4,
            "Tolga",
            "Ünal",
            "https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        User(
            5,
            "ali",
            "Ünal",
            "https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        User(
            6,
            "ayşe",
            "Ünal",
            "https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        User(
            7,
            "mehmet",
            "Ünal",
            "https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        User(
            8,
            "ahmet",
            "Ünal",
            "https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        )
    )

    private val _userList: MutableStateFlow<MutableList<User>> = MutableStateFlow(users)
    val userList: StateFlow<List<User>> = _userList

    private val _adapter: MutableStateFlow<Adapter> = MutableStateFlow(Adapter.Idle)
    val adapter: StateFlow<Adapter> = _adapter

    sealed class Adapter{
        object Idle:Adapter()
        class Remove(val position: Int):Adapter()
        class Add(val user:User):Adapter()
    }

    fun removeItem(user: User) {
        viewModelScope.launch {
            val removedUserListIndex = _userList.value.indexOf(user)

            _userList.value.removeAt(removedUserListIndex)
            _adapter.value = Adapter.Remove(removedUserListIndex)
        }
    }

}

