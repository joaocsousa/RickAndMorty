package xyz.aranhapreta.rickAndMorty.database

import android.annotation.SuppressLint
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

@SuppressLint("StaticFieldLeak")
object DatabaseAppContext {
    lateinit var AppContext: Context
}

actual fun createSqlDriver(): SqlDriver {
    return AndroidSqliteDriver(
        schema = Database.Schema,
        context = DatabaseAppContext.AppContext,
        name = "database.db"
    )
}