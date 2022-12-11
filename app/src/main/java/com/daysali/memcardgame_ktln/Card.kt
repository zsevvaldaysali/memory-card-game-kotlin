package com.daysali.memcardgame_ktln

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Card(var name: String? = null,var house : String? = null, var image:String? = null, var score: String? = null) {
}