package com.example.randomquoteapp

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.call.body
import io.ktor.client.statement.*
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuoteApp { quoteToShare ->
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "\"${quoteToShare.text}\" — ${quoteToShare.author}"
                    )
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share quote via"))
            }
        }
    }
}

@Composable
fun QuoteApp(onShare: (Quote) -> Unit) {
    var currentQuote by remember { mutableStateOf(QuoteRepository.getRandomQuote()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        QuoteDisplay(quote = currentQuote)

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(onClick = { currentQuote = QuoteRepository.getRandomQuote() }) {
                Text("New Quote")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { onShare(currentQuote) }) {
                Text("Share")
            }
        }
    }
}

@Composable
fun QuoteDisplay(quote: Quote) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = quote.imageResId),
            contentDescription = "Quote Image",
            modifier = Modifier
                .height(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "\"${quote.text}\"",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "- ${quote.author}",
            style = MaterialTheme.typography.displaySmall
        )
    }
}
