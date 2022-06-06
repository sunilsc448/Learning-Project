package movies.component

import movies.modules.AppModule
import movies.modules.MoviesApiModule
import movies.view.MoviesActivity
import movies.view.MoviesListFragment
import dagger.Component
import movies.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MoviesApiModule::class, ViewModelModule::class])
interface MoviesComponent {
    fun inject(activity:MoviesActivity)
    fun inject(fragment:MoviesListFragment)
}