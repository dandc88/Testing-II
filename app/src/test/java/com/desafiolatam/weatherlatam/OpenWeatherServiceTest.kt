package com.desafiolatam.weatherlatam

import com.desafiolatam.weatherlatam.data.local.WeatherWrapper
import com.desafiolatam.weatherlatam.data.remote.OpenWeatherService
import kotlinx.coroutines.runBlocking
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenWeatherServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: OpenWeatherService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        // Crea una instancia de Retrofit usando el MockWebServer
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))  // Configura MockWebServer como base URL
            .addConverterFactory(GsonConverterFactory.create())  // Usa Gson en lugar de Moshi
            .client(okhttp3.OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .build()

        service = retrofit.create(OpenWeatherService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test getWeatherData returns correct data`() = runBlocking {
        // Define la respuesta simulada del servidor
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("{\"coord\":{\"lon\":139,\"lat\":35},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":20.0,\"pressure\":1013,\"humidity\":60,\"temp_min\":20.0,\"temp_max\":20.0},\"wind\":{\"speed\":5.1,\"deg\":180},\"clouds\":{\"all\":0},\"dt\":1560350192,\"sys\":{\"type\":3,\"id\":2019346,\"message\":0.0065,\"country\":\"JP\",\"sunrise\":1560281377,\"sunset\":1560333478},\"timezone\":32400,\"id\":1851632,\"name\":\"Shuzenji\",\"cod\":200}")
        mockWebServer.enqueue(mockResponse)

        // Llama a la función getWeatherData
        val response: Response<WeatherWrapper?> = service.getWeatherData(35.0, 139.0, "fakeAppId")

        // Verifica que la respuesta no sea nula y contenga los datos esperados
        val weatherData = response.body()
        assertEquals(200, response.code())
        assertEquals(35.0, weatherData?.coord?.lat)
        assertEquals("Clear", weatherData?.weather?.first()?.main)
    }
}