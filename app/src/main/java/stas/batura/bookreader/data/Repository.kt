package stas.batura.bookreader.data

import androidx.datastore.DataStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
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

    /**
     * устанавливаем полседнюю страницу
     * @param type тип выводимого списка
     */
    override fun setLastPage(page: Int) {
        repScope.launch {
            protoData.updateData { t: UserPreferences ->
                t.toBuilder().setLastPage(page).build()
            }
        }
    }

    override fun getLastPage(): Int {
        var res: Int
        runBlocking {
            var asi = repScope.async { getLastPageDb().first() }
            res = asi.await()
        }
        return res
    }

    /**
     * получаем последнюю страницу
     */
    fun getLastPageDb(): Flow<Int> {
        return protoData.data.map {
            it.lastPage
        }
    }
}