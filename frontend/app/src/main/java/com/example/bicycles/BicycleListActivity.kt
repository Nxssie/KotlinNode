package com.example.bicycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bicycles.models.Bicycle

class BicycleListActivity : AppCompatActivity() {

    lateinit var bicycles: ArrayList<Bicycle>

    private lateinit var viewManager: RecyclerView.ViewHolder
    private lateinit var viewAdapter: BicycleAdapter(Bicycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicycle_list)

        viewManager = LinearLayoutManager(this)
        viewAdapter = BicycleAdapter(bicycles)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewBicycles).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }

        getAllBicycles()
    }

    private fun getAllBicycles() {
        bicycles = ArrayList<Bicycle>();

        bicycles.add(Bicycle("BH", "star"))
        bicycles.add(Bicycle("Orbea", "machine"))
    }

}