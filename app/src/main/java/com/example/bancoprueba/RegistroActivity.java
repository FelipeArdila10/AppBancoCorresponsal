package com.example.bancoprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Corresponsal.CorrespondentBankUser;
import com.example.Datos;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText passwordConfirm;
    TextInputEditText phone;
    TextInputEditText id;
    Button crearCuenta;
    Datos data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();
        data = new Datos(this);



        name = findViewById(R.id.nameUser);
        email = findViewById(R.id.email);
        password = findViewById(R.id.passwordRegister);
        passwordConfirm = findViewById(R.id.tiPassword);
        phone = findViewById(R.id.numberPhone);
        id = findViewById(R.id.idRegister);

        crearCuenta = findViewById(R.id.retirarDinero);
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = password.getText().toString();
                String pinConfirm = passwordConfirm.getText().toString();
                String emailR = email.getText().toString();

                CorrespondentBankUser usuario;
                usuario = new CorrespondentBankUser(
                        id.getText().toString(),
                        name.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        phone.getText().toString()
                );
                usuario.setSaldo(1000000);
                if (Datos.checkEmail(emailR)) {
                    data.open();
                    data.insertUsuario(usuario);
                    Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Correo valido", Toast.LENGTH_SHORT).show();
                } else {
                    email.setError("El correo no es valido");
                }

                if (data.validatePassword(password)) {
                    Toast.makeText(RegistroActivity.this, "Contraseña Segura", Toast.LENGTH_SHORT).show();

                } else {
                    password.setError("Contraseña muy debil");
                }
                if (pinConfirm.equals(pin)) {
                    Toast.makeText(RegistroActivity.this, "Se agrego el usuario", Toast.LENGTH_SHORT).show();
                } else {
                    passwordConfirm.setError("Error al registrarse contrasñas no coinciden");
                }


            }
        });
    }
}