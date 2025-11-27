package org.yourappdev.homeinterior.ui.Generate.UiScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import homeinterior.composeapp.generated.resources.Res
import homeinterior.composeapp.generated.resources.sofa
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun ResultScreen(
    onCloseClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            TopBar {
                onCloseClick()
            }
            CreateContent()
        }
    }
}


@Composable
private fun CreateContent() {
    val imageList = listOf(
        Res.drawable.sofa,
        Res.drawable.sofa,
        Res.drawable.sofa,
        Res.drawable.sofa,
        Res.drawable.sofa,
        Res.drawable.sofa,
        Res.drawable.sofa,
        Res.drawable.sofa,
        Res.drawable.sofa,
    )

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        itemsIndexed(
            items = imageList,
            key = { index, _ -> index },
            span = { index, _ ->
                if ((index + 1) % 3 == 0) {
                    StaggeredGridItemSpan.FullLine
                } else {
                    StaggeredGridItemSpan.SingleLane
                }
            }
        ) { index, imageResource ->
            ImageCard(
                imageResource = imageResource,
                isLarge = (index + 1) % 3 == 0
            )
        }
    }
}

@Composable
private fun ImageCard(
    imageResource: DrawableResource,
    isLarge: Boolean,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageResource),
        contentDescription = "Room design",
        modifier = modifier.then(
            if (isLarge) {
                Modifier.fillMaxWidth().height(176.dp)
            } else {
                Modifier.aspectRatio(1f)
            }
        ).clip(RoundedCornerShape(9.dp))
            .border(
                width = 1.dp,
                color = if (isLarge) Color(0xFFCFCFCF) else Color(0xFFCFCFCF),
                shape = RoundedCornerShape(9.dp)
            ),
        contentScale = ContentScale.Crop
    )
}