package com.example.moviesseal.movies.view

import android.content.Intent
import android.provider.AlarmClock
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.moviesseal.activity.MainActivity
import com.example.moviesseal.commons.CONSTANTS

@Composable
fun MoviesView (userName: String){
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()){
        Button(onClick = {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra(CONSTANTS.LOG_OUT, CONSTANTS.LOG_OUT)
            }
            context.startActivity(intent)
        }) {
            Text(text = "Cerrar sesión")
        }
        Text(text = userName)
    }
}