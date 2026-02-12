package xyz.aranhapreta.rickAndMorty.database

import app.cash.sqldelight.db.SqlDriver

internal expect fun createSqlDriver(): SqlDriver
