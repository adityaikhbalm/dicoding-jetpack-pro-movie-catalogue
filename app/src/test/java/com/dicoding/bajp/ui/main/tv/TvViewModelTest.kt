package com.dicoding.bajp.ui.main.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.bajp.data.network.Resource
import com.dicoding.bajp.data.remote.response.ApiResponse
import com.dicoding.bajp.utils.LiveDataTestUtil
import com.dicoding.bajp.utlis.FakeDataDummy
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TvViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerGetTv: Observer<Resource<ApiResponse>>

    @Mock
    private lateinit var viewModel: TvViewModel

    private lateinit var data: Resource<ApiResponse>
    private lateinit var tv: MutableLiveData<Resource<ApiResponse>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel.allTvShows()
        data = Resource.success(
            Gson().fromJson(FakeDataDummy.jsonDiscoverTv, ApiResponse::class.java)
        )
        tv = MutableLiveData()
        tv.value = data
    }

    @Test
    fun getTvShows() {
        `when`(viewModel.fetchTv).thenReturn(tv)
        viewModel.fetchTv.observeForever(observerGetTv)
        verify(observerGetTv).onChanged(data)
        val liveDataTest = LiveDataTestUtil.getValue(viewModel.fetchTv)
        assertEquals(20, liveDataTest.data?.results?.size)
    }
}