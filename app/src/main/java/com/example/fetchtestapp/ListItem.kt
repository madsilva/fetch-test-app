package com.example.fetchtestapp

class ListItem(val id : Int, val listId : Int, val name : String?) : Comparable<ListItem> {
    override fun toString(): String {
        return "Item $id name ${name}"
    }

    override fun compareTo(other: ListItem): Int {
        return when {
            this.id > other.id -> 1
            this.id < other.id -> -1
            else -> 0
        }
    }
}