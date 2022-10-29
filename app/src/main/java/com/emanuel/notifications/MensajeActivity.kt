package com.emanuel.notifications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MensajeActivity : AppCompatActivity() {

    companion object{
            const val NOTIFICACIONES = "NOTIFICACIONES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        // Instanciar vistas
        val tvMensaje = findViewById<TextView>(R.id.tvMensaje)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Recibir datos
        val bundle = intent.extras
        val mensajes = bundle!!.getString("key_mensaje")

        // Mostrar dato
        tvMensaje.text = mensajes

        btnVolver.setOnClickListener {
            val intent = Intent()
            intent.putExtra(NOTIFICACIONES,true)
            setResult(RESULT_OK, intent)
            //finish()
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}