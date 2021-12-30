package com.jitendraalekar.githubgeek.domain

import com.jitendraalekar.githubgeek.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception

abstract class UseCase<in P, R> (private val coroutineDispatcher: CoroutineDispatcher){

    suspend  operator fun invoke(parameters : P) : Result<R> {
        return try {
            withContext(coroutineDispatcher){
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        }catch (e : Exception){

            Result.Error(e)
        }
    }

    /**
     * override this method to perform actual task
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P) : R
}