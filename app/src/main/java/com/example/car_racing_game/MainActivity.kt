package com.example.car_racing_game

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), GameTask {

    lateinit var rootLayout: LinearLayout
    lateinit var startbtn: Button
    lateinit var mGameview: GameView
    lateinit var score: TextView
    lateinit var restart: Button
    lateinit var txtview: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startbtn = findViewById(R.id.startBtn)
        rootLayout = findViewById(R.id.root_layout)
        score = findViewById(R.id.id_Score)
        restart = findViewById(R.id.restartBtn)
        txtview = findViewById(R.id.txtview)
        restart.setOnClickListener {
            finish()

            startActivity(intent)
            overridePendingTransition(0, 1)
            Toast.makeText(this, "Restart game", Toast.LENGTH_SHORT).show()
        }

        mGameview = GameView(this, this)
        startbtn.setOnClickListener {
            mGameview.setBackgroundResource(R.drawable.road2)
            rootLayout.addView(mGameview)
            startbtn.visibility = View.GONE
            score.visibility = View.GONE
            restart.visibility = View.GONE
            txtview.visibility = View.GONE


        }


    }

    override fun closeGame(mScore: Int) {
        score.text = "Score: $mScore"
        rootLayout.removeView(mGameview)
        startbtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        restart.visibility = View.VISIBLE
        txtview.visibility = View.VISIBLE

    }

}