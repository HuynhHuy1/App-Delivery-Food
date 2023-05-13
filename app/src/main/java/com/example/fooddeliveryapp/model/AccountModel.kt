package com.example.fooddeliveryapp.model

import android.widget.Toast

class AccountModel () {
    var userName : String = ""
    constructor(userName : String?) : this() {
        if (userName != null) {
            this.userName = userName
        }
    }
}