package com.example.litterlegends_.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.litterlegends_.model.UserProfile
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
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
