package es.proyect.besocial.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.proyect.besocial.data.repository.AuthRepositoryImplements
import es.proyect.besocial.domain.repository.AuthRepository
import es.proyect.besocial.domain.usecases.auth.AuthUseCase
import es.proyect.besocial.domain.usecases.auth.GetCurrentUser
import es.proyect.besocial.domain.usecases.auth.Login

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideAuthRepository(implementation: AuthRepositoryImplements): AuthRepository {
        return implementation
    }

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) =
        AuthUseCase(getCurrentUser = GetCurrentUser(repository), login = Login(repository))
}