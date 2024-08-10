# AmpersandTA
Technical Assessment
# Android API Fetcher App

This Android app fetches data from an API and displays it in a list view with a details view. It supports both phone and tablet layouts, including a dedicated landscape layout for tablets.

## How to Run the App

1. Clone the repository to your local machine.
2. Open the project in Android Studio (version 4.2 or later recommended).
3. Ensure you have the latest Android SDK tools and platform tools installed.
4. Connect an Android device or start an emulator.
5. Click the "Run" button in Android Studio.

## Libraries and Tools Used

- **Jetpack Compose**: For building the UI
- **ViewModel**: For managing UI-related data
- **Retrofit**: For making API calls
- **Kotlin Coroutines**: For managing background tasks
- **Kotlin Flow**: For reactive programming

## Design Decisions and Assumptions

1. **API**: The app fetches data from `https://api.restful-api.dev/objects`. We assume this API is always available and returns data in the expected format.

2. **UI Design**:
   - Used Jetpack Compose for modern, declarative UI development.
   - Implemented a responsive design that adapts to different screen sizes and orientations.
   - For phones and smaller tablets: Single-pane view with navigation between list and details.
   - For larger tablets in landscape: Two-pane view with list and details side-by-side.

3. **Data Handling**:
   - Used ViewModel to separate UI logic from data management.
   - Implemented error handling to manage potential API failures or data inconsistencies.

4. **Performance**:
   - Used `LazyColumn` (Compose equivalent of RecyclerView) for efficient list rendering.
   - Implemented basic caching of API responses to reduce unnecessary network calls.

5. **Tablet Support**:
   - Defined tablets as devices with a smallest width of 600dp or larger.
   - Created a dedicated landscape layout for tablets to maximize screen real estate.

6. **Assumptions**:
   - The app requires an internet connection to fetch data.
   - The API response structure remains consistent.
   - The app supports Android API level 24 (Android 7.0) and above.
