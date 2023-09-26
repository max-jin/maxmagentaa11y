/*
 * 2023 - Developed by Max Jin
 * Source code subject to change. Refer to NOTICE.txt in source tree for changes and attributions.
 */

package com.maxjin.a11y.ui.component.carousel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.SubcomposeAsyncImage
import com.maxjin.a11y.ui.theme.MagentaA11yTheme
import com.maxjin.a11y.ui.util.composable.CodeSnippet
import com.maxjin.a11y.ui.util.composable.HorizontalDivider
import com.maxjin.a11y.ui.util.composable.LargeTopBar
import com.maxjin.a11y.ui.util.composable.TitleTextView
import com.maxjin.a11y.ui.util.dimenB10
import com.maxjin.a11y.ui.util.dimenB3
import com.maxjin.a11y.ui.util.dimenB4
import com.maxjin.a11y.ui.util.dimenB5
import com.maxjin.a11y.ui.util.dimenB6
import com.maxjin.a11y.ui.util.dimenB7
import com.maxjin.a11y.ui.util.ext.verticalGradient
import com.maxjin.a11y.ui.util.randomSampleImageUrl
import com.maxjin.a11y.util.AppUtil
import com.maxjin.a11y.util.component.Carousel
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CarouselScreen(navigateUp: () -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val urlHandler = LocalUriHandler.current
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalGradient(),
        topBar = {
            LargeTopBar(
                title = "Carousel",
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate up"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // TODO open link
                        urlHandler.openUri(AppUtil.WebLinks.CAROUSEL_URL)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Link,
                            contentDescription = "Magenta A11y Carousel page"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                TitleTextView("Horizontal Carousel/Pager", modifier = Modifier.padding(horizontal = dimenB4))
                Spacer(modifier = Modifier.height(dimenB5))
                val horizontalPagerState = rememberPagerState()
                val horizontalPagerImages by rememberSaveable {
                    mutableStateOf(
                        mutableListOf<String>().apply {
                            repeat(5) {
                                add(randomSampleImageUrl(width = 600))
                            }
                        }.toList()
                    )
                }
                HorizontalPager(
                    state = horizontalPagerState,
                    modifier = Modifier
                        .fillMaxWidth(),
                    pageCount = horizontalPagerImages.size,
                    contentPadding = PaddingValues(horizontal = dimenB7)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier
                            .graphicsLayer {
                                // Calculate the absolute offset for the current page from the scroll position. We use the absolute value which allows us to mirror
                                // any effects for both directions
                                val pageOffset = (horizontalPagerState.currentPage - pageIndex + horizontalPagerState.currentPageOffsetFraction).absoluteValue
                                // We animate the scaleX + scaleY, between 75% and 100%
                                lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                                // We animate the alpha, between 50% and 100%
                                alpha = lerp(
                                    start = 0.3f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                            .clip(RoundedCornerShape(dimenB5))
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .semantics(mergeDescendants = true) {},
                        contentAlignment = Alignment.Center
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            SubcomposeAsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = horizontalPagerImages[pageIndex],
                                loading = {
                                    CircularProgressIndicator(modifier = Modifier.padding(130.dp))
                                },
                                contentDescription = "image"
                            )
                            Box(
                                modifier = Modifier
                                    .size(dimenB10)
                                    .padding(dimenB4)
                                    .clip(RoundedCornerShape(dimenB3))
                                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)), contentAlignment = Alignment.Center
                            ) {
                                Text(text = (pageIndex + 1).toString(), style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onBackground)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(dimenB6))
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimenB4),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                Spacer(modifier = Modifier.height(dimenB5))
                TitleTextView("Vertical Carousel/Pager", modifier = Modifier.padding(horizontal = dimenB4))
                Spacer(modifier = Modifier.height(dimenB5))
                val verticalPagerState = rememberPagerState()
                val verticalPagerImages by rememberSaveable {
                    mutableStateOf(
                        mutableListOf<String>().apply {
                            repeat(5) {
                                add(randomSampleImageUrl(width = 600))
                            }
                        }.toList()
                    )
                }
                VerticalPager(
                    state = verticalPagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    pageCount = verticalPagerImages.size,
                    contentPadding = PaddingValues(horizontal = dimenB7)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(dimenB5))
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .semantics(mergeDescendants = true) {},
                        contentAlignment = Alignment.Center
                    ) {
                        Box {
                            SubcomposeAsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = verticalPagerImages[pageIndex],
                                loading = {
                                    CircularProgressIndicator(modifier = Modifier.padding(130.dp))
                                },
                                contentDescription = "image"
                            )
                            Box(
                                modifier = Modifier
                                    .size(dimenB10)
                                    .padding(dimenB4)
                                    .clip(RoundedCornerShape(dimenB3))
                                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)), contentAlignment = Alignment.Center
                            ) {
                                Text(text = (pageIndex + 1).toString(), style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onBackground)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(dimenB5))

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimenB4),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                Spacer(modifier = Modifier.height(dimenB4))
                Text(
                    text = "Code example",
                    modifier = Modifier.padding(start = dimenB4, top = dimenB3, bottom = dimenB3),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                CodeSnippet(codeText = Carousel.CODE_SNIPPET)
                Spacer(modifier = Modifier.height(dimenB5))
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CarouselScreenPreview() {
    MagentaA11yTheme {
        CarouselScreen(navigateUp = {})
    }
}