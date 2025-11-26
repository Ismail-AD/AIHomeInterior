package org.yourappdev.homeinterior.ui.CreateAndExplore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.yourappdev.homeinterior.domain.repo.RoomsRepository
import org.yourappdev.homeinterior.ui.common.base.CommonUiEvent
import org.yourappdev.homeinterior.ui.common.base.CommonUiEvent.ShowError
import org.yourappdev.homeinterior.utils.executeApiCall

class RoomsViewModel(val roomsRepository: RoomsRepository) : ViewModel() {
    private val _state = MutableStateFlow(RoomUiState())
    val state: StateFlow<RoomUiState> = _state.asStateFlow()
    private val _uiEvent = MutableSharedFlow<CommonUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getRooms()
    }

    fun onRoomEvent(event: RoomEvent) {
        when (event) {
            is RoomEvent.OnSearchQueryChange -> {
                _state.value = _state.value.copy(searchQuery = event.query)
                applyFiltersAndSearch()
            }
            is RoomEvent.OnApplyFilters -> {
                val count = calculateFilterCount(event.filterState)
                _state.value = _state.value.copy(
                    filterState = event.filterState,
                    filterCount = count,
                    showFilterSheet = false
                )
                applyFiltersAndSearch()
            }
            RoomEvent.OnFilterClick -> {
                _state.value = _state.value.copy(showFilterSheet = true)
            }
            RoomEvent.OnDismissFilterSheet -> {
                _state.value = _state.value.copy(showFilterSheet = false)
            }
            RoomEvent.OnClearFilters -> {
                _state.value = _state.value.copy(
                    filterState = FilterState(),
                    filterCount = 0
                )
                applyFiltersAndSearch()
            }
        }
    }

    private fun applyFiltersAndSearch() {
        val state = _state.value
        var filtered = state.allRooms

        // Apply search filter
        if (state.searchQuery.isNotBlank()) {
            filtered = filtered.filter { room ->
                room.room_type.contains(state.searchQuery, ignoreCase = true)
            }
        }

        // Apply room type filter
        if (state.filterState.selectedRoomTypes.isNotEmpty() &&
            !state.filterState.selectedRoomTypes.contains("All")) {
            filtered = filtered.filter { room ->
                state.filterState.selectedRoomTypes.contains(room.room_type)
            }
        }

        // Apply style filter
        if (state.filterState.selectedStyles.isNotEmpty() &&
            !state.filterState.selectedStyles.contains("All")) {
            filtered = filtered.filter { room ->
                state.filterState.selectedStyles.any { style ->
                    room.room_style.contains(style, ignoreCase = true)
                }
            }
        }

        _state.value = _state.value.copy(filteredRooms = filtered)
    }

    private fun calculateFilterCount(filterState: FilterState): Int {
        var count = 0
        if (filterState.selectedRoomTypes.isNotEmpty() &&
            !filterState.selectedRoomTypes.contains("All")) count++
        if (filterState.selectedStyles.isNotEmpty() &&
            !filterState.selectedStyles.contains("All")) count++
        if (filterState.selectedColors.isNotEmpty()) count++
        if (filterState.selectedFormats.isNotEmpty() &&
            !filterState.selectedFormats.contains("All")) count++
        if (filterState.selectedPrices.isNotEmpty()) count++
        return count
    }
    
    fun getRooms() {
        viewModelScope.launch {
            executeApiCall(
                updateState = { result ->
                    _state.value = _state.value.copy(getRoomsResponse = result)
                },
                apiCall = {
                    roomsRepository.getRoomsList()
                }, onSuccess = { response ->
                    if (response.success) {
                        val trending = response.rooms.filter { it.is_trending == 1 }
                        _state.value = _state.value.copy(
                            trendingRooms = trending,
                            allRooms = response.rooms,
                            filteredRooms = response.rooms,
                        )
                    } else {
                        _uiEvent.emit(ShowError("Something went wrong"))
                    }
                },
                onError = { errorMessage ->
                    viewModelScope.launch { _uiEvent.emit(ShowError(errorMessage)) }
                }
            )
        }
    }


}