# capacitor-mapboxnav

capacitor mapbox navigation ndk

## Install

To use npm

```bash
npm install capacitor-mapboxnav
````

To use bun

```bash
bun add capacitor-mapboxnav
```

Sync native files

```bash
npx cap sync 
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

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### initialize(...)

```typescript
initialize(options: { accessToken: string; }) => Promise<void>
```

| Param         | Type                                  |
| ------------- | ------------------------------------- |
| **`options`** | <code>{ accessToken: string; }</code> |

--------------------


### startNavigation(...)

```typescript
startNavigation(options: { origin: { latitude: number; longitude: number; }; destination: { latitude: number; longitude: number; }; simulateRoute?: boolean; }) => Promise<void>
```

| Param         | Type                                                                                                                                               |
| ------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ origin: { latitude: number; longitude: number; }; destination: { latitude: number; longitude: number; }; simulateRoute?: boolean; }</code> |

--------------------

</docgen-api>

### Example

```typescript
import { capacitormapboxnav } from 'capacitor-mapboxnav';

async function navigate() {
  await capacitormapboxnav.initialize({
    accessToken: 'YOUR_PUBLIC_MAPBOX_ACCESS_TOKEN'
  });

  await capacitormapboxnav.startNavigation({
    origin: { latitude: 37.7749, longitude: -122.4194 },
    destination: { latitude: 37.7833, longitude: -122.4167 },
    simulateRoute: true
  });
}
```
