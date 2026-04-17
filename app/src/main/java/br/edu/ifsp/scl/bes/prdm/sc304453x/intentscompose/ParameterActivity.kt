package br.edu.ifsp.scl.bes.prdm.sc304453x.intentscompose

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.bes.prdm.sc304453x.intentscompose.ui.theme.IntentsComposeTheme

class ParameterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsComposeTheme {
                ParameterScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParameterScreen() {
    var parameter by remember { mutableStateOf("") }

    val context = LocalContext.current
    val receivedParameter = (context as ComponentActivity).intent.getStringExtra("EXTRA_PARAMETER") ?: ""

    LaunchedEffect(receivedParameter) {
        parameter = receivedParameter
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Set Parameter") },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    value = parameter,
                    onValueChange = { parameter = it },
                    label = { Text("Parameter") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val returnIntent = Intent()
                        returnIntent.putExtra("EXTRA_PARAMETER", parameter)
                        (context as ComponentActivity).setResult(RESULT_OK, returnIntent)
                        (context as ComponentActivity).finish()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save and Quit")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IntentsComposeTheme {
        ParameterScreen()
    }
}