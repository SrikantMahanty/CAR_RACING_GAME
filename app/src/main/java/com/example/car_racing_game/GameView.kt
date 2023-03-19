package com.example.car_racing_game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class GameView(var c: Context, var gameTask: GameTask) : View(c) {

    private var myPaint: Paint? = null
    private var speed = 1
    private var time = 0
    private var score = 0
    private var carPosition = 0
    private val otherCars = ArrayList<HashMap<String, Any>>()
    private var viewWidth = 0
    private var viewHeight = 0

    init {
        myPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        viewWidth = this.measuredWidth
        viewHeight = this.measuredHeight
        if (time % 700 < 10 + speed) {
            val map = HashMap<String, Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            otherCars.add(map)

        }
        time = time + 10 + speed
        val carWidth = viewWidth / 5
        var carHeight = carWidth + 10
        myPaint!!.style = Paint.Style.FILL
        val d = resources.getDrawable(R.drawable.car3, null)
        d.setBounds(
            carPosition * viewWidth / 3 + viewWidth / 15 + 25,
            viewHeight - 2 - carHeight,
            carPosition * viewWidth / 3 + viewWidth / 15 + carWidth - 25,
            viewHeight - 2


        )
        d.draw(canvas!!)
        myPaint!!.color=Color.GREEN
        var highScore = 0

        for (i in otherCars.indices) {
            try {
                val carX = otherCars[i]["lane"] as Int * viewWidth / 3 + viewWidth / 14
                val cary = time - otherCars[i]["startTime"] as Int
                val d2 = resources.getDrawable(R.drawable.car4, null)
                d2.setBounds(
                    carX + 25,
                    cary - carHeight,
                    carX + carWidth - 25,
                    cary
                )
                d2.draw(canvas)
                if (otherCars[i]["lane"] as Int == carPosition) {
                    if (cary > viewHeight - 2 - carHeight
                        && cary < viewHeight - 2
                    ) {
                        gameTask.closeGame(score)

                    }
                }
                if (cary > viewHeight + carHeight) {
                    otherCars.removeAt(i)
                    score++
                    speed = 1 + Math.abs(score / 7)
                    if (score > highScore) {
                        highScore = score
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        myPaint!!.color = Color.LTGRAY
        myPaint!!.textSize = 42f
        canvas.drawText("Score : $score", 80f, 80f, myPaint!!)
        canvas.drawText("Speed : $speed", 380f, 80f, myPaint!!)
        invalidate()


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                val x1 = event.x
                if (x1 < viewWidth / 2) {
                    if (carPosition > 0) {
                        carPosition--
                    }
                    }
                    if (x1 > viewWidth / 2) {
                        if (carPosition<2){
                        carPosition++
                    }
                }
                invalidate()

            }
            MotionEvent.ACTION_UP->{

            }
        }


        return true

    }


}