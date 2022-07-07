package com.nactem.acronym

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nactem.acronym.model.AcronymData
import com.nactem.acronym.network.AcronymApi
import io.reactivex.Observable
import io.reactivex.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock


class AcronymViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        //setup view model with dependencies mocked
       val mockRepository = mock(AcronymApi::class.java)

        //mock the live data observer
        var mockObserver = mock(Observer::class.java)
    }
    @Test
    fun testSuccess(){
        val expectedResponse: Observable<List<AcronymData>> = Observable.just(emptyList())

    }


}