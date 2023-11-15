package pe.com.test.presentation.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.com.test.BuildConfig
import pe.com.test.data.model.Movie
import pe.com.test.data.repository.MoviesRepository
import pe.com.test.presentation.ErrorMessage
import pe.com.test.presentation.Event
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    private val _popularMovies = MutableLiveData<Event<List<Movie?>>>()
    var popularMovies : LiveData<Event<List<Movie?>>> = _popularMovies

    private val _upcomingMovies = MutableLiveData<Event<List<Movie?>>>()
    var upcomingMovies : LiveData<Event<List<Movie?>>> = _upcomingMovies

    private val _errorMessage = MutableLiveData<Event<ErrorMessage>>()
    var errorMessage : LiveData<Event<ErrorMessage>> = _errorMessage


    fun data() {

        viewModelScope.launch {
            val popularMovies = repository.getPopularMovies(BuildConfig.API_KEY, "1", "en-US")
            if (popularMovies != null) {
                _popularMovies.postValue(Event(popularMovies.results))
            } else {
                _errorMessage.postValue(Event(ErrorMessage.ERROR_SEARCH))
            }

            val movieUpcomingResponse = repository.getUpcomingMovies(BuildConfig.API_KEY, "1", "en-US")
            if (movieUpcomingResponse != null) {
                _upcomingMovies.postValue(Event(movieUpcomingResponse.results))
            } else {
                _errorMessage.postValue(Event(ErrorMessage.ERROR_SEARCH))
            }
        }
    }

}