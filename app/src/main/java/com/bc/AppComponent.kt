package com.bc

import com.bc.datasource.remote.UserRemoteDataSource
import com.bc.datasource.remote.UserRemoteDataSourceImpl
import com.bc.datasource.remote.service.UserService
import com.bc.features.details.DetailViewModel
import com.bc.features.splash.SplashViewModel
import com.bc.features.users.UsersViewModel
import com.bc.repository.UserRepository
import com.bc.repository.UserRepositoryImpl
import com.bc.utils.Connectivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appComponent = listOf(
        createService("https://jsonplaceholder.typicode.com/"),
        createRepositories(),
        createDataSources(),
        createViewModels(),
        createFirestore(),
        createUtils()
)

fun createViewModels() = module{
    viewModel { SplashViewModel(get()) }
    viewModel { UsersViewModel() }
    viewModel { DetailViewModel() }
}

fun createRepositories() = module{
    factory { UserRepositoryImpl(get(), get()) as UserRepository }
}

fun createDataSources() = module{
    factory { UserRemoteDataSourceImpl(get()) as UserRemoteDataSource }
}

fun createFirestore() = module {
    factory { Firebase.firestore }
}

fun createUtils() = module{
    factory { Connectivity(get()) }
}

fun createService(baseUrl: String) = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    single {
        Retrofit.Builder()
                .client(get())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
    }

    factory { get<Retrofit>().create(UserService::class.java) }

}