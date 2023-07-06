import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookMyTicket1 extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu userMenu, theatreMenu, movieMenu, showtimeMenu, seatMenu, bookingMenu;
    private JMenuItem addUserItem, deleteUserItem, updateUserItem, displayUsersItem;
    private JMenuItem addTheatreItem, deleteTheatreItem, updateTheatreItem, displayTheatresItem;
    private JMenuItem addMovieItem, deleteMovieItem, updateMovieItem, displayMoviesItem;
    private JMenuItem addShowtimeItem, deleteShowtimeItem, updateShowtimeItem, displayShowtimesItem;
    private JMenuItem addSeatItem, deleteSeatItem, updateSeatItem, displaySeatsItem;
    private JMenuItem addBookingItem, deleteBookingItem, updateBookingItem, displayBookingsItem;
    
    

    private Connection conn;

    public BookMyTicket1() {
        // Create the menu bar
        menuBar = new JMenuBar();

        // Create the menus
        userMenu = new JMenu("User");
        theatreMenu = new JMenu("Theatre");
        movieMenu = new JMenu("Movie");
        showtimeMenu = new JMenu("Showtime");
        seatMenu = new JMenu("Seat");
        bookingMenu = new JMenu("Booking");

        // Create the menu items for User menu
        addUserItem = new JMenuItem("Add User");
        deleteUserItem = new JMenuItem("Delete User");
        updateUserItem = new JMenuItem("Update User");
        displayUsersItem = new JMenuItem("Display Users");

        // Create the menu items for Theatre menu
        addTheatreItem = new JMenuItem("Add Theatre");
        deleteTheatreItem = new JMenuItem("Delete Theatre");
        updateTheatreItem = new JMenuItem("Update Theatre");
        displayTheatresItem = new JMenuItem("Display Theatres");

        // Create the menu items for Movie menu
        addMovieItem = new JMenuItem("Add Movie");
        deleteMovieItem = new JMenuItem("Delete Movie");
        updateMovieItem = new JMenuItem("Update Movie");
        displayMoviesItem = new JMenuItem("Display Movies");

        // Create the menu items for Showtime menu
        addShowtimeItem = new JMenuItem("Add Showtime");
        deleteShowtimeItem = new JMenuItem("Delete Showtime");
        updateShowtimeItem = new JMenuItem("Update Showtime");
        displayShowtimesItem = new JMenuItem("Display Showtimes");

        // Create the menu items for Seat menu
        addSeatItem = new JMenuItem("Add Seat");
        deleteSeatItem = new JMenuItem("Delete Seat");
        updateSeatItem = new JMenuItem("Update Seat");
        displaySeatsItem = new JMenuItem("Display Seats");

        // Create the menu items for Booking menu
        addBookingItem = new JMenuItem("Add Booking");
        deleteBookingItem = new JMenuItem("Delete Booking");
        updateBookingItem = new JMenuItem("Update Booking");
        displayBookingsItem = new JMenuItem("Display Bookings");
        
        
        
        

        // Add action listeners to the menu items
        addUserItem.addActionListener(this);
        deleteUserItem.addActionListener(this);
        updateUserItem.addActionListener(this);
        displayUsersItem.addActionListener(this);
        addTheatreItem.addActionListener(this);
        deleteTheatreItem.addActionListener(this);
        updateTheatreItem.addActionListener(this);
        displayTheatresItem.addActionListener(this);
        addMovieItem.addActionListener(this);
        deleteMovieItem.addActionListener(this);
        updateMovieItem.addActionListener(this);
        displayMoviesItem.addActionListener(this);
        addShowtimeItem.addActionListener(this);
        deleteShowtimeItem.addActionListener(this);
        updateShowtimeItem.addActionListener(this);
        displayShowtimesItem.addActionListener(this);
        addSeatItem.addActionListener(this);
        deleteSeatItem.addActionListener(this);
        updateSeatItem.addActionListener(this);
        displaySeatsItem.addActionListener(this);
        addBookingItem.addActionListener(this);
        deleteBookingItem.addActionListener(this);
        updateBookingItem.addActionListener(this);
        displayBookingsItem.addActionListener(this);

        // Add the menu items to the menus
        userMenu.add(addUserItem);
        userMenu.add(deleteUserItem);
        userMenu.add(updateUserItem);
        userMenu.add(displayUsersItem);
        theatreMenu.add(addTheatreItem);
        theatreMenu.add(deleteTheatreItem);
        theatreMenu.add(updateTheatreItem);
        theatreMenu.add(displayTheatresItem);
        movieMenu.add(addMovieItem);
        movieMenu.add(deleteMovieItem);
        movieMenu.add(updateMovieItem);
        movieMenu.add(displayMoviesItem);
        showtimeMenu.add(addShowtimeItem);
        showtimeMenu.add(deleteShowtimeItem);
        showtimeMenu.add(updateShowtimeItem);
        showtimeMenu.add(displayShowtimesItem);
        seatMenu.add(addSeatItem);
        seatMenu.add(deleteSeatItem);
        seatMenu.add(updateSeatItem);
        seatMenu.add(displaySeatsItem);
        bookingMenu.add(addBookingItem);
        bookingMenu.add(deleteBookingItem);
        bookingMenu.add(updateBookingItem);
        bookingMenu.add(displayBookingsItem);

        // Add the menus to the menu bar
        menuBar.add(userMenu);
        menuBar.add(theatreMenu);
        menuBar.add(movieMenu);
        menuBar.add(showtimeMenu);
        menuBar.add(seatMenu);
        menuBar.add(bookingMenu);

        // Set the menu bar to the frame
        setJMenuBar(menuBar);

        // Set the frame properties
        setTitle("BookMyTicket");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    
        // Connect to the database
        Connection conn; // Declare the connection variable as a class member
        connectToDatabase();
    }
    public void connectToDatabase() {
            conn = ConnectDb(); // Assign the connection to the class member
        }

        private void addUser() {
            String UserID = JOptionPane.showInputDialog(this, "Enter user id:");
            String Name = JOptionPane.showInputDialog(this, "Enter user name:");
            String Email = JOptionPane.showInputDialog(this, "Enter user email:");
            String Password = JOptionPane.showInputDialog(this, "Enter user password:");
            String PhoneNo = JOptionPane.showInputDialog(this, "Enter user phone number:");

            try {
                String query = "INSERT INTO User (UserID, Name, Email, Password, PhoneNo) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, UserID);
                statement.setString(2, Name);
                statement.setString(3, Email);
                statement.setString(4, Password);
                statement.setString(5, PhoneNo);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "User added successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add user");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred");
            }
        }

        public static Connection ConnectDb() {
            Connection conn = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS", "root", "laxmansai");
                System.out.println("Connected to the database");
                
            } catch (Exception e) {
                e.printStackTrace();
            }

            return conn;
        }

       
            
         

    private void deleteUser() {
        try {
            int userID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter user ID:"));

            String query = "DELETE FROM User WHERE UserID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User deleted successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void updateUser() {
        try {
            int userID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter user ID:"));
            String name = JOptionPane.showInputDialog(this, "Enter new user name:");
            String email = JOptionPane.showInputDialog(this, "Enter new user email:");
            String password = JOptionPane.showInputDialog(this, "Enter new user password:");
            String phoneNo = JOptionPane.showInputDialog(this, "Enter new user phone number:");

            String query = "UPDATE User SET Name = ?, Email = ?, Password = ?, PhoneNo = ? WHERE UserID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, phoneNo);
            statement.setInt(5, userID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void displayUsers() {
        try {
            String query = "SELECT * FROM User";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder sb = new StringBuilder();
            sb.append("UserID\tName\tEmail\tPassword\tPhoneNo\n");
            while (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String phoneNo = resultSet.getString("PhoneNo");

                sb.append(userID).append("\t").append(name).append("\t").append(email).append("\t")
                        .append(password).append("\t").append(phoneNo).append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void addTheatre() {
        String theatreid = JOptionPane.showInputDialog(this, "Enter theatre ID:");
        String theatrename = JOptionPane.showInputDialog(this, "Enter theatre name:");
        String theatrecity = JOptionPane.showInputDialog(this, "Enter theatre City:");
        String theatreseatcapacity = JOptionPane.showInputDialog(this, "Enter theatre SeatCapacity:");

        try {
            String query = "INSERT INTO Theatre (TheatreID,Name,City,SeatingCapacity ) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, theatreid);
            statement.setString(2, theatrename);
            statement.setString(3, theatrecity);
            statement.setString(4, theatreseatcapacity);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Theatre added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add theatre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void deleteTheatre() {
        try {
            int theatreID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter theatre ID:"));

            String query = "DELETE FROM Theatre WHERE TheatreID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, theatreID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Theatre deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete theatre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred");
        }
    }

    private void updateTheatre() {
        try {
            int theatreID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter theatre ID:"));
            String name = JOptionPane.showInputDialog(null, "Enter new theatre name:");
            String city = JOptionPane.showInputDialog(null, "Enter new theatre city:");
            int seatingCapacity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new seating capacity:"));

            String query = "UPDATE Theatre SET Name = ?, City = ?, SeatingCapacity = ? WHERE TheatreID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, city);
            statement.setInt(3, seatingCapacity);
            statement.setInt(4, theatreID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Theatre updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update theatre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred");
        }
    }
    private void displayTheatres() {
        try {
            String query = "SELECT * FROM Theatre";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder sb = new StringBuilder();
            sb.append("TheatreID\tName\tCity\tSeatingCapacity\n");
            while (resultSet.next()) {
                int theatreID = resultSet.getInt("TheatreID");
                String name = resultSet.getString("Name");
                String city = resultSet.getString("City");
                int seatingCapacity = resultSet.getInt("SeatingCapacity");

                sb.append(theatreID).append("\t").append(name).append("\t").append(city).append("\t").append(seatingCapacity).append("\n");
            }

            JOptionPane.showMessageDialog(null, sb.toString(), "Theatre List", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void addMovie() {
    	
        String title = JOptionPane.showInputDialog(this, "Enter movie title:");
        String genre = JOptionPane.showInputDialog(this, "Enter movie genre:");
        String language = JOptionPane.showInputDialog(this, "Enter movie language:");
        String cast = JOptionPane.showInputDialog(this, "Enter movie cast:");
        String director = JOptionPane.showInputDialog(this, "Enter movie director:");

        try {
            String query = "INSERT INTO Movie (Title, Genre, Language, Cast, Director) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setString(3, language);
            statement.setString(4, cast);
            statement.setString(5, director);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Movie added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add movie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void deleteMovie() {
        try {
            int movieID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter movie ID:"));

            String query = "DELETE FROM Movie WHERE MovieID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, movieID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Movie deleted successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete movie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void updateMovie() {
        try {
            int movieID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter movie ID:"));
            String title = JOptionPane.showInputDialog(this, "Enter new movie title:");
            String genre = JOptionPane.showInputDialog(this, "Enter new movie genre:");
            String language = JOptionPane.showInputDialog(this, "Enter new movie language:");
            String cast = JOptionPane.showInputDialog(this, "Enter new movie cast:");
            String director = JOptionPane.showInputDialog(this, "Enter new movie director:");

            String query = "UPDATE Movie SET Title = ?, Genre = ?, Language = ?, Cast = ?, Director = ? WHERE MovieID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setString(3, language);
            statement.setString(4, cast);
            statement.setString(5, director);
            statement.setInt(6, movieID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Movie updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update movie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void displayMovies() {
        try {
            String query = "SELECT * FROM Movie";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder sb = new StringBuilder();
            sb.append("MovieID\tTitle\tGenre\tLanguage\tCast\tDirector\n");
            while (resultSet.next()) {
                int movieID = resultSet.getInt("MovieID");
                String title = resultSet.getString("Title");
                String genre = resultSet.getString("Genre");
                String language = resultSet.getString("Language");
                String cast = resultSet.getString("Cast");
                String director = resultSet.getString("Director");

                sb.append(movieID).append("\t").append(title).append("\t").append(genre).append("\t")
                        .append(language).append("\t").append(cast).append("\t").append(director).append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }


    private void addShowtime() {
        int ShowtimeID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Showtime ID:"));
        int theatreID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Theatre ID:"));
        String dateTime = JOptionPane.showInputDialog(this, "Enter showtime (YYYY-MM-DD HH:MM):");
        double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter price:"));
        int availableSeats = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter available seats:"));

        try {
            String query = "INSERT INTO Showtime (ShowTimeID, TheatreID, DateTime, Price, AvailableSeats) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, ShowtimeID);
            statement.setInt(2, theatreID);
            statement.setString(3, dateTime);
            statement.setDouble(4, price);
            statement.setInt(5, availableSeats);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Showtime added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add showtime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void deleteShowtime() {
        try {
            int showtimeID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter showtime ID:"));

            String query = "DELETE FROM Showtime WHERE ShowtimeID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, showtimeID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Showtime deleted successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete showtime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void updateShowtime() {
        try {
            int showtimeID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter showtime ID:"));
            int theatreID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new theatre ID:"));
            String dateTime = JOptionPane.showInputDialog(this, "Enter new showtime (YYYY-MM-DD HH:MM):");
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter new price:"));
            int availableSeats = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new available seats:"));

            String query = "UPDATE Showtime SET TheatreID = ?, DateTime = ?, Price = ?, AvailableSeats = ? WHERE ShowtimeID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, theatreID);
            statement.setString(3, dateTime);
            statement.setDouble(4, price);
            statement.setInt(5, availableSeats);
            statement.setInt(6, showtimeID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Showtime updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update showtime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void displayShowtimes() {
        try {
            String query = "SELECT * FROM ShowTime";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder sb = new StringBuilder();
            sb.append("ShowtimeID\tTheatreID\tDatetime\tPrice\tAvailableSeats\n");
            while (resultSet.next()) {
                int showtimeID = resultSet.getInt("ShowtimeID");
                String theatreName = resultSet.getString("TheatreID");
                String showtime = resultSet.getString("Datetime");
                double price = resultSet.getDouble("Price");
                int availableSeats = resultSet.getInt("AvailableSeats");

                sb.append(showtimeID).append("\t").append(theatreName).append("\t")
                        .append(showtime).append("\t").append(price).append("\t").append(availableSeats).append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }



    private void addSeat() {
        int theatreID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter theatre ID:"));
        String seatNumber = JOptionPane.showInputDialog(this, "Enter seat number:");
        int row = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter row number:"));
        int column = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter column number:"));

        try {
            String query = "INSERT INTO Seat (TheatreID, SeatNumber, Row, Column) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, theatreID);
            statement.setString(2, seatNumber);
            statement.setInt(3, row);
            statement.setInt(4, column);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Seat added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add seat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void deleteSeat() {
        try {
            int seatID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter seat ID:"));

            String query = "DELETE FROM Seat WHERE SeatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, seatID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Seat deleted successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete seat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void updateSeat() {
        try {
            int seatID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter seat ID:"));
            int theatreID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new theatre ID:"));
            String seatNumber = JOptionPane.showInputDialog(this, "Enter new seat number:");
            int row = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new row number:"));
            int column = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new column number:"));

            String query = "UPDATE Seat SET TheatreID = ?, SeatNumber = ?, Row = ?, Column = ? WHERE SeatID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, theatreID);
            statement.setString(2, seatNumber);
            statement.setInt(3, row);
            statement.setInt(4, column);
            statement.setInt(5, seatID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Seat updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update seat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void displaySeats() {
        try {
            String query = "SELECT Seat.SeatID, Theatre.Name AS TheatreName, Seat.SeatNumber " +
                    "FROM Seat " +
                    "JOIN Theatre ON Seat.TheatreID = Theatre.TheatreID";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder sb = new StringBuilder();
            sb.append("SeatID\tTheatreName\tSeatNumber\n");
            while (resultSet.next()) {
                int seatID = resultSet.getInt("SeatID");
                String theatreName = resultSet.getString("TheatreName");
                String seatNumber = resultSet.getString("SeatNumber");

                sb.append(seatID).append("\t").append(theatreName).append("\t").append(seatNumber).append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }
    private void displayBookings() {
        try {
            String query = "SELECT * FROM Booking";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder sb = new StringBuilder();
            sb.append("BookingID\tUserID\tTheatreID\tShowtimeID\tSeatsBooked\tStatus\n");
            while (resultSet.next()) {
                int bookingID = resultSet.getInt("BookingID");
                int userID = resultSet.getInt("UserID");
                int theatreID = resultSet.getInt("TheatreID");
                int showtimeID = resultSet.getInt("ShowtimeID");
                int seatsBooked = resultSet.getInt("SeatsBooked");
                String status = resultSet.getString("Status");

                sb.append(bookingID).append("\t").append(userID).append("\t").append(theatreID).append("\t")
                        .append(showtimeID).append("\t").append(seatsBooked).append("\t").append(status).append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void updateBooking() {
        try {
            int bookingID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter booking ID:"));
            int seatsBooked = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new seats booked:"));
            String status = JOptionPane.showInputDialog(this, "Enter new status:");

            String query = "UPDATE Booking SET SeatsBooked = ?, Status = ? WHERE BookingID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, seatsBooked);
            statement.setString(2, status);
            statement.setInt(3, bookingID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Booking updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update booking");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void deleteBooking() {
        try {
            int bookingID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter booking ID:"));

            String query = "DELETE FROM Booking WHERE BookingID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, bookingID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Booking deleted successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete booking");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }

    private void addBooking() {
        int BookingID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Booking ID:"));   	
        int userID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter user ID:"));
        int theatreID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter theatre ID:"));
        int showtimeID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter showtime ID:"));
        int seatsBooked = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter seats booked:"));
        String status = JOptionPane.showInputDialog(this, "Enter status:");

        try {
            String query = "INSERT INTO Booking (BookingID,UserID, TheatreID, ShowtimeID, SeatsBooked, Status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, BookingID);            
            statement.setInt(2, userID);
            statement.setInt(3, theatreID);
            statement.setInt(4, showtimeID);
            statement.setInt(5, seatsBooked);
            statement.setString(6, status);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Booking added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add booking");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add User":
                addUser();
                break;
            case "Delete User":
                deleteUser();
                break;
            case "Update User":
                updateUser();
                break;
            case "Display Users":
                displayUsers();
                break;
            case "Add Theatre":
                addTheatre();
                break;
            case "Delete Theatre":
               deleteTheatre();
                break;
            case "Update Theatre":
                updateTheatre();
                break;
            case "Display Theatres":
                displayTheatres();
                break;
            case "Add Movie":
                addMovie();
                // Perform insert operation on Movie table
                // Implement your logic here
                break;
            case "Delete Movie":
                deleteMovie();
                // Perform delete operation on Movie table
                // Implement your logic here
                break;
            case "Update Movie":
                updateMovie();
                // Perform update operation on Movie table
                // Implement your logic here
                break;
            case "Display Movies":
                displayMovies();
                break;
            case "Add Showtime":
                addShowtime();// Perform insert operation on Showtime table
                // Implement your logic here
                break;
            case "Delete Showtime":
                deleteShowtime();// Perform delete operation on Showtime table
                // Implement your logic here
                break;
            case "Update Showtime":
                updateShowtime();// Perform update operation on Showtime table
                // Implement your logic here
                break;
            case "Display Showtimes":
                displayShowtimes();
                break;
            case "Add Seat":
                addSeat();
                // Perform insert operation on Seat table
                // Implement your logic here
                break;
            case "Delete Seat":
                deleteSeat();
                // Perform delete operation on Seat table
                // Implement your logic here
                break;
            case "Update Seat":
                updateSeat();
                // Perform update operation on Seat table
                // Implement your logic here
                break;
            case "Display Seats":
                displaySeats();
                break;
            
            case "Add Booking":
                addBooking();
                // Perform insert operation on Booking table
                // Implement your logic here
                break;
            case "Delete Booking":
                deleteBooking();
                // Perform delete operation on Booking table
                // Implement your logic here
                break;
            case "Update Booking":
                updateBooking();
                // Perform update operation on Booking table
                // Implement your logic here
                break;
            case "Display Bookings":
                displayBookings();
                break;
            
        }
    }
    

    

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookMyTicket1();
            }
        });
    }
}

