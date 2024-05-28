package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView

class splashscreen : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splashscreen)
        var img=findViewById<ImageView>(R.id.img1)
        var t1=findViewById<TextView>(R.id.t1)


        img.animate().apply{
            duration=2000
            rotationBy(360f)
        }.start()




        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 2000
        t1.startAnimation(fadeIn)



        Handler().postDelayed({
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()

        }, 3000)

    }
}