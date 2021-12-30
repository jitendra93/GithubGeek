package com.jitendraalekar.githubgeek.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jitendraalekar.githubgeek.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<TextView>(R.id.tv_appname).postDelayed({
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }, 1000
        )

    }
}