package stas.batura.bookreader.data

import androidx.datastore.DataStore
import stas.batura.radioproject.UserPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor() : IRepository {

    @Inject
    lateinit var protoData: DataStore<UserPreferences>



}