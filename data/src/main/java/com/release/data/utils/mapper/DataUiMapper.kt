package com.release.data.utils.mapper

interface DataUiMapper<D, UI> {

    fun mapDataToUi(dataEntity: D): UI

    fun mapUiToData(uiEntity: UI): D =
        throw NotImplementedError("Mapping UI to network not implemented in ${javaClass.simpleName}.")
}