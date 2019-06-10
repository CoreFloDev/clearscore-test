package io.coreflodev.exampleapplication.score.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class ScoreCircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var box: RectF? = null
    private var percent = 0

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.GREEN
        strokeWidth = 10F
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        box = computeBox(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        if (box == null) {
            box = computeBox(width, height)
        }

        val sweep = 360f * percent.toFloat() * 0.01f
        canvas.drawArc(box, -90F, sweep, false, paint)
    }

    private fun computeBox(x: Int, y: Int): RectF {
        val margin = 20F
        val adjustY = if (y > x) {
            (y - x) / 2F
        } else 0F

        return RectF(0F + margin, 0F + margin + adjustY, x - margin, x - margin + adjustY)
    }

    fun setPercent(percent: Int) {
        this.percent = percent
        handler.post { invalidate() }
    }
}