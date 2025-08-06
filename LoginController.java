package Controller;

import Database.DbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import musicboxservice.MusicBoxService;
import Model.User;
import Controller.UserDashboardController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController implements Initializable {

    @FXML private Button btnLoginMinimize,  btnLoginClose;
    @FXML
    private PasswordField txtPwd;
    @FXML
    private RadioButton rdadmin;
    @FXML
    private ToggleGroup rdUserType;
    @FXML
    private TextField txtUsername;
    @FXML
    private RadioButton rdstaff;
    @FXML
    private Button btnlogin;
    @FXML
    private Label lb_warning_username;
    @FXML
    private Label lb_warning_password;
@FXML
    private VBox glassyPane;
    @FXML
    private Text textT;

    @FXML
    private Text textH1;

    @FXML
    private Text textR;

    @FXML
    private Text textH2;

    @FXML
    private Text textX;

    @FXML
    private Text textY;

    @FXML
    private Text textM;
    
    @FXML
    private Text textK1;
    
    @FXML
    private Text textA1;
    
    @FXML
    private Text textR2;
    
    @FXML
    private Text textA2;
    
    @FXML
    private Text textO;
    
    @FXML
    private Text textK2;
    
    @FXML
    private Text textE;
    private Connection con;
    private Parent root;
    private Set<String> cachedUsernames = new HashSet<>();

    public static String username;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DbConnection db = new DbConnection();
        try {
            con = db.getConnection();
            loadAllUsernames();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection error: " + ex.getMessage());
        }

        lb_warning_username.setVisible(false);
        lb_warning_password.setVisible(false);

        txtUsername.textProperty().addListener((obs, oldText, newText) -> {
            String trimmed = newText.trim();
            if (trimmed.isEmpty()) {
                lb_warning_username.setText("*Warning* Please fill username");
                lb_warning_username.setVisible(true);
            } else if (!cachedUsernames.contains(trimmed)) {
                lb_warning_username.setText("*Warning* Username not found");
                lb_warning_username.setVisible(true);
            } else {
                lb_warning_username.setVisible(false);
            }
        });

        txtPwd.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() < 8) {
                lb_warning_password.setText("*Warning* Password must be 8 characters");
                lb_warning_password.setVisible(true);
            } else {
                lb_warning_password.setVisible(false);
            }
        });
        
        Text[] letters = { textR, textH1, textY, textT, textH2, textM, textX  };
        Timeline timeline = new Timeline();
        Duration delay = Duration.seconds(0);

   for (int i = 0; i < letters.length; i++) {
    Text letter = letters[i];

    DropShadow glow = new DropShadow();
    glow.setColor(Color.web("#0BFBE6"));  
    glow.setRadius(5);                 
    glow.setSpread(0.7);                

    KeyFrame on = new KeyFrame(delay, e -> {
        letter.setEffect(glow);
        letter.setFill(Color.web("#B0FFFF"));  
    });

    KeyFrame off = new KeyFrame(delay.add(Duration.seconds(0.4)), e -> {
        letter.setEffect(null);
        letter.setFill(Color.web("#CCCCCC")); 
    });

    timeline.getKeyFrames().addAll(on, off);
    delay = delay.add(Duration.seconds(0.6));
    }

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    
    Text[] letters2 = { textK1, textA1, textR2, textA2, textO, textK2, textE };

    
    Timeline tl = new Timeline();
    Duration time = Duration.seconds(0);

    for (int i = 0; i < letters.length; i++) {
    Text letterr = letters2[i];

    DropShadow glow = new DropShadow();
    glow.setColor(Color.web("#0BFBE6")); 
    glow.setRadius(5);                  
    glow.setSpread(0.7);                

    KeyFrame on = new KeyFrame(time, e -> {
        letterr.setEffect(glow);
        letterr.setFill(Color.web("#B0FFFF"));  
    });

    KeyFrame off = new KeyFrame(time.add(Duration.seconds(0.4)), e -> {
        letterr.setEffect(null);
        letterr.setFill(Color.web("#CCCCCC"));  
    });

    tl.getKeyFrames().addAll(on, off);
    time = time.add(Duration.seconds(0.6));
}

    tl.setCycleCount(Timeline.INDEFINITE);
    tl.play();
    }

    private void loadAllUsernames() throws SQLException {
        String sql = "SELECT user_name FROM users";
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                cachedUsernames.add(rs.getString("user_name"));
            }
        }
    }

   @FXML
private void handleMinimize(ActionEvent event) {
    Stage stage = (Stage) btnLoginMinimize.getScene().getWindow();
    stage.setIconified(true);
}

@FXML
private void handleClose(ActionEvent event) {
    Stage stage = (Stage) btnLoginClose.getScene().getWindow();
    stage.close();
}
    
    public void handleloginaction(ActionEvent event) throws ClassNotFoundException, IOException {
    String uname = txtUsername.getText().trim();
    String pwd = txtPwd.getText();

    if (uname.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill username");
        return;
    } 
    if (pwd.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill password");
        return;
    }
    if (!rdadmin.isSelected() && !rdstaff.isSelected()) {
        JOptionPane.showMessageDialog(null, "Please choose user type");
        return;
    }
    if (!cachedUsernames.contains(uname)) {
        JOptionPane.showMessageDialog(null, "Username not found");
        return;
    }
    if (pwd.length() < 8) {
        JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long");
        return;
    }

    
    String userType = rdstaff.isSelected() ? "desktop_staff" : "admin";
            
    String sql = "SELECT user_id, user_name, user_password, role, status FROM users WHERE user_name = ? AND role = ?";
    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, uname);
        pst.setString(2, userType);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String hashedPwd = rs.getString("user_password");
            String status = rs.getString("status"); 

            if (BCrypt.checkpw(pwd, hashedPwd)) {
                if ("inactive".equalsIgnoreCase(status)) { 
                    JOptionPane.showMessageDialog(null, "Your account is inactive. Please contact the administrator.");
                    return; 
                }

                int userId = rs.getInt("user_id");
                String username = rs.getString("user_name");
                String role = rs.getString("role");

                User.getInstance().setUserId(userId);
                User.getInstance().setUsername(username);
                User.getInstance().setRole(role);

                Stage stage = MusicBoxService.stage;
                Parent root;
                if (role.equals("desktop_staff")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserDashboard.fxml"));
                    root = loader.load();

                    UserDashboardController controller = loader.getController();
                    controller.playOpeningSong();
                } else {
                    root = FXMLLoader.load(getClass().getResource("/View/AdminDashboard.fxml"));
                }

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Password");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Information");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        lb_warning_password.setText("Database error.");
        lb_warning_password.setTextFill(javafx.scene.paint.Color.RED);
        lb_warning_password.setVisible(true);
    }
}
}