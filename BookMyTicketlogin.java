import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookMyTicketlogin extends JFrame implements ActionListener {
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private Connection conn;
    private String loggedInUser;

    public BookMyTicketlogin() {
        // Set the frame properties
        setTitle("BookMyTicket");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Create the components
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(15); // Increase the width of the field by specifying the columns
        passwordField = new JPasswordField(15); // Increase the width of the field by specifying the columns
        loginButton = new JButton("Login");

        // Add action listener to the login button
        loginButton.addActionListener(this);

        // Create the GridBagConstraints object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Set the insets for spacing

        // Add the components to the frame using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Align to the center
        add(loginButton, gbc);

        // Connect to the database
        connectToDatabase();

        // Display the frame
        setVisible(true);
    }




    private void connectToDatabase() {
        try {
            // Update the database URL, username, and password with your own credentials
            String dbURL = "jdbc:mysql://localhost:3306/DBMS";
            String username = "root";
            String password = "laxmansai";

            // Connect to the database
            conn = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean verifyLogin(String username, String password) {
        try {
            String query = "SELECT * FROM User WHERE Name = ? AND Password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                loggedInUser = resultSet.getString("Name");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    private void openBookingFrame() {
        if (loggedInUser != null) {
            // Create a new frame for booking details
            JFrame bookingFrame = new JFrame("Booking Details");
            bookingFrame.setSize(600, 400);
            bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bookingFrame.setLocationRelativeTo(null);

            // Add the booking details components to the booking frame
            // Modify and add your booking details components here

            try {
                // Fetch movie names from the database
                String movieQuery = "SELECT Title FROM Movie";
                PreparedStatement movieStatement = conn.prepareStatement(movieQuery);
                ResultSet movieResultSet = movieStatement.executeQuery();

                // Create a list to store movie names
                List<String> movieNames = new ArrayList<>();

                // Add movie names to the list
                while (movieResultSet.next()) {
                    movieNames.add(movieResultSet.getString("Title"));
                }

                // Create and add the movie combo box
                JLabel movieLabel = new JLabel("Select Movie:");
                JComboBox<String> movieComboBox = new JComboBox<>(movieNames.toArray(new String[0]));

                // Fetch theatre names from the database
                String theatreQuery = "SELECT Name FROM Theatre";
                PreparedStatement theatreStatement = conn.prepareStatement(theatreQuery);
                ResultSet theatreResultSet = theatreStatement.executeQuery();

                // Create a list to store theatre names
                List<String> theatreNames = new ArrayList<>();

                // Add theatre names to the list
                while (theatreResultSet.next()) {
                    theatreNames.add(theatreResultSet.getString("Name"));
                }

                // Create and add the theatre combo box
                JLabel theatreLabel = new JLabel("Select Theatre:");
                JComboBox<String> theatreComboBox = new JComboBox<>(theatreNames.toArray(new String[0]));

                // Fetch time slots from the database
                String timeQuery = "SELECT DateTime FROM ShowTime";
                PreparedStatement timeStatement = conn.prepareStatement(timeQuery);
                ResultSet timeResultSet = timeStatement.executeQuery();

                // Create a list to store time slots
                List<String> timeSlots = new ArrayList<>();

                // Add time slots to the list
                while (timeResultSet.next()) {
                    timeSlots.add(timeResultSet.getString("DateTime"));
                }

                // Create and add the time combo box
                JLabel timeLabel = new JLabel("Select Time:");
                JComboBox<String> timeComboBox = new JComboBox<>(timeSlots.toArray(new String[0]));

                JLabel seatsLabel = new JLabel("Number of Seats:");
                JTextField seatsField = new JTextField();

                JButton bookButton = new JButton("Book");
                bookButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Perform booking logic here
                        String movie = (String) movieComboBox.getSelectedItem();
                        String theatre = (String) theatreComboBox.getSelectedItem();
                        String time = (String) timeComboBox.getSelectedItem();
                        String seats = seatsField.getText();
                        
                        

                        // Implement your booking logic here
                        // For example, you can insert the booking details into the Booking table using SQL

                        // SQL query to insert into Booking table
                        String insertQuery = "INSERT INTO Booking (UserID, TheatreID, ShowtimeID, SeatsBooked, Status) VALUES (?, ?, ?, ?, ?)";

                        try {
                            // Prepare the statement
                            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

                            // Set the parameter values
                            preparedStatement.setInt(1, getUserID());
                            preparedStatement.setInt(2, getTheatreID(theatre));
                            preparedStatement.setInt(3, getShowtimeID(time));
                            preparedStatement.setInt(4, Integer.parseInt(seats));
                            preparedStatement.setString(5, "Booked"); // Assuming "Booked" as the status

                            // Execute the query
                            preparedStatement.executeUpdate();

                            // Display a confirmation message with the booking details
                            String message = "Booking Details:\n\n";
                            message += "Movie: " + movie + "\n";
                            message += "Theatre: " + theatre + "\n";
                            message += "Time: " + time + "\n";
                            message += "Seats: " + seats + "\n";

                            JOptionPane.showMessageDialog(null, message, "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);

                            // Reset the input fields
                            movieComboBox.setSelectedIndex(0);
                            theatreComboBox.setSelectedIndex(0);
                            timeComboBox.setSelectedIndex(0);
                            seatsField.setText("");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            // Handle the exception and display an error message if needed
                        }
                    }

                    private int getUserID() {
                        int userID = 0;

                        // Assuming you have a way to retrieve the currently logged-in user information
                        // For example, if you have a User class with a loggedInUser variable
                        if (loggedInUser != null) {
                            try {
                                // SQL query to retrieve the User ID
                                String query = "SELECT UserID FROM User WHERE Name = ?";

                                // Prepare the statement
                                PreparedStatement preparedStatement = conn.prepareStatement(query);
                                preparedStatement.setString(1, loggedInUser);

                                // Execute the query
                                ResultSet resultSet = preparedStatement.executeQuery();

                                // Retrieve the User ID
                                if (resultSet.next()) {
                                    userID = resultSet.getInt("UserID");
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                                // Handle the exception if needed
                            }
                        }

                        return userID;
                    }


                    // Function to get the Theatre ID based on the selected theatre name
                    private int getTheatreID(String theatre) {
                        int theatreID = 0;

                        try {
                            // SQL query to retrieve the Theatre ID based on the theatre name
                            String query = "SELECT TheatreID FROM Theatre WHERE Name = ?";

                            // Prepare the statement
                            PreparedStatement preparedStatement = conn.prepareStatement(query);
                            preparedStatement.setString(1, theatre);

                            // Execute the query
                            ResultSet resultSet = preparedStatement.executeQuery();

                            // Retrieve the Theatre ID
                            if (resultSet.next()) {
                                theatreID = resultSet.getInt("TheatreID");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            // Handle the exception if needed
                        }

                        return theatreID;
                    }

                    // Function to get the Showtime ID based on the selected showtime
                    private int getShowtimeID(String time) {
                        int showtimeID = 0;

                        try {
                            // SQL query to retrieve the Showtime ID based on the showtime
                            String query = "SELECT ShowtimeID FROM Showtime WHERE DateTime = ?";

                            // Prepare the statement
                            PreparedStatement preparedStatement = conn.prepareStatement(query);
                            preparedStatement.setString(1, time);

                            // Execute the query
                            ResultSet resultSet = preparedStatement.executeQuery();

                            // Retrieve the Showtime ID
                            if (resultSet.next()) {
                                showtimeID = resultSet.getInt("ShowtimeID");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            // Handle the exception if needed
                        }

                        return showtimeID;
                    }
                });


          
                


                    

                // Set the layout for the booking frame
                bookingFrame.setLayout(new GridLayout(5, 2));
                
                bookButton.addActionListener(this);
                // Add the booking details components to the booking frame
                bookingFrame.add(movieLabel);
                bookingFrame.add(movieComboBox);
                bookingFrame.add(theatreLabel);
                bookingFrame.add(theatreComboBox);
                bookingFrame.add(timeLabel);
                bookingFrame.add(timeComboBox);
                bookingFrame.add(seatsLabel);
                bookingFrame.add(seatsField);
                bookingFrame.add(new JLabel()); // Empty label for spacing
                bookingFrame.add(bookButton);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Display the booking frame
            bookingFrame.setVisible(true);
        }
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (verifyLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                openBookingFrame();
                dispose(); // Close the login frame
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        }
    
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookMyTicketlogin();
            }
        });
    }
}
