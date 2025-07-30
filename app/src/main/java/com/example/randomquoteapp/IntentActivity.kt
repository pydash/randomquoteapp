package com.example.randomquoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomquoteapp.ui.theme.RandomquoteappTheme
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.sp

class IntentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomquoteappTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize() // Column takes the full screen
                        .padding(16.dp), // Optional padding
                    verticalArrangement = Arrangement.Center, // Centers children vertically
                    horizontalAlignment = Alignment.CenterHorizontally // Centers children horizontally
                ) {
                    // Greeting could be here if you want it above the centered button
                    Greeting(intro = "to the Random Quote Generator App")
                    GoToNextAct()
                }
            }
        }
    }
}



@Composable
fun Greeting(intro: String, modifier: Modifier = Modifier) {
    Text(
        text = "Welcome $intro!",
        modifier = modifier.padding(bottom = 18.dp),
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        lineHeight = 40.sp
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RandomquoteappTheme {
        Greeting("Random Quote Generator App")
    }
}

@Composable
fun GoToNextAct(modifier: Modifier = Modifier) { // modifier parameter is good practice
    val mContext = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(mContext, MainActivity::class.java)
            intent.putExtra("IntentBtn", "StartApplication")
            mContext.startActivity(intent)
        },
        modifier = modifier
            .fillMaxWidth(0.7f) // Example: Button takes 70% of the Column's width
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "Start App",
            fontSize = 20.sp,
        )
    }
}

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Row {
//            Button(
//                onClick = {
//                    // Pass mContext as the packageContext
//                    val intent = Intent (mContext, MainActivity::class.java)
//                    intent.putExtra("IntentBtn", "StartApplication")
//                    // Call startActivity on mContext
//                    mContext.startActivity(intent)
//                }
//            )
//            {
//                Text("Start App")
//            }
//
//        }
//    }
