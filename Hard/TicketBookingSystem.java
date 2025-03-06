import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// TicketBooking class to manage seat bookings
class TicketBooking {
    private final int totalSeats;
    private int availableSeats;
    private final Lock lock = new ReentrantLock();

    public TicketBooking(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public void bookTicket(String customerName, boolean isVIP) {
        // Use lock to ensure thread safety
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(customerName + " booked a ticket. Remaining seats: " + (availableSeats - 1));
                availableSeats--;
            } else {
                System.out.println("No available seats for " + customerName);
            }
        } finally {
            lock.unlock();
        }
    }
}

// BookingThread class to handle booking requests
class BookingThread extends Thread {
    private final TicketBooking ticketBooking;
    private final String customerName;
    private final boolean isVIP;

    public BookingThread(TicketBooking ticketBooking, String customerName, boolean isVIP) {
        this.ticketBooking = ticketBooking;
        this.customerName = customerName;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        // Set thread priority for VIP customers
        if (isVIP) {
            this.setPriority(Thread.MAX_PRIORITY);
        } else {
            this.setPriority(Thread.NORM_PRIORITY);
        }
        ticketBooking.bookTicket(customerName, isVIP);
    }
}

// Main class to run the ticket booking system
public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketBooking ticketBooking = new TicketBooking(5); // Total 5 seats

        // Create booking threads
        BookingThread customer1 = new BookingThread(ticketBooking, "Alice (VIP)", true);
        BookingThread customer2 = new BookingThread(ticketBooking, "Bob", false);
        BookingThread customer3 = new BookingThread(ticketBooking, "Charlie (VIP)", true);
        BookingThread customer4 = new BookingThread(ticketBooking, "David", false);
        BookingThread customer5 = new BookingThread(ticketBooking, "Eve (VIP)", true);
        BookingThread customer6 = new BookingThread(ticketBooking, "Frank", false);
        BookingThread customer7 = new BookingThread(ticketBooking, "Grace (VIP)", true);

        // Start booking threads
        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
        customer5.start();
        customer6.start();
        customer7.start();
    }
}
