package com.example.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layouts.ui.theme.LayoutsAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsAppTheme {
                LayoutsCodelab()
            }
        }
    }
}

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(innerPadding)
    }
}

@Composable
fun BodyContent(innerPadding: PaddingValues) {
    val listSize = 100
    val scrollState = rememberLazyListState()

    Column(
        Modifier
            .padding(innerPadding)
            .padding(16.dp)) {
        ButtonsRow(listSize, scrollState)
        ImageList(listSize, scrollState)
    }
}

@Composable
fun ButtonsRow(listSize: Int, scrollState: LazyListState) {
    val coroutineScope = rememberCoroutineScope()

    Row {
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }
        ) {
            Text("Scroll to the top")
        }

        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }
        ) {
            Text("Scroll to the end")
        }
    }
}

@Composable
fun ImageList(listSize: Int, scrollState: LazyListState) {
    LazyColumn(
        modifier = Modifier.background(color = MaterialTheme.colors.surface),
        state = scrollState
    ) {
        items(listSize) {
            PhotographerCard(index = it)
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier, index: Int) {
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f))
            .clickable(onClick = {})
            .padding(16.dp)
    ) {
        Surface(
            Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
            Box(contentAlignment = Center) {
                Text("#$index")
            }
        }
        Column(
            Modifier
                .align(CenterVertically)
                .padding(start = 8.dp)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    LayoutsAppTheme {
        PhotographerCard(index = 1)
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    LayoutsAppTheme {
        LayoutsCodelab()
    }
}