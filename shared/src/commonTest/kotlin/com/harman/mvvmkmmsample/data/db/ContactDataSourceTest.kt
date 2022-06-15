package com.harman.mvvmkmmsample.data.db

import com.harman.mvvmkmmsample.domain.model.Contact
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class ContactDataSourceTest {
    private lateinit var db: Database

    @BeforeTest
    fun setup() = runTest {
        db = Database(
            testDbConnection()
        )
    }

    @Test
    fun insertContactTest() {
        var mockCon = Contact(10, "Dha", "M", 3553554445, "di@gmail.com")
        var id = db.saveContact(mockCon)
        assertNotNull(db,"repo null ")
        assertNotNull(id, "insert failed")
    }

    @Test
    fun deleteContact(){
    //  assert
    }


}