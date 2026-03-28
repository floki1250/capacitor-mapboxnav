# Get Started with the Navigation SDK for Android

This guide describes the steps to install the latest version of the **Mapbox Navigation SDK for Android**, configure your Android app to use the SDK, and run a minimal example showing turn-by-turn navigation using a simulated user location.

## Prerequisites

-   **A Mapbox account**: Sign up or log into a free account on [Mapbox](https://account.mapbox.com/auth/signup/).
-   **Android Studio**: This guide includes details specific to the latest version of [Android Studio](https://developer.android.com/studio/install).
-   **Gradle**: Make sure you have [Gradle](https://gradle.org/install/) installed.

Before proceeding, **create a new Android project** in Android Studio using the `Empty Activity` project type.

Your browser doesn't support HTML5 video. Open [link to the video](https://docs.mapbox.com/android/android/assets/medias/navigation-guides-install-example-12cd5b46aa3f4f97bc38500226aec83b.webm).

## Part 1: Configure credentials

### Step 1: Create a secret token

A [secret access token](https://docs.mapbox.com/help/dive-deeper/access-tokens/#secret-tokens) is required to download the SDK dependencies in your Android project. This token is used by gradle to authenticate with the Mapbox maven server where the SDK packages are hosted.

To create a secret token, follow these steps:

1.  Go to your account's [tokens page](https://console.mapbox.com/account/access-tokens/).
2.  Click the **Create a token** button.
3.  Name your token, for this example we've used *InstallTokenAndroid*.
4.  Scroll down to the **Secret Scope** section and check the `Downloads:Read` scope box.
5.  Click the **Create token** button at the bottom of the page to create your token.
6.  Enter your password to confirm the creation of your token.
7.  Now, you'll be returned to your account's [tokens page](https://console.mapbox.com/account/access-tokens/), where you can copy your created token. Note, this token is a *secret token*, which means you will only have one opportunity to copy it, so **save this token somewhere secure**.

Your browser doesn't support HTML5 video. Open [link to the video](https://docs.mapbox.com/android/android/assets/medias/create-secret-token-compressed-823407693dc7c7f3351fb5c23377d5bf.mp4).

> **Note (warning): Protect secret access tokens**
> 
> You should not expose secret access tokens in publicly-accessible source code where unauthorized users might find them. Instead, you should store them somewhere safe on your computer and take advantage of [Gradle properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties) to make sure they're only added when your app is compiled.

### Step 2: Configure your secret token

Next, add your secret token to your global `gradle.properties` file. The global `gradle.properties` file is located in your Gradle user home folder, but is also available in Android Studio's project explorer under `Gradle Scripts/gradle.properties (Global Properties)`

If you don't have a `gradle.properties` file, create one. Add your secret token to the `gradle.properties` file as shown below, replacing the placeholder `YOUR_SECRET_MAPBOX_ACCESS_TOKEN` with your secret token.

```text
MAPBOX_DOWNLOADS_TOKEN=YOUR_SECRET_MAPBOX_ACCESS_TOKEN
```

Your secret token is now configured and will be used to authenticate with the Mapbox maven server when you add the SDK dependency to your project.

### Step 3: Configure your public token

Your app must have a public access token configured to associate its usage of Mapbox resources with your account.

Follow these steps to add a public access token from your Mapbox account as an [Android string resource](https://developer.android.com/guide/topics/resources/string-resource#String).

1.  Locate the resource folder:

-   In the project explorer, find and expand your resource folder located at `app/res/values`.

2.  Create a new string resource file :

-   Right click on the `values` folder
-   Select **New > Values Resource File**
-   Name the file `mapbox_access_token.xml`
-   Click the **OK** button.

4.  In the new file, copy and paste the code snippet below.

-   Make sure you are signed in to docs.mapbox.com. This will insert your default public token into the code snippet (a long string that starts with `pk.`).
-   If you are not signed in, you will need to replace the placeholder `YOUR_MAPBOX_ACCESS_TOKEN` with a token from your account's [tokens page](https://console.mapbox.com/account/access-tokens/).

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <string name="mapbox_access_token" translatable="false" tools:ignore="UnusedResources">YOUR_MAPBOX_ACCESS_TOKEN</string>
</resources>
```

Your Mapbox public access token is now available for use in your Android project. The Maps SDK for Android automatically looks for the `mapbox_access_token` string resource when you initialize a map, so you don't need to do anything else to use the token in your app.

Details

**Advanced Topics: Best Practices, Rotating Tokens & Adding Tokens at Runtime**

> **Related content (guide): [Access token best practices](https://docs.mapbox.com/help/troubleshooting/private-access-token-android-and-ios/)**
> 
> Learn how to keep access tokens private in mobile apps.

**Adding Tokens at Runtime**

You can also implement tokens at runtime, but this requires you to have a separate server to store your tokens. This is helpful if you want to rotate your tokens or add additional security by storing your tokens outside of the APK, but is a much more complex method of implementation.

If you do choose to follow this method, we recommend calling `MapboxOptions.accessToken = YOUR_PUBLIC_MAPBOX_ACCESS_TOKEN` before inflating the `MapView`, otherwise the app will crash.

**Rotating Tokens**

For more information on access token rotation, consult the [Access Tokens Information page](https://docs.mapbox.com/help/how-mapbox-works/access-tokens/).

### Step 4: Understand permissions

Real-time navigation requires access to the user's location, access to the network, and access to notify the user of updates. The SDK already includes all necessary permissions in its `AndroidManifest.xml` file, and these will be merged with any permissions you have in your app's manifest file via [Manifest Merging](https://minimum-viable-product.github.io/marshmallow-docs/tools/building/manifest-merge.html#:~:text=During%20the%20build%20process%2C%20manifest,by%20the%20manifest's%20file%20location.).

You do not need to add any additional permissions to your app's manifest file, but you should make sure that your app requests the necessary permissions at runtime. The SDK provides a [`PermissionsManager`](https://docs.mapbox.com/android/maps/guides/user-location/permissions/) class to help you manage permissions. You can check whether the user has granted location permission and request permissions if the user hasn't granted them yet using the `PermissionsManager`.

The permissions used by the SDK are listed below:

```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE_LOCATION" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

> **Note: Understanding the POST_NOTIFICATIONS permission**
> 
> If your app targets Android 13 or higher, the [`POST_NOTIFICATIONS`](https://developer.android.com/guide/topics/ui/notifiers/notification-permission) permission is required to show notifications to the user. The SDK shows the notifications during both **Free Drive** and **Active Guidance** if you don't explicitly disable the foreground service launch by calling `MapboxNavigation#startTripSession(false)`.
> 
> The notification contains some useful trip progress information and UI controls, so it is highly recommended that you [request the permission in runtime](https://developer.android.com/training/permissions/requesting#request-permission).

## Part 2: Add the Dependency

### Step 1: Add the Mapbox Maven repository

Mapbox provides the Maps SDK dependencies via a private Maven repository. To download Mapbox dependencies, you must add the Maven repository's URL to your project.

1.  In the project explorer, find and open `Gradle Scripts/settings.gradle.kts`.
2.  Add a new `maven {...}` definition inside `dependencyResolutionManagement.repositories`.

**Groovy**

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Mapbox Maven repository
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                basic(BasicAuthentication)
            }
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                password = providers.gradleProperty("MAPBOX_DOWNLOADS_TOKEN").get()
            }
        }
    }
}
```

**Kotlin**

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Mapbox Maven repository
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                password = providers.gradleProperty("MAPBOX_DOWNLOADS_TOKEN").get()
            }
        }
    }
}
```

This configuration uses the **secret access token** you configured in **Part 1** to authenticate with the Mapbox Maven repository.

> **Note (warning): Add the Mapbox Maven repository in the correct location**
> 
> Make sure you configure the Mapbox maven repository inside `dependencyResolutionManagement` and not inside `pluginManagement`.

### Step 2: Add Nav SDK dependencies

Next, add the Nav SDK modules to your project to start using the Mapbox Navigation SDK for Android:

1.  In Android Studio, open your *module-level* `build.gradle` file.
2.  Add the `navigationcore:android` dependency under `dependencies`.
    -   This will allow you to access base navigation services, like creating a route.

**Groovy**

Title: `build.gradle`

```groovy
dependencies {
  ...
    implementation "com.mapbox.navigationcore:android-ndk27:3.21.0-rc.1"  // Adds core Navigation SDK functionality
    // if your app does not require 16 KB page size support, the default dependency without -ndk27 can be used
    // implementation "com.mapbox.navigationcore:android:3.21.0-rc.1"
  ...
}
```

**Kotlin**

Title: `build.gradle.kts`

```kotlin
dependencies {
  ...
    implementation("com.mapbox.navigationcore:android-ndk27:3.21.0-rc.1")  // Adds core Navigation SDK functionality
    // if your app does not require 16 KB page size support, the default dependency without -ndk27 can be used
    // implementation("com.mapbox.navigationcore:android:3.21.0-rc.1")
  ...
}
```

> **Note (warning): Mapbox Maps SDK and Search SDK compatibility**
> 
> If you want to use the Navigation SDK with the Mapbox Maps and the Search SDKs, make sure you are using SDKs with the same sub-version number. For example if you are using v3.18.0 of the Navigation SDK, you should use v3.18.0 of the Maps SDK and use v2.18.0 of the Search SDK.
> 
> If you are using v3.16.0 of the Navigation SDK or older, review the [Navigation SDK release notes](https://github.com/mapbox/mapbox-navigation-android/releases) to check for the correct compatible versions.

3.  Additional dependencies are available for specific features of the Navigation SDK. You can choose to add any of these additional modules to your project depending on the needs of your app.
    
    -   None of these are necessary to run the example code in this guide, but you can always add them later as you expand your app's navigation features.

| Module | Description |
| --- | --- |
| `com.mapbox.navigationcore:copilot` | The *Copilot* is a component that collects detailed trace files of navigation sessions together with search analytics data. |
| `com.mapbox.navigationcore:ui-maps` | The *Maps* component provides a set of classes that can enhance the navigation experience for your users, for example, `Navigation Camera` to simplify management of the map's camera object, or `Route Line` to render a route line on a map. |
| `com.mapbox.navigationcore:navigation` | The *Navigation* module is the primary interface for engaging with the Navigation SDK. |
| `com.mapbox.navigationcore:tripdata` | The *Trip Data* component provides convenient API to request maneuver instructions, route shields, speed limit, and trip progress data. |
| `com.mapbox.navigationcore:ui-components` | The *UI components* module provides pre-built UI widgets. |
| `com.mapbox.navigationcore:voice` | The Voice component allows users to access Voice API. |

**Groovy**

Title: `build.gradle`

```groovy
dependencies {
  ...
    implementation "com.mapbox.navigationcore:copilot-ndk27:3.21.0-rc.1"
    implementation "com.mapbox.navigationcore:ui-maps-ndk27:3.21.0-rc.1"
    implementation "com.mapbox.navigationcore:voice-ndk27:3.21.0-rc.1"
    implementation "com.mapbox.navigationcore:tripdata-ndk27:3.21.0-rc.1"
    implementation "com.mapbox.navigationcore:android-ndk27:3.21.0-rc.1"
    implementation "com.mapbox.navigationcore:ui-components-ndk27:3.21.0-rc.1"

    // if your app does not require 16 KB page size support, the default dependency without -ndk27 can be used
    // for example, for the copilot module:
    // implementation com.mapbox.navigationcore:copilot:3.21.0-rc.1
  ...
}
```

**Kotlin**

Title: `build.gradle.kts`

```kotlin
dependencies {
  ...
    implementation("com.mapbox.navigationcore:copilot-ndk27:3.21.0-rc.1")
    implementation("com.mapbox.navigationcore:ui-maps-ndk27:3.21.0-rc.1")
    implementation("com.mapbox.navigationcore:voice-ndk27:3.21.0-rc.1")
    implementation("com.mapbox.navigationcore:tripdata-ndk27:3.21.0-rc.1")
    implementation("com.mapbox.navigationcore:android-ndk27:3.21.0-rc.1")
    implementation("com.mapbox.navigationcore:ui-components-ndk27:3.21.0-rc.1")

    // if your app does not require 16 KB page size support, the default dependency without -ndk27 can be used
    // for example, for the copilot module:
    // implementation("com.mapbox.navigationcore:copilot:3.21.0-rc.1")
  ...
}
```

> **Note: About 16 KB page size support**
> 
> Starting with version 3.11.0 and 2.21.0, **NDK 27 is supported** with dedicated artifacts that include [support for 16 KB page sizes](https://developer.android.com/guide/practices/page-sizes). If your app does not require 16 KB page size support, you can keep using our default artifacts without `-ndk27` suffix.

4.  In Android Studio, run **File > Sync Project with Gradle Files** to make sure the new dependencies are added to your project. Once your project has synced successfully, you are ready to implement navigation.

## Part 3: Run a Navigation Example

To confirm that the Navigation SDK dependencies are correctly installed and your tokens are configured properly, create a turn-by-turn navigation with simulated user movement.

1.  In the project explorer, find and open `app/kotlin+java/PROJECT_NAME/MainActivity.kt`.
    
2.  Paste the code snippet below into the `MainActivity.kt` file, replacing the existing code. Make sure to replace the package import line at the top of the file with your project's package import line. This will typically follow the format of `package com.example.PROJECT_NAME`.
    

This code displays a scaled-down navigation experience with a map, route line, and location puck. For more details on the steps show to set up navigation in this example, see the [Initialize the Navigation SDK guide](https://docs.mapbox.com/android/navigation/guides/initialization/).

**Android View**

```kotlin
package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.common.location.Location
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.animation.camera
import com.mapbox.maps.plugin.attribution.attribution
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.logo.logo
import com.mapbox.maps.plugin.scalebar.scalebar
import com.mapbox.navigation.base.ExperimentalPreviewMapboxNavigationAPI
import com.mapbox.navigation.base.extensions.applyDefaultNavigationOptions
import com.mapbox.navigation.base.options.NavigationOptions
import com.mapbox.navigation.base.route.NavigationRoute
import com.mapbox.navigation.base.route.NavigationRouterCallback
import com.mapbox.navigation.base.route.RouterFailure
import com.mapbox.navigation.core.MapboxNavigation
import com.mapbox.navigation.core.directions.session.RoutesObserver
import com.mapbox.navigation.core.lifecycle.MapboxNavigationApp
import com.mapbox.navigation.core.lifecycle.MapboxNavigationObserver
import com.mapbox.navigation.core.lifecycle.requireMapboxNavigation
import com.mapbox.navigation.core.replay.route.ReplayProgressObserver
import com.mapbox.navigation.core.replay.route.ReplayRouteMapper
import com.mapbox.navigation.core.trip.session.LocationMatcherResult
import com.mapbox.navigation.core.trip.session.LocationObserver
import com.mapbox.navigation.ui.maps.camera.NavigationCamera
import com.mapbox.navigation.ui.maps.camera.data.MapboxNavigationViewportDataSource
import com.mapbox.navigation.ui.maps.location.NavigationLocationProvider
import com.mapbox.navigation.ui.maps.route.line.api.MapboxRouteLineApi
import com.mapbox.navigation.ui.maps.route.line.api.MapboxRouteLineView
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineApiOptions
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineViewOptions

class MainActivity : ComponentActivity() {
    private lateinit var mapView: MapView
    private lateinit var viewportDataSource: MapboxNavigationViewportDataSource
    private lateinit var navigationCamera: NavigationCamera
    private lateinit var routeLineApi: MapboxRouteLineApi
    private lateinit var routeLineView: MapboxRouteLineView
    private lateinit var replayProgressObserver: ReplayProgressObserver
    private val navigationLocationProvider = NavigationLocationProvider()
    private val replayRouteMapper = ReplayRouteMapper()

    // Activity result launcher for location permissions
    private val locationPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                permissions ->
            when {
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                    initializeMapComponents()
                }
                else -> {
                    Toast.makeText(
                        this,
                        "Location permissions denied. Please enable permissions in settings.",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check/request location permissions
        if (
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            // Permissions are already granted
            initializeMapComponents()
        } else {
            // Request location permissions
            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
        }
    }

    private fun initializeMapComponents() {
        // create a new Mapbox map
        mapView = MapView(this, MapInitOptions(
            this,
            cameraOptions = CameraOptions.Builder()
                .center(Point.fromLngLat(-122.43539772352648, 37.77440680146262))
                .zoom(14.0)
                .build(),
        ))

        mapView.scalebar.marginTop = 200f
        mapView.compass.marginTop = 200f
        mapView.logo.marginBottom = 140f
        mapView.attribution.marginBottom = 140f
        
        // Initialize location puck using navigationLocationProvider as its data source
        mapView.location.apply {
            setLocationProvider(navigationLocationProvider)
            locationPuck = LocationPuck2D()
            enabled = true
        }

        setContentView(mapView)

        // set viewportDataSource, which tells the navigationCamera where to look
        viewportDataSource = MapboxNavigationViewportDataSource(mapView.mapboxMap)

        // set padding for the navigation camera
        val pixelDensity = this.resources.displayMetrics.density
        viewportDataSource.followingPadding =
            EdgeInsets(
                180.0 * pixelDensity,
                40.0 * pixelDensity,
                150.0 * pixelDensity,
                40.0 * pixelDensity
            )

        // initialize a NavigationCamera
        navigationCamera = NavigationCamera(mapView.mapboxMap, mapView.camera, viewportDataSource)

        // Initialize route line api and view for drawing the route on the map
        routeLineApi = MapboxRouteLineApi(MapboxRouteLineApiOptions.Builder().build())
        routeLineView = MapboxRouteLineView(MapboxRouteLineViewOptions.Builder(this).build())
    }

    // routes observer draws a route line and origin/destination circles on the map
    private val routesObserver = RoutesObserver { routeUpdateResult ->
        if (routeUpdateResult.navigationRoutes.isNotEmpty()) {
            // generate route geometries asynchronously and render them
            routeLineApi.setNavigationRoutes(routeUpdateResult.navigationRoutes) { value ->
                mapView.mapboxMap.style?.apply { routeLineView.renderRouteDrawData(this, value) }
            }

            // update viewportSourceData to include the new route
            viewportDataSource.onRouteChanged(routeUpdateResult.navigationRoutes.first())
            viewportDataSource.evaluate()

            // set the navigationCamera to OVERVIEW
            navigationCamera.requestNavigationCameraToOverview()
        }
    }

    // locationObserver updates the location puck and camera to follow the user's location
    private val locationObserver =
        object : LocationObserver {
            override fun onNewRawLocation(rawLocation: Location) {}

            override fun onNewLocationMatcherResult(locationMatcherResult: LocationMatcherResult) {
                val enhancedLocation = locationMatcherResult.enhancedLocation
                // update location puck's position on the map
                navigationLocationProvider.changePosition(
                    location = enhancedLocation,
                    keyPoints = locationMatcherResult.keyPoints,
                )

                // update viewportDataSource to trigger camera to follow the location
                viewportDataSource.onLocationChanged(enhancedLocation)
                viewportDataSource.evaluate()

                // set the navigationCamera to FOLLOWING
                navigationCamera.requestNavigationCameraToFollowing()
            }
        }

    // define MapboxNavigation
    @OptIn(ExperimentalPreviewMapboxNavigationAPI::class)
    private val mapboxNavigation: MapboxNavigation by
    requireMapboxNavigation(
        onResumedObserver =
            object : MapboxNavigationObserver {
                @SuppressLint("MissingPermission")
                override fun onAttached(mapboxNavigation: MapboxNavigation) {
                    // register observers
                    mapboxNavigation.registerRoutesObserver(routesObserver)
                    mapboxNavigation.registerLocationObserver(locationObserver)

                    replayProgressObserver =
                        ReplayProgressObserver(mapboxNavigation.mapboxReplayer)
                    mapboxNavigation.registerRouteProgressObserver(replayProgressObserver)
                    mapboxNavigation.startReplayTripSession()
                }

                override fun onDetached(mapboxNavigation: MapboxNavigation) {}
            },
        onInitialize = this::initNavigation
    )

    // on initialization of MapboxNavigation, request a route between two fixed points
    @OptIn(ExperimentalPreviewMapboxNavigationAPI::class)
    private fun initNavigation() {
        MapboxNavigationApp.setup(NavigationOptions.Builder(this).build())

        // initialize location puck
        mapView.location.apply {
            setLocationProvider(navigationLocationProvider)
            this.locationPuck = createDefault2DPuck()
            enabled = true
        }

        val origin = Point.fromLngLat(-122.43539772352648, 37.77440680146262)
        val destination = Point.fromLngLat(-122.42409811526268, 37.76556957793795)

        mapboxNavigation.requestRoutes(
            RouteOptions.builder()
                .applyDefaultNavigationOptions()
                .coordinatesList(listOf(origin, destination))
                .layersList(listOf(mapboxNavigation.getZLevel(), null))
                .build(),
            object : NavigationRouterCallback {
                override fun onCanceled(routeOptions: RouteOptions, routerOrigin: String) {}

                override fun onFailure(reasons: List<RouterFailure>, routeOptions: RouteOptions) {}

                override fun onRoutesReady(routes: List<NavigationRoute>, routerOrigin: String) {
                    mapboxNavigation.setNavigationRoutes(routes)

                    // start simulated user movement
                    val replayData =
                        replayRouteMapper.mapDirectionsRouteGeometry(routes.first().directionsRoute)
                    mapboxNavigation.mapboxReplayer.pushEvents(replayData)
                    mapboxNavigation.mapboxReplayer.seekTo(replayData[0])
                    mapboxNavigation.mapboxReplayer.play()
                }
            }
        )
    }
}
```

**Jetpack Compose**

```kotlin
package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.common.location.Location
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.animation.camera
import com.mapbox.maps.plugin.attribution.attribution
import com.mapbox.maps.plugin.compass.compass
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.logo.logo
import com.mapbox.maps.plugin.scalebar.scalebar
import com.mapbox.navigation.base.ExperimentalPreviewMapboxNavigationAPI
import com.mapbox.navigation.base.extensions.applyDefaultNavigationOptions
import com.mapbox.navigation.base.options.NavigationOptions
import com.mapbox.navigation.base.route.NavigationRoute
import com.mapbox.navigation.base.route.NavigationRouterCallback
import com.mapbox.navigation.base.route.RouterFailure
import com.mapbox.navigation.core.MapboxNavigationProvider
import com.mapbox.navigation.core.replay.route.ReplayProgressObserver
import com.mapbox.navigation.core.replay.route.ReplayRouteMapper
import com.mapbox.navigation.core.trip.session.LocationMatcherResult
import com.mapbox.navigation.core.trip.session.LocationObserver
import com.mapbox.navigation.ui.maps.camera.NavigationCamera
import com.mapbox.navigation.ui.maps.camera.data.MapboxNavigationViewportDataSource
import com.mapbox.navigation.ui.maps.location.NavigationLocationProvider
import com.mapbox.navigation.ui.maps.route.line.api.MapboxRouteLineApi
import com.mapbox.navigation.ui.maps.route.line.api.MapboxRouteLineView
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineApiOptions
import com.mapbox.navigation.ui.maps.route.line.model.MapboxRouteLineViewOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MapboxComposeApp() }
    }
}

@Composable
fun MapboxComposeApp() {
    val context = LocalContext.current
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        hasPermission = perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (!hasPermission) {
            Toast.makeText(
                context,
                "Location permission denied. Enable it in settings.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
        }
    }

    if (hasPermission) {
        NavigationMapScreen()
    } else {
        Box(Modifier.fillMaxSize()) {
            Button(onClick = {
                permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
            }) { Text("Grant location permission") }
        }
    }
}

@OptIn(ExperimentalPreviewMapboxNavigationAPI::class)
@Composable
fun NavigationMapScreen() {
    val context = LocalContext.current
    val density = LocalDensity.current

    // Map + Navigation state holders
    val mapViewState = remember { mutableStateOf<MapView?>(null) }
    val navigationLocationProvider = remember { NavigationLocationProvider() }
    val replayRouteMapper = remember { ReplayRouteMapper() }

    // Route line + camera state
    var viewportDataSource by remember { mutableStateOf<MapboxNavigationViewportDataSource?>(null) }
    var navigationCamera by remember { mutableStateOf<NavigationCamera?>(null) }
    val routeLineApi by remember {
        mutableStateOf(MapboxRouteLineApi(MapboxRouteLineApiOptions.Builder().build()))
    }
    val routeLineView by remember {
        mutableStateOf(
            MapboxRouteLineView(
                MapboxRouteLineViewOptions.Builder(context).build()
            )
        )
    }

    // Create MapboxNavigation once for this screen
    val mapboxNavigation = remember {
        MapboxNavigationProvider.create(
            NavigationOptions.Builder(context).build()
        )
    }

    // Observers
    val routesObserver = remember {
        com.mapbox.navigation.core.directions.session.RoutesObserver { routeUpdate ->
            val mv = mapViewState.value ?: return@RoutesObserver
            if (routeUpdate.navigationRoutes.isNotEmpty()) {
                // draw the route
                routeLineApi.setNavigationRoutes(routeUpdate.navigationRoutes) { drawData ->
                    mv.mapboxMap.style?.let { style ->
                        routeLineView.renderRouteDrawData(style, drawData)
                    }
                }

                // update viewport and go to overview
                viewportDataSource?.onRouteChanged(routeUpdate.navigationRoutes.first())
                viewportDataSource?.evaluate()
                navigationCamera?.requestNavigationCameraToOverview()
            }
        }
    }

    val locationObserver = remember {
        object : LocationObserver {
            override fun onNewRawLocation(rawLocation: Location) {}

            override fun onNewLocationMatcherResult(locationMatcherResult: LocationMatcherResult) {
                val enhanced = locationMatcherResult.enhancedLocation
                navigationLocationProvider.changePosition(
                    location = enhanced,
                    keyPoints = locationMatcherResult.keyPoints
                )
                viewportDataSource?.onLocationChanged(enhanced)
                viewportDataSource?.evaluate()
                navigationCamera?.requestNavigationCameraToFollowing()
            }
        }
    }

    // Build the MapView inside Compose
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            MapView(ctx, mapInitOptions = MapInitOptions(
                ctx,
                cameraOptions = CameraOptions.Builder()
                    .center(Point.fromLngLat(-122.43539772352648, 37.77440680146262))
                    .zoom(14.0)
                    .build()
                )).apply {
                scalebar.marginTop = 200f
                compass.marginTop = 200f
                logo.marginBottom = 140f
                attribution.marginBottom = 140f

                // Enable location puck using NavigationLocationProvider
                location.apply {
                    setLocationProvider(navigationLocationProvider)
                    locationPuck = LocationPuck2D() // replaced later with default 2D puck as well
                    enabled = true
                }
                mapViewState.value = this

                // Viewport, padding, and camera
                viewportDataSource = MapboxNavigationViewportDataSource(mapboxMap).also { vds ->
                    val top = with(density) { 180.dp.toPx().toDouble() }
                    val left = with(density) { 40.dp.toPx().toDouble() }
                    val bottom = with(density) { 150.dp.toPx().toDouble() }
                    val right = with(density) { 40.dp.toPx().toDouble() }
                    vds.followingPadding = EdgeInsets(top, left, bottom, right)
                }
                navigationCamera = NavigationCamera(mapboxMap, camera, viewportDataSource!!)
            }
        },
        update = { /* no-op */ }
    )

    // Wire up Navigation lifecycle with Compose
    DisposableEffect(Unit) {
        // Register observers and kick off a replayed trip session
        val mv = mapViewState.value
        if (mv != null) {
            // ensure the nice default puck
            mv.location.apply {
                setLocationProvider(navigationLocationProvider)
                locationPuck = createDefault2DPuck()
                enabled = true
            }
        }

        val replayProgressObserver = ReplayProgressObserver(mapboxNavigation.mapboxReplayer)
        mapboxNavigation.registerRoutesObserver(routesObserver)
        mapboxNavigation.registerLocationObserver(locationObserver)
        mapboxNavigation.registerRouteProgressObserver(replayProgressObserver)
        mapboxNavigation.startReplayTripSession()

        // Request a simple 2-point route and push replay events
        val origin = Point.fromLngLat(-122.43539772352648, 37.77440680146262)
        val destination = Point.fromLngLat(-122.42409811526268, 37.76556957793795)

        @SuppressLint("MissingPermission")
        fun requestRoute() {
            mapboxNavigation.requestRoutes(
                RouteOptions.builder()
                    .applyDefaultNavigationOptions()
                    .coordinatesList(listOf(origin, destination))
                    .layersList(listOf(mapboxNavigation.getZLevel(), null))
                    .build(),
                object : NavigationRouterCallback {
                    override fun onCanceled(routeOptions: RouteOptions, routerOrigin: String) {}
                    override fun onFailure(
                        reasons: List<RouterFailure>,
                        routeOptions: RouteOptions
                    ) {}

                    override fun onRoutesReady(
                        routes: List<NavigationRoute>,
                        routerOrigin: String
                    ) {
                        mapboxNavigation.setNavigationRoutes(routes)

                        // Simulate user movement along the route
                        val replayData = replayRouteMapper
                            .mapDirectionsRouteGeometry(routes.first().directionsRoute)
                        mapboxNavigation.mapboxReplayer.pushEvents(replayData)
                        mapboxNavigation.mapboxReplayer.seekTo(replayData.first())
                        mapboxNavigation.mapboxReplayer.play()
                    }
                }
            )
        }

        requestRoute()

        onDispose {
            // Unregister and clean up
            mapboxNavigation.unregisterRoutesObserver(routesObserver)
            mapboxNavigation.unregisterLocationObserver(locationObserver)
            mapboxNavigation.stopTripSession()
            MapboxNavigationProvider.destroy() // releases the singleton instance
            mapViewState.value = null
        }
    }
}
```

Now save your work and start the emulator. Once the emulator is finished loading, you should see the globe. You can zoom in and out and move around the map to explore.

After the map loads, you will see simulated navigation begin with the camera following the user's location along the route.

Your browser doesn't support HTML5 video. Open [link to the video](https://docs.mapbox.com/android/android/assets/medias/navigation-guides-install-example-12cd5b46aa3f4f97bc38500226aec83b.webm).

You can experiment with the code to customize the navigation experience. For example, you can change the route's origin and destination points, or add custom logic to handle user location updates.

With a minimal navigation example running in your app, you are ready to begin customizing the user experience and exploring the features of the **Mapbox Navigation SDK for Android**.

> **Related content (example): [Add a complete turn-by-turn experience](https://docs.mapbox.com/android/navigation/examples/turn-by-turn-experience/)**
> 
> See the full example for a more complex turn-by-turn navigation experience using the full suite of the SDK's UI components, including speed limit, route shields, maneuver instructions, and voice prompts.

## Troubleshooting

If your implementation is not working as expected, view the following troubleshooting solutions:

Details

**Black screen**

If when you run simulator your screen displays black, here are some possible solutions:

-   Make sure both your secret token and public token are both valid and present
    -   If you see `YOUR_MAPBOX_ACCESS_TOKEN` in your code, your map will not render.
-   If you are also seeing this error `onmaploaderror: style, message: failed to load style: couldn't connect to server: exception in croneturlrequest: net::err_name_not_resolved`, you will need to wipe your simulator data.
    -   This error could be caused by your simulator losing access to the internet. To fix this problem, try the following:
        -   Make sure you have access to the internet.
        -   If you have internet access, next click `Tools > Device Manager`.
        -   Then click  and select **Wipe Data**.
        -   Wait until your simulator loads back up, and then try running the simulator again.

Details

**Route does not appear**

If your map appears but the route does not appear on the map, here are some possible solutions:

-   If this is the first time running the simulator after setting location permissions, try restarting the simulator and running it again.
-   Make sure you are using a valid secret token.
    -   This token must use the `Downloads:Read` scope.
-   Make sure your token is assigned in your gradle.properties file.
-   Make sure the `settings.gradle.kts` file inside the `credentials` struct, the username is `mapbox` and not your username.

Details

**Maximum method count**

Try enabling Multidex by following the official Android guides, using [multidex](https://developer.android.com/studio/build/multidex) and [optimizing your code](https://developer.android.com/studio/build/shrink-code).

Details

**My application is crashing on start or won't build**

-   Make sure the package line in your `MainActivity.kt` matches your project name and does not read `package com.example.myapplication`.
-   Make sure you configure the Mapbox maven repository inside `dependencyResolutionManagement` and not inside `pluginManagement` in your `settings.gradle.kts` file.

Details

**General Tips**

Make sure your always click `Save All` and `Sync Project with Gradle Files` when making changes to your application.

## Next Steps

With the Navigation SDK installed and a minimal navigation experience rendering in your app, you can explore additional features of the SDK:

-   [Nav SDK UI Components](https://docs.mapbox.com/android/navigation/guides/ui-components/)
-   [Initializing the SDK](https://docs.mapbox.com/android/navigation/guides/initialization/)
-   [Offline Navigation](https://docs.mapbox.com/android/navigation/guides/advanced/offline/)

Explore other learning related to **Mapbox Navigation SDK for Android**:

> **Related content (related): [Mapbox Navigation Examples App](https://github.com/mapbox/mapbox-navigation-android-examples)**
> 
> Clone the Mapbox Navigation Examples App repository to view examples in action.

> **Related content (example): [Display the user’s location](https://docs.mapbox.com/android/maps/examples/android-view/display-the-users-location/)**
> 
> This example demonstrates how to track and display the user's location.

> **Related content (example): [Simulate a navigation route](https://docs.mapbox.com/mapbox-gl-js/example/mapbox-gl-draw/)**
> 
> This examples demonstrates how to simulate a navigation route on the map.