package com.example.multiplatformapp.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.multiplatformapp.domain.Repository

@Composable
fun Item(repository: Repository) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFFE6F6DF)
    ) {
        BaseRepositoryInfo(repository)
    }
}

@Composable
fun BaseRepositoryInfo(repository: Repository) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RepositoryImage(repository = repository)
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = repository.name.toString(),
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
            Text(
                text = repository.owner?.login.toString(),
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 5.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun RepositoryImage(repository: Repository) {
    Image(
        painter = rememberAsyncImagePainter(repository.owner?.avatarUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(74.dp)
            .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
    )
}