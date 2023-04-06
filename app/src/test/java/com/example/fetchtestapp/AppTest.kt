package com.example.fetchtestapp

import org.junit.Assert.assertEquals
import org.junit.Test

class AppTest {
    private val testHashMap = hashMapOf<Int, MutableList<ListItem>>(
        1 to mutableListOf<ListItem>(ListItem(1, 1, "")),
        2 to mutableListOf<ListItem>(ListItem(3, 2, ""), ListItem(4, 2, ""))
    )
    @Test
    fun testGetRecyclerDataset (){
        val input = testHashMap
        val expectedOutput = mutableListOf<Any>(
            ListID(1),
            ListItem(1, 1, ""),
            ListID(2),
            ListItem(3, 2, "",),
            ListItem(4, 2, "")
        )
        assertEquals(expectedOutput, getRecyclerDataset(input))
    }

    @Test
    fun testGetListIdMap () {
        val input = listOf<ListItem> (
            ListItem(3, 2, ""),
            ListItem(4, 2, ""),
            ListItem(1, 1, "")
                )
        val expectedOutput = testHashMap
        assertEquals(expectedOutput, getListIdMap(input))
    }
}
