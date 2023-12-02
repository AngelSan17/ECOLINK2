package me.sandria.ecolink

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicialización de las vistas
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        // Configuración del botón de inicio de sesión
        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            performLogin()
        }

        // Configuración del botón de registro
        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            // Aquí deberías iniciar la actividad de registro
            // Ejemplo: startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun performLogin() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (validateInput(username, password)) {
            // Aquí agregarías la lógica para verificar las credenciales
            // Simularemos que la autenticación es exitosa y navegaremos a la siguiente pantalla
            val intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent)
            finish() // Finaliza LoginActivity para que el usuario no pueda volver a ella
        } else {
            // Mostrar mensaje de error
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        // Aquí deberías implementar la lógica para validar el nombre de usuario y la contraseña
        // Por ejemplo, verificar que el nombre de usuario no esté vacío y que la contraseña cumpla con ciertos requisitos
        return username.isNotEmpty() && password.isNotEmpty()
    }
}

