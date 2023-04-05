package com.example.fetchtestapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<LinearLayout>(R.id.container)
        val recycleView = container.findViewById<RecyclerView>(R.id.recycler)
        lifecycleScope.launch {
            try {
                val apiResult = FetchAPI.retrofitService.getData()
                if (!apiResult.isNullOrEmpty()) {
                    // Filtering names that are null or ""
                    // And getting the items we want in the proper format
                    val listIdMap = getListIdMap(apiResult.filterNot {
                        (it.name == null) or (it.name == "")
                    })
                    recycleView.adapter = ItemAdapter(getRecyclerDataset(listIdMap))
                } else {
                    val textView = container.findViewById<TextView>(R.id.fail_message)
                    textView.visibility = View.VISIBLE
                }
            } catch (e : Exception) {
                Log.d("error", "${e.message}")
                val textView = container.findViewById<TextView>(R.id.fail_message)
                textView.visibility = View.VISIBLE
            }
        }
    }
}

// Creating a HashMap in the format {ListID: [ListItem, ListItem...], ListID2: [...],}
fun getListIdMap(initialList : List<ListItem>) : HashMap<Int, MutableList<ListItem>> {
    val listIdMap = HashMap<Int, MutableList<ListItem>> ()
    for (item in initialList) {
        if (listIdMap.containsKey(item.listId)) {
            listIdMap[item.listId]?.add(item)
        } else {
            listIdMap.put(item.listId, mutableListOf<ListItem>(item))
        }
    }
    return listIdMap
}

// Flattening the HashMap into a List for the RecyclerView
fun getRecyclerDataset(initialMap : HashMap<Int, MutableList<ListItem>>) : MutableList<Any> {
    val dataset = mutableListOf<Any>()
    for ((k,v) in initialMap) {
        // ListIDs are headers for each list
        dataset.add(ListID(k))
        if (v != null) {
            v.sort()
            dataset.addAll(v)
        }
    }
    return dataset
}
