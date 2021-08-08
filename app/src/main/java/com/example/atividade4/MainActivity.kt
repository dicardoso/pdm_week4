package com.example.atividade4

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView

const val CAMERA = 1
class MainActivity : AppCompatActivity() {
    private lateinit var btn_foto: Button
    private lateinit var img_foto: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.img_foto = findViewById(R.id.imgFoto)
        this.btn_foto = findViewById(R.id.btn_foto)
        this.btn_foto.setOnClickListener { OpenCamera() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null) {
            val imageBitmap = data?.getParcelableExtra<Bitmap>("data")
            this.img_foto.setImageBitmap(imageBitmap)
        }
    }

    inner class OpenCamera: View.OnClickListener{
        override fun onClick(v: View?) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, CAMERA)
            }
        }
    }
}