package com.harman.mvvmkmmsample

import android.content.Context
import com.harman.mvvmkmmsample.db.ContactDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ContactDatabase.Schema, context, "AppDatabase.db")
    }
}

actual fun getSqlDriver(contextArgs: ContextArgs?): SqlDriver {
    return AndroidSqliteDriver(ContactDatabase.Schema, contextArgs?.mContext!!, "AppDatabase.db")
}