package io.coreflodev.exampleapplication.score.injection

import dagger.Component
import io.coreflodev.exampleapplication.common.injection.ApplicationComponent
import io.coreflodev.exampleapplication.score.ui.ScoreActivity

@ScoreScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ScoreModule::class]
)
interface ScoreComponent {

    fun inject(activity: ScoreActivity)
}