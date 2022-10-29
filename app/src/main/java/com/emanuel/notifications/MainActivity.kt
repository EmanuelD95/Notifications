package com.emanuel.notifications

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.emanuel.notifications.MensajeActivity.Companion.NOTIFICACIONES
import com.emanuel.notifications.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val bundle = Bundle()
    //lateinit var vNotification: View

    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
        if (activityResult.resultCode == RESULT_OK){
            val valor = activityResult.data?.getBooleanExtra(NOTIFICACIONES, false)
            // Validacion Notificacion
            if (valor == true) {
                //vNotification.visibility = View.GONE
                binding.vNotification.visibility = View.GONE
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener {
            // Capturar mensaje y guardar en objeto bundle
            val mensaje = binding.etMensaje.text.toString()
            bundle.putString("key_mensaje", mensaje)

            // Mostrar notificacion
            binding.vNotification.visibility = View.VISIBLE

            // Ocutar teclado
            binding.btnEnviar.hideKeyboard()
        }

        binding.btnMensaje.setOnClickListener {
            val intent = Intent(this, MensajeActivity::class.java)
            intent.putExtras(bundle)
            //startActivity(intent)
            responseLauncher.launch(intent)
        }

    }

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instanciar vistas
        val etMensaje = findViewById<EditText>(R.id.etMensaje)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val btnMensaje = findViewById<Button>(R.id.btnMensaje)
        vNotification = findViewById<View>(R.id.vNotification)

        btnEnviar.setOnClickListener {
            // Capturar mensaje y guardar en objeto bundle
            val mensaje = etMensaje.text.toString()
            bundle.putString("key_mensaje", mensaje)

            // Mostrar notificacion
            vNotification.visibility = View.VISIBLE

            // Ocutar teclado
            btnEnviar.hideKeyboard()
        }

        btnMensaje.setOnClickListener {
            val intent = Intent(this, MensajeActivity::class.java)
            intent.putExtras(bundle)
            //startActivity(intent)
            responseLauncher.launch(intent)
        }

    }
*/

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}