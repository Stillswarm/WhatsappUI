package com.example.myapplication.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.data.BottomBarItem
import com.example.myapplication.data.DataSource
import com.example.myapplication.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHome(modifier: Modifier = Modifier) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            AppTopBar(
                actionIcons = DataSource.topBarActionIcons,
                scrollBehavior = scrollBehaviour,
                //modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            )
        },
        bottomBar = { AppBottomBar(bottomBarItems = DataSource.bottomBarItems) },
        floatingActionButton = { AppFab() }

    ) {
        ChatsScreen(modifier = modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    @DrawableRes actionIcons: List<Int>,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "WhatsApp",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        },

        actions = {
            for (action in actionIcons) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = action),
                        contentDescription = "Action Button",
                    )
                }
            }
        },

        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@Composable
fun AppBottomBar(
    bottomBarItems: List<BottomBarItem>
) {
    var selected by remember {
        mutableIntStateOf(0)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            bottomBarItems.forEachIndexed { index, item ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(32.dp)
                            .width(60.dp)
                    ) {

                        if (index == selected) {
                            Spacer(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f))
                            )
                        }

                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) {
                                    selected = index
                                },
                        )


                    }

                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (index == selected) FontWeight.ExtraBold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun AppFab(modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = {}) {
        Icon(
            painter = painterResource(id = R.drawable.add_comment_24px__1_),
            contentDescription = "New Chat"
        )
    }
}

@Preview
@Composable
private fun AppHomePreview() {
    AppTheme {
        AppHome()
    }
}