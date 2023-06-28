package com.example.chattingapp

class User {
    var name:String? = null
    var email:String?=null
    var uid:String?=null

    constructor(){

    }
    constructor(name:String?,emil:String?,uid:String?){
        this.name = name
        this.uid = uid
        this.email = name
    }
}