package com.mbs.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mbs.motivation.R
import com.mbs.motivation.auxiliaryclasses.Constants
import com.mbs.motivation.auxiliaryclasses.SecurityPreferences
import com.mbs.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener(this)

        supportActionBar?.hide()

        checkUsername()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.save_button) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.edittextUsername.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeName(Constants.Username, name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.name_empty_alert, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkUsername() {
        val name = SecurityPreferences(this).getName(Constants.Username)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}