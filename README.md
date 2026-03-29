# Oti (אוֹתִיּוֹת)

Hebrew reading practice web app for iPad.

## Overview

Oti is a Kotlin/JS web application for practicing Hebrew letter recognition. Runs in Safari on any iPad (iOS 12+).

## Architecture

```
oti/
├── shared/              # Kotlin Multiplatform module
│   └── src/commonMain/kotlin/klein/shmulik/oti/
│       ├── data/        # Data models (HebrewAlphabet)
│       └── domain/      # Use cases (LetterUseCase)
└── web/                 # Web frontend
    └── src/jsMain/kotlin/klein/shmulik/oti/
        └── main.kt     # UI entry point
```

## Tech Stack

- **Language**: Kotlin 1.9.22
- **Platform**: Web (any browser, iOS 12+ Safari)
- **Architecture**: Kotlin Multiplatform with JS target

## Features (MVP)

- Display 22 Hebrew letters (Alef-Bet)
- Button navigation between letters
- Keyboard navigation (arrow keys)
- Letter name display with transliteration
- Letter counter (1/22)

## Build

### Prerequisites
- Gradle 8+
- Kotlin 1.9.22
- Node.js (for Kotlin/JS)

### Build web app
```bash
cd oti
./gradlew :web:build
```

### Output
Production files in: `web/build/dist/js/productionExecutable/`

## Development

```bash
./gradlew :web:build
# Open web/build/dist/js/productionExecutable/index.html in browser
```

## Deploy

1. Build: `./gradlew :web:build`
2. Upload contents of `web/build/dist/js/productionExecutable/` to any static host:
   - GitHub Pages
   - Netlify
   - Vercel
3. Open URL in Safari on iPad
4. Tap Share → Add to Home Screen (works like a native app)

## License

MIT
