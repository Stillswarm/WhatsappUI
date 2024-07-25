package com.example.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.ChatProperties
import com.example.myapplication.data.DataSource
import com.example.myapplication.ui.theme.AppTheme

@Composable
fun ChatsScreen(modifier: Modifier = Modifier) {
    ChatScreenContent(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        chatItems = DataSource.getChatItems()
    )
}

@Composable
fun SearchTool(modifier: Modifier = Modifier) {
    var value: String by remember {
        mutableStateOf("")
    }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        value = value,
        onValueChange = { value = it },
        placeholder = {
            Text(
                text = "Search...",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        },
        shape = CircleShape,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_24px),
                contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ChatFilterChips(
    filterType: List<String>,
) {
    var selected by remember {
        mutableIntStateOf(0)
    }
    Row {
        filterType.forEachIndexed { index, item ->
            FilterChip(
                selected = index == selected,
                onClick = { selected = index },
                label = {
                    Text(
                        text = item,
                        color = if (index == selected) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = W900
                    )
                },
                shape = CircleShape,
                modifier = Modifier.padding(end = 8.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = false,
                    selected = false,
                    borderWidth = 0.dp
                )
            )
        }
    }
}

@Composable
fun ChatScreenContent(
    chatItems: List<ChatProperties>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(20.dp)) {
        item {
            SearchTool()
        }

        item {
            ChatFilterChips(filterType = DataSource.chatFilterType)
        }

        items(chatItems) { item ->
            ChatDetails(item, modifier = Modifier.clickable { })
        }

        item {
            HorizontalDivider(thickness = 0.2.dp)
            Text(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                text = "Your personal messages are end-to-end encrypted",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ChatDetails(
    chat: ChatProperties,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = chat.senderProfilePic),
            contentDescription = "Chat with : " + chat.senderName,
            modifier = Modifier
                .height(48.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = chat.senderName,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 17.sp,
                    fontWeight = ExtraBold
                )
                Text(
                    text = chat.timeOfLastMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )

            }

            Text(
                text = chat.lastMessage,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 14.sp
            )

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ElementPreview() {
    AppTheme {
        ChatsScreen()
    }
}