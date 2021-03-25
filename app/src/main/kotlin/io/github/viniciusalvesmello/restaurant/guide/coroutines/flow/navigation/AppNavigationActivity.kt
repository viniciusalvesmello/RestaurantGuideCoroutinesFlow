package io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.R

@AndroidEntryPoint
class AppNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_navigation)
    }
}
