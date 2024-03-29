package com.example.myapplication.datastore.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.base.AppTheme
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.TopBar
import com.example.myapplication.datastore.SaveDataStore
import com.example.myapplication.datastore.SavePreferenceData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreDetailsActivity : BaseActivity() {

    private val savePreferenceData by lazy { SavePreferenceData(context = this) }
    private val saveDataStore by lazy { SaveDataStore(context = this) }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (intent.action == "data_store_preference") {
                InputDataViewOther(savePreferenceData) {
                    GlobalScope.async {
                        savePreferenceData.storeUserInfo(it.name, it.email)
                    }
                }
            } else {
                InputDataView(saveDataStore) {
                    GlobalScope.async {
                        saveDataStore.saveStudentInfo(
                            name = it.name,
                            rollNo = it.rollNumber,
                            mobile = it.mobile,
                            stuClass = it.classInfo
                        )
                    }
                }
            }
        }
    }
}

data class UserData(val name: String, val email: String)
data class StudentInfo(val name: String, val mobile: String, val rollNumber: String, val classInfo: String)

@Composable
fun InputDataView(saveDataStore: SaveDataStore, onClick: (StudentInfo) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var mobile by remember { mutableStateOf(TextFieldValue("")) }
    var classValue by remember { mutableStateOf(TextFieldValue("")) }
    var rollNumber by remember { mutableStateOf(TextFieldValue("")) }

    var nameString by remember { mutableStateOf("Hello") }
    var classString by remember { mutableStateOf("World") }
    var mobileString by remember { mutableStateOf("World") }
    var rollNumberString by remember { mutableStateOf("World") }

    val scope = rememberCoroutineScope()

    AppTheme {
        Scaffold(topBar = { TopBar(stringResource(id = R.string.data_store)) }) { it ->
            Row(modifier = Modifier.padding(it)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextWidget(value = nameString)
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextWidget(value = rollNumberString)
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextWidget(value = mobileString)
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextWidget(value = classString)
                    OutlinedTextField(
                        value = name,
                        onValueChange = { data -> name = data },
                        label = { Text(text = "Enter your Name") },
                        placeholder = { Text(text = "Enter Full Name") })

                    OutlinedTextField(
                        value = rollNumber,
                        onValueChange = { data -> rollNumber = data },
                        label = { Text(text = "Enter your roll number") },
                        placeholder = { Text(text = "roll number") })

                    OutlinedTextField(
                        value = mobile,
                        onValueChange = { data -> mobile = data },
                        label = { Text(text = "Enter your roll number") },
                        placeholder = { Text(text = "roll number") })

                    OutlinedTextField(
                        value = classValue,
                        onValueChange = { data -> classValue = data },
                        label = { Text(text = "Enter your roll number") },
                        placeholder = { Text(text = "roll number") })

                    ButtonWidget {
                        onClick(
                            StudentInfo(
                                name = name.text,
                                mobile = mobile.text,
                                classInfo = classValue.text,
                                rollNumber = rollNumber.text
                            )
                        )
                        scope.launch {
                            saveDataStore.studentInfo.collect {
                                nameString = it.stuName
                                rollNumberString = it.stuRollNumber
                                mobileString = it.stuContactNumber
                                classString = it.stuClass
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputDataViewOther(savePreferenceData: SavePreferenceData, onClick: (UserData) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }

    var nameString by remember { mutableStateOf("Hello") }
    var emailString by remember { mutableStateOf("World") }
    val scope = rememberCoroutineScope()

    AppTheme {
        Scaffold(topBar = { TopBar(stringResource(id = R.string.data_store)) }) { it ->
            Row(modifier = Modifier.padding(it)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextWidget(value = nameString)
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextWidget(value = emailString)
                    OutlinedTextField(
                        value = name,
                        onValueChange = { data -> name = data },
                        label = { Text(text = "Enter your Name") },
                        placeholder = { Text(text = "Enter Full Name") })

                    OutlinedTextField(
                        value = email,
                        onValueChange = { data -> email = data },
                        label = { Text(text = "Enter your email") },
                        placeholder = { Text(text = "Email") })

                    ButtonWidget {
                        onClick(UserData(name = name.text, email = email.text))
                        scope.launch {
                            savePreferenceData.getUserNameFlow().collect {
                                nameString = it
                                Log.e("Jay", "Jay Email = $emailString")
                            }
                        }

                        scope.launch {
                            savePreferenceData.getEmailAddress().collect {
                                emailString = it
                                Log.e("Jay", "Jay Email = $emailString")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonWidget(onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            enabled = true,
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Save", color = Color.White)
        }
    }
}

@Composable
fun TextWidget(value: String) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        Arrangement.Center
    ) {
        Text(text = value, fontSize = 16.sp, color = colorResource(id = R.color.white))
    }
}