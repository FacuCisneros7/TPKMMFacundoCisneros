package org.firstproject.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.firstproject.project.Data.Local.DatabaseDriverFactory
import org.firstproject.project.Data.Local.PokemonLocalDataSource
import org.firstproject.project.UI.App
import org.firstproject.project.UI.Screens.PokemonScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val localDataSource = PokemonLocalDataSource(DatabaseDriverFactory(this))
            PokemonScreen(localDataSource)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}