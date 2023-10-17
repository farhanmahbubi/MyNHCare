package com.example.mynhcare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.inputEmail)
        passwordInput = findViewById(R.id.inputPass)
        loginButton = findViewById(R.id.buttonLogin)
        registerButton = findViewById(R.id.buttonRegister)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username == "user123" && password == "password123") {
                // Login berhasil, alihkan ke aktivitas lain (misalnya, MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Login gagal, berikan pesan kesalahan
                Toast.makeText(this, "Login gagal. Periksa username dan password.", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            // Tambahkan logika untuk mengarahkan pengguna ke form registrasi
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

