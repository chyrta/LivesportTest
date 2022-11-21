## Overview

An application that utilizes Livesport API in order to list search results based on search query and one of the filters.
The app contains two screens: list and detail. Business logic which is done in pure Kotlin Multiplatform is covered by unit tests.

|<image src="assets/screenshot_1.png" width="250px">|<image src="assets/screenshot_2.png" width="250px">|
|---|--|
|<image src="assets/screenshot_3.png" width="250px">|<image src="assets/screenshot_4.png" width="250px">|

## Setup for iOS
Before launching the project for iOS, you have to prepare Swift Package using KMMBridge Gradle plugin.
You need to launch the following command:
```
./gradlew shared:spmDevBuild
```
This command will generate an XCFramework file with business logic of the app and put it into the following path: `/shared/build/XCFrameworks/debug/LivesportKit.xcframework`

## Components
- MVI architecture (inspired by [Daniel Avila Domingo project|https://github.com/daniaviladomingo/kmm])
- Jetpack Compose
- SwiftUI
- Kotlin Coroutines
- Kotlin Serialization (serialization of JSON)
- Ktor (HTTP-client, unit testing)
- Mockk (mocking dependencies)
- Kover (generating code coverage reports)
- Detekt
- Ktlint

## Project Structure
The project is utilizing feature-based approach and contains several modules:
- **androidApp:** Android version of the Livesport Search app
- **iosApp** iOS version of the Livesport Search app
- **core:common:** Core components for business logic of the app, snippets, utils
- **core:ui:** Core components for Livesport Search Android part of the app
- **search:logic** A single feature called `Search` which is present in the app
    - **data** Data sources, repository
    - **di** Dependency injection module related to Search feature
    - **domain** Mappers, usecases, local entity models
    - **presentation** Presentation logic with the help of a viewmodel and a contract
- **search:ui** Jetpack Compose UI components, providers for better previews
- **shared** A dummy module which goal is to prepare iOS framework
- **includedBuild:dependencies** List of dependencies used by the app, a composite build approach compared to `buildSrc`
- **includedBuild:gradleConfiguration** Gradle plugins to reduce the amount of boilerplate code
