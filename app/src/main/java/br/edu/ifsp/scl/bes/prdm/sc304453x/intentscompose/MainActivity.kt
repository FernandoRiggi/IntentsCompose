package br.edu.ifsp.scl.bes.prdm.sc304453x.intentscompose

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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

    val callPhonePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val isPermissionGranted = permissions.getOrDefault("android.permission.CALL_PHONE", false)
            if (isPermissionGranted) {
                callOrDialPhoneNumber(true)
            } else {
                Toast.makeText(context, "Permission required!", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Intents Example") },
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Set Parameter") },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Make Call") },
                            onClick = {
                                expanded = false
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (context.checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                                        == android.content.pm.PackageManager.PERMISSION_GRANTED
                                    ) {
                                        callOrDialPhoneNumber(true)
                                    } else {
                                        callPhonePermissionLauncher.launch(arrayOf(android.Manifest.permission.CALL_PHONE))
                                    }
                                } else {
                                    callOrDialPhoneNumber(true)
                                }
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
                    .padding(16.dp)
            ) {
                Text(text = "Use the menu to make a call or dial a number", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Current Phone: 123456789",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    )
}
