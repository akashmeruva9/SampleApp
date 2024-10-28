package com.cred.sampleapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


// application tells hilt to start generating code in the project
@HiltAndroidApp
class SampleAppApplication : Application() {
}