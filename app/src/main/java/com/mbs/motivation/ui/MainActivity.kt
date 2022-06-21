package com.mbs.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mbs.motivation.R
import com.mbs.motivation.auxiliaryclasses.Constants
import com.mbs.motivation.auxiliaryclasses.SecurityPreferences
import com.mbs.motivation.data.Mock
import com.mbs.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleUsername()
        handleFilter(R.id.infinity_button)
        pickRandomPhrase()
        supportActionBar?.hide()

        //onClick events
        binding.newPhraseButton.setOnClickListener(this)
        binding.infinityButton.setOnClickListener(this)
        binding.smileButton.setOnClickListener(this)
        binding.sunButton.setOnClickListener(this)
    }
    private var phraseStatus = Constants.PhraseCategory.sun

    override fun onClick(view: View) {
        if (view.id == R.id.new_phrase_button) {
            pickRandomPhrase()
        } else if (view.id in listOf(R.id.smile_button, R.id.sun_button, R.id.infinity_button)) {
            handleFilter(view.id)
        }

    }

    private fun handleFilter(id: Int) {

        setAllToDefalutColor()

        if (id == R.id.infinity_button) {
            binding.infinityButton.setColorFilter((ContextCompat.getColor(this, R.color.white)))
            phraseStatus = Constants.PhraseCategory.infinity
        } else if (id == R.id.sun_button) {
            binding.sunButton.setColorFilter((ContextCompat.getColor(this, R.color.white)))
            phraseStatus = Constants.PhraseCategory.sun
        } else if (id == R.id.smile_button) {
            binding.smileButton.setColorFilter((ContextCompat.getColor(this, R.color.white)))
            phraseStatus = Constants.PhraseCategory.smile
        }
    }

    private fun handleUsername() {
        val name = SecurityPreferences(this).getName(Constants.Username)
        binding.usernameReplacer.text = "Olá, ${name}!"
    }

    private fun setAllToDefalutColor() {
        binding.infinityButton.setColorFilter((ContextCompat.getColor(this, R.color.darkest_red)))
        binding.sunButton.setColorFilter((ContextCompat.getColor(this, R.color.darkest_red)))
        binding.smileButton.setColorFilter((ContextCompat.getColor(this, R.color.darkest_red)))
    }
    private fun pickRandomPhrase () {
        binding.phrase.text = Mock().getPhrase(phraseStatus)
    }
}