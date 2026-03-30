# Location tracking

The Mapbox Navigation SDK uses raw device location to power important navigation features including:

-   Enhancing location updates and snapping them to the road graph.
-   Determining which instruction to show next, and when to show it, based on where the user is along the route.
-   Detecting when the driver has deviated from the current route, prompting the SDK to generate a new route to get them back on track.
-   Proactively downloading map tiles and routing information ahead of the driver's current location in case the driver enters an area with no or poor network connection.

The Navigation SDK depends on the **Maps SDK for Android** library to handle location permissions, track location updates, and show device location on a map. This SDK is bundled with the Navigation SDK, and you do not need to install it separately.

## Privacy and permissions

Users must grant your application permission before it can access information about their location. During this permission prompt, you can present a custom string explaining how location will be used.

If your project targets Android API 23 or higher, make sure to check the user location permission in runtime using [`PermissionsManager`](https://docs.mapbox.com/android/maps/guides/user-location/permissions/).

## Location provider

The Navigation SDK utilizes one of the [`LocationProvider`](https://docs.mapbox.com/android/maps/api/latest/mapbox-maps-android/com.mapbox.common.location/-location-provider/) implementations to fetch the user's location. It simplifies the process of getting location information and supports the following providers:

-   Google's Fused Location Providers
-   Android GPS and Network Providers

> **Note: Use Google's Fused Location Provider**
> 
> In applications using Android 11, the raw location updates might suffer from precision issues.The SDK also comes with support for the [Google's Fused Location Provider](https://developers.google.com/location-context/fused-location-provider). If you target devices that support Google Play Services, add the following [Google Play Location Services dependency](https://developers.google.com/android/guides/setup) to your project, and the Navigation SDK will use the Google's Fused Location Provider in your application automatically:
> 
> `implementation("com.google.android.gms:play-services-location:21.0.1")`

### Custom location provider

If you want to use a custom [`LocationProvider`](https://docs.mapbox.com/android/maps/api/latest/mapbox-maps-android/com.mapbox.common.location/-location-provider/) pass it with [`NavigationOptions#locationOptions`](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/base/com.mapbox.navigation.base.options/-navigation-options/-builder/location-options.html):

```kotlin
NavigationOptions.Builder()
    .locationOptions(
        LocationOptions.Builder()
            .locationProviderFactory(
                DeviceLocationProviderFactory { request ->
                    ExpectedFactory.createValue(MyLocationProvider(request))
                },
                LocationProviderType.<YOUR_TYPE>
            )
            .build()
    )
```

Here `MyLocationProvider` implements [`DeviceLocationProvider`](https://docs.mapbox.com/android/maps/api/latest/mapbox-maps-android/com.mapbox.common.location/-device-location-provider/). Note that together with [`DeviceLocationProviderFactory`](https://docs.mapbox.com/android/maps/api/latest/mapbox-maps-android/com.mapbox.common.location/-device-location-provider-factory/), you have to pass a location provider type. It can be either `REAL`, `MOCKED` or `MIXED`.

-   Use [REAL](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/base/com.mapbox.navigation.base.options/-location-options/-location-provider-type/-r-e-a-l.html) if your location provider emits only real locations, where the device really is.
    
-   Use [MOCKED](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/base/com.mapbox.navigation.base.options/-location-options/-location-provider-type/-m-o-c-k-e-d.html) if your location provider emits only mocked/simulated locations. Can be used for cases with location simulations, replay, etc.
    
-   Use [MIXED](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/base/com.mapbox.navigation.base.options/-location-options/-location-provider-type/-m-i-x-e-d.html) if your provider emits both mocked and real locations. Can be used for cases when you need to switch location providers in runtime, for example, if you have a toggle that enables/disables location simulation.
    

Note that if you have `MOCKED` or `MIXED` type, the non-real locations must have `isMock` extra flag set to true. Real locations must either have it set to false or not set at all. To set this flag, use:

```kotlin
Location.Builder()
    .extra(
        Value.valueOf(
            hashMapOf(
                LocationExtraKeys.IS_MOCK to Value.valueOf(true/false)
            )
        )
    )
```

Use built-in functions [`toCommonLocation`](https://docs.mapbox.com/android/maps/api/latest/mapbox-maps-android/com.mapbox.common.location/to-common-location.html) and [`toAndroidLocation`](https://docs.mapbox.com/android/maps/api/latest/mapbox-maps-android/com.mapbox.common.location/to-android-location.html) to convert between Android and Mapbox `Location` types.

## Enhanced location updates

To receive location updates while the trip session is running, you can subscribe to the [`LocationObserver`](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/navigation/com.mapbox.navigation.core.trip.session/-location-observer/) with [your already-instantiated `MapboxNavigation` object](https://docs.mapbox.com/android/navigation/guides/initialization/).

```kotlin
private val locationObserver = object : LocationObserver {

	override fun onNewRawLocation(rawLocation: Location) {
        ...
	}

	override fun onNewLocationMatcherResult(locationMatcherResult: LocationMatcherResult) {
	    ...
	}
}

mapboxNavigation.registerLocationObserver(locationObserver)
```

This will allow you to listen for two types of location updates:

-   **Raw location (`onNewRawLocation`)**: Invoked as soon as a new `Location` has been received.
-   **Enhanced location (`onNewLocationMatcherResult`)**: Invoked when a new `Location` is snapped to the route or map matched to the road.

Don’t forget to unregister the `LocationObserver` interface if you don't need to observe location anymore.

```kotlin
mapboxNavigation.unregisterLocationObserver(locationObserver)
```

### `onNewRawLocation` callback

The [`onNewRawLocation`](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/navigation/com.mapbox.navigation.core.trip.session/-location-observer/on-new-raw-location.html) method delivers a raw `Location` object from the developer-provided or default internal `LocationProvider`.

### `onNewLocationMatcherResult` callback

The [`onNewLocationMatcherResult`](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/navigation/com.mapbox.navigation.core.trip.session/-location-observer/on-new-location-matcher-result.html) method provides the most accurate location update possible. The location is snapped to the route, or if possible, to the road network.

This Navigation SDK enhanced location logic helps to solve common navigation issues such as:

-   Loss of location coordinate precision in cities with tall buildings
-   Frequency issues
-   Loss of signal in tunnels

If the SDK isn't able to compute a better `Location` update, the raw `Location` will be returned.

`locationMatcherResult` provides details about the status of the enhanced location. `keyPoints` is a potentially empty list of predicted `Location` coordinates that lead up to the latest `Location` update coordinate. If the list isn't empty, the last coordinate in the list is always equal to the `enhancedLocation` object.

These coordinates are snapped to a route or road. This is especially helpful for making sure the device location puck doesn't cut intersection corners as it traverses through a turn maneuver.

## Location puck on the map

If your application includes a map, you can display the user location puck on the map using the [Mapbox Maps SDK's user location component](https://docs.mapbox.com/android/maps/guides/user-location/location-on-map/). The plugin can display both a 2D and 3D puck.

![](https://docs.mapbox.com/android/ideal-img/navigation-overview-device-location-user-location.480.png)

> **Note: Mapbox Maps SDK**
> 
> For more details on how to work with the Maps SDK, [see the Maps integration guide](https://docs.mapbox.com/android/navigation/guides/ui-components/maps/).

After you've added a [`MapView`](https://docs.mapbox.com/android/maps/api/latest/mapbox-maps-android/com.mapbox.maps/-map-view/) to your application, create an instance of [`NavigationLocationProvider`](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/ui-maps/com.mapbox.navigation.ui.maps.location/-navigation-location-provider/) class, which helps to display the user location puck:

```kotlin
val navigationLocationProvider = NavigationLocationProvider()
```

Then set the puck drawable (included with the Navigation SDK), and set the location data provider:

```kotlin
mapView.location.apply {
    this.locationPuck = LocationPuck2D(
        bearingImage = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.mapbox_navigation_puck_icon
        )
    )
    setLocationProvider(navigationLocationProvider)
    puckBearingEnabled = true
    enabled = true
}
```

Once the puck is initialized, you can start passing the enhanced location updates to the puck and render the updated location on the map:

```kotlin
private val locationObserver = object : LocationObserver {

    override fun onNewRawLocation(rawLocation: Location) {
        ...
    }

    override fun onNewLocationMatcherResult(locationMatcherResult: LocationMatcherResult) {
        navigationLocationProvider.changePosition(
            location = locationMatcherResult.enhancedLocation,
            keyPoints = locationMatcherResult.keyPoints,
        )
    }
}
```

> **Related content (example): [Render current location on a map](https://docs.mapbox.com/android/navigation/examples/show-current-location/)**
> 
> Use `NavigationLocationProvider` to show a device's current location as a puck on a map.

## Location API

### Location options

To change default location request or to pass a custom [`LocationProvider`](https://docs.mapbox.com/android/maps/api/latest/mapbox-maps-android/com.mapbox.common.location/-location-provider/) use [`NavigationOptions#locationOptions`](https://docs.mapbox.com/android/navigation/api/coreframework/3.21.0-rc.1/base/com.mapbox.navigation.base.options/-navigation-options/-builder/location-options.html).

1.  If you want to use everything that is default, don't set location options. Default request is:

```kotlin
LocationProviderRequest.Builder()
    .interval(
        IntervalSettings.Builder()
            .minimumInterval(500)
            .interval(1000L)
            .build()
    )
    .accuracy(AccuracyLevel.HIGH)
    .build()
```

2.  If you want to customize the request and use the default location provider, set the options in the following way:

```kotlin
NavigationOptions.Builder()
    .locationOptions(
        LocationOptions.Builder()
            .request(myRequest)
            .build()
    )
```

3.  If you want to change a location provider, check how you can do it in [Location provider](https://docs.mapbox.com/android/navigation/guides/device-location/#location-provider) section.

### Replay locations

If you want the locations to be replayed by the Navigation SDK, you should use:

```kotlin
// no location options
val options = NavigationOptions.Builder(context).build()

// activate Navigation SDK with the options created above and get `mapboxNavigation`

...

// use the `MapboxReplayer` instead of a real location provider
mapboxNavigation.startReplayTripSession()

// use the `MapboxReplayer` to handle a replay session
val mapboxReplayer = mapboxNavigation.mapboxReplayer
mapboxReplayer.pushEvents(events)
mapboxReplayer.play()
```

Check how to [initialize `MapboxNavigation` object](https://docs.mapbox.com/android/navigation/guides/initialization/).