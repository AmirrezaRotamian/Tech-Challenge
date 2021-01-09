package com.example.rayakavosh_task.presentation.ui

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.domain.Card
import com.example.rayakavosh_task.R
import com.example.rayakavosh_task.databinding.ActivityMainBinding
import com.example.rayakavosh_task.presentation.viewmodels.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * main Activity and EntryPoint for DI
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var bindings: ActivityMainBinding
    lateinit var options:RequestOptions
    val cardViewModel:CardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindings=DataBindingUtil.setContentView(this,R.layout.activity_main)
        bindings.lifecycleOwner=this
        bindings.viewModel=cardViewModel
        cardViewModel.pickrandomCard()
        setGlide()
        setCardData(this)
        bindings.button.setOnClickListener {
            cardViewModel.pickrandomCard()
        }
    }


    private fun setGlide() {
        options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.drawable.image_not_found)


    }
    private fun setCardData(context:Context) {
        cardViewModel.cardItem.observe(this){card->
            when (card) {
                is Card.PictureCard -> {
                    setPictureCard(card, context)

                }
                is Card.VibratorCard -> {
                    setVibratorCard(card)
                }
                is Card.SoundCard->{
                    setSoundCard(card)
                }
            }
        }
    }

    private fun setSoundCard(card: Card.SoundCard) {
        bindings.title.text = card.title
        bindings.description.text = card.description
        bindings.imageView.visibility = LinearLayout.GONE
        //play a sound
        playSound(this)
    }

    private fun setVibratorCard(card: Card.VibratorCard) {
        bindings.title.text = card.title
        bindings.description.text = card.description
        bindings.imageView.visibility = LinearLayout.GONE
        //do a little vibration
        val vibe = this.getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibe.vibrate(80)
    }

    private fun setPictureCard(
        card: Card.PictureCard,
        context: Context
    ) {
        bindings.title.text = card.title
        bindings.description.text = card.description
        bindings.imageView.visibility = LinearLayout.VISIBLE
        Glide.with(context)
            .load(card.image)
            .apply(options)
            .into(bindings.imageView)
    }

    private fun playSound(context: Context) {
        val resId = resources.getIdentifier(R.raw.sound.toString(),
            "raw", context.packageName
        )

        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer.start()
    }
}