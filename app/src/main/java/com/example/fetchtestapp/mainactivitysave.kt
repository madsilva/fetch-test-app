/*
package com.example.fetchtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var notNull : List<ListItem> = emptyList()
        var numKeys : Int = 0
        GlobalScope.launch {
            try {
                val apiResult = FetchAPI.retrofitService.getData()
                Log.d("ayush: ", "success: ${apiResult.size}")
                notNull = apiResult.filterNot {
                    (it.name == null) and (it.name == "")
                }
                Log.d("maddie", "success: ${notNull}")

            } catch (e : Exception) {
                Log.d("error", "${e.message}")
            }

        }.invokeOnCompletion {
            val listIdMap = HashMap<Int, MutableList<ListItem>> ()
            for (item in notNull) {
                if (listIdMap.containsKey(item.listId)) {
                    listIdMap[item.listId]?.add(item)
                } else {
                    listIdMap.put(item.listId, mutableListOf<ListItem>(item))
                }
            }

            for ((k,v) in listIdMap) {
                val myList = listIdMap[k]
                if (myList != null) {
                    numKeys++
                    myList.sort()
                }
            }
            val sortedList = notNull.sorted()
            Log.d("maddie", "success: ${listIdMap}")


            val container = findViewById<LinearLayout>(R.id.container2)
            Log.d("maddie", "$numKeys")
            for (i in 1..numKeys) {
                // Inflate the CardView layout
                val cardView = layoutInflater.inflate(R.layout.card_view_layout, null)
                cardView.id = i
                val recycleView = cardView.findViewById<RecyclerView>(R.id.recycle)
                container.addView(cardView)
                val currentList = listIdMap[i]
                if (currentList != null) {
                    recycleView.adapter = ItemAdapter(this, currentList)
                }

                /*
                // Get references to the CardView child views
                val textView = cardView.findViewById<TextView>(R.id.text_view)

                // Set the text for the TextView
                textView.text = "Card $i"
                Log.d("maddie", "making card $i")
                container.addView(cardView)
                val items = listIdMap[i]
                if (items != null) {
                    for (item in items) {
                        val cardView = findViewById<CardView>(i)
                        val currentList = listIdMap[i]
                        if (currentList != null) {
                            for (item in currentList) {
                                textView.append("\nItem${item.id}")
                            }
                        }
                    }
                }
                */
            }


        }

    }
}*/

