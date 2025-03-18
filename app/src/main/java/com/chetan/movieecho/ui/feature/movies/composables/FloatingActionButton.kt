package com.chetan.movieecho.ui.feature.movies.composables


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import com.chetan.movieecho.R

@Composable
fun RandomMovieFloatingActionButton(
    onRandomMovieClick: () -> Unit
){
    FloatingActionButton(
        onClick = onRandomMovieClick,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.size(dimensionResource(R.dimen.random_movie_fab_size) )
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_random),
            contentDescription = "Random Movie"
            , modifier = Modifier.size(dimensionResource(R.dimen.random_movie_fab_icon_size))
        )
    }
}
@Preview( showBackground = true)
@Composable
fun RandomMovieFloatingActionButtonPreview(){
    RandomMovieFloatingActionButton(onRandomMovieClick = {})
}