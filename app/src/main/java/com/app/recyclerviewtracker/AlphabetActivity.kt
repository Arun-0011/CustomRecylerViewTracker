package com.app.recyclerviewtracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recyclerviewtracker.adapter.ListAdapter
import com.app.recyclerviewtracker.customSideBarView.AlphabetSideBarView
import com.app.recyclerviewtracker.databinding.ActivityAlphabetBinding
import com.app.recyclerviewtracker.model.DataModel

class AlphabetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlphabetBinding
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlphabetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
    }

    private fun setAdapter() {
        val data = ArrayList<DataModel>().apply {
            add(DataModel("Alice"))
            add(DataModel("Bob"))
            add(DataModel("Charlie"))
            add(DataModel("David"))
            add(DataModel("Eve"))
            add(DataModel("Frank"))
            add(DataModel("Grace"))
            add(DataModel("Hank"))
            add(DataModel("Ivy"))
            add(DataModel("Jack"))
            add(DataModel("Karen"))
            add(DataModel("Leo"))
            add(DataModel("Mona"))
            add(DataModel("Nina"))
            add(DataModel("Oscar"))
            add(DataModel("Paul"))
            add(DataModel("Quincy"))
            add(DataModel("Rachel"))
            add(DataModel("Steve"))
            add(DataModel("Tina"))
            add(DataModel("Uma"))
            add(DataModel("Victor"))
            add(DataModel("Wendy"))
            add(DataModel("Xander"))
            add(DataModel("Yara"))
            add(DataModel("Zane"))
            add(DataModel("Aaron"))
            add(DataModel("Beth"))
            add(DataModel("Cathy"))
            add(DataModel("Doug"))
            add(DataModel("Ella"))
            add(DataModel("Fred"))
            add(DataModel("Gina"))
            add(DataModel("Harry"))
            add(DataModel("Isabel"))
            add(DataModel("James"))
            add(DataModel("Kathy"))
            add(DataModel("Liam"))
            add(DataModel("Megan"))
            add(DataModel("Nick"))
            add(DataModel("Olivia"))
            add(DataModel("Pete"))
            add(DataModel("Queenie"))
            add(DataModel("Randy"))
            add(DataModel("Sam"))
            add(DataModel("Tara"))
            add(DataModel("Ursula"))
            add(DataModel("Vince"))
            add(DataModel("Will"))
            add(DataModel("Xenia"))
            add(DataModel("Yvonne"))
            add(DataModel("Zara"))
            add(DataModel("Albert"))
            add(DataModel("Brenda"))
            add(DataModel("Carl"))
            add(DataModel("Dana"))
            add(DataModel("Ethan"))
            add(DataModel("Fiona"))
            add(DataModel("George"))
            add(DataModel("Holly"))
            add(DataModel("Ian"))
            add(DataModel("Judy"))
            add(DataModel("Kevin"))
            add(DataModel("Linda"))
            add(DataModel("Mark"))
            add(DataModel("Nora"))
            add(DataModel("Owen"))
            add(DataModel("Patty"))
            add(DataModel("Quinn"))
            add(DataModel("Rita"))
            add(DataModel("Scott"))
            add(DataModel("Tess"))
            add(DataModel("Ulysses"))
            add(DataModel("Violet"))
            add(DataModel("Walter"))
            add(DataModel("Xavier"))
            add(DataModel("Yvette"))
            add(DataModel("Zack"))
            add(DataModel("Andrew"))
            add(DataModel("Brittany"))
            add(DataModel("Cody"))
            add(DataModel("Diana"))
            add(DataModel("Elliot"))
            add(DataModel("Faith"))
            add(DataModel("Gabe"))
            add(DataModel("Hannah"))
            add(DataModel("Isaac"))
            add(DataModel("Jasmine"))
            add(DataModel("Kyle"))
            add(DataModel("Laura"))
            add(DataModel("Mason"))
            add(DataModel("Natalie"))
            add(DataModel("Omar"))
            add(DataModel("Paula"))
            add(DataModel("Ralph"))
            add(DataModel("Sophie"))
            add(DataModel("Theo"))
            add(DataModel("Umar"))
            add(DataModel("Vanessa"))
            add(DataModel("Wade"))
            add(DataModel("Xander"))
            add(DataModel("Yasmin"))
            add(DataModel("Zachary"))
            add(DataModel("Daisy"))
        }

        adapter = ListAdapter(data)
        binding.recyclerView.adapter = adapter

        binding.customSideBarView.setOnIndexChangedListener(object : AlphabetSideBarView.OnIndexChangedListener {
            override fun onIndexChanged(letter: String) {
                val position = getPositionForSection(letter)
                if (position >= 0) {
                    (binding.recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 0)
                }
            }
        })
    }

    private fun getPositionForSection(letter: String): Int {
        for (i in adapter.list.indices) {
            val item = adapter.list[i]
            if (item.title.startsWith(letter, ignoreCase = true)) {
                return i
            }
        }
        return -1
    }
}