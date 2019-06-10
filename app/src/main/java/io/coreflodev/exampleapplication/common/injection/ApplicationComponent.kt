package io.coreflodev.exampleapplication.common.injection

import dagger.Component
import io.coreflodev.exampleapplication.common.network.ClearScoreApi

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun typicodeApi(): ClearScoreApi

}
