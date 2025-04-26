package com.example.weatherly.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherly.presentation.screen.CurrentWeather.view.CurrentWeatherScreen
import com.example.weatherly.presentation.screen.ForecastWeather.ForecastWeatherScreen

@Composable
fun NavGraph (
    navHostController: NavHostController
) {
    NavHost (
        navController = navHostController,
        startDestination = Route.CurrentWeather.route
    ) {
        composable (
            route = Route.CurrentWeather.route,
        ) {
            CurrentWeatherScreen(
                navigateToForecast = {
                    navigateToForecastWeatherScreen(
                        navController = navHostController
                    )
                }
            )
        }

        composable (
            route = Route.ForecastWeather.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        400, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(400, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            ForecastWeatherScreen()
        }
    }
}

private fun navigateToForecastWeatherScreen (
    navController: NavController,
) {
    navController.navigate(Route.ForecastWeather.route)
}