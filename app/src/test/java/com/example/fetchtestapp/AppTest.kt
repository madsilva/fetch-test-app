package com.example.fetchtestapp

import org.junit.Assert.assertEquals
import org.junit.Test

class AppTest {
    @Test
    fun testGetRecyclerDataset (){
        val input = hashMapOf<Int, MutableList<ListItem>>(
            1 to mutableListOf<ListItem>(ListItem(1, 1, ""))
        )
        val expectedOutput = mutableListOf<Any>(
            ListID(1),
            ListItem(1, 1, "")
        )
        assertEquals(expectedOutput, getRecyclerDataset(input))
    }

    @Test fun testRecyclerViewRender () {

    }
}
