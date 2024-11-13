package com.nosh.noshassignment.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PowerSettingsNew
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nosh.noshassignment.R
import com.nosh.noshassignment.data.Dish
import com.nosh.noshassignment.util.doublePulseEffect
import com.nosh.noshassignment.util.merge
import com.nosh.noshassignment.ui.theme.BlueGray900
import com.nosh.noshassignment.ui.theme.NoshTheme

@Composable
fun NoshTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(
                WindowInsets.navigationBars
                    .union(WindowInsets.statusBars)
                    .only(WindowInsetsSides.Horizontal.plus(WindowInsetsSides.Top))
                    .asPaddingValues()
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NoshSearchBar(modifier = Modifier.weight(1F))
        ScheduledMealCard(modifier = Modifier.padding(start = 24.dp, end = 8.dp))
        IconButton(onClick = {}) {
            Icon(Icons.Outlined.Notifications, contentDescription = "Notifications")
        }
        IconButton(onClick = {}) {
            Icon(
                Icons.Outlined.PowerSettingsNew,
                tint = MaterialTheme.colorScheme.error,
                contentDescription = "Notifications"
            )
        }
    }
}

@Composable
fun ScheduledMealCard(
    modifier: Modifier = Modifier,
    @DrawableRes mealImage: Int = R.drawable.snacks,
    @StringRes mealName: Int = R.string.italian_spaghetti_pasta,
    mealScheduledTime: String = "6:30 AM"
) {
    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            ScheduledMealCardSmall(modifier, mealImage, mealName, mealScheduledTime)
        } else {
            ScheduledMealCardLarge(modifier, mealImage, mealName, mealScheduledTime)
        }
    }
}

@Composable
fun ScheduledMealCardSmall(
    modifier: Modifier = Modifier,
    @DrawableRes mealImage: Int,
    @StringRes mealName: Int,
    mealScheduledTime: String,
    onClick: () -> Unit = {},
    containerColor: Color = BlueGray900,
    contentColor: Color = Color.White
) {
    Surface(
        modifier = modifier
            .size(44.dp)
            .doublePulseEffect(
                targetScale = 1.2f,
                initialScale = 0.8f,
                color = MaterialTheme.colorScheme.primary
            ),
        shape = CircleShape,
        color = BlueGray900,
        onClick = onClick
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = mealImage),
            contentDescription = null
        )
    }
}

@Composable
private fun ScheduledMealCardLarge(
    modifier: Modifier = Modifier,
    @DrawableRes mealImage: Int = R.drawable.indian_dish,
    @StringRes mealName: Int = R.string.italian_spaghetti_pasta,
    mealScheduledTime: String = "6:30 AM",
    onClick: () -> Unit = {},
    containerColor: Color = BlueGray900,
    contentColor: Color = Color.White
) {
    Card(
        modifier = modifier
            .widthIn(max = 200.dp)
            .wrapContentHeight(),
        onClick = onClick,
        shape = CircleShape,
        colors = CardDefaults.cardColors().copy(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .doublePulseEffect(color = MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = mealImage),
                    contentDescription = null
                )
            }
            Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp)) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = stringResource(id = mealName),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Scheduled $mealScheduledTime",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


@Composable
fun NoshSearchBar(modifier: Modifier = Modifier) {
    OutlinedTextField(
        modifier = modifier.wrapContentHeight(),
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            errorContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                text = stringResource(id = R.string.placeholder_search)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        }
    )
}

@Composable
fun CookSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int = R.string.whats_on_your_mind,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.padding(bottom = 16.dp)) {
        Text(
            modifier = Modifier
                .padding(
                    WindowInsets.navigationBars
                        .only(WindowInsetsSides.Horizontal)
                        .asPaddingValues()
                )
                .paddingFromBaseline(top = 36.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium
        )
        content()
    }
}

@Composable
fun WhatsOnYourMindRow(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal)
            .asPaddingValues().merge(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(whatsOnYourMindData.size) {
            WhatsOnYourMindItem(
                drawable = whatsOnYourMindData[it].drawable,
                text = whatsOnYourMindData[it].text
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MealCard(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.drawable.snacks,
    @StringRes title: Int = R.string.italian_spaghetti_pasta,
    imageUrl: String? = null,
    imageTitle: String? = null,
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .wrapContentHeight()
            .widthIn(max = 220.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy((-12).dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color.LightGray)
                        .padding(8.dp)
                ) {
                    GlideImage(
                        model = imageUrl ?: painterResource(image),
                        modifier = Modifier
                            .padding(horizontal = 32.dp, vertical = 12.dp)
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(title)
                    )
                    Image(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.TopEnd),
                        painter = painterResource(R.drawable.non_veg),
                        contentDescription = "non veg"
                    )

                }
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = "★ 4.2",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                imageTitle ?: stringResource(title),
                maxLines = 2,
                color = LocalContentColor.current,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    tint = LocalContentColor.current,
                    painter = painterResource(R.drawable.cooking_pot),
                    contentDescription = null
                )
                Text(
                    "30 min  •  Medium prep.",
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun RecommendationsRow(
    modifier: Modifier = Modifier,
    dishes: List<Dish> = emptyList(),
    isLoading: Boolean = false,
    onCardClick: () -> Unit = {}
) {
    if (isLoading) {
        Box(modifier.fillMaxWidth()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(40.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    } else {
        LazyRow(
            modifier = modifier,
            contentPadding = WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal)
                .asPaddingValues().merge(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(dishes.size) {
                MealCard(
                    onClick = onCardClick,
                    imageUrl = dishes[it].imageUrl,
                    imageTitle = dishes[it].dishName,
                )
            }
        }
    }
}

@Composable
fun WhatsOnYourMindItem(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier.wrapContentSize(),
        shape = CircleShape,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3F)
                    ),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = drawable),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun NoshButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.elevatedButtonColors(),
    textAlignment: Alignment = Alignment.Center,
    image: @Composable () -> Unit = { },
    imageScale: ContentScale = ContentScale.FillBounds,
    imageAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .wrapContentWidth()
            .widthIn(max = 300.dp)
            .height(48.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.containerColor,
            contentColor = colors.contentColor,
            disabledContainerColor = colors.disabledContainerColor,
            disabledContentColor = colors.disabledContentColor
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        onClick = onClick
    ) {
        Box {
            Box(
                modifier = Modifier.align(imageAlignment)
            ) {
                image()
            }
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(textAlignment)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                content()
            }
        }
    }
}

private val whatsOnYourMindData = listOf(
    R.drawable.rice to R.string.rice_items,
    R.drawable.indian_dish to R.string.indians,
    R.drawable.curry to R.string.curries,
    R.drawable.soup to R.string.soups,
    R.drawable.desserts to R.string.desserts,
    R.drawable.snacks to R.string.snacks
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true, widthDp = 100, name = "Small")
@Preview(showBackground = true, widthDp = 700, name = "Large")
@Composable
fun ScheduledMealItemPreview() {
    NoshTheme {
        ScheduledMealCard(
            mealImage = R.drawable.indian_dish,
            mealName = R.string.italian_spaghetti_pasta,
            mealScheduledTime = "6:30 AM"
        )
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 100)
@Composable
fun NoshSearchBarPreview() {
    NoshTheme {
        NoshSearchBar()
    }
}

@Preview(showBackground = true, widthDp = 700, heightDp = 100, name = "Large")
@Preview(showBackground = true, widthDp = 500, heightDp = 100, name = "Small")
@Composable
fun NoshTopAppBarPreview() {
    NoshTheme {
        NoshTopAppBar()
    }
}

@Preview
@Composable
fun NoshButtonPreview() {
    NoshTheme {
        NoshButton(onClick = {}, image = {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.foods),
                contentDescription = null
            )
        }) {
            Text("Confused what to cook?")
        }
    }
}

@Preview
@Composable
fun MealCardPreview() {
    NoshTheme {
        MealCard()
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsOnYourMindRowPreview() {
    NoshTheme {
        WhatsOnYourMindRow()
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsOnYourMindItemPreview() {
    NoshTheme {
        WhatsOnYourMindItem(
            drawable = R.drawable.rice,
            text = R.string.rice_items
        )
    }
}