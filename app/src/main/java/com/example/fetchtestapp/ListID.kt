package com.example.fetchtestapp

class ListID(val id : Int) {
    override fun toString() = "List $id"

    override fun equals(other: Any?) = if (other is ListID) (this.id == other.id) else false
}
