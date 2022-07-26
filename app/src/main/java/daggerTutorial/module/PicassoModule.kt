package daggerTutorial.module

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import daggerTutorial.scope.GithubAppScope

@Module(includes = [ContextModule::class])
class PicassoModule {
    @GithubAppScope
    @Provides
    fun getPicasso(context: Context):Picasso{
        return Picasso.Builder(context).build()
    }
}