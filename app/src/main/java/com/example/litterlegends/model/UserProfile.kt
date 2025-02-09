package com.example.litterlegends.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class UserProfile : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var imageUrl: String = ""
    var status: String = ""
}
