package com.chetan.movieecho.ui.feature.movies.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.chetan.movieecho.R
import com.chetan.movieecho.data.model.TopMoviesResponseItem
import com.chetan.movieecho.data.model.buildTopMoviesResponseItemPreview

@Composable
fun  MovieListItem(
    topMoviesResponseItem: TopMoviesResponseItem,
    onMovieClick: (TopMoviesResponseItem) -> Unit
) {

    Card(
        shape = MaterialTheme.shapes.small,
       modifier = Modifier.fillMaxWidth()
          .padding(
              horizontal = dimensionResource(R.dimen.padding_small),
              vertical = dimensionResource(R.dimen.padding_xsmall)
          )
          .clickable {
              onMovieClick(topMoviesResponseItem)
          },
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.padding_xsmall))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(
                dimensionResource(R.dimen.padding_medium))
        ){

            // Image Loading with Coil
           AsyncImage(
               placeholder = painterResource(R.drawable.ic_movie_item_place_holder),
               error = painterResource(R.drawable.ic_movie_poster_loading_error),
               model = topMoviesResponseItem.primaryImage,
               contentDescription = "Movie Poster",
               modifier = Modifier.size(dimensionResource(R.dimen.movie_list_item_image_size))
                   .padding(end = dimensionResource(R.dimen.movie_list_item_image_padding_end))
               ,
               contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = topMoviesResponseItem.originalTitle,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_xsmall)),
                    color = MaterialTheme.colorScheme.onBackground
                    ,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_xsmall)))
                Text(
                    text = "Votes: ${topMoviesResponseItem.numVotes}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_xsmall)))
                Text(
                    text = stringResource(R.string.movie_item_rating_label)+"${topMoviesResponseItem.averageRating}",
                    maxLines = 1,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
            }

        }
    }

}

@Preview( showBackground = true)
@Composable
fun MovieListItemPreview(){
    MovieListItem(
        topMoviesResponseItem = buildTopMoviesResponseItemPreview(),
        onMovieClick = {})
}