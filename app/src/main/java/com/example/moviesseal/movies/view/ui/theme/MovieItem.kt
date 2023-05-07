package com.example.moviesseal.movies.view.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c.Constants
import coil.compose.AsyncImage
import com.example.moviesseal.R
import com.example.moviesseal.remote.movies.models.Movie

@Composable
fun MovieItem(movieItem: Movie, onClick: (Movie) -> Unit) {
    val offset = Offset(3.0f, 5.0f)
    Row(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.size(width = 200.dp, height = 200.dp)) {
            Text(
                text = movieItem.title,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 16.dp)
                    .clickable {
                        onClick.invoke(movieItem)
                    },
                fontWeight = FontWeight.Light,
                style = TextStyle(
                    fontSize = 20.sp,
                    shadow = Shadow(
                        color = colorResource(id = R.color.teal_700),
                        offset = offset,
                        blurRadius = 3f
                    )
                ),
                color = colorResource(id = R.color.fourth_soft),
            )
            Text(
                text = "(" + movieItem.release_date.take(4) + ")",
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                fontWeight = FontWeight.Light,
            )

            Row(modifier = Modifier.padding(start = 16.dp)) {
                Icon(
                    painter =
                    if (movieItem.vote_average > 7) painterResource(id = R.drawable.star)
                    else if (movieItem.vote_average > 5) painterResource(id = R.drawable.halfstar)
                    else painterResource(id = R.drawable.emptystar),
                    tint = colorResource(id = R.color.third_soft),
                    contentDescription = "star"
                )
                Text(
                    text = movieItem.vote_average.toString(),
                    fontWeight = FontWeight.Light,
                )
            }
        }

        AsyncImage(
            modifier = Modifier
                .clickable {
                    onClick.invoke(movieItem)
                }
                .size(150.dp, 200.dp)
                .padding(start = 24.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    border = BorderStroke(10.dp, colorResource(id = R.color.teal_700)),
                    shape = RoundedCornerShape(10),
                ),
            model = Constants.POSTER_URL + movieItem.poster_path,
            contentDescription = null
        )
    }
}