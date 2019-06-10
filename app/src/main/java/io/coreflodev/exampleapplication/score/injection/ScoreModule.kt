package io.coreflodev.exampleapplication.score.injection

import dagger.Module
import dagger.Provides
import io.coreflodev.exampleapplication.common.arch.Screen
import io.coreflodev.exampleapplication.common.network.ClearScoreApi
import io.coreflodev.exampleapplication.score.ScoreInput
import io.coreflodev.exampleapplication.score.ScoreOutput
import io.coreflodev.exampleapplication.score.ScoreScreen
import io.coreflodev.exampleapplication.score.repo.RankRepository
import io.coreflodev.exampleapplication.score.repo.ClearScoreRankRepository
import io.coreflodev.exampleapplication.score.use_cases.DisplayScoreUseCase

@Module
class ScoreModule {

    @Provides
    @ScoreScope
    fun providePostDetailsUseCase(repository: RankRepository) =
        DisplayScoreUseCase(repository)

    @Provides
    @ScoreScope
    fun provideDetailsScreen(displayScoreUseCase: DisplayScoreUseCase) : Screen<ScoreInput, ScoreOutput> =
            ScoreScreen(displayScoreUseCase)

    @Provides
    @ScoreScope
    fun provideDetailsRepository(clearScoreApi: ClearScoreApi) : RankRepository =
            ClearScoreRankRepository(clearScoreApi)
}
