package com.example.litterlegends.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class User : RealmObject {
    @PrimaryKey
    var email: String = ""          // Unique identifier for the user
    var username: String = ""       // User's display name
    var password: String = ""       // ⚠️ Must be hashed before storing
}
