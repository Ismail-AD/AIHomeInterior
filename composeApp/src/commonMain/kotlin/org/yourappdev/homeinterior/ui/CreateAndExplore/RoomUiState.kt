package org.yourappdev.homeinterior.ui.CreateAndExplore

import org.yourappdev.homeinterior.data.remote.util.ResultState
import org.yourappdev.homeinterior.domain.model.Room
import org.yourappdev.homeinterior.domain.model.Rooms

data class RoomUiState(
    val getRoomsResponse: ResultState<Rooms> = ResultState.Stable,
    val trendingRooms: List<Room> = emptyList(),
    val allRooms: List<Room> = emptyList(),
    val filteredRooms: List<Room> = emptyList(),
    val searchQuery: String = "",
    val filterState: FilterState = FilterState(),
    val filterCount: Int = 0,
    val isLoading: Boolean = true,
    val showFilterSheet: Boolean = false
)


data class FilterState(
    var selectedRoomTypes: Set<String> = setOf("Bedroom"),
    var selectedStyles: Set<String> = setOf("Modern"),
    var selectedColors: Set<Int> = setOf(3),
    var selectedFormats: Set<String> = setOf("JPEG"),
    var selectedPrices: Set<String> = setOf("Free")
)