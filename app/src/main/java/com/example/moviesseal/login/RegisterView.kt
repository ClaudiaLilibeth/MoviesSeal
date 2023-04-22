package com.example.moviesseal.login.view

import android.content.Intent
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moviesseal.commons.CONSTANTS
import com.example.moviesseal.login.AuthViewModel
import com.example.moviesseal.login.data.Resource
import com.example.moviesseal.movies.view.MoviesActivity

@Composable
fun RegisterView(
    registerViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val signUp = rememberSaveable { mutableStateOf(true) }
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val name = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
//#firebase 5 view uses flows from vm
    val loginFlow = registerViewModel.loginFlow.collectAsState()
    val signUpFlow = registerViewModel.signupFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(signUp.value) {
            //Nombre
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        // Email
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Correo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Password
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (passwordVisible.value)
                Icon(painterResource(id = com.google.android.material.R.drawable.design_ic_visibility), "visibility on")
                else Icon(painterResource(id = com.google.android.material.R.drawable.design_ic_visibility_off), "visivility off")

                IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Sign up - register
        Button(
            onClick = {
                if(signUp.value)
                    registerViewModel.signupUser(name.value, email.value, password.value)
                else
                    registerViewModel.loginUser(email.value, password.value)
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text( if(signUp.value)"Registrarse" else "Iniciar sesión")
        }

        // Log in - iniciar sesiòn
        TextButton(
           onClick = {
               signUp.value = !signUp.value
                },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                if(signUp.value) "¿Ya tienes una cuenta? Inicia sesión"
            else "¿No tienes una cuenta? Regístrate"
            )
        }

        loginFlow.value?.let {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(context, "ERROR EN INICIO DE SESION.", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp))
                }
                is Resource.Success -> {
                    Toast.makeText(context, "INICIO DE SESIÓN CORRECTA", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MoviesActivity::class.java).apply {
                        putExtra(CONSTANTS.NAME, registerViewModel.currentUser?.displayName)
                    }
                    context.startActivity(intent)
                }
            }
        }

        signUpFlow.value?.let {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(context, "ERROR EN REGISTRO.", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp))
                }
                is Resource.Success -> {
                    Toast.makeText(context, "REGISTRO CORRECTO", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MoviesActivity::class.java).apply {
                        putExtra(CONSTANTS.NAME, registerViewModel.currentUser?.displayName)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}

