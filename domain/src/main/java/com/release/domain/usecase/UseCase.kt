package com.release.domain.usecase

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun execute(params: Params): Type
}

object None
