# NewsApp - Modern Android News Application

A modern Android news application built with Kotlin and Jetpack Compose that fetches real-time news from NewsAPI and supports offline reading through local article storage.

## Features

- **Real-time News Feed**: Fetches latest news articles from NewsAPI
- **Clean UI**: Modern interface built with Jetpack Compose
- **Article Details**: In-app WebView for full article reading
- **Offline Support**: Save articles for offline reading
- **Local Storage**: Room database implementation for article management
- **Pull to Refresh**: Update news feed with latest articles
- **MVVM Architecture**: Clean and maintainable codebase
- **Dark Mode Support**: Comfortable reading in any lighting condition
- **Article Categories**: Browse news by different topics
- **Share Functionality**: Share interesting articles with friends

## App Screenshots
<div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 10px; align-items: flex-start;">
  <img src="https://github.com/user-attachments/assets/2bf852ed-0317-4062-aba6-1fc22e8ac68f" alt="Home Screen Light" width="250"/>
  <img src="https://github.com/user-attachments/assets/8dd559be-cc58-481c-a7cc-2564a1a012ee" alt="Article Detail Light" width="250"/>
  <img src="https://github.com/user-attachments/assets/2ab6b8c4-a39f-4890-893e-a281332474d5" alt="Saved Articles Light" width="250"/>
</div>

## Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/binayshaw7777/EkaCareAssignment.git
```

2. Open the project in Android Studio Arctic Fox or later

3. Add your NewsAPI key in `local.properties`:
```properties
API_KEY=your_api_key_here
```

4. Build and run the project

### Architecture
- **MVVM Pattern**: Separation of concerns and better testability
- **Clean Architecture**: Domain, Data, and Presentation layers
- **Repository Pattern**: Single source of truth for data

## Logging
- Timber

#### UI
- **Jetpack Compose**: Modern UI toolkit for native UI
- **Material3**: Material Design components for Compose
- **Coil**: Image loading and caching

#### Networking
- **Retrofit**: Type-safe HTTP client
- **OkHttp**: HTTP client and logging
- **Kotlin Serialization**: JSON parsing

#### Local Storage
- **Room**: SQLite database abstraction
- **DataStore**: Key-value pair storage

#### Asynchronous Programming
- **Coroutines**: Asynchronous programming
- **Flow**: Reactive streams

#### Dependency Injection
- **Hilt**: Dependency injection

## Architecture

The application follows Clean Architecture with MVVM pattern:

```
app/
├── data/
│   ├── local/
│   │   ├── dao/           # Data Access Objects for Room
│   │   ├── model/         # Local database entities
│   │   ├── repository/    # Local data repository implementations
│   │   └── Database.kt    # Room database configuration
│   │
│   └── remote/
│       ├── interceptor/   # Network interceptors
│       ├── network/       # API service interfaces
│       ├── repository/    # Remote data repository implementations
│       ├── request/       # API request models
│       ├── response/      # API response models
│       └── ApiInterface   # API endpoint definitions
│
├── di/
│   ├── ApiModule         # Dependency injection for API services
│   ├── AppModule        # Application-level dependencies
│   ├── DatabaseModule   # Database-related dependencies
│   └── RepositoryModule # Repository dependencies
│
├── domain/
│   ├── model/           # Domain models/entities
│   └── repository/      # Repository interfaces
│
└── ui/
    ├── components/      # Reusable UI components
    ├── navigation/      # Navigation-related classes
    ├── screens/
    │   ├── detail/     # Article detail screen
    │   ├── home/       # Home screen
    │   └── saved/      # Saved articles screen
    │
    ├── theme/
    │   ├── Color.kt    # App color definitions
    │   ├── Theme.kt    # Theme configuration
    │   └── Type.kt     # Typography definitions
    │
    └── utils/
        ├── ComposeUtils.kt   # Compose utility functions
        ├── Constants.kt      # App constants
        ├── Resource.kt       # Resource wrapper class
        └── Utils.kt          # General utility functions
```

## Acknowledgments

- [NewsAPI](https://newsapi.org/) for providing the news data
- [Android Jetpack](https://developer.android.com/jetpack) documentation
- Material Design guidelines
