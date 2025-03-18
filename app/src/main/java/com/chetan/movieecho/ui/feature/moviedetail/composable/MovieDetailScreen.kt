package com.chetan.movieecho.ui.feature.moviedetail.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.chetan.movieecho.R
import com.chetan.movieecho.data.model.TopMoviesResponseItem
import com.chetan.movieecho.data.model.buildTopMoviesResponseItemPreview
import com.chetan.movieecho.ui.feature.moviedetail.MovieDetailContract
import com.chetan.movieecho.ui.feature.moviedetail.MovieDetailViewModel

@JvmOverloads
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    topMoviesResponseItem: TopMoviesResponseItem,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onNavigationRequested: (navigationEffect: MovieDetailContract.Effect.Navigation) -> Unit
){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                windowInsets = TopAppBarDefaults.windowInsets,

                title = {
                    Text(text = topMoviesResponseItem.primaryTitle,
                        color = MaterialTheme.colorScheme.onPrimary,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,

                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),


                navigationIcon = {
                    IconButton(
                        colors = IconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.primary,
                            disabledContentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        onClick = { onNavigationRequested(MovieDetailContract.Effect.Navigation.OnBackClicked)}) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = topMoviesResponseItem.primaryImage,
                contentDescription = topMoviesResponseItem.primaryTitle,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = topMoviesResponseItem.primaryTitle, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = topMoviesResponseItem.description, style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "⭐ ${topMoviesResponseItem.averageRating} (${topMoviesResponseItem.numVotes} votes)", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Open IMDB link */ }) {
                Text(text = "View on IMDB")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview(){
    MovieDetailScreen(
        topMoviesResponseItem = buildTopMoviesResponseItemPreview(),
onNavigationRequested = {}
    )
}
