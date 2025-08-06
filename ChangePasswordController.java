package Controller; 

import Database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.animation.PauseTransition; 
import javafx.util.Duration; 
import Model.User;
import org.mindrot.jbcrypt.BCrypt;

public class ChangePasswordController {

    @FXML
    private PasswordField txtCurrentPassword;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private Label lbMessage;

    private Connection con;
    PreparedStatement checkPst,updatePst;
    ResultSet rs;

    @FXML
    public void initialize() {
        try {
            DbConnection dbConnect = new DbConnection();
            con = dbConnect.getConnection();
            if (con == null) {
                lbMessage.setText("Database connection error.");
            }
        } catch (ClassNotFoundException ex) {
            lbMessage.setText("Database connection failed: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        String currentPassword = txtCurrentPassword.getText();
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            lbMessage.setText("Please fill in all fields.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            lbMessage.setText("New password and confirm password do not match.");
            return;
        }

        if (newPassword.length() < 8) { 
            lbMessage.setText("New password must be at least 8 characters long.");
            return;
        }

        int userId = User.getInstance().getUserId();
        System.out.println("ChangePasswordController: User ID retrieved from session: " + userId); 
        
        if (userId == 0) { 
            lbMessage.setText("Error: User session not found. Please re-login.");
            return; 
        }

        String checkSql = "SELECT user_password FROM users WHERE user_id = ?";
        String updateSql = "UPDATE users SET user_password = ? WHERE user_id = ?";

        try {

            checkPst = con.prepareStatement(checkSql);
            updatePst = con.prepareStatement(updateSql);
            
            checkPst.setInt(1, userId);
            rs = checkPst.executeQuery();

            if (rs.next()) {
               String storedPassword = rs.getString("user_password");
                
                //if (storedPassword.equals(currentPassword)) {
                if(BCrypt.checkpw(currentPassword, storedPassword)){
                    //updatePst.setString(1, newPassword);
                    String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
                    updatePst.setString(1, hashedNewPassword);
                    updatePst.setInt(2, userId);
                    int rowsAffected = updatePst.executeUpdate();

                    if (rowsAffected > 0) {
                        lbMessage.setText("Password changed successfully!");
                        lbMessage.setStyle("-fx-text-fill: green;"); 

                      
                        PauseTransition delay = new PauseTransition(Duration.seconds(1)); 
                        delay.setOnFinished(e -> {
                            ((Stage) lbMessage.getScene().getWindow()).close();
                        });
                        delay.play();
                      

                    } else {
                        lbMessage.setText("Failed to change password. No rows affected.");
                        lbMessage.setStyle("-fx-text-fill: red;"); 
                    }
                } else {
                    lbMessage.setText("Incorrect current password.");
                    lbMessage.setStyle("-fx-text-fill: red;"); 
                }
            } else {
                lbMessage.setText("User not found in database for password check (ID: " + userId + ").");
                lbMessage.setStyle("-fx-text-fill: red;"); 
            }

        } catch (SQLException ex) {
            lbMessage.setText("Database error during password change: " + ex.getMessage());
            lbMessage.setStyle("-fx-text-fill: red;"); 
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        ((Stage) txtCurrentPassword.getScene().getWindow()).close();
    }
}