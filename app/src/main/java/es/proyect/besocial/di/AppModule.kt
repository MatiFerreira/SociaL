package es.proyect.besocial.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.proyect.besocial.Core.Constants.USERS
import es.proyect.besocial.data.repository.AuthRepositoryImplements
import es.proyect.besocial.data.repository.UserRepositoryImplements
import es.proyect.besocial.domain.repository.AuthRepository
import es.proyect.besocial.domain.repository.UserRepository
import es.proyect.besocial.domain.usecases.auth.AuthUseCase
import es.proyect.besocial.domain.usecases.auth.GetCurrentUser
import es.proyect.besocial.domain.usecases.auth.LogOut
import es.proyect.besocial.domain.usecases.auth.Login
import es.proyect.besocial.domain.usecases.auth.SignUp
import es.proyect.besocial.domain.usecases.users.Create
import es.proyect.besocial.domain.usecases.users.GetUserById
import es.proyect.besocial.domain.usecases.users.UserUseCase

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
        AuthUseCase(
            getCurrentUser = GetCurrentUser(repository),
            login = Login(repository),
            loginOut = LogOut(repository),
            signUp = SignUp(repository)
        )

    @Provides
    fun provideFireStorage(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    fun providesUserRef(db: FirebaseFirestore): CollectionReference {
        return db.collection(USERS)
    }

    @Provides
    fun providesUserRepository(impl: UserRepositoryImplements): UserRepository = impl

    @Provides
    fun provideUserUseCases(repository: UserRepository) = UserUseCase(
        create = Create(repository), getEmailId = GetUserById(repository)
    )
}