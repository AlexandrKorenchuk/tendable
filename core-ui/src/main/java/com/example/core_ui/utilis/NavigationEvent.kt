package com.example.core_ui.utilis

sealed class NavigationEvent {

    data class Forward(
        val navigate: Int
    ) : NavigationEvent()

    data class ForwardWithBundle(
        val navigate: Int,
        val from: String
    ) : NavigationEvent()

    data class ForwardWithPopUp(
        val popUpTo: Int,
        val navigate: Int
    ) : NavigationEvent()

    data class Pop(
        val navigate: Int,
        val inclusive: Boolean
    ) : NavigationEvent()

    data class ReplaceGraph(
        val setGraph: Int
    ) : NavigationEvent()

    data class SetDestinationAndGraph(
        val startDestination: Int,
        val setGraph: Int
    ) : NavigationEvent()
}