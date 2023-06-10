package com.example.deloittecodechallenge.data.remote

import retrofit2.Response

sealed class DefaultResponse<T> {

    data class Fail<T>(val error: Throwable) : DefaultResponse<T>()
    data class Success<T>(val body: T) : DefaultResponse<T>()
    class Empty<T> : DefaultResponse<T>()

    companion object {

        fun <T> create(/*moshi: Moshi,*/ response: Response<T>): DefaultResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                Success(body ?: throw EmptyResponseException())

            } else
                Fail(Exception())
//                when (response.code()) {
//                    401 -> {
//                        try {
//                            val err = response.errorBody()
//                            Fail(UnauthorizedException(moshi, err?.string()))
//                        } catch (ex: Exception) {
//                            Fail(Exception())
//                        }
//                    }
//                    in 400..499 -> {
//                        try {
//                            val err = response.errorBody()
//                            Fail(ValidationException(moshi, err?.string()))
//                        } catch (ex: Exception) {
//                            Fail(Exception())
//                        }
//                    }
//                    500 -> {
//                        Fail(Exception())
//                    }
//                    else -> {
//                        Fail(Exception())
//                    }
//                }
        }

        fun <T> create(error: Throwable): Fail<T> {
            return Fail(error)
        }

    }

}

