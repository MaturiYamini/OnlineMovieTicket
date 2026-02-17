# 🎬 Online Movie Ticket Booking App

An Android mobile application for booking movie tickets with an intuitive user interface and seamless booking experience.

## 📱 Features

- **User Authentication**: Secure login and registration system
- **Movie Browsing**: Browse available movies with details
- **Seat Selection**: Interactive theater seat selection interface
- **Multiple Payment Options**: Credit Card, Debit Card, UPI, Net Banking
- **Booking History**: View past bookings and transaction details
- **User Feedback**: Rate and review booking experience
- **Offline Storage**: SQLite database for local data persistence

## 🛠️ Technologies Used

- **Language**: Java
- **IDE**: Android Studio
- **Database**: SQLite
- **UI Components**: Material Design, RecyclerView, Custom Components
- **Architecture**: MVC Pattern
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📂 Project Structure
OnlineMovieTicket/
├── activities/ # All activity classes
│ ├── LoginActivity
│ ├── RegisterActivity
│ ├── HomeActivity
│ ├── MovieDetailsActivity
│ ├── SeatSelectionActivity
│ ├── PaymentActivity
│ ├── BookingHistoryActivity
│ └── FeedbackActivity
├── models/ # Data models
│ ├── User
│ ├── Movie
│ ├── Seat
│ └── Booking
├── adapters/ # RecyclerView adapters
│ ├── MovieAdapter
│ ├── SeatAdapter
│ └── BookingHistoryAdapter
├── components/ # Custom UI components
│ ├── CustomButton
│ ├── CustomEditText
│ ├── MovieCard
│ ├── PaymentCard
│ ├── SeatView
│ └── TimeSlotButton
└── utils/ # Utility classes
└── DatabaseHelper
