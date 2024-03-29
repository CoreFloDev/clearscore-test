package io.coreflodev.exampleapplication.common

import android.app.Application
import android.content.Context
import io.coreflodev.exampleapplication.common.injection.ApplicationComponent
import io.coreflodev.exampleapplication.common.injection.DaggerApplicationComponent

open class ClearScoreApplication : Application() {

    protected open val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }

    companion object {

        fun applicationComponent(context: Context) =
            (context.applicationContext as ClearScoreApplication).applicationComponent
    }


}
