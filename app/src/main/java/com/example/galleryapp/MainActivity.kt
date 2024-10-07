package com.example.galleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.galleryapp.data.DataSource
import com.example.galleryapp.model.GalleryItem
import com.example.galleryapp.ui.theme.GalleryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryLayout()
                }
            }
        }
    }
}

@Composable
fun GalleryLayout (modifier: Modifier = Modifier) {
    val galleryItems = DataSource().loadGalleryItems()
    var currentImage by remember { mutableIntStateOf(0) }

    Column (modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.Center) {
        Text(
            text = "Gallery App",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = galleryItems[currentImage].imageSrc),
            contentDescription = stringResource(id = galleryItems[currentImage].description),
            modifier = Modifier.fillMaxSize().weight(0.5f).clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Column (Modifier.padding(top= 20.dp, bottom = 60.dp)) {
            Text(
                text = stringResource(id = galleryItems[currentImage].club),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 5.dp),
                fontWeight = FontWeight.Black
            )
            Text(
                text = stringResource(id = galleryItems[currentImage].description) +" (${galleryItems[currentImage].year})",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row (modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            ButtonElt(
                onClick = { currentImage = decrease(currentImage, galleryItems) },
                text = "Previous",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            ButtonElt(
                onClick = { currentImage = increase(currentImage, galleryItems) },
                text = "Next",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ButtonElt(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF111111),
            contentColor = Color.White,
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .widthIn(min = 200.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 6.dp)
        )
    }
}

fun increase (currentImage: Int, galleryItems: List<GalleryItem>): Int {
    return if (currentImage < galleryItems.size - 1) currentImage + 1 else 0
}

fun decrease (currentImage: Int, galleryItems: List<GalleryItem>): Int {
    return if (currentImage > 0) currentImage - 1 else galleryItems.size - 1
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GalleryAppTheme {
        GalleryLayout()
    }
}