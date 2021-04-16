package stas.batura.radioproject.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import stas.batura.bookreader.data.UserPreferencesSerializer
import stas.batura.radioproject.UserPreferences

@Module
@InstallIn (ApplicationComponent::class)
class ProtoDataModule {

    @Provides
    fun provideProtoData(@ApplicationContext appContext: Context): DataStore<UserPreferences> {
        return appContext.createDataStore(
            fileName = "user_prefs.pb",
            serializer = UserPreferencesSerializer
        )

    }

}