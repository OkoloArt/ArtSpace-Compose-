package com.example.artspace

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpace()
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    val imageResources = listOf(R.drawable.cat ,
            R.drawable.tiger , R.drawable.dog ,
            R.drawable.lion , R.drawable.leopard)

    val titles = listOf(stringResource(R.string.cat_title) , stringResource(R.string.tiger_title) , stringResource(
                R.string.dog_title) , stringResource(R.string.lion_title) , stringResource(R.string.leopard_title))
    val artists = listOf(stringResource(R.string.cat_artist) , stringResource(R.string.cat_artist) , stringResource(
                R.string.dog_artist) , stringResource(R.string.lion_artist) , stringResource(R.string.leopard_artist))

    var currentImage by remember { mutableStateOf(0) }

    Box(Modifier.fillMaxSize()
            .padding(10.dp)) {

        Column(verticalArrangement = Arrangement.Center ,
               horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(elevation = 20.dp ,
                    modifier = Modifier.padding(12.dp),
                    shape = RoundedCornerShape(10.dp) ) {

                Image(painter = painterResource(id = imageResources[currentImage]) ,
                      contentDescription = null ,
                      modifier = Modifier
                          .fillMaxWidth()
                          .scale(0.92f))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = titles[currentImage] ,
                 textAlign = TextAlign.Center,
                 fontWeight = FontWeight.Bold,
                 fontSize = 30.sp,
                 style = MaterialTheme.typography.h4,
                 fontFamily = FontFamily.Cursive)

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = artists[currentImage] , textAlign = TextAlign.Center,
                 fontWeight = FontWeight.SemiBold,
                 fontSize = 20.sp,
                 style = MaterialTheme.typography.subtitle1,
                 fontFamily = FontFamily.Monospace)
        }
        Row(Modifier.align(Alignment.BottomCenter) ,
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.Center) {

            val context = LocalContext.current

            GalleryChangeButton(
                    buttonName = stringResource(R.string.previous) ,
                    onClick = { if (currentImage > 0) currentImage-- else Toast.makeText(context,"First Image",Toast.LENGTH_SHORT).show() }
            )

            Spacer(modifier = Modifier.width(50.dp))

            GalleryChangeButton(
                    buttonName = stringResource(R.string.next) ,
                    onClick = { if (currentImage < imageResources.size - 1) currentImage++ else Toast.makeText(context,"Last Image",Toast.LENGTH_SHORT).show() }
            )
        }
    }
}


@Composable
fun GalleryChangeButton(modifier: Modifier = Modifier, buttonName: String, onClick : () -> Unit){
    Button(onClick = onClick,
    shape = RoundedCornerShape(20.dp)) {
        Text(text = buttonName)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview(){
    ArtSpaceTheme {
        ArtSpace(Modifier.fillMaxSize())
    }
}