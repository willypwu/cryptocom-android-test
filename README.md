# 🪙 Crypto.com Wallet Dashboard – Android Demo

This is a coding test project for Crypto.com's Android Developer position. It implements a simple Wallet Dashboard using Kotlin and modern Android architecture components.

## 📲 Features

- Load wallet data from three local JSON files
- Show supported currencies (BTC, ETH, CRO)
- Calculate total USD value using exchange rates
- Display wallet balance with coin logo and symbol
- Tab layout navigation (Wallet / DeFi)
- MVVM architecture + Coroutine Flow

## 📸 Demo

<img src="https://github.com/user-attachments/assets/6837c621-8c73-44a1-8e10-ccece201de83" width="300"/>

*Demo showing wallet dashboard in action*

👉 [Click here to view full-resolution screenshot](https://github.com/user-attachments/assets/746b67fc-3ba7-42b6-8376-ce2e33a24ea7)


## 🧠 Architecture

The project uses **MVVM** architecture and separates concerns by feature:
 ```
main/
├── assets/                         # Local data in JSON format
│   ├── currencies.json             
│   ├── live-rates.json             
│   └── wallet-balance.json        
│
├── java/com.test.cryptocom/
│
│   ├── data/                       # Data models mapped from JSON
│   │   ├── model/
│   │   │   ├── CurrencyModels.kt
│   │   │   ├── ExchangeRateModels.kt
│   │   │   └── WalletBalanceModels.kt
│   │   └── repository/
│   │       ├── JsonWalletRepository.kt   # Loads JSON into models
│   │       └── WalletRepository.kt       # Define Repository interface
│
│   ├── presentation.ui/           # UI logic organized by feature
│   │   ├── defi/
│   │   │   └── DeFiFragment.kt             # DeFi UI
│   │   ├── wallet/
│   │   │   ├── model/
│   │   │   │   ├── WalletBalanceDisplay.kt # Wallet display item
│   │   │   │   └── WalletUiState.kt        # UI state
│   │   │   ├── WalletAdapter.kt
│   │   │   ├── WalletDiffCallback.kt
│   │   │   ├── WalletFragment.kt           # Wallet UI
│   │   │   └── WalletViewModel.kt
│   │   └── MainActivity.kt                 # Hosts Nav + BottomBar
│
│   └── util/
│       ├── Constants.kt
│       └── JsonLoader.kt          
 ```

### Libraries Used
	•	Kotlin
	•	Coroutine Flow
	•	Jetpack ViewModel
	•	Jetpack Navigation
	•	RecyclerView
	•	Glide
	•	kotlinx.serialization

## 🧪 JSON Mock Data

All JSON files are placed in the `assets/` folder and loaded via `JsonLoader`.

- `currencies.json` – supported coin names and logos
- `live-rates.json` – exchange rate (from / to pair)
- `wallet-balance.json` – wallet coin amount

## 🚀 Run the App

1. Clone this repo  
2. Open in Android Studio Arctic Fox or newer  
3. Run on emulator or physical device (API 26+)

> ✅ The project compiles and runs successfully.


## 🤝 Thanks

Thanks for reviewing this test. I enjoyed working on this and imagining the potential of contributing to Crypto.com's Android team!

---
