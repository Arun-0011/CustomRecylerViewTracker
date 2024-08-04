package com.app.recyclerviewtracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.recyclerviewtracker.adapter.ListAdapter
import com.app.recyclerviewtracker.customSideBarView.CustomThumbSideBarView
import com.app.recyclerviewtracker.databinding.ActivityThumbBinding
import com.app.recyclerviewtracker.model.DataModel

class ThumbActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThumbBinding
    private lateinit var adapter: ListAdapter
    private var isThumbDragging = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThumbBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setAdapter() {
        val data = ArrayList<DataModel>().apply {
            for (i in 1..100) {
                add(DataModel("Item $i"))
            }
        }

        adapter = ListAdapter(data)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // Set listener for thumb position changes
        binding.customThumbSideBarView.setOnThumbPositionChangedListener(object :
            CustomThumbSideBarView.OnThumbPositionChangedListener {
            override fun onThumbPositionChanged(position: Float) {
                if (!isThumbDragging) return
                val totalItemCount = adapter.itemCount
                val targetPosition = (position * totalItemCount).toInt()
                layoutManager.scrollToPositionWithOffset(targetPosition, 0)
            }
        })

        // Set RecyclerView scroll listener
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (isThumbDragging) return

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                // Calculate the scroll position ratio
                val scrollPosition =
                    firstVisibleItemPosition.toFloat() / (totalItemCount - visibleItemCount).toFloat()
                binding.customThumbSideBarView.setThumbPosition(scrollPosition)
            }
        })

        // Set touch listener for the thumb to detect dragging state
        binding.customThumbSideBarView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> isThumbDragging = true
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> isThumbDragging = false
            }
            false
        }
    }
}
