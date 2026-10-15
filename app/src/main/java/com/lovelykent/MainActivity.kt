package com.lovelykent

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var root: LinearLayout
    private var stage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showStage()
    }

    private fun showStage() {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 28, 28, 28)
            setBackgroundColor(Color.rgb(20, 15, 28))
        }

        val message = TextView(this).apply {
            textSize = 27f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            text = when (stage) {
                0 -> "🐱💗\nТы меня любишь?"
                1 -> "🥺\nТы уверен?"
                2 -> "💗\nТы меня любишь?"
                3 -> "❤️\nЯ тебя тоже люблю!"
                else -> "💀\nПОШЁЛ НАХУЙ"
            }
        }
        root.addView(message)

        if (stage == 3) {
            message.text = "❤️❤️❤️\nЯ тебя тоже люблю!\n❤️❤️❤️"
        }

        if (stage == 4) {
            message.textSize = 32f
            root.setBackgroundColor(Color.BLACK)
        }

        if (stage <= 2) {
            val yes = Button(this).apply {
                text = "Да ❤️"
                setOnClickListener {
                    stage = if (stage == 0 || stage == 2) 3 else 4
                    showStage()
                }
            }
            val no = Button(this).apply {
                text = "Нет 💔"
                setOnClickListener {
                    stage = when (stage) {
                        0 -> 1
                        1 -> 2
                        else -> 4
                    }
                    showStage()
                }
            }
            root.addView(yes)
            root.addView(no)
        } else {
            val close = Button(this).apply {
                text = "Закрыть"
                setOnClickListener { finish() }
            }
            root.addView(close)
        }

        setContentView(root)
    }
}
