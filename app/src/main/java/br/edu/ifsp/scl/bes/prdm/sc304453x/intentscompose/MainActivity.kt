package br.edu.ifsp.scl.bes.prdm.sc304453x.intentscompose

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.edu.ifsp.scl.bes.prdm.sc304453x.intentscompose.ui.theme.IntentsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsComposeTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val callOrDialPhoneNumber = { call: Boolean ->
        val intent = if (call) {
            Intent(Intent.ACTION_CALL, Uri.parse("tel:123456789"))
        } else {
            Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"))
        }
        context.startActivity(intent)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Intents Example") },
                actions = {
                    IconButton(onClick = { expanded = !expanded}) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false}
                    ) {
                        DropdownMenuItem(
                            text = { Text("SetParameter")},
                            OnClick = { expanded = false}
                        )
                        DropdownMenuItem(
                            text = { Text("Make Call") },
                            onClick = {
                                expanded = false
                                callOrDialPhoneNumber(true)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Dial Number") },
                            onClick = {
                                expanded = false
                                callOrDialPhoneNumber(false)
                            }
                        )
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
            }
        }
    )
}