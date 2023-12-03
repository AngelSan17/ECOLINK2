package me.sandria.ecolink

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


    // ... el resto de tu código ...


class RegisterActivity : AppCompatActivity() {
    // Declaración de variables para los campos de entrada y el botón de registro
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicialización de variables con los IDs de los EditTexts y Button del layout
        nameEditText = findViewById(R.id.etName)
        surnameEditText = findViewById(R.id.etSurname)
        emailEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etPassword)
        registerButton = findViewById(R.id.registerBotton)

        registerButton.setOnClickListener { onRegisterClicked() }
    }

    private fun onRegisterClicked() {
        // Obtener los valores ingresados por el usuario
        val name = nameEditText.text.toString()
        val surname = surnameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Aquí debes implementar la lógica para registrar al usuario,
        // por ejemplo, validación de campos, guardar en base de datos, etc.

        // Por ahora, solo vamos a mostrar un mensaje de registro exitoso
        if (name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            // Registro exitoso, puedes iniciar LoginActivity o mostrar un mensaje
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

            // Intenta iniciar LoginActivity aquí si es necesario
        } else {
            // Mostrar mensaje de error
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
