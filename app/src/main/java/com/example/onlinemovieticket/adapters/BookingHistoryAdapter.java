package com.example.onlinemovieticket.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinemovieticket.R;
import com.example.onlinemovieticket.models.Booking;
import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingViewHolder> {
    private List<Booking> bookings;

    public BookingHistoryAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_history, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.bind(booking);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    class BookingViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMovieTitle, tvShowTime, tvSeats, tvAmount, tvDate, tvPaymentMethod;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvShowTime = itemView.findViewById(R.id.tvShowTime);
            tvSeats = itemView.findViewById(R.id.tvSeats);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPaymentMethod = itemView.findViewById(R.id.tvPaymentMethod);
        }

        public void bind(Booking booking) {
            tvMovieTitle.setText(booking.getMovieTitle());
            tvShowTime.setText(booking.getShowTime());
            tvSeats.setText("Seats: " + String.join(", ", booking.getSelectedSeats()));
            tvAmount.setText("₹" + booking.getTotalAmount());
            tvDate.setText(booking.getBookingDate());
            tvPaymentMethod.setText(booking.getPaymentMethod());
        }
    }
}