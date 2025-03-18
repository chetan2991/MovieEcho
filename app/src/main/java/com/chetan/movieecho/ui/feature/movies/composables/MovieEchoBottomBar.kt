package com.chetan.movieecho.ui.feature.movies.composables

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.chetan.movieecho.R

@Composable
fun MovieEchoBottomBar(
){

    NavigationBar(
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
        tonalElevation = dimensionResource(R.dimen.movie_echo_bottom_bar_tonal_elevation),
        contentColor = MaterialTheme.colorScheme.surface,
    ) {

        NavigationBarItem(
            icon = {
                Icon(Icons.Default.Home,
                    contentDescription = "Home")
            },
            label = { Text(text = "Home", fontSize = dimensionResource(R.dimen.movie_echo_font_size_12_sp).value.sp) },
            selected = true,
            onClick = { /*TODO*/ },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface
                ,indicatorColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.padding(vertical =
                dimensionResource(R.dimen.padding_small))
        )
        NavigationBarItem(
            icon = {
                Icon(Icons.Default.Favorite,
                    contentDescription = "Favorite")
            },
            label = { Text(text = "Favorites", fontSize = dimensionResource(R.dimen.movie_echo_font_size_12_sp).value.sp) },

            selected = false,
            onClick = { /*TODO*/ },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface
                , indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
                   , modifier = Modifier.padding(vertical =
                dimensionResource(R.dimen.padding_small))

        )
        NavigationBarItem(
            icon = {
                Icon(Icons.Default.Person,
                    contentDescription = "Profile")
            },
            label = { Text(text = "Profile", fontSize = dimensionResource(R.dimen.movie_echo_font_size_12_sp).value.sp) },

            selected = false,
            onClick = { /*TODO*/ },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface
    , indicatorColor = MaterialTheme.colorScheme.primaryContainer
            )
                 ,   modifier = Modifier.padding(vertical =
                dimensionResource(R.dimen.padding_small))

        )
    }
}
@Preview(showBackground = true)
@Composable
fun MovieEchoBottomBarPreview(){
    MovieEchoBottomBar()
}