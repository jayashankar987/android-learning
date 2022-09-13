package com.example.myapplication.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.myapplication.StudentDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SaveDataStore(private val context: Context) {
    private val Context.studentDataStore: DataStore<StudentDetails> by
    dataStore(fileName = "student.pb", serializer = StudentSerializer())

    suspend fun saveStudentInfo(name: String, stuClass: String, rollNo: String, mobile: String) {
        context.studentDataStore.updateData { studentDetails ->
            studentDetails.toBuilder()
                .setStuName(name)
                .setStuContactNumber(mobile)
                .setStuClass(stuClass)
                .setStuRollNumber(rollNo)
                .build()
        }
    }

    val studentInfo: Flow<StudentDetails> = context.studentDataStore.data.map {
        it
    }
}