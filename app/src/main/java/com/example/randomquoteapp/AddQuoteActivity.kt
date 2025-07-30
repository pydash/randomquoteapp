package com.example.randomquoteapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class AddQuoteActivity : ComponentActivity() {
    private var author = ""
    private var quote = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddQuoteScreen()
        }
    }
}


@SuppressLint("ContextCastToActivity")
@Composable
fun AddQuoteScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("This is Add Quote Screen")
            Spacer(modifier = Modifier.height(16.dp))
            QuoteInput()
        }
    }
}

@Composable
fun QuoteInput() {
    var author by rememberSaveable { mutableStateOf("") }
    var quote by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color(0xFFF0F0F0)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = quote,
            onValueChange = { quote = it },
            label = { Text("Quote") },
            singleLine = false,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color(0xFFF0F0F0)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        AddQuoteButtonRow(author = author, quote = quote, onClear = {author = ""; quote = ""})
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun AddQuoteButtonRow(author: String, quote: String, onClear: () -> Unit) {
    val mContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { (mContext as Activity).finish() }) {
            Text("Back")
        }
        Spacer(modifier = Modifier.padding(8.dp))

        Button(onClick = {
            coroutineScope.launch {
                addQuote(mContext, author, quote)
                onClear()
            }
        }) {
            Text("Add Quote")
        }
    }
}

suspend fun addQuote(context: Context, author: String, quote: String) {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get("http://192.168.1.7/randomquoteapp/REST/addquote.php") {
        url {
            parameters.append("author", author)
            parameters.append("quote", quote)
        }
    }
    val stringBody: String = response.body<String>().toString()
    println(response.status.toString())
    context.toast(stringBody)
    client.close()
}

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}