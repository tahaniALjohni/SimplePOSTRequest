package com.example.simplepostrequest

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.edName)
        val submit = findViewById<Button>(R.id.btnSubmit)

        val progressDialog = ProgressDialog(this@MainActivity)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        submit.setOnClickListener {
            if (name.text.isNotBlank()) {
                progressDialog.setMessage("Add Successfully")
                progressDialog.show()
                if (apiInterface != null) {
                    apiInterface.addUser(Person(name.text.toString()))?.enqueue(object : Callback<Person?> {
                            override fun onResponse(call: Call<Person?>,response: Response<Person?>) {
                                progressDialog.dismiss()
                            }

                            override fun onFailure(call: Call<Person?>, t: Throwable) {
                                Toast.makeText(applicationContext, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                                progressDialog.dismiss()
                            }
                        })
                }
            } else {
                Toast.makeText(applicationContext, "Please Enter Name", Toast.LENGTH_SHORT).show()
            }
        }
    }
    }
