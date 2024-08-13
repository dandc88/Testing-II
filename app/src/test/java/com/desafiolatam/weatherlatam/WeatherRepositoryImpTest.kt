package com.desafiolatam.weatherlatam

import com.desafiolatam.weatherlatam.data.WeatherRepositoryImp
import com.desafiolatam.weatherlatam.data.entityToDto
import com.desafiolatam.weatherlatam.data.local.WeatherDao
import com.desafiolatam.weatherlatam.data.local.WeatherEntity
import com.desafiolatam.weatherlatam.data.toEntity
import com.desafiolatam.weatherlatam.model.WeatherDto
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherRepositoryImpTest {

    @Mock
    private lateinit var weatherDao: WeatherDao

    private lateinit var repository: WeatherRepositoryImp

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = WeatherRepositoryImp(weatherDao)
    }

    @Test
    fun `getWeatherDataById returns correct data`() = runTest {
        val id = 1
        val weatherEntity = WeatherEntity(
            id = id,
            currentTemp = 20.0,
            maxTemp = 25.0,
            minTemp = 15.0,
            pressure = 1013.0,
            humidity = 60.0,
            windSpeed = 5.0,
            sunrise = 1627474800,
            sunset = 1627521600,
            cityName = "Santiago"
        )

        // Utiliza entityToDto para la conversión
        val expectedDto = entityToDto(weatherEntity)

        // Simula la respuesta del WeatherDao
        Mockito.`when`(weatherDao.getWeatherDataById(id)).thenReturn(flowOf(weatherEntity))

        // Llama a la función que se va a testear
        val result = repository.getWeatherDataById(id).single()

        // Verifica que el resultado sea el esperado
        assertEquals(expectedDto, result)
    }

    @Test
    fun `insertData inserts data into the database`() = runTest {
        val weatherDto = WeatherDto(
            id = 1,
            currentTemp = 20.0,
            maxTemp = 25.0,
            minTemp = 15.0,
            pressure = 1013.0,
            humidity = 60.0,
            windSpeed = 5.0,
            sunrise = 1627474800,
            sunset = 1627521600,
            cityName = "Santiago"
        )

        val weatherEntity = weatherDto.toEntity()

        // Llama a la función que se va a testear
        repository.insertData(weatherDto)

        // Verifica que el DAO haya recibido la entidad correcta
        Mockito.verify(weatherDao).insertData(weatherEntity)
    }
}