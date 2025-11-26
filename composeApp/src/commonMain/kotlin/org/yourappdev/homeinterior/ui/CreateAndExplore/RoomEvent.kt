package org.yourappdev.homeinterior.ui.CreateAndExplore

sealed class RoomEvent {
    data class OnSearchQueryChange(val query: String) : RoomEvent()
    data class OnApplyFilters(val filterState: FilterState) : RoomEvent()
    object OnFilterClick : RoomEvent()
    object OnDismissFilterSheet : RoomEvent()
    object OnClearFilters : RoomEvent()
}