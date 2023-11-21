package com.example.bandungdestination.data

import com.example.bandungdestination.model.DataBandungDestination
import com.example.bandungdestination.model.Destination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class BandungDestinationRepository {
    private val dummyBandungDestination = mutableListOf<DataBandungDestination>()

    init {
        if (dummyBandungDestination.isEmpty()){
            Destination.dummyDestination.forEach {
                dummyBandungDestination.add(it)
            }
        }
    }

    fun SearchBandungDestination(query: String): Flow<List<DataBandungDestination>> = flow {
        val data = dummyBandungDestination.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    fun updateBandungDestination(destinationId: Int, newState: Boolean): Flow<Boolean>{
        val index = dummyBandungDestination.indexOfFirst { it.id == destinationId }
        val result = if (index >= 0){
            val bandungDestination = dummyBandungDestination[index]
            dummyBandungDestination[index] = bandungDestination.copy(isFavorite = newState)
            true
        }else{
            false
        }
        return flowOf(result)
    }

    fun getBandungDestinationById(destinationId: Int) : DataBandungDestination{
        return dummyBandungDestination.first {
            it.id == destinationId
        }
    }

    fun getBandungDestinationList(): Flow<List<DataBandungDestination>>{
        return flowOf(dummyBandungDestination.filter { it.isFavorite })
    }


    companion object{
        @Volatile
        private var instance: BandungDestinationRepository? = null

        fun getInstance(): BandungDestinationRepository =
            instance ?: synchronized(this){
                BandungDestinationRepository().apply {
                    instance = this
                }
            }
    }
}