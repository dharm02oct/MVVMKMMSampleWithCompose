package com.harman.mvvmkmmsample


import com.harman.mvvmkmmsample.data.db.Database
import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver() : SqlDriver
}


expect fun getSqlDriver(contextArgs: ContextArgs? = null): SqlDriver

object DatabaseCreator {
    fun getDataBase(contextArgs: ContextArgs?): Database? {
        val sqlDriver  = getSqlDriver(contextArgs)
        if (sqlDriver != null) {
            return Database(sqlDriver)
        } else {
            return null
        }
    }
}