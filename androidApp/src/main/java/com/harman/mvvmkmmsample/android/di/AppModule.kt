package com.harman.mvvmkmmsample.android.di
import com.harman.mvvmkmmsample.android.ui.contacts.ContactListViewModel
import com.harman.mvvmkmmsample.android.ui.editcontact.EditContactViewModel
import com.harman.mvvmkmmsample.android.ui.users.GithubUsersViewModel
import com.harman.mvvmkmmsample.android.ui.viewcontact.ViewContactViewModel
import com.harman.mvvmkmmsample.data.net.GitHubUsersApi
import com.harman.mvvmkmmsample.di.commonUseCases
import com.harman.mvvmkmmsample.di.dbModule
import com.harman.mvvmkmmsample.domain.interactor.GetUsers
import com.harman.mvvmkmmsample.domain.repository.UsersRepository
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
private const val TIMEOUT_MILLIS = 30000L
private const val CACHE_MAX_SIZE_BYTES = 1024L


val client = HttpClient() {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
            isLenient = false
            ignoreUnknownKeys = true
            allowSpecialFloatingPointValues = true
            useArrayPolymorphism = false
        })
    }
}


val networkModule = module {
    single { GitHubUsersApi() }
    single<UsersRepository> {
        com.harman.mvvmkmmsample.data.repository.UsersRepository(
            get(),
            client,
            ""
        )
    }
}

val viewModelModule = module {
    viewModel { ContactListViewModel(get(), get()) }
    viewModel { GithubUsersViewModel(get()) }
    viewModel { ViewContactViewModel(get(),get()) }
    viewModel { EditContactViewModel(get(),get()) }
}


val useCasesModule = module {
    single { GetUsers(get()) }

}



fun allModules() = listOf(networkModule ,useCasesModule , commonUseCases ,viewModelModule ,
    dbModule

)