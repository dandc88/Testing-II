package com.desafiolatam.weatherlatam

import com.desafiolatam.weatherlatam.data.local.Clouds
import com.desafiolatam.weatherlatam.data.local.Coord
import com.desafiolatam.weatherlatam.data.local.Main
import com.desafiolatam.weatherlatam.data.local.Rain
import com.desafiolatam.weatherlatam.data.local.Sys
import com.desafiolatam.weatherlatam.data.local.Weather
import com.desafiolatam.weatherlatam.data.local.WeatherWrapper
import com.desafiolatam.weatherlatam.data.local.Wind
import com.desafiolatam.weatherlatam.data.toWeatherDto
import com.desafiolatam.weatherlatam.model.WeatherDto
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `test WeatherWrapper toWeatherDto`() {
        // Arrange
        val weatherWrapper = WeatherWrapper(
            coord = Coord(lon = -70.64666, lat = -33.43107),
            weather = listOf(Weather(id = 1, main = "Clear", description = "Clear sky", icon = "01d")),
            base = "stations",
            main = Main(
                temp = 25.0,
                feels_like = 27.0,
                temp_min = 22.0,
                temp_max = 28.0,
                pressure = 1012,
                humidity = 65,
                seaLevel = 1012,
                grndLevel = 1005
            ),
            visibility = 10000,
            wind = Wind(speed = 5.0, deg = 250, gust = 6.0),
            rain = Rain(the1H = 0.0),
            clouds = Clouds(all = 0),
            dt = 1631001600L,
            sys = Sys(type = 1, id = 1234, country = "Country", sunrise = 1630972800L, sunset = 1631016000L),
            timezone = -18000,
            id = 123456789L,
            name = "CityName",
            cod = 200
        )

        val expectedWeatherDto = WeatherDto(
            id = 0,
            currentTemp = 25.0,
            maxTemp = 28.0,
            minTemp = 22.0,
            pressure = 1012.0,
            humidity = 65.0,
            windSpeed = 5.0,
            sunrise = 1630972800L,
            sunset = 1631016000L,
            cityName = "CityName, Country"
        )

        // Act
        val result = weatherWrapper.toWeatherDto()

        // Assert
        assertEquals(expectedWeatherDto, result)
    }
}