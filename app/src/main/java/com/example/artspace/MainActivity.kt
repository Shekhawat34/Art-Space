package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Outline.Rounded
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()


                }
            }
        }
    }
}
@Composable
fun ArtSpaceApp() {
    ImageWithTextAndButton(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}



@Composable
fun ImageWithTextAndButton(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(0) }

    val imageAndTextList = listOf(
        ImageAndText(R.drawable.art_space1, R.string.first_text),
        ImageAndText(R.drawable.art_space2, R.string.second_text),
        ImageAndText(R.drawable.art_space3, R.string.third_text),
        ImageAndText(R.drawable.art_space4, R.string.fourth_text)
    )

    val currentImageAndText = imageAndTextList[result]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = currentImageAndText.imageResource),
            contentDescription = currentImageAndText.textResource.toString(),
            modifier = Modifier
                .height(600.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(BorderStroke(width = 3.dp, color = Color.Gray))
                .animateContentSize()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = stringResource(id = currentImageAndText.textResource),
            onValueChange = { /* No-op */ },
            label = { Text("Description") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(BorderStroke(width = 3.dp,color= Color.Gray))
                .padding(0.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { result = (result - 1 + imageAndTextList.size) % imageAndTextList.size },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                )
            ) {
                Text(stringResource(id = R.string.Previous))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { result = (result + 1) % imageAndTextList.size },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                )) {
                Text(stringResource(id = R.string.Next))
            }
        }
      
    }
}

data class ImageAndText(val imageResource: Int, val textResource: Int)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()

    }
}