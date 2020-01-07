package com.dicoding.bajp.ui.main.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.bajp.data.local.model.Favorite
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class FavoriteMovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerFavorite: Observer<PagedList<Favorite>>

    @Mock
    private lateinit var viewModel: FavoriteMovieViewModel

    private lateinit var liveMovie: MutableLiveData<PagedList<Favorite>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel.favoriteMovie()
        liveMovie = MutableLiveData()
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getAllFavoriteMovie() {
        val pagedList = mock(PagedList::class.java) as PagedList<Favorite>
        liveMovie.value = pagedList

        `when`(viewModel.fetchFavoriteMovie).thenReturn(liveMovie)
        viewModel.fetchFavoriteMovie.observeForever(observerFavorite)
        verify(observerFavorite).onChanged(pagedList)
    }
}