package com.syahrul.loginregistersqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_daftar.*
import kotlinx.android.synthetic.main.user_masuk.*

class MainActivity : AppCompatActivity() {

    lateinit var handler: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = DbHelper(this)

        beranda()

        id_daftar.setOnClickListener{
            daftar()
        }

        id_masuk.setOnClickListener {
            masuk()
        }

        simpan.setOnClickListener {
            handler.insertUser(username_df.text.toString(),email_df.text.toString(),password_df.text.toString())
            Toast.makeText(this, "Daftar Berhasil", Toast.LENGTH_SHORT).show()
            beranda()
        }

        login.setOnClickListener {
            if(handler.userPresent(email_msk.text.toString(),password_msk.text.toString())) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, Dashboard::class.java))
            }else
                Toast.makeText(this,"Login Gagal",Toast.LENGTH_SHORT).show()
        }
    }

    private fun daftar(){
        layout_daftar.visibility = View.VISIBLE
        layout_login.visibility = View.GONE
        beranda.visibility = View.GONE
    }

    private fun masuk(){
        layout_daftar.visibility = View.GONE
        layout_login.visibility = View.VISIBLE
        beranda.visibility = View.GONE
    }
    private fun beranda(){
        layout_daftar.visibility = View.GONE
        layout_login.visibility = View.GONE
        beranda.visibility = View.VISIBLE
    }
}
