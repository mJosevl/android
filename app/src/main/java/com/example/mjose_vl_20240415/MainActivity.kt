package com.example.mjose_vl_20240415

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    companion object {
        var openCount = 0  // Variable estática para contar aperturas
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener SharedPreferences
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        openCount = prefs.getInt("openCount", 0) // Obtener el contador desde SharedPreferences, 0 es el valor por defecto

        openCount++  // Incrementar el contador cada vez que se crea la actividad
        Log.d("MainActivity", "La aplicación se ha abierto $openCount veces.")

        // Almacenar el nuevo valor de openCount en SharedPreferences
        val editor = prefs.edit()
        editor.putInt("openCount", openCount)
        editor.apply()

        // Configuración de los componentes de la UI
        val sportInput = findViewById<EditText>(R.id.sportInput)
        val timeInput = findViewById<EditText>(R.id.timeInput)
        val showButton = findViewById<Button>(R.id.showButton)

        // Establecer el evento onClick para el botón
        showButton.setOnClickListener {
            val sport = sportInput.text.toString().trim()
            val time = timeInput.text.toString().trim()

            if (sport.isEmpty() || time.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Please enter both sport and time.")
                    .setPositiveButton("OK", null)
                    .show()
            } else {
                Log.d("Training Info", "Sport: $sport, Time: $time minutes")
                AlertDialog.Builder(this)
                    .setTitle("Training Details")
                    .setMessage("Sport: $sport\nTime: $time minutes")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}
