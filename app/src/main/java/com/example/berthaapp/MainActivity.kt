package com.example.berthaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.ShareActionProvider
import android.view.MenuItem
import android.view.View

import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.support.v7.widget.Toolbar

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    private var preferences: SharedPreferences? = null
    private var usernameField: EditText? = null
    private var passwordField: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar);
        val actionBar = supportActionBar
        //  actionBar.setDisplayHomeAsUpEnabled(true);

        preferences = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        usernameField = findViewById(R.id.etName)
        passwordField = findViewById(R.id.etPassword)
        val username = preferences!!.getString(USERNAME, null)
        val password = preferences!!.getString(PASSWORD, null)
        if (username != null && password != null) {
            usernameField!!.setText(username)
            passwordField!!.setText(password)
        }


    }

    fun validate(view: View) {
        val username = usernameField!!.text.toString()
        val password = passwordField!!.text.toString()
        val ok = PasswordChecker.Check(username, password)
        if (ok) {
            val checkBox = findViewById<CheckBox>(R.id.login_rem)
            val editor = preferences!!.edit()
            if (checkBox.isChecked) {
                editor.putString(USERNAME, username)
                editor.putString(PASSWORD, password)
            } else {
                editor.remove(USERNAME)
                editor.remove(PASSWORD)
            }
            editor.apply()
            val intent = Intent(this, DataListView::class.java)
            intent.putExtra(USERNAME, username)
            startActivity(intent)
        } else {
            usernameField!!.error = "Wrong username or password"
        }
    }

    companion object {

        val PREF_FILE_NAME = "loginPref"
        val USERNAME = "anbo"
        val PASSWORD = "1234"
    }

}
