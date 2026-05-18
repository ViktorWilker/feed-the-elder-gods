# 🐙 Feed the Elder Gods

> A Lovecraftian tamagotchi:
>  feed, entertain and care for an ancient god before he consumes everything... including you.

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat&logo=jetpackcompose&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white)

---

## About the project

Feed the Elder Gods is a mobile game where the player adopts an ancient god — Cthulhu or Hastur — and must keep him alive by managing 5 stats simultaneously. Neglect any stat and the god dies. Survive 50 age cycles to win.

Developed as a portfolio project focusing on Android architecture, interactive minigames and thematic UI design in pure Jetpack Compose.

---

## Functionalities

- **Two playable gods** — Cthulhu and Hastur, each with unique personality and messages
- **5 dynamic stats** — Hunger, Happiness, Tiredness, Bathroom and Dirtiness, with color-coded danger feedback
- **Soul minigame** — frantic tap gameplay with progressively faster soul spawning
- **Sanity minigame** — read a victim's profile and infer the two correct visions to shatter their mind. Each correct answer adds +15s to the timer
- **Time cycle** — every action ages the god and degrades all stats
- **Thematic game over** — unique messages for each death cause and victory condition

---

## Architecture

Each minigame has its own ViewModel. The `GameViewModel` only receives the final result via a single method call (`feedSouls(score)`, `feedSanity(drained)`), keeping temporary game state fully isolated from the god's state.

---

## Tech Stack

| Technology | Usage |
|---|---|
| Kotlin | Primary language |
| Jetpack Compose | Declarative UI |
| ViewModel | State management |
| Navigation Compose | Screen navigation |
| SoundPool | Sound feedback |
| JSON + Assets | Persona data |

---
