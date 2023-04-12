package com.example.moviesseal.login.view

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moviesseal.login.AuthViewModel
import com.example.moviesseal.login.data.Resource
import io.grpc.Context

@Composable
fun RegisterView(
    context: android.content.Context,
    loginViewModel: AuthViewModel
) {
    val signUp = rememberSaveable { mutableStateOf(true) }
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val name = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    val loginFlow = loginViewModel.loginFlow.collectAsState()

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
                loginViewModel.signupUser(name.value, email.value, password.value)
                else
                    loginViewModel.loginUser(email.value, password.value)
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Registrarse")
        }

        // Log in - iniciar sesiòn
        TextButton(
           onClick = {
               if(signUp.value) signUp.value = false else signUp.value = true
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

        loginFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(context, "ERROR EN REGISTRO. Intente màs tarde", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    //navigation TODO
                    Toast.makeText(context, "Success!!!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

