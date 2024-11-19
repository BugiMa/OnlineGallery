# Online Gallery App

An Android app for browsing and viewing images from the LoremPicsum API. Built with **MVI** pattern, **Clean Architecture**, and **Jetpack Compose**.

## Screens & Features

- **Image Browser**: Endless scrolling, pull-to-refresh, and "jump to top" for easy navigation.
- **Image Details**: Displays details of a selected image with clickable URLs.

## Tech Stack

- **Kotlin**
- **Jetpack Compose** & **Compose Navigation**
- **Retrofit** & **OkHttp** for networking
- **Coil** for image loading
- **Hilt** for dependency injection

## Architecture

- **MVI** with **Clean Architecture**
  - **Data Layer**: API calls, data source & repository
  - **Domain Layer**: Use cases
  - **Presentation Layer**: UI & state management

## Api

- [Lorem Picsum API](https://picsum.photos/) for pictures.
