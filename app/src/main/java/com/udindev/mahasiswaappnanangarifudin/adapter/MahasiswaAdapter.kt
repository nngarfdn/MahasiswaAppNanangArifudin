package com.iniudin.githubuserapp.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.udindev.mahasiswaappnanangarifudin.R
import com.udindev.mahasiswaappnanangarifudin.activity.DetailActivity
import com.udindev.mahasiswaappnanangarifudin.model.getdata.DataItem
import kotlinx.android.synthetic.main.item_mahasiswa.view.*


class MahasiswaAdapter(private val list: List<DataItem?>?) :
    RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa, parent, false)
    )

    override fun getItemCount(): Int = list?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.txt_nama_mahasiswa.text = list?.get(position)?.mahasiswaNama


        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MAHASISWA, list?.get(position))
            holder.itemView.context.startActivity(intent)
            Toast.makeText(holder.itemView.context, "${list?.get(position)?.mahasiswaNama}", Toast.LENGTH_SHORT).show()
        }
    }



}