package com.chetan.movieecho.ui.feature.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.chetan.movieecho.R

@Composable
fun MovieEchoError(
    onRetryClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.movie_echo_something_went_wrong),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.movie_echo_please_try_again_later),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            textAlign = TextAlign.Center
        )
        OutlinedButton (
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.outlinedButtonColors(),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            onClick = { onRetryClick() },
        ) {
            Text(text = stringResource(R.string.movie_echo_retry))
        }
    }
}