package com.udindev.mahasiswaappnanangarifudin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.udindev.mahasiswaappnanangarifudin.R
import com.udindev.mahasiswaappnanangarifudin.viewmodel.MahasiswaViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var mahasiswaViewModel: MahasiswaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        mahasiswaViewModel = ViewModelProviders.of(this).get(MahasiswaViewModel::class.java)

        btn_add.setOnClickListener {
            val nama = edt_nama.text.toString()
            val alamat = edt_alamat.text.toString()
            val nohp = edt_nohp.text.toString()

            if (nama.isNotEmpty() && alamat.isNotEmpty() && nohp.isNotEmpty()) {
                mahasiswaViewModel.insertData(nama, nohp, alamat)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Pastikan data terisi semua", Toast.LENGTH_SHORT).show()
            }


        }
    }
}