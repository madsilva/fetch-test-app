package com.example.fetchtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            var filteredListItems : List<ListItem> = emptyList()
            try {
                val apiResult = FetchAPI.retrofitService.getData()
                filteredListItems = apiResult.filterNot {
                    (it.name == null) or (it.name == "")
                }
            } catch (e : Exception) {
                Log.d("error", "${e.message}")
            }
            val listIdMap = HashMap<Int, MutableList<ListItem>> ()
            for (item in filteredListItems) {
                if (listIdMap.containsKey(item.listId)) {
                    listIdMap[item.listId]?.add(item)
                } else {
                    listIdMap.put(item.listId, mutableListOf<ListItem>(item))
                }
            }
            // Creating the dataset for the RecyclerView
            val dataset = mutableListOf<Any>()
            for ((k,v) in listIdMap) {
                // ListIDs are headers for each list
                dataset.add(ListID(k))
                if (v != null) {
                    v.sort()
                    dataset.addAll(v)
                }
            }
            Log.d("maddie", "success: ${listIdMap}")
            val container = findViewById<LinearLayout>(R.id.container)
            val recycleView = container.findViewById<RecyclerView>(R.id.recycler)
            recycleView.adapter = ItemAdapter(dataset)
        }
    }
}
