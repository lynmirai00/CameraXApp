package com.example.cameraxapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cameraxapp.databinding.ActivityCompleteBinding

class CompleteActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCompleteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val byteArray = intent.getByteArrayExtra("captured_bitmap")
        byteArray?.let {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            viewBinding.capturedImageView.setImageBitmap(bitmap)
        }
    }
}