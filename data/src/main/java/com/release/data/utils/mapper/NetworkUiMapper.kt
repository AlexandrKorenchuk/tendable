package com.release.data.utils.mapper

interface NetworkUiMapper<NT, UI> {

    fun mapNetworkToUi(networkEntity: NT): UI

    fun mapUiToNetwork(uiEntity: UI): NT =
        throw NotImplementedError("Mapping UI to network not implemented in ${javaClass.simpleName}.")
}