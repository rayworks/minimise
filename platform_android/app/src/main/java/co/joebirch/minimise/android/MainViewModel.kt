package co.joebirch.minimise.android

import androidx.lifecycle.ViewModel
import co.joebirch.minimise.android.core.di.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    sharedPreferences: Preferences
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainState>(MainState.Loading)
    val uiState: StateFlow<MainState> = _uiState

    init {
        sharedPreferences.accessToken?.let {
            _uiState.value = MainState.Authenticated
        } ?: run {
            _uiState.value = MainState.NotAuthenticated
        }
    }
}
