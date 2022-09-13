package com.example.myapplication.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.myapplication.StudentDetails
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class StudentSerializer : Serializer<StudentDetails> {

    override val defaultValue: StudentDetails = StudentDetails.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): StudentDetails {
        try {
            return StudentDetails.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(" Error in reading proto", exception)
        }
    }

    override suspend fun writeTo(t: StudentDetails, output: OutputStream) = t.writeTo(output)
}