package org.firstproject.project.Data.Local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.firstproject.project.PokemonDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(PokemonDatabase.Schema, "pokemon.db")
    }
}