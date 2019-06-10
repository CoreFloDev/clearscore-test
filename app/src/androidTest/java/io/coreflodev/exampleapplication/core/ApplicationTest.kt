package io.coreflodev.exampleapplication.core

import io.coreflodev.exampleapplication.common.ClearScoreApplication
import io.coreflodev.exampleapplication.common.injection.ApplicationComponent
import io.coreflodev.exampleapplication.common.injection.DaggerApplicationComponent

class ApplicationTest : ClearScoreApplication() {

    override val applicationComponent: ApplicationComponent
        get() = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModuleTest())
            .build()
}
