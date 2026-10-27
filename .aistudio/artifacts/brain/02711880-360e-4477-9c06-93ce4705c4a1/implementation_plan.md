# Wealth Odyssey - Gamified Financial Literacy Expedition

A mythical Greek odyssey quiz game that transforms essential financial literacy—budgeting, debt management, and investing—into an epic Aegean sea voyage. Players unlock islands, conquer guardian boss trials, earn golden laurel badges, and ascend the Mount Olympus Pantheon leaderboard.

## User Review & Critical Decisions

> [!IMPORTANT]
> The architectural and thematic foundations reflect the preferences confirmed in Phase 1:
> - **Visual & Thematic Style**: Ancient Greek Mythic Voyage with textured parchment tones, deep Aegean blue, Olympian gold laurels, and classical column styling.
> - **Expedition Structure**: Sequential island archipelago map (The Isle of Budgets, Strait of Scylla & Debt, and The Golden Fleece of Investing) featuring staged quiz nodes and culminating in an epic Guardian Boss trial.
> - **Leaderboard & Ranks**: Mythic Pantheon leaderboard featuring dynamic demigod and hero rivals (e.g., Midas the Compounder, Athena of Thrifty Wisdom, Hermes of Cashflows) alongside the voyager's evolving rank.

- **App Naming**: Standardized to **Wealth Odyssey** across the Android app title and platform metadata.
- **Persistence Strategy**: Device-local Room database with reactive StateFlows, guaranteeing full offline playability, instant response times, and zero latency when tracking level progress, drachma coins, badges, and quiz logs.

---

## 1. Overview & Core Concept

- **What It Does**: Wealth Odyssey guides players across a sequential maritime voyage through 3 mythic financial realms. Each island contains progressively challenging quiz encounters on personal finance fundamentals:
  1. **Isle of Budgets (The Oasis of Ithaca)**: Needs vs. wants, the 50/30/20 rule, emergency funds, cashflow tracking, inflation erosion.
  2. **Strait of Debt (The Trials of Scylla & Charybdis)**: Snowball vs. Avalanche repayment, APR vs. APY, predatory interest traps, student loans, credit scores & utilization ratios.
  3. **Isle of Wealth & Assets (The Golden Fleece)**: Index funds vs. picking stocks, compounding growth, dollar-cost averaging, asset allocation, 401(k) / IRA retirement accounts, risk tolerance.
  4. **The Mount Olympus Boss Trials**: Comprehensive timed gauntlets testing practical decision-making against mythical guardians (e.g., The Minotaur of Credit Traps, The Sphinx of Compound Interest).
- **Target Audience**: Young adults, students, and everyday earners seeking practical mastery of budgeting, debt freedom, and wealth building without dry spreadsheets or intimidation.
- **Key Value**: Gamifies real financial education with instant educational rationales for every question, dynamic drachma coin rewards, unlockable stages, badges, and prestige titles.

---

## 2. User Experience & Visual Design

```
┌─────────────────────────────────────────────────────────────┐
│                      WEALTH ODYSSEY                         │
│  [🔱 Level 4 Voyager]   [💰 1,450 Drachmas]   [⚡ Streak 5] │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~   │
│                 ( Island 1: Isle of Budgets )               │
│                  [★] Node 1: Needs vs Wants                 │
│                  [★] Node 2: The 50/30/20 Rule              │
│                  [★] Node 3: Emergency Anchors              │
│                  [⚔️] Boss: Cerberus of Overspending        │
│                           │                                 │
│                   ====== SEA STRAIT ======                  │
│                           ▼                                 │
│                 ( Island 2: Strait of Debt )                │
│                  [★] Node 1: APR & Compounding Debt         │
│                  [★] Node 2: Avalanche vs Snowball          │
│                  [🔒] Node 3: Credit Score Citadel          │
│                           │                                 │
│                           ▼                                 │
│              ( Island 3: The Golden Fleece )                │
│                  [🔒] Investing & Compounding               │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│  [ 🗺️ Voyage Map ]  [ 🏛️ Pantheon ]  [ 🏅 Badges ]  [ 📜 Codex ]
└─────────────────────────────────────────────────────────────┘
```

### Key User Flows
1. **The Voyage Map (Home)**: Displays the grand Aegean sea map with floating islands, golden navigation lines, active ships marking player position, stars earned (1-3 stars per node), and lock status.
2. **Quiz Encounter / Boss Trial**:
   - Distinctive parchment card presentation with Greek geometric key border motifs.
   - Scenario-driven questions (e.g., *"You earn $3,000/mo. Applying the 50/30/20 rule, how much should go to savings?"*).
   - Tapping an answer triggers smooth haptic ripple and instant visual feedback (Laurel Green for correct, Terracotta Red for incorrect).
   - Expandable "Wisdom of Athena" explanation detailing the real-world financial logic.
   - Earns Drachmas (gold coins), XP, and Laurel Stars.
3. **Mount Olympus Pantheon (Leaderboard)**:
   - Mythical leaderboard displaying historical heroes, mythological rivals, and the player's live rank.
   - Rivals advance and react dynamically based on the player's drachma wealth and accuracy.
   - Divisions from *Mortal Pilgrim* to *Titan of Capital*.
4. **Treasury of Badges**:
   - Showcase of mythic medallions (e.g., *Slayer of Debt*, *Golden Fleece Accumulator*, *Stoic Budgeter*, *Perfect Oracles*).
   - Detailed unlocks with progress bars and lore descriptions.
5. **The Oracle's Codex (Study Library)**:
   - Quick reference guides for key formulas (Rule of 72, 50/30/20 breakdown, Emergency Fund math, Avalanche vs. Snowball calculator).

### Visual Identity & Palette
- **Primary / Dominant Atmosphere**: Deep Mediterranean Aegean Blue (`#0B192C` / `#1E3E62`), creating rich maritime depth.
- **Parchment Surface**: Warm Hellenic Papyrus (`#F7F3E9` / `#FFFDF9` in light mode, `#16213E` deep parchment in dark mode).
- **Accents & Radiance**: Olympian Gold (`#E6AF2E` / `#F3C623`) for laurels, coins, active stars, and primary buttons.
- **Status Accents**: Athenian Olive Laurel (`#2D8B57`) for correct insights, Spartan Crimson (`#BA3B46`) for errors and debt hazard warnings.
- **Typography & Details**: Classic serif styling for headings with clean, modern legible sans-serif for financial scenarios and numeric calculations.

---

## 3. Key Product Decisions & Trade-Offs

- **Decision 1: Device-Local Room Database vs. Remote Cloud Backend**
  - *Chosen Approach*: Device-local Room database with pre-populated questions and reactive Flow streams.
  - *Why*: Delivers an immediate, zero-latency gaming experience with zero login friction or network failures during quizzes. Progress, coins, scores, and unlocks persist permanently across app restarts.
  - *Alternatives Considered*: Cloud Firestore (requires sign-in friction and network connection, overkill for single-player campaign mode).
- **Decision 2: Curated High-Yield Financial Question Bank vs. Generic Trivia**
  - *Chosen Approach*: 40+ carefully authored, high-yield financial literacy questions covering real numbers, realistic situations, calculations, and clear practical explanations.
  - *Why*: Financial quizzes only help users improve financial literacy if they teach actionable rules of thumb (50/30/20, high-interest debt priority, employer 401k match, emergency fund sizing).
- **Decision 3: Sequential Level Unlocking with Star Thresholds**
  - *Chosen Approach*: Nodes require passing previous trials or reaching star thresholds, with guardian boss quizzes requiring 80%+ accuracy to open the subsequent island.
  - *Why*: Encourages mastery and replayability; users who score 1 star can replay to achieve 3 stars and earn bonus Drachma coins.

---

## 4. Technical Architecture & Data Strategy

```
┌───────────────────────────────────────────────────────────────┐
│                       Jetpack Compose UI                      │
│   ┌───────────────────┬───────────────────┬───────────────┐   │
│   │   VoyageMapScreen │   QuizPlayScreen  │ PantheonScreen│   │
│   └─────────▲─────────┴─────────▲─────────┴───────▲───────┘   │
│             │                   │                 │           │
│   ┌─────────┴───────────────────┴─────────────────┴───────┐   │
│   │                 WealthOdysseyViewModel                │   │
│   │  - playerProfile: StateFlow<PlayerProfile>            │   │
│   │  - islands: StateFlow<List<IslandWithNodes>>          │   │
│   │  - activeQuizState: StateFlow<QuizSessionState>       │   │
│   │  - pantheonRivals: StateFlow<List<PantheonRival>>     │   │
│   │  - badges: StateFlow<List<BadgeItem>>                 │   │
│   └─────────────────────────────▲─────────────────────────┘   │
│                                 │                             │
│   ┌─────────────────────────────┴─────────────────────────┐   │
│   │              WealthOdysseyRepository                  │   │
│   │  - Evaluates answers, calculates XP/Drachmas/Stars    │   │
│   │  - Checks & awards badges (e.g. Debt Slayer)          │   │
│   │  - Updates Pantheon leaderboard positions             │   │
│   └─────────────────────────────▲─────────────────────────┘   │
│                                 │                             │
│   ┌─────────────────────────────┴─────────────────────────┐   │
│   │            Room Database (AppDatabase)                │   │
│   │  - PlayerDao: PlayerProfile (XP, Drachmas, Streak)    │   │
│   │  - StageProgressDao: IslandNodeProgress (Stars, Score)│   │
│   │  - BadgeDao: Badges & Unlocks                         │   │
│   │  - Pre-seeded question dataset & financial codex      │   │
│   └───────────────────────────────────────────────────────┘   │
└───────────────────────────────────────────────────────────────┘
```

### Core Entities & State
1. **PlayerProfile**: Total Drachmas, Level / Title, Current Island ID, Total Stars, Streak Days, Questions Answered, Accuracy Rate.
2. **QuizQuestion**: ID, Topic (Budgeting, Debt, Investing), IslandId, NodeIndex, QuestionText, Options (4), CorrectIndex, ExplanationText, Difficulty, DrachmaReward.
3. **StageProgress**: NodeId, IslandId, IsUnlocked, HighScore, StarsEarned (0..3), CompletedTimestamp.
4. **BadgeItem**: ID, Title, Description, IconType, Category, UnlockedAt, CurrentProgress, TargetProgress.
5. **PantheonEntry**: Rank, Name, Title, DrachmasScore, IslandProgress, IsPlayer.

---

## Execution Plan & Milestones

1. **Database & Data Layer**:
   - Define Room entities: `PlayerEntity`, `StageProgressEntity`, `BadgeEntity`.
   - Create curated financial question bank with 40+ questions across Budgeting, Debt, and Investing.
   - Build `WealthOdysseyRepository` with reactive Flow queries and progress calculation.
2. **ViewModel & Game Mechanics**:
   - Implement `WealthOdysseyViewModel` managing current tab, quiz engine, answer checking, star grading, and badge triggers.
3. **Theme & UI Styling**:
   - Create mythic Greek palette with gold laurels, parchment cards, column borders, and nautical waves.
   - Configure typography, custom shapes, and icons.
4. **Screens & Components**:
   - `VoyageMapScreen`: Interactive island map with path connections, node pins, stars, and boss gates.
   - `QuizPlayScreen`: Immersive card trial with timer, question counter, answer options, immediate explanation, and victory fanfare screen.
   - `PantheonScreen`: Leaderboard with podium for top 3 champions, player rank highlight, and tier divisions.
   - `BadgesScreen`: Mythic medals showcase with unlock celebrations.
   - `CodexScreen`: The Oracle's financial cheat-sheets (Rule of 72, Debt Avalanche, 50/30/20).
5. **Polishing & Verification**:
   - Update `metadata.json` and `strings.xml` to "Wealth Odyssey".
   - Test compile with `compile_applet` and verify all flows.
