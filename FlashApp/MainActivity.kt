package com.example.flashapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    lateinit var img1:ImageView
    private var isActive=false
    lateinit var lay:ConstraintLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img1=findViewById(R.id.image1)
        lightState(isActive)
        lay=findViewById(R.id.l1)


        img1.setOnClickListener {
            if(isActive==false){
                img1.setImageResource(R.drawable.t2)
                isActive=true
                lightState(isActive)
                img1.setBackgroundColor(Color.WHITE)
                lay.setBackgroundColor(Color.WHITE)
            }
            else{
                img1.setImageResource(R.drawable.t3)
                isActive=false
                lightState(isActive)
                img1.setBackgroundColor(Color.BLACK)
                lay.setBackgroundColor(Color.BLACK)
            }
        }
    }

    private fun lightState(state: Boolean) {
        val cameraManager:CameraManager=getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId:String?=null
        try{
            cameraId=cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId,state)
        }catch (e:Exception){
            Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
        }

    }
}