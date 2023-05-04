package com.example.fooddeliveryapp.model

import android.widget.Toast

class AccountModel () {
    var userName : String = ""
    var passWord : String = ""
    constructor(userName : String, passWord : String) : this() {
       this.userName = userName
        this.passWord = passWord
    }
}