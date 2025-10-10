package com.example.cameraxapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cameraxapp.databinding.ActivityCompleteBinding

class CompleteActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCompleteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        /*
        val byteArray = intent.getByteArrayExtra("captured_bitmap")
        byteArray?.let {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            viewBinding.capturedImageView.setImageBitmap(bitmap)
        }
        */
        val imagePath = intent.getStringExtra("image_path")
        val correctedBitmap = getCorrectlyOrientedBitmap(imagePath!!)
        viewBinding.capturedImageView.setImageBitmap(correctedBitmap)
    }

    private fun getCorrectlyOrientedBitmap(imagePath: String): Bitmap {
        val bitmap = BitmapFactory.decodeFile(imagePath)
        val exif = ExifInterface(imagePath)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )

        val matrix = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> matrix.preScale(-1f, 1f)
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> matrix.preScale(1f, -1f)
        }

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}