package com.dicoding.bajp.ui.main.favorite.tv

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

class FavoriteTvViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerFavorite: Observer<PagedList<Favorite>>

    @Mock
    private lateinit var viewModel: FavoriteTvViewModel

    private lateinit var movie: MutableLiveData<PagedList<Favorite>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel.favoriteTv()
        movie = MutableLiveData()
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getAllFavoriteTv() {
        val pagedList = mock(PagedList::class.java) as PagedList<Favorite>
        movie.value = pagedList

        `when`(viewModel.fetchFavoriteTv).thenReturn(movie)
        viewModel.fetchFavoriteTv.observeForever(observerFavorite)
        verify(observerFavorite).onChanged(pagedList)
    }
}