package com.example.hellogames

class Game (id : Int, name : String, picture : String) {
    val id : Int = 0
    val name : String = ""
    val picture : String = ""
    override fun toString(): String {
        return "id: " + this.id + ", name: " + this.name + ", urlImage: " + this.picture
    }
}