package xyz.aranhapreta.rickAndMorty

import android.app.Application
import xyz.aranhapreta.rickAndMorty.database.DatabaseAppContext

class RickAndMortyApp : Application() {
    override fun onCreate() {
        DatabaseAppContext.AppContext = this
        super.onCreate()
    }
}