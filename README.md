# 📱 PokeApp

**PokeApp** is a native Android application built to demonstrate modern Android development practices, clean architecture, and a scalable UI structure.  

The app is a showcase project designed to explore and implement advanced Android concepts, focusing on scalability, maintainability, architectural patterns, separation of concerns and code quality expected in enterprise-level applications.

The application interacts with the [PokeAPI](https://pokeapi.co/) to provide an infinite scrolling list of Pokémon and allows users to manage a list of favorites stored locally.

---


## 📸 Screenshots

<img src="https://github.com/user-attachments/assets/85ae6010-6d3c-4a54-abef-60b48defd4f3" width="220" alt="PokeApp">
<img src="https://github.com/user-attachments/assets/ec945d60-996d-46c5-8a6c-9c9cf27c9152" width="220" alt="PokeApp">

---

## 🏛️ Architecture

The application follows the **MVVM (Model-View-ViewModel)** pattern combined with **Clean Architecture principles**. The code is structured to ensure separation of concerns, making it testable and easy to maintain.

### Layers Breakdown

- **Presentation Layer:**
    - **UI:** Fragments using XML Layouts and **ViewBinding**.
    - **State Management:** ViewModels using **StateFlow** to expose UI States (Success, Loading, Error).
    - **Dynamic UI:** Uses the **Palette API** to extract dominant colors from Pokémon images for dynamic CardView backgrounds.
- **Domain Layer:**
    - Encapsulates business logic using **Use Cases**.
- **Data Layer:**
    - **Repository Pattern:** Mediates between data sources.
    - **Remote:** Retrofit for API calls.
    - **Local:** Room Database for persisting user favorites.

<img src="https://github.com/user-attachments/assets/9df8711c-08ba-4a02-9d9e-feb2d7320c7f" width="500" alt="PokeApp Architecture">

---

## 🛠️ Tech Stack & Libraries

- **Language:** Kotlin (100%)

- **Concurrency:** Coroutines & Flow (Structured Concurrency)

- **Network:** Retrofit (REST API integration)

- **Pagination:** Paging 3 (Infinite Scrolling)

- **Dependency Injection:** Manual Dependency Injection (AppContainer pattern) - intentionally designed for migration to Jetpack Hilt

- **Local Persistence:** Room Database

- **Image Loading:** Glide + Palette API

- **Navigation:** Jetpack Navigation Component

- **Testing:** JUnit (Unit Tests) & Espresso (UI Tests)

---

## 🚀 Features

- Pokémon list with **infinite scrolling (Paging 3)**
- Pokémon images and names displayed in **MaterialCardView**
- Dynamic card background colors extracted using **Palette**
- Favorite Pokémon support:
  - Add Pokémon to favorites
  - Persist favorites locally using Room
  - View and remove favorites
- UI state handling:
  - Loading
  - Success
  - Error
 
---

## Observations about this App
This app isn't finished yet!

---

## Feedback and contact

I will be happy if you give me some feedback. I want to become a better developer!

Connect with me on [LinkedIn](https://br.linkedin.com/in/lucassimoesmartins).

You can use this Project as you wish, it's free!

---

## ⚙️ How to Run

- Clone this repository.

```
$ git clone https://github.com/LucasSimoesMartins/PokeApp.git
```

- Open the project in Android Studio.

- Sync Gradle files.

- Run on an Emulator or Physical Device.

---
Developed by [Lucas Simões Martins](https://br.linkedin.com/in/lucassimoesmartins) - 2026
