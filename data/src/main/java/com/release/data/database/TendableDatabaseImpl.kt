package com.release.data.database

import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class TendableDatabaseImpl @Inject constructor() : TendableDatabase {

    override val realm: Realm by lazy {
        Realm.getInstance(config)
    }

    private val config: RealmConfiguration = RealmConfiguration.Builder()
        .schemaVersion(realmVersion)
        .build()

    companion object {
        private const val DB_NAME = "tendable.db"
        private const val realmVersion = 1L
    }
}
