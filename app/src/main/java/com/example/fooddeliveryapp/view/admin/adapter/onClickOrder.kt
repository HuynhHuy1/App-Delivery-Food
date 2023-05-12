package com.example.fooddeliveryapp.view.admin.adapter

interface onClickOrder {
    fun handleOnClickConfirm(position: Int, id: Int)
    fun handleOnClickComplete(position: Int, id: Int)
}