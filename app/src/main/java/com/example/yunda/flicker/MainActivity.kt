package com.example.yunda.flicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.yunda.flicker.utils.GlideUtil
import com.example.yunda.flicker.view.ImgDialogFragment
import java.io.IOException


class MainActivity : AppCompatActivity() {
    val PICK_IMAGE = 1

    var imgUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_choose.setOnClickListener({
            val gallIntent = Intent(Intent.ACTION_GET_CONTENT)
            gallIntent.type = "image/*"
            startActivityForResult(Intent.createChooser(gallIntent, "Select Picture"), PICK_IMAGE)
        })
        iv_main.setOnClickListener {
            if (imgUri!=null){
                ImgDialogFragment(imgUri!!).show(supportFragmentManager, "imgdialog")
            }else{
                Toast.makeText(this,"请选择图片",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === PICK_IMAGE && resultCode === RESULT_OK && data != null && data.getData() != null) {
            val uri = data.getData()
            imgUri = uri
            try {
                GlideUtil.loadvagueImg(this,uri,iv_main)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }
}
