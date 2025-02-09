package com.example.litterlegends.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.litterlegends.model.UserProfile
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val realm: Realm

    init {
        val config = RealmConfiguration.Builder(schema = setOf(UserProfile::class))
            .name("user_profiles.realm")
            .build()
        realm = Realm.open(config)
    }

    fun saveProfile(imageUrl: String, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            realm.writeBlocking {
                copyToRealm(UserProfile().apply {
                    this.imageUrl = imageUrl
                    this.status = status
                })
            }
        }
    }
}
