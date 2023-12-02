package me.sandria.ecolink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class RegisterActivity : AppCompatActivity() {
    // Declaración correcta de variables
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicialización de variables
        usernameEditText = findViewById(R.id.usernameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener { onRegisterClicked() }
    }

    private fun onRegisterClicked() {
        val username = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (password == confirmPassword) {
            // Guardar el usuario en SharedPreferences (no seguro para producción)
            val sharedPreferences = getSharedPreferences("ECOLINK_PREFS", MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putString("USERNAME", username)
                putString("EMAIL", email)
                // NUNCA almacenes contraseñas en texto plano. Esto es solo un ejemplo.
                putString("PASSWORD", password)
                apply()
            }

            // Navegar a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Mostrar mensaje de que las contraseñas no coinciden
        }
    }
}