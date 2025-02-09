package com.example.litterlegends_.repository

import com.example.litterlegends_.utils.SecurityUtils.hashPassword
import com.example.litterlegends_.utils.SecurityUtils.verifyPassword
import com.example.litterlegends_.model.User
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {
    private val realm: Realm

    init {
        val config = RealmConfiguration.Builder(schema = setOf(User::class))
            .name("litter_legends_.realm")
            .build()
        realm = Realm.open(config)
    }

    suspend fun registerUser(email: String, username: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val existingUser = realm.query<User>("email == $0", email).first().find()
            if (existingUser != null) return@withContext false

            realm.writeBlocking {
                copyToRealm(User().apply {
                    this.email = email
                    this.username = username
                    this.password = hashPassword(password) // ✅ Hashing the password
                })
            }
            return@withContext true
        }
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val user = realm.query<User>("email == $0", email).first().find()
            return@withContext user != null && verifyPassword(password, user.password) // ✅ Verify password
        }
    }
}
