package daggerTutorial.module

import android.content.Context
import dagger.Module
import dagger.Provides
import daggerTutorial.scope.GithubAppScope

@Module
class ContextModule(private val context: Context) {
    @Provides
    @GithubAppScope
    fun getContext() = context
}