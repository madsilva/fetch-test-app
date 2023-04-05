package com.example.fetchtestapp

class ListItem(val id : Int, val listId : Int, val name : String?) : Comparable<ListItem> {
    override fun toString() = "Item $id"

    override fun compareTo(other: ListItem) = when {
        this.id > other.id -> 1
        this.id < other.id -> -1
        else -> 0
    }

    override fun equals(other: Any?) = if (other is ListItem) (
        (this.id == other.id) and (this.listId == other.listId) and (this.name == other.name)
    )
    else false
}
