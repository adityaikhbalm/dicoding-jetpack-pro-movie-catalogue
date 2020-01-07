package com.dicoding.bajp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.bajp.data.local.model.Favorite
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

class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerFavorite: Observer<Favorite>

    @Mock
    private lateinit var viewModel: DetailViewModel

    private lateinit var favoriteMovie: MutableLiveData<Favorite>
    private lateinit var favoriteTv: MutableLiveData<Favorite>

    private lateinit var dummyMovie: Favorite
    private lateinit var dummyTv: Favorite

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val movie =
            Gson().fromJson(FakeDataDummy.jsonDiscoverMovie, ApiResponse::class.java).results[0]
        movie.type = "movie"
        dummyMovie = Favorite(
            movie.id,
            movie.backdropPath,
            movie.originalLanguage,
            movie.overview,
            movie.posterPath,
            movie.releaseDate,
            movie.title,
            movie.voteAverage,
            movie.voteCount,
            movie.type
        )
        favoriteMovie = MutableLiveData()
        favoriteMovie.value = dummyMovie

        val tv = Gson().fromJson(FakeDataDummy.jsonDiscoverTv, ApiResponse::class.java).results[0]
        tv.type = "tv"
        dummyTv = Favorite(
            tv.id,
            tv.backdropPath,
            tv.originalLanguage,
            tv.overview,
            tv.posterPath,
            tv.firstAirDate,
            tv.name,
            tv.voteAverage,
            tv.voteCount,
            tv.type
        )
        favoriteTv = MutableLiveData()
        favoriteTv.value = dummyTv
    }

    @Test
    fun is_favorite_movie() {
        viewModel.fetchFavorite(dummyMovie.id)

        `when`(viewModel.isFavorite).thenReturn(favoriteMovie)
        viewModel.isFavorite.observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyMovie)

        val liveDataTest = LiveDataTestUtil.getValue(viewModel.isFavorite)
        assertNotNull(liveDataTest)
        assertEquals(419704, liveDataTest.id)
    }

    @Test
    fun insert_favorite_movie() {
        viewModel.fetchFavorite(dummyMovie.id)
        viewModel.insertFavorite(dummyMovie)

        `when`(viewModel.isFavorite).thenReturn(favoriteMovie)
        viewModel.isFavorite.observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyMovie)

        val liveDataTest = LiveDataTestUtil.getValue(viewModel.isFavorite)
        assertNotNull(liveDataTest)
        assertEquals(419704, liveDataTest.id)
    }

    @Test
    fun delete_favorite_movie() {
        viewModel.insertFavorite(dummyMovie)
        viewModel.deleteFavorite(dummyMovie)

        `when`(viewModel.isFavorite).thenReturn(favoriteMovie)
        viewModel.isFavorite.observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyMovie)

        val liveDataTest = LiveDataTestUtil.getValue(viewModel.isFavorite)
        assertNotNull(liveDataTest)
        assertEquals(419704, liveDataTest.id)
    }

    @Test
    fun is_favorite_tv() {
        viewModel.fetchFavorite(dummyTv.id)

        `when`(viewModel.isFavorite).thenReturn(favoriteTv)
        viewModel.isFavorite.observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyTv)

        val liveDataTest = LiveDataTestUtil.getValue(viewModel.isFavorite)
        assertNotNull(liveDataTest)
        assertEquals(1622, liveDataTest.id)
    }

    @Test
    fun insert_favorite_tv() {
        viewModel.fetchFavorite(dummyTv.id)
        viewModel.insertFavorite(dummyTv)

        `when`(viewModel.isFavorite).thenReturn(favoriteTv)
        viewModel.isFavorite.observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyTv)

        val liveDataTest = LiveDataTestUtil.getValue(viewModel.isFavorite)
        assertNotNull(liveDataTest)
        assertEquals(1622, liveDataTest.id)
    }

    @Test
    fun delete_favorite_tv() {
        viewModel.fetchFavorite(dummyTv.id)
        viewModel.insertFavorite(dummyTv)
        viewModel.deleteFavorite(dummyTv)

        `when`(viewModel.isFavorite).thenReturn(favoriteTv)
        viewModel.isFavorite.observeForever(observerFavorite)
        verify(observerFavorite).onChanged(dummyTv)

        val liveDataTest = LiveDataTestUtil.getValue(viewModel.isFavorite)
        assertNotNull(liveDataTest)
        assertEquals(1622, liveDataTest.id)
    }
}