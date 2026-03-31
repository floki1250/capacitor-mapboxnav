# @castelio-it/capacitor-mapboxnav

capacitor mapbox navigation ndk

## Install

To use npm

```bash
npm install @castelio-it/capacitor-mapboxnav
````

To use bun

```bash
bun add @castelio-it/capacitor-mapboxnav
```

Sync native files

```bash
bunx cap sync 
```

## Android Setup

Mapbox Navigation requires a secret token to download its SDK.

1.  Go to your Mapbox account's [tokens page](https://console.mapbox.com/account/access-tokens/).
2.  Create a token with the `Downloads:Read` scope.
3.  Add the following to your global `~/.gradle/gradle.properties` or to `android/gradle.properties` in your project:
    ```text
    MAPBOX_DOWNLOADS_TOKEN=YOUR_SECRET_MAPBOX_ACCESS_TOKEN
    ```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`initialize(...)`](#initialize)
* [`startNavigation(...)`](#startnavigation)
* [`startTurnByTurnExperience(...)`](#startturnbyturnexperience)
* [`startFreeDrive()`](#startfreedrive)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

Echo back the provided value.

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### initialize(...)

```typescript
initialize(options: { accessToken: string; }) => Promise<void>
```

Initialize the Mapbox Navigation SDK with your public access token.
This must be called before any navigation activity.

| Param         | Type                                  |
| ------------- | ------------------------------------- |
| **`options`** | <code>{ accessToken: string; }</code> |

--------------------


### startNavigation(...)

```typescript
startNavigation(options: { origin: { latitude: number; longitude: number; }; destination: { latitude: number; longitude: number; }; simulateRoute?: boolean; }) => Promise<void>
```

Start a basic navigation session with a simple map overview.
Useful for background navigation or lightweight tracking.

| Param         | Type                                                                                                                                               |
| ------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ origin: { latitude: number; longitude: number; }; destination: { latitude: number; longitude: number; }; simulateRoute?: boolean; }</code> |

--------------------


### startTurnByTurnExperience(...)

```typescript
startTurnByTurnExperience(options: { origin: { latitude: number; longitude: number; }; destination: { latitude: number; longitude: number; }; simulateRoute?: boolean; }) => Promise<void>
```

Start a full Turn-by-Turn navigation experience.
Includes instruction banners, maneuvers, speed limits, and total trip progress.

| Param         | Type                                                                                                                                               |
| ------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ origin: { latitude: number; longitude: number; }; destination: { latitude: number; longitude: number; }; simulateRoute?: boolean; }</code> |

--------------------


### startFreeDrive()

```typescript
startFreeDrive() => Promise<void>
```

Start a Free Drive session (Position tracking).
Shows the map centered on the user with no active route or destination.

--------------------

</docgen-api>

### Example

```typescript
import { capacitormapboxnav } from '@castelio-it/capacitor-mapboxnav';

async function startTurnByTurn() {
  await capacitormapboxnav.initialize({
    accessToken: 'YOUR_PUBLIC_MAPBOX_ACCESS_TOKEN'
  });

  await capacitormapboxnav.startTurnByTurnExperience({
    origin: { latitude: 35.723976, longitude: 10.745365 },
    destination: { latitude: 35.831016, longitude: 10.626204 },
    simulateRoute: true
  });
}
```

