package com.app.recyclerviewtracker.customSideBarView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.app.recyclerviewtracker.R

class AlphabetSideBarView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var onIndexChangedListener: OnIndexChangedListener? = null
    private var textSize = 20f
    private var textColor = Color.BLACK
    private var selectedTextSize = 40f
    private var selectedTextColor = Color.RED
    private var selectedIndex = -1

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomSideBarView,
            0, 0
        ).apply {
            try {
                textSize = getDimension(R.styleable.CustomSideBarView_textSize, textSize)
                textColor = getColor(R.styleable.CustomSideBarView_textColor, textColor)
            } finally {
                recycle()
            }
        }
    }

    private val paint = Paint().apply {
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val heightPerLetter = height / indexLetters.size.toFloat()

        for (i in indexLetters.indices) {
            paint.textSize = if (i == selectedIndex) selectedTextSize else textSize
            paint.color = if (i == selectedIndex) selectedTextColor else textColor

            val xPos = width / 2
            val yPos = heightPerLetter * i + heightPerLetter

            canvas.drawText(indexLetters[i], xPos.toFloat(), yPos, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val y = event.y
        val newSelectedIndex = (y / height * indexLetters.size).toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                if (newSelectedIndex != selectedIndex && newSelectedIndex in indexLetters.indices) {
                    selectedIndex = newSelectedIndex
                    onIndexChangedListener?.onIndexChanged(indexLetters[selectedIndex])
                    invalidate()
                }
            }

            MotionEvent.ACTION_UP -> {
                selectedIndex = -1
                invalidate()
            }
        }
        return true
    }

    fun setOnIndexChangedListener(listener: OnIndexChangedListener) {
        this.onIndexChangedListener = listener
    }

    interface OnIndexChangedListener {
        fun onIndexChanged(letter: String)
    }

    private val indexLetters = arrayOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z"
    )
}
