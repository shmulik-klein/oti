# Oti (אוֹתִיּוֹת)

Hebrew reading practice app for iPad.

## Overview

Oti is a Kotlin Multiplatform (KMP) project for practicing Hebrew letter recognition. It targets iOS 12+ to support older iPads.

## Architecture

```
oti/
├── shared/              # Kotlin Multiplatform module
│   └── src/commonMain/kotlin/com/oti/
│       ├── data/       # Data models (HebrewAlphabet)
│       └── domain/    # Use cases (LetterUseCase)
└── ios-app/            # iOS app (Swift/UIKit)
    └── Oti/
        ├── AppDelegate.swift
        └── LetterViewController.swift
```

## Tech Stack

- **Language**: Kotlin 1.9.22
- **Platform**: iOS 12+
- **UI Framework**: UIKit (no SwiftUI/Compose for iOS 12 compatibility)
- **Architecture**: KMP with shared business logic

## Features (MVP)

- Display 22 Hebrew letters (Alef-Bet)
- Swipe or button navigation between letters
- Letter name display
- Letter counter (1/22)

## Build

### Prerequisites
- macOS with Xcode (for iOS builds)
- Gradle 8+
- Kotlin 1.9.22

### Build iOS framework
```bash
cd oti
./gradlew :shared:linkOtiFramework
```

### Generate Xcode project
```bash
cd oti/ios-app
xcodegen generate
```

## Development

1. Build KMP framework: `./gradlew :shared:linkOtiFramework`
2. Open `ios-app/oti.xcworkspace` in Xcode
3. Select iPad simulator and run

## License

MIT