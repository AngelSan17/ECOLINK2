package me.sandria.ecolink

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Inicialización de variables con los IDs de los EditTexts y Button del layout
        nameEditText = findViewById(R.id.etName)
        surnameEditText = findViewById(R.id.etSurname)
        emailEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etPassword)
        registerButton = findViewById(R.id.registerBotton)

        registerButton.setOnClickListener { onRegisterClicked() }
    }

    private fun onRegisterClicked() {
        val name = nameEditText.text.toString().trim()
        val surname = surnameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            // Registrar al usuario en Firebase
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // El registro fue exitoso
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                        // Aquí puedes también iniciar LoginActivity si lo deseas
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Si falla el registro, muestra un mensaje al usuario
                        Toast.makeText(this, "Registro fallido: ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
