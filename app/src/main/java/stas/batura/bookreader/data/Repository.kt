package stas.batura.bookreader.data

import androidx.datastore.DataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import stas.batura.radioproject.UserPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor() : IRepository {

    @Inject
    lateinit var protoData: DataStore<UserPreferences>

    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    private var repositoryJob = Job()

    /**
     * A [CoroutineScope] keeps track of all coroutines started by this ViewModel.
     *
     * Because we pass it [repositoryJob], any coroutine started in this uiScope can be cancelled
     *
     * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
     * the main thread on Android.
     */
    private val repScope = CoroutineScope(Dispatchers.IO + repositoryJob)


}