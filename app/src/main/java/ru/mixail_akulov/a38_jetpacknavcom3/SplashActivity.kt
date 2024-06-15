package ru.mixail_akulov.a38_jetpacknavcom3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Точка входа в приложение.
 *
 * Заставка содержит только фон окна,
 * вся остальная логика инициализации помещается в [SplashFragment] и [SplashViewModel].
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}