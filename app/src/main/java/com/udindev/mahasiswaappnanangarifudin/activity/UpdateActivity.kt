package com.udindev.mahasiswaappnanangarifudin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.udindev.mahasiswaappnanangarifudin.R
import com.udindev.mahasiswaappnanangarifudin.model.getdata.DataItem
import com.udindev.mahasiswaappnanangarifudin.viewmodel.MahasiswaViewModel
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    private lateinit var mahasiswaViewModel: MahasiswaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        mahasiswaViewModel = ViewModelProviders.of(this).get(MahasiswaViewModel::class.java)

        val mahasiswa = intent.getParcelableExtra<DataItem>(DetailActivity.EXTRA_MAHASISWA)

        edt_nama.setText(mahasiswa?.mahasiswaNama)
        edt_alamat.setText(mahasiswa?.mahasiswaAlamat)
        edt_nohp.setText(mahasiswa?.mahasiswaNohp)

        btn_update.setOnClickListener {
            val id = mahasiswa?.idMahasiswa
            val nama = edt_nama.text.toString()
            val alamat = edt_alamat.text.toString()
            val nohp = edt_nohp.text.toString()

            if (nama.isNotEmpty() && alamat.isNotEmpty() && nohp.isNotEmpty()) {
                mahasiswaViewModel.updateData(id, nama, nohp, alamat)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Pastikan data terisi semua", Toast.LENGTH_SHORT).show()
            }

        }
    }
}