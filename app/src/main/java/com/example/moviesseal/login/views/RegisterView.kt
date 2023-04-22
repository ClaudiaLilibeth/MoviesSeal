package com.example.moviesseal.login.views

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moviesseal.R
import com.example.moviesseal.login.utils.CONSTANTS
import com.example.moviesseal.login.AuthViewModel
import com.example.moviesseal.login.data.Resource
import com.example.moviesseal.movies.view.MoviesActivity

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterView(
    registerViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val signUp = rememberSaveable { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current
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
                onValueChange = { if(it.length<= CONSTANTS.MAX_CHAR) name.value = it },
                label = { Text(stringResource(R.string.nombre)) },
                singleLine = true,
                maxLines = 1,
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()}
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        // Email
        OutlinedTextField(
            value = email.value,
            onValueChange = { if(it.length<= CONSTANTS.MAX_MAIL_CHAR) email.value = it },
            label = { Text(stringResource(R.string.correo)) },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Password
        OutlinedTextField(
            value = password.value,
            onValueChange = { if(it.length<= CONSTANTS.MAX_CHAR) password.value = it },
            label = { Text(stringResource(R.string.contrasenia)) },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,  imeAction = ImeAction.Done),
            singleLine = true,
            maxLines = 1,
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}
            ),
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
            Text( if(signUp.value) stringResource(R.string.registrarse) else stringResource(R.string.iniciar_sesion))
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
                if(signUp.value) stringResource(R.string.ya_tienes_cuenta)
            else stringResource(R.string.registrate)
            )
        }

        loginFlow.value?.let {
            when (it) {
                is Resource.Error -> {
                   Log.e("ERROR", "ERROR EN INICIO DE SESIÒN")
                    registerViewModel.logout()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp))
                }
                is Resource.Success -> {
                    Toast.makeText(context, stringResource(R.string.inicio_sesion_correcta), Toast.LENGTH_SHORT).show()
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
                    Log.e("ERROR", "ERROR EN REGISTRO")
                    val message = it.message?: ""
                    if(message.contains(CONSTANTS.MAIL_ALREADY_IN_USE)){
                        Toast.makeText(context, stringResource(R.string.correo_ya_registrado), Toast.LENGTH_SHORT).show()
                    }
                    else if (message.contains(CONSTANTS.MAIL_BAD_FORMAT)) {
                        Toast.makeText(context, stringResource(R.string.correo_mal_escrito), Toast.LENGTH_SHORT).show()
                    }
                    registerViewModel.logout()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp))
                }
                is Resource.Success -> {
                    Toast.makeText(context, stringResource(R.string.registro_correcto), Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MoviesActivity::class.java).apply {
                        putExtra(CONSTANTS.NAME, registerViewModel.currentUser?.displayName)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}

