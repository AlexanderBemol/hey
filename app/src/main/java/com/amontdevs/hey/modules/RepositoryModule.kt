package com.amontdevs.hey.modules

import android.content.Context
import com.amontdevs.hey.repository.FriendRepository
import com.amontdevs.hey.repository.IFriendRepository
import com.amontdevs.hey.repository.IPreferencesRepository
import com.amontdevs.hey.repository.IRandomUserRepository
import com.amontdevs.hey.repository.PreferencesRepository
import org.koin.dsl.module
import com.amontdevs.hey.repository.RandomUserRepository
import com.amontdevs.hey.source.IFriendDao
import com.amontdevs.hey.source.ILocalFileManager
import com.amontdevs.hey.source.IPictureDownloader
import com.amontdevs.hey.source.IRandomUserRemote
import org.koin.android.ext.koin.androidContext

fun provideRandomUserRepository(
    randomUserRemote : IRandomUserRemote,
    friendDao : IFriendDao,
    pictureDownloader : IPictureDownloader,
    localFileManager : ILocalFileManager
) : IRandomUserRepository =
    RandomUserRepository(randomUserRemote, friendDao, pictureDownloader, localFileManager)

fun provideFriendRepository(
    friendDao: IFriendDao,
    localFileManager: ILocalFileManager
) : IFriendRepository = FriendRepository(friendDao,localFileManager)

fun providePreferencesRepository(context: Context) : IPreferencesRepository =
    PreferencesRepository(context)

val repositoryModule = module {
    single { provideRandomUserRepository(get(),get(),get(),get(),) }
    single { provideFriendRepository(get(), get()) }
    single { providePreferencesRepository(androidContext()) }
}