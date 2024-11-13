package com.nosh.noshassignment.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nosh.noshassignment.R
import com.nosh.noshassignment.data.MainViewModel
import com.nosh.noshassignment.ui.components.ScheduleCookingBottomSheet
import com.nosh.noshassignment.ui.components.CookSection
import com.nosh.noshassignment.ui.components.NoshButton
import com.nosh.noshassignment.ui.components.NoshTopAppBar
import com.nosh.noshassignment.ui.components.RecommendationsRow
import com.nosh.noshassignment.ui.components.WhatsOnYourMindRow
import kotlinx.coroutines.flow.map


@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val dishes by viewModel.uiState.map { it.dishes }.collectAsState(emptyList())
    val isLoading by viewModel.uiState.map { it.isLoading }.collectAsState(false)
    val error by viewModel.uiState.map { it.errorMessage }.collectAsState(null)

    val snackBarState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = error) {
        if (!error.isNullOrEmpty()) {
            snackBarState.showSnackbar(error!!)
            viewModel.onErrorShown()
        }
    }

    Box(modifier = modifier.safeContentPadding()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 70.dp)
        ) {
            NoshTopAppBar()
            CookSection(
                title = R.string.whats_on_your_mind
            ) {
                WhatsOnYourMindRow()
            }
            CookSection(
                title = R.string.recommendations
            ) {
                RecommendationsRow(dishes = dishes, isLoading = isLoading, onCardClick = {
                    showBottomSheet = true
                })
            }
        }
        BoxWithConstraints(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            var bottomPadding = PaddingValues()
            if (maxWidth >= 600.dp) {
                bottomPadding = PaddingValues(bottom = 16.dp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottomPadding),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                NoshButton(
                    onClick = {},
                    textAlignment = Alignment.CenterStart,
                    imageAlignment = Alignment.CenterEnd,
                    image = {
                        Image(
                            modifier = Modifier.sizeIn(maxWidth = 100.dp),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(R.drawable.food_trans),
                            contentDescription = null
                        )
                    }) {
                    Text(text = "Explore all dishes", textAlign = TextAlign.Center)
                }
                NoshButton(onClick = {}, image = {
                    Image(
                        modifier = Modifier.sizeIn(maxWidth = 100.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(R.drawable.foods),
                        contentDescription = null
                    )
                }) {
                    Text(
                        text = "What to cook?",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        if (showBottomSheet) {
            ScheduleCookingBottomSheet(onDismissRequest = { showBottomSheet = false })
        }
    }
}