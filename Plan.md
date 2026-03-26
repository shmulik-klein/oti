# Oti - Hebrew Reading Practice App

## 1. Project Overview

**Project Name:** Oti (אוֹתִיּוֹת - "letters" in Hebrew)  
**Type:** iPad Educational Application  
**Target Platform:** iOS 12+ (supports older iPads)  
**Technology:** Kotlin Multiplatform (KMP) with UIKit for iOS

## 2. Technical Architecture

### Technology Stack
- **Framework:** Kotlin Multiplatform Mobile (KMM)
- **iOS UI:** UIKit (better iOS 12 compatibility than SwiftUI/Compose)
- **Language:** Kotlin 1.9.x (for iOS 12 compatibility - newer versions require iOS 13+)
- **Shared Code:** Business logic in Kotlin common module
- **Minimum iOS:** 12.0

### Build Configuration Notes
```kotlin
kotlin {
    ios {
        binaries.framework("HebrewReaderFramework") {
            baseName = "HebrewReaderFramework"
        }
    }
}
```

## 3. Feature Roadmap (MVP to Full)

### Phase 1: Core MVP (Simple Letter Recognition)
**Goal:** Display Hebrew letters for basic recognition

1. **Hebrew Alphabet Display**
   - Show Hebrew letters (Alef-Bet) one at a time
   - Display letter name in Hebrew
   - Large, clear typography suitable for beginners

2. **Basic Navigation**
   - Swipe left/right to navigate between letters
   - Simple "Next" / "Previous" buttons
   - Letter counter (e.g., "1/22")

3. **Letter Audio**
   - Play letter pronunciation on tap
   - Use pre-recorded audio files

### Phase 2: Letter + Nikud (Vowels)
**Goal:** Introduce vowel marks

4. **Nikud System**
   - Introduce vowel diacritics (nikud)
   - Display letters with different nikud combinations
   - Color-coded nikud marks for clarity

5. **Audio for Nikud**
   - Audio pronunciation with vowels
   - Syllable-based pronunciation

### Phase 3: Words and Short Phrases
**Goal:** Form simple words

6. **Word Display**
   - Show simple Hebrew words with nikud
   - Word + translation display
   - Image association for concrete nouns

7. **Word Audio**
   - Word pronunciation
   - Slow pronunciation mode

### Phase 4: Practice Modes
**Goal:** Active learning

8. **Quiz Mode**
   - Multiple choice: "What letter is this?"
   - Letter to sound matching

9. **Listening Exercise**
   - Play sound, user identifies letter/word
   - Progress tracking

### Phase 5: Progress & Persistence
**Goal:** Track learning

10. **Progress Tracking**
    - Correct/incorrect counts per letter
    - Weak letters identification
    - Simple statistics

11. **Local Storage**
    - Save progress using SQLite or DataStore
    - User preferences

## 4. UI/UX Design

### Layout Approach
- **iPad-optimized:** Large touch targets, landscape support
- **Simple Navigation:** Tab-less, flow-based
- **Large Typography:** Hebrew text needs clear rendering

### Color Scheme
- **Background:** Light cream/off-white (easy on eyes)
- **Text:** Dark gray/black for contrast
- **Accent:** Blue for interactive elements
- **Success:** Green for correct answers
- **Error:** Red for incorrect answers

### Typography
- **Hebrew Font:** David (or similar clear Hebrew font)
- **Sizes:** Extra large for letters (200pt+)
- **Direction:** RTL support is essential

## 5. Data Structure

### Letter Data Model
```
Letter:
  - character: Char (Hebrew letter)
  - name: String (Hebrew name)
  - category: LetterCategory (consonant/vowel)
  - audioFile: String
```

### Word Data Model
```
Word:
  - text: String (Hebrew with nikud)
  - meaning: String (English translation)
  - category: WordCategory (noun/verb/adjective)
  - audioFile: String
  - imageAsset: String (optional)
```

## 6. Project Structure

```
HebrewReader/
├── shared/                    # Kotlin Multiplatform module
│   ├── src/
│   │   ├── commonMain/        # Shared business logic
│   │   │   ├── data/          # Data models, repositories
│   │   │   ├── domain/        # Use cases, business rules
│   │   │   └── ui/            # Shared UI state
│   │   ├── iosMain/           # iOS-specific code
│   │   └── androidMain/       # (optional) Android support
│   └── build.gradle.kts
├── ios-app/                   # Xcode iOS project
│   ├── HebrewReader/
│   ├── Resources/
│   │   └── audio/             # Hebrew audio files
│   └── Info.plist
└── build.gradle.kts
```

## 7. Assets Required

### Audio Files (Phase 1-3)
- 22 Hebrew letter pronunciations
- Nikud sound samples
- Word pronunciations
- Need: Professional Hebrew audio recordings

### Fonts
- Hebrew font bundled with app
- Consider: David, Noto Sans Hebrew

## 8. Implementation Priorities

### First Build (Day 1-2)
1. Set up KMP project
2. Display Hebrew alphabet
3. Basic navigation

### Alpha (Week 1)
4. Audio playback
5. Nikud display

### Beta (Week 2)
6. Word list
7. Quiz mode basics

### Release Candidate (Week 3)
8. Progress tracking
9. Polish UI
10. Testing on real iPad

## 9. Considerations for iOS 12

- **No SwiftUI:** Use UIKit only
- **No Compose:** Use traditional UIKit views
- **Limited Kotlin version:** 1.9.x series
- **Memory constraints:** Optimize image/audio loading
- **No Dark Mode:** Handle gracefully if available

## 10. Future Enhancements (Post-MVP)

- User accounts / cloud sync
- Stories with Hebrew text
- Handwriting recognition
- Multiple difficulty levels
- Hebrew grammar basics
- Parent/teacher dashboard

---

**Start with Phase 1 MVP - keep it simple, iterate based on user feedback.**
