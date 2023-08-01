package com.amontdevs.hey.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.amontdevs.hey.model.Constants
import com.amontdevs.hey.source.HeyDatabase
import com.amontdevs.hey.source.ILocalFileManager
import com.amontdevs.hey.source.IPictureDownloader
import com.amontdevs.hey.source.IRandomUserRemote
import com.amontdevs.hey.source.IRandomUserService
import com.amontdevs.hey.source.LocalFileManager
import com.amontdevs.hey.source.PictureDownloader
import com.amontdevs.hey.source.RandomUserRemoteImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getOkHttpClient() =
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .build()
    } else OkHttpClient
        .Builder()
        .build()

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


fun getApiService(retrofit: Retrofit): IRandomUserService = retrofit.create(
    IRandomUserService::class.java)


fun getRandomUserRemoteImpl(randomUserService: IRandomUserService):
       IRandomUserRemote = RandomUserRemoteImpl(randomUserService)

private val Context.preferencesDataStore
        by preferencesDataStore(Constants.DATA_STORE_NAME)

fun getDataStore(appContext: Context) :
        DataStore<Preferences> = appContext.preferencesDataStore

fun getRoomDatabase(appContext: Context) =
   Room.databaseBuilder(appContext, HeyDatabase::class.java, Constants.DATABASE_NAME).build()

fun getPictureDownloader() : IPictureDownloader = PictureDownloader()

fun getLocalFileManager(appContext: Context) : ILocalFileManager = LocalFileManager(appContext)

val sourceModule = module {
    single { getOkHttpClient() }
    single { getRetrofit(get()) }
    single { getApiService(get()) }
    single { getRandomUserRemoteImpl(get()) }
    single { getDataStore(androidContext()) }
    single { getRoomDatabase(androidContext()) }
    single { getPictureDownloader() }
    single { getLocalFileManager(androidContext()) }
}

fun getFriendsDAO(db: HeyDatabase) = db.friendsDao()

val daoModule = module {
    single { getFriendsDAO(get()) }
}

