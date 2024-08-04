package com.app.recyclerviewtracker.customSideBarView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import com.app.recyclerviewtracker.R

class CustomThumbSideBarView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var thumbHeight: Float
    private var thumbColor: Int
    private var trackerColor: Int
    private var thumbPosition: Float = 0f
    private var thumbPaint = Paint()
    private var trackerPaint = Paint()
    private var onThumbPositionChangedListener: OnThumbPositionChangedListener? = null
    private var isDragging = false
    private var cornerRadius: Float

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomThumbSideBarView)
        thumbColor =
            typedArray.getColor(R.styleable.CustomThumbSideBarView_thumbColor, Color.DKGRAY)
        trackerColor =
            typedArray.getColor(R.styleable.CustomThumbSideBarView_trackerColor, Color.LTGRAY)
        thumbHeight =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40f, resources.displayMetrics)
        cornerRadius =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics)
        typedArray.recycle()

        thumbPaint.color = thumbColor
        thumbPaint.isAntiAlias = true

        trackerPaint.color = trackerColor
        trackerPaint.isAntiAlias = true
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw tracker background with rounded corners
        val trackerRect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(trackerRect, cornerRadius, cornerRadius, trackerPaint)

        // Draw thumb with rounded corners
        val thumbTop = thumbPosition * (height - thumbHeight)
        val thumbRect = RectF(0f, thumbTop, width.toFloat(), thumbTop + thumbHeight)
        canvas.drawRoundRect(thumbRect, cornerRadius, cornerRadius, thumbPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                isDragging = true
                val y = event.y
                thumbPosition = y / height
                thumbPosition = thumbPosition.coerceIn(0f, 1f)

                onThumbPositionChangedListener?.onThumbPositionChanged(thumbPosition)
                invalidate()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isDragging = false
            }
        }
        return true
    }

    fun setOnThumbPositionChangedListener(listener: OnThumbPositionChangedListener) {
        this.onThumbPositionChangedListener = listener
    }

    fun setThumbPosition(position: Float) {
        thumbPosition = position.coerceIn(0f, 1f)
        invalidate()
    }

    interface OnThumbPositionChangedListener {
        fun onThumbPositionChanged(position: Float)
    }
}
