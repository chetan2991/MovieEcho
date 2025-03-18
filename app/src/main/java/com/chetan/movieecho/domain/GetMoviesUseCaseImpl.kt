package com.chetan.movieecho.domain

import com.chetan.movieecho.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.chetan.movieecho.common.ApiResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * The GetMoviesUseCaseImpl is  well-structured for Clean Architecture.
 * SOLID: It adheres to the Single Responsibility Principle (SRP) because it has one clear responsibility: getting movies.
 *
 *GetMoviesUseCase: uses the MoviesRepository Interface  to fetch the top 250 movies from the API.
 * Benefits of using an interface for MoviesRepository:
 * Testability:
 * Mocking: Interfaces make it much easier to mock dependencies in your unit tests. You can create a mock implementation of the use case interface and inject it into your ViewModel for testing. This allows you to isolate the ViewModel's logic and test it independently of the actual use case implementation.
 * Test Doubles: Interfaces allow you to use test doubles (mocks, stubs, spies) to control the behavior of the use case in your tests.
 *
 * Flexibility and Decoupling:
 * Loose Coupling: Interfaces decouple the ViewModel from the concrete implementation of the use case. The ViewModel only depends on the interface, not the specific class.
 * Swappable Implementations: You can easily swap out different implementations of the use case without modifying the ViewModel. This is useful for:
 * Different Data Sources: You might have different use case implementations that fetch data from a network, a local database, or a mock data source.
 * Different Business Logic: You might have different use case implementations for different scenarios (e.g., a "lite" version of the app vs. a "full" version).
 *
 * Clean Architecture:
 * Dependency Inversion: Using interfaces for MoviesRepository aligns with the Dependency Inversion Principle (DIP) from SOLID
 * and Clean Architecture.
 * High-level modules (like the UseCase) should not depend on low-level modules (like the concrete MoviesRepository implementation).
 * They should both depend on abstractions (interfaces).
 *
 * Maintainability:
 * Easier Refactoring: If you need to refactor the use case implementation, you can do so without affecting the ViewModel, as long as the interface remains the same.
 */
class GetMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository

) : GetMoviesUseCase {
    override suspend operator fun invoke() = flow {
        emit(ApiResult.Loading())
        val result =  moviesRepository.getTop250MoviesList()
        emit(ApiResult.Success(result))
    }.catch {
        emit(ApiResult.Error(message = it.message ?: "Something went wrong",
            throwable = it))
    }.flowOn(Dispatchers.IO)
}