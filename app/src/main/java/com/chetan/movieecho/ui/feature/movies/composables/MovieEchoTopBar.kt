package com.chetan.movieecho.ui.feature.movies.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chetan.movieecho.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieEchoTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.movie_screen_top_bar_title)
                ,color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}
@Preview( showBackground = true)
@Composable
fun MovieEchoTopBarPreview(){
    MovieEchoTopBar()
}