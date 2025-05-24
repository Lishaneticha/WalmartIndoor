package com.lishan.walmartindoor.viewmodel

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lishan.walmartindoor.model.entity.Section
import com.lishan.walmartindoor.model.entity.Shelf
import com.lishan.walmartindoor.model.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val repository: StoreRepository
) : ViewModel() {

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess

    fun saveSectionWithShelves(
        sectionName: String,
        shelfLabels: List<String>,
        rectOffset: Offset
    ) {
        viewModelScope.launch {
            try {
                val section = Section(name = sectionName)

                val shelves = shelfLabels.map { label ->
                    Shelf(
                        label = label,
                        sectionOwnerId = 0, // will be updated after inserting Section
                        offsetX = rectOffset.x,
                        offsetY = rectOffset.y
                    )
                }

                repository.insertSectionWithShelves(section, shelves)
                _saveSuccess.value = true
            } catch (e: Exception) {
                Log.e("StoreViewModel", "Error saving: ${e.message}")
                _saveSuccess.value = false
            }
        }
    }

    fun clearSuccessFlag() {
        _saveSuccess.value = false
    }
}