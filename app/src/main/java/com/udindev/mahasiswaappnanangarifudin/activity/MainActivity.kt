package com.udindev.mahasiswaappnanangarifudin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.iniudin.githubuserapp.adapter.MahasiswaAdapter
import com.udindev.mahasiswaappnanangarifudin.R
import com.udindev.mahasiswaappnanangarifudin.model.getdata.ResponseGetData
import com.udindev.mahasiswaappnanangarifudin.viewmodel.MahasiswaViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var mahasiswaViewModel: MahasiswaViewModel
    private lateinit var adapter: MahasiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mahasiswaViewModel = ViewModelProviders.of(this).get(MahasiswaViewModel::class.java)

        getData()

        fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getData() {
        mahasiswaViewModel.loadResults()
        mahasiswaViewModel.getResults().observe(this, Observer<ResponseGetData> { result ->
            Log.d(TAG, "onCreate: $result")
            adapter = MahasiswaAdapter(result.data)
            rv_list_mahasiswa.adapter = adapter
            initRecView()

        })
    }

    private fun initRecView() {
        rv_list_mahasiswa.layoutManager = LinearLayoutManager(this)
        rv_list_mahasiswa.setHasFixedSize(true)
        adapter.notifyDataSetChanged()
    }

}