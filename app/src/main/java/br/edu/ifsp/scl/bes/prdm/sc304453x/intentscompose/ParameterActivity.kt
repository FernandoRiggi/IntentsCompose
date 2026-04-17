package br.edu.ifsp.scl.bes.prdm.sc304453x.intentscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Set Parameter")},
                actions = {
                    IconButton(onClick = {} ) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)
            ) {

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