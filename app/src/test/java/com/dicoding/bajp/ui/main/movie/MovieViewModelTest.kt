package com.dicoding.bajp.ui.main.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.bajp.data.network.Resource
import com.dicoding.bajp.data.remote.response.ApiResponse
import com.dicoding.bajp.utils.LiveDataTestUtil
import com.dicoding.bajp.utlis.FakeDataDummy
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerGetMovie: Observer<Resource<ApiResponse>>

    @Mock
    private lateinit var viewModel: MovieViewModel

    private lateinit var data: Resource<ApiResponse>
    private lateinit var movie: MutableLiveData<Resource<ApiResponse>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel.allMovies()
        data = Resource.success(
            Gson().fromJson(FakeDataDummy.jsonDiscoverMovie, ApiResponse::class.java)
        )
        movie = MutableLiveData()
        movie.value = data
    }

    @Test
    fun getMovies() {
        `when`(viewModel.fetchMovie).thenReturn(movie)
        viewModel.fetchMovie.observeForever(observerGetMovie)
        verify(observerGetMovie).onChanged(data)

        val liveDataTest = LiveDataTestUtil.getValue(viewModel.fetchMovie)
        assertNotNull(liveDataTest)
        assertEquals(20, liveDataTest.data?.results?.size)
    }
}