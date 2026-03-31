# @castelio-it/capacitor-mapboxnav
[![forthebadge](data:image/svg+xml;base64,PHN2ZyBkYXRhLXYtM2M4N2I3YjQ9IiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjUxLjg1MTczMDM0NjY3OTciIGhlaWdodD0iMzUiIHZpZXdCb3g9IjAgMCAyNTEuODUxNzMwMzQ2Njc5NyAzNSIgY2xhc3M9ImJhZGdlLXN2ZyI+PGRlZnMgZGF0YS12LTNjODdiN2I0PSIiPjwhLS0tLT48IS0tLS0+PCEtLS0tPjwvZGVmcz48cmVjdCBkYXRhLXYtM2M4N2I3YjQ9IiIgd2lkdGg9IjE5Ny42ODk1Mjk0MTg5NDUzIiBoZWlnaHQ9IjM1IiBmaWxsPSIjMThkYzgxIi8+PHJlY3QgZGF0YS12LTNjODdiN2I0PSIiIHg9IjE5Ny42ODk1Mjk0MTg5NDUzIiB3aWR0aD0iNTQuMTYyMjAwOTI3NzM0Mzc1IiBoZWlnaHQ9IjM1IiBmaWxsPSIjNTQ1NDU0Ii8+PGcgZGF0YS12LTNjODdiN2I0PSIiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDEwLCA1LjUpIHNjYWxlKDEpIj48cGF0aCBkYXRhLXYtM2M4N2I3YjQ9IiIgZD0iTTE4LjQzOTUgNS41NTg2Yy0uNjc1IDEuMTY2NC0xLjM1MiAyLjMzMTgtMi4wMjc0IDMuNDk4LS4wMzY2LS4wMTU1LS4wNzQyLS4wMjg2LS4xMTEzLS4wNDMtMS44MjQ5LS42OTU3LTMuNDg0LS44LTQuNDItLjc4Ny0xLjg1NTEuMDE4NS0zLjM1NDQuNDY0My00LjI1OTcuODIwMy0uMDg0LS4xNDk0LTEuNzUyNi0zLjAyMS0yLjAyMTUtMy40ODY0YTEuMTQ1MSAxLjE0NTEgMCAwIDAtLjE0MDYtLjE5MTRjLS4zMzEyLS4zNjQtLjkwNTQtLjQ4NTktMS4zNzktLjIwMy0uNDc1LjI4Mi0uNzEzNi45MzYxLS4zODg2IDEuNTAxOSAxLjk0NjYgMy4zNjk2LS4wOTY2LS4yMTU4IDEuOTQ3MyAzLjM1OTMuMDE3Mi4wMzEtLjQ5NDYuMjY0Mi0xLjM5MjYgMS4wMTc3QzIuODk4NyAxMi4xNzYuNDUyIDE0Ljc3MiAwIDE4Ljk5MDJoMjRjLS4xMTktMS4xMTA4LS4zNjg2LTIuMDk5LS43NDYxLTMuMDY4My0uNzQzOC0xLjkxMTgtMS44NDM1LTMuMjkyOC0yLjc0MDItNC4xODM2YTEyLjEwNDggMTIuMTA0OCAwIDAgMC0yLjEzMDktMS42ODc1Yy42NTk0LTEuMTIyIDEuMzEyLTIuMjU1OSAxLjk2NDktMy4zODQ4LjIwNzctLjM2MTUuMTg4Ni0uNzk1Ni0uMDA3OS0xLjExOTFhMS4xMDAxIDEuMTAwMSAwIDAgMC0uODUxNS0uNTMzMmMtLjUyMjUtLjA1MzYtLjkzOTIuMzEyOC0xLjA0ODguNTQ0OXptLS4wMzkxIDguNDYxYy4zOTQ0LjU5MjYuMzI0IDEuMzMwNi0uMTU2MyAxLjY1MDMtLjQ3OTkuMzE5Ny0xLjE4OC4wOTg1LTEuNTgyLS40OTQxLS4zOTQ0LS41OTI3LS4zMjQtMS4zMzA3LjE1NjMtMS42NTA0LjQ3MjctLjMxNSAxLjE4MTItLjEwODYgMS41ODIuNDk0MXpNNy4yMDcgMTMuNTI3M2MuNDgwMy4zMTk3LjU1MDYgMS4wNTc3LjE1NjMgMS42NTA0LS4zOTQuNTkyNi0xLjEwMzguODEzOC0xLjU4NC40OTQxLS40OC0uMzE5Ny0uNTUwMy0xLjA1NzctLjE1NjMtMS42NTA0LjQwMDgtLjYwMjEgMS4xMDg3LS44MTA2IDEuNTg0LS40OTQxeiIgZmlsbD0iI0ZGRkZGRiIvPjwvZz48dGV4dCBkYXRhLXYtM2M4N2I3YjQ9IiIgeD0iMTEyLjg0NDc2NDcwOTQ3MjY2IiB5PSIxNy41IiBkeT0iMC4zNWVtIiBmb250LXNpemU9IjEyIiBmb250LWZhbWlseT0iUm9ib3RvLCBzYW5zLXNlcmlmIiBmaWxsPSIjRkZGRkZGIiB0ZXh0LWFuY2hvcj0ibWlkZGxlIiBsZXR0ZXItc3BhY2luZz0iMiIgZm9udC13ZWlnaHQ9IjYwMCIgZm9udC1zdHlsZT0ibm9ybWFsIiB0ZXh0LWRlY29yYXRpb249Im5vbmUiIGZpbGwtb3BhY2l0eT0iMSIgZm9udC12YXJpYW50PSJub3JtYWwiIHN0eWxlPSJ0ZXh0LXRyYW5zZm9ybTogdXBwZXJjYXNlOyI+QlVJTFQgRk9SIEFORFJPSUQ8L3RleHQ+PCEtLS0tPjx0ZXh0IGRhdGEtdi0zYzg3YjdiND0iIiB4PSIyMjQuNzcwNjI5ODgyODEyNSIgeT0iMTcuNSIgZHk9IjAuMzVlbSIgZm9udC1zaXplPSIxMiIgZm9udC1mYW1pbHk9IlJvYm90bywgc2Fucy1zZXJpZiIgZmlsbD0iI2ZmZmZmZiIgdGV4dC1hbmNob3I9Im1pZGRsZSIgZm9udC13ZWlnaHQ9IjkwMCIgbGV0dGVyLXNwYWNpbmc9IjEuNSIgZm9udC1zdHlsZT0ibm9ybWFsIiB0ZXh0LWRlY29yYXRpb249Im5vbmUiIGZpbGwtb3BhY2l0eT0iMSIgZm9udC12YXJpYW50PSJub3JtYWwiIHN0eWxlPSJ0ZXh0LXRyYW5zZm9ybTogdXBwZXJjYXNlOyI+Jmd0OyA3LjA8L3RleHQ+PCEtLS0tPjwvc3ZnPg==)](https://forthebadge.com)

[![NPM Version](https://img.shields.io/npm/v/@castelio-it/capacitor-mapboxnav?style=for-the-badge&color=3380FF)](https://www.npmjs.com/package/@castelio-it/capacitor-mapboxnav)
[![NPM Downloads](https://img.shields.io/npm/dm/@castelio-it/capacitor-mapboxnav?style=for-the-badge&color=00D1B2)](https://www.npmjs.com/package/@castelio-it/capacitor-mapboxnav)
[![License](https://img.shields.io/npm/l/@castelio-it/capacitor-mapboxnav?style=for-the-badge&color=F8D800)](https://www.npmjs.com/package/@castelio-it/capacitor-mapboxnav)
[![Capacitor](https://img.shields.io/badge/Capacitor-8+-blue?style=for-the-badge&logo=capacitor&logoColor=white)](https://capacitorjs.com/)

> [!IMPORTANT]
> **Mapbox Navigation Plugin for Capacitor**
> Robust Turn-by-Turn navigation, Location Tracking, and Free Drive support for Android (iOS coming soon).

**capacitor-mapboxnav** provides a powerful bridge for the Mapbox Navigation SDK (NDK) for Capacitor apps, allowing you to build professional-grade navigation apps with turn-by-turn guidance.

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

