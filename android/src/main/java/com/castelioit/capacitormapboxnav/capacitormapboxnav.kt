package com.castelioit.capacitormapboxnav

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.getcapacitor.Logger
import com.mapbox.common.MapboxOptions
import com.mapbox.navigation.core.lifecycle.MapboxNavigationApp
import com.mapbox.navigation.base.options.NavigationOptions

class capacitormapboxnav {

    fun echo(value: String): String {
        Logger.info("Echo", value)
        return value
    }

    fun initialize(context: Context, accessToken: String) {
        MapboxOptions.accessToken = accessToken
        if (!MapboxNavigationApp.isSetup()) {
            MapboxNavigationApp.setup(NavigationOptions.Builder(context).build())
        }
    }

    fun startNavigation(activity: Activity, originLat: Double, originLng: Double, destLat: Double, destLng: Double, simulateRoute: Boolean) {
        val intent = Intent(activity, NavigationActivity::class.java).apply {
            putExtra("originLat", originLat)
            putExtra("originLng", originLng)
            putExtra("destLat", destLat)
            putExtra("destLng", destLng)
            putExtra("simulateRoute", simulateRoute)
        }
        activity.startActivity(intent)
    }

    fun startTurnByTurnExperience(activity: Activity, originLat: Double, originLng: Double, destLat: Double, destLng: Double, simulateRoute: Boolean) {
        val intent = Intent(activity, TurnByTurnExperienceActivity::class.java).apply {
            putExtra("originLat", originLat)
            putExtra("originLng", originLng)
            putExtra("destLat", destLat)
            putExtra("destLng", destLng)
            putExtra("simulateRoute", simulateRoute)
        }
        activity.startActivity(intent)
    }

    fun startFreeDrive(activity: Activity) {
        val intent = Intent(activity, FreeDriveActivity::class.java)
        activity.startActivity(intent)
    }
}
