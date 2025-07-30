package com.example.randomquoteapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    var query by remember { mutableStateOf("") }

    Column {
        // 🔹 Call SearchBar here
        SearchBar(onSearchTextChanged = { newText ->
            query = newText
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AddQuoteButton()
            Spacer(modifier = Modifier.width(16.dp))
            generateQuoteButton()

        }
    }
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search category (motivational, love, fun.)",
    onSearchTextChanged: (String) -> Unit
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Column{
        Text(text = "Search Bar")

        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchTextChanged(it.text)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            placeholder = { Text(hint) },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color(0xFFF0F0F0),
                disabledContainerColor = Color(0xFFE0E0E0)
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Transparent)
        )
    }
}

@Composable
fun AddQuoteButton(modifier: Modifier = Modifier) {
    val mContext = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(mContext, AddQuoteActivity::class.java)
            mContext.startActivity(intent)
        },
        modifier = modifier
    ) {
        Text("Add Quote")
    }
}

@Composable
fun generateQuoteButton(modifier: Modifier = Modifier) {
    val mContext = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(mContext, MainActivity::class.java)
            mContext.startActivity(intent)
        },
        modifier = modifier
    ) {
        Text("Generate Quote")
    }

}