package org.techtown.roomtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    var name : String,
    var age : String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}