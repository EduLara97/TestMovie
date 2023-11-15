package pe.com.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pe.com.test.presentation.ErrorMessage
import pe.com.test.presentation.home.viewModel.HomeViewModel
import pe.com.test.repositories.FakeMoviesRepositoryImpl

class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(FakeMoviesRepositoryImpl())
    }

    @Test
    fun `get popular and upcoming movies, return list`() {
        homeViewModel.data()

        val popularMovies = homeViewModel.popularMovies.getOrAwaitValueTest()
        val upcomingMovies = homeViewModel.upcomingMovies.getOrAwaitValueTest()

        assertThat(popularMovies.getContentIfNotHandled()?.size).isEqualTo(1)
        assertThat(upcomingMovies.getContentIfNotHandled()?.size).isEqualTo(1)
    }

    @Test
    fun `get popular and upcoming movies, return error`() {
        val fakeRepositoryInstance = FakeMoviesRepositoryImpl()
        homeViewModel = HomeViewModel(fakeRepositoryInstance)
        fakeRepositoryInstance.setReturnErrorCall(true)
        homeViewModel.data()

        val errorMessage = homeViewModel.errorMessage.getOrAwaitValueTest()

        assertThat(errorMessage.getContentIfNotHandled()).isEqualTo(ErrorMessage.ERROR_SEARCH)
    }

}