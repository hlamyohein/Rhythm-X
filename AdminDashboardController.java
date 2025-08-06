package Controller;
import javafx.util.StringConverter;
import Database.DbConnection;
import Database.MusicDAO;
import Model.Albums;
import Model.Artists;
import Model.Room;
import Model.Room_Details;
import Model.Songs;
import Model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import musicboxservice.MusicBoxService;
import org.mindrot.jbcrypt.BCrypt;

public class AdminDashboardController implements Initializable {

    @FXML private Button btnMinimize,  btnClose;
    @FXML private Button btnArtistList;
    @FXML private Button btnReport;
    @FXML private Button btnLogOut;
    @FXML private Button btnSongLIsts;
    @FXML private Button btnAlbumList;
    @FXML private Button btnRoom;
    @FXML private AnchorPane RoomPane;
    @FXML private AnchorPane songListPane;
    @FXML private AnchorPane artistListPane;
    @FXML private AnchorPane uploadAlbPane;
    @FXML private AnchorPane uploadSongPane;
    @FXML private GridPane artistAddPane;
    @FXML private ImageView ad_img_artist1;
    @FXML private ImageView ad_img_artist2;
    @FXML private ImageView ad_img_artist3;
    @FXML private ImageView ad_img_artist4;
    @FXML private ImageView ad_img_artist5;
    @FXML private ImageView ad_img_artist6;
    @FXML private ImageView ad_img_artist7;
    @FXML private ImageView ad_img_artist8;
    @FXML private ImageView ad_img_artist9;
    @FXML private ImageView ad_img_artist10;
    @FXML private ImageView ad_img_artist11;
    @FXML private ImageView ad_img_artist12;
    @FXML private ImageView imgAlbView;
    @FXML private ImageView imgSongView;
    @FXML private Label ad_lb_artist1;
    @FXML private Label ad_lb_artist2;
    @FXML private Label ad_lb_artist3;
    @FXML private Label ad_lb_artist4;
    @FXML private Label ad_lb_artist5;
    @FXML private Label ad_lb_artist6;
    @FXML private Label ad_lb_artist7;
    @FXML private Label ad_lb_artist8;
    @FXML private Label ad_lb_artist9;
    @FXML private Label ad_lb_artist10;
    @FXML private Label ad_lb_artist11;
    @FXML private Label ad_lb_artist12;
    @FXML private ImageView alb_img_artist1;
    @FXML private Label alb_lb_artist1;
    @FXML private ImageView alb_img_artist2;
    @FXML private Label alb_lb_artist2;
    @FXML private ImageView alb_img_artist3;
    @FXML private Label alb_lb_artist3;
    @FXML private ImageView alb_img_artist4;
    @FXML private Label alb_lb_artist4;
    @FXML private ImageView alb_img_artist5;
    @FXML private Label alb_lb_artist5;
    @FXML private ImageView alb_img_artist6;
    @FXML private Label alb_lb_artist6;
    @FXML private ImageView alb_img_artist7;
    @FXML private Label alb_lb_artist7;
    @FXML private ImageView alb_img_artist8;
    @FXML private Label alb_lb_artist8;
    @FXML private ImageView alb_img_artist9;
    @FXML private Label alb_lb_artist9;
    @FXML private ImageView alb_img_artist10;
    @FXML private Label alb_lb_artist10;
    @FXML private ImageView alb_img_artist11;
    @FXML private Label alb_lb_artist11;
    @FXML private ImageView alb_img_artist12;
    @FXML private Label alb_lb_artist12;
    @FXML private TextField txt_albumsearch;
    @FXML private Label lbAlbartistPagelabel;
    @FXML private Label lbSongPagelabel;
    @FXML private ImageView so_img_artist1;
    @FXML private Label so_lb_artist1;
    @FXML private ImageView so_img_artist2;
    @FXML private Label so_lb_artist2;
    @FXML private ImageView so_img_artist3;
    @FXML private Label so_lb_artist3;
    @FXML private ImageView so_img_artist4;
    @FXML private Label so_lb_artist4;
    @FXML private ImageView so_img_artist5;
    @FXML private Label so_lb_artist5;
    @FXML private ImageView so_img_artist6;
    @FXML private Label so_lb_artist6;
    @FXML private ImageView so_img_artist7;
    @FXML private Label so_lb_artist7;
    @FXML private ImageView so_img_artist8;
    @FXML private Label so_lb_artist8;
    @FXML private ImageView so_img_artist9;
    @FXML private Label so_lb_artist9;
    @FXML private ImageView so_img_artist10;
    @FXML private Label so_lb_artist10;
    @FXML private ImageView so_img_artist11;
    @FXML private Label so_lb_artist11;
    @FXML private ImageView so_img_artist12;
    @FXML private Label so_lb_artist12;
    @FXML private TextField txt_artistsearch;
    @FXML private Label lbartistPagelabel;
    @FXML private Button btnAdd;
    @FXML private AnchorPane uploadArtistPane;
    @FXML private ImageView imgView;
    @FXML private TextField txtArtistName;
    @FXML private ComboBox combo_txtGender;
    @FXML private TextField txtCountry;
    @FXML private TextField txtAlbArtistName;
    @FXML private TextField txtSongName;
    @FXML private TextField txt_songsearch;
    @FXML private TextField txtArtistId;
    @FXML private TextField txtAlbumId;
    @FXML private Button btnUploadSongImg;
    ;
    @FXML private TextField txtSongPath;
    @FXML private TextField txtInstruPath;
    @FXML private Button btnUpload;
    @FXML private Button btnBack;
    @FXML private Button btnSave;
    @FXML private Button btnNextArtist;
    @FXML private Button btnpevArtist;
    @FXML private Button btnRemove;
    @FXML private Button btnSetting;
    @FXML private AnchorPane albumPane;
    @FXML private AnchorPane ReportPane;
    @FXML private Button btnAllbpevArtist;
    @FXML private Button btnAlbNextArtist;
    @FXML private Button btnAlbUpload;
    @FXML private Button btnAlbBack;
    @FXML private Button btnAlbSave;
    @FXML private Button btnAlbRemove;
    @FXML private Button btnSongBack;
    @FXML private Button btnSongRemove;
     @FXML private StackPane[] allArtistPanes;
    @FXML private StackPane pane1;
    @FXML private StackPane pane2;
    @FXML private StackPane pane3;
    @FXML private StackPane pane4;
    @FXML private StackPane pane5;
    @FXML private StackPane pane6;
    @FXML private StackPane pane7;
    @FXML private StackPane pane8;
    @FXML private StackPane pane9;
    @FXML private StackPane pane10;
    @FXML private StackPane pane11;
    @FXML private StackPane pane12;
    @FXML private StackPane[] allSongPanes;
    @FXML private StackPane sopane1;
    @FXML private StackPane sopane2;
    @FXML private StackPane sopane3;
    @FXML private StackPane sopane4;
    @FXML private StackPane sopane5;
    @FXML private StackPane sopane6;
    @FXML private StackPane sopane7;
    @FXML private StackPane sopane8;
    @FXML private StackPane sopane9;
    @FXML private StackPane sopane10;
    @FXML private StackPane sopane11;
    @FXML private StackPane sopane12;
    @FXML private StackPane[] allAlbumPanes;
    @FXML private StackPane albpane1;
    @FXML private StackPane albpane2;
    @FXML private StackPane albpane3;
    @FXML private StackPane albpane4;
    @FXML private StackPane albpane5;
    @FXML private StackPane albpane6;
    @FXML private StackPane albpane7;
    @FXML private StackPane albpane8;
    @FXML private StackPane albpane9;
    @FXML private StackPane albpane10;
    @FXML private StackPane albpane11;
    @FXML private StackPane albpane12;
    @FXML private Button btnStaffAddPane;
    @FXML private AnchorPane staffAddPane;
//    @FXML private TableView<User> staffAddTable;
    @FXML private TableColumn<User, String> colUserName;
    @FXML private TableColumn<User, String> colRole;
    @FXML private TableColumn<User, String> colStatus;
    private ObservableList<User> userList = FXCollections.observableArrayList();
    @FXML private AnchorPane editArtistPane;
    @FXML private TextField edit_txtArtistName;
    @FXML private ComboBox combo_edit_txtGender;
    @FXML private TextField edit_txtCountry;
    @FXML private Button edit_btnUpload;
    @FXML private ImageView edit_imgView;
    @FXML private Button edit_btnBack;
    @FXML private Button edit_btnSave;
     @FXML private Button btnArtistEdit;
    @FXML private Button btnStaffAdd;
    @FXML private Button btnStaffDelete;
    @FXML private AnchorPane uploadStaffPane;
    @FXML private TextField txtUserName;
     @FXML
    private ComboBox<Songs> txtSongCategory;
      @FXML
    private ComboBox<Songs> edit_txtSongCategory;
     @FXML
    private AnchorPane editSongPane;

    @FXML
    private ImageView edit_imgSongView;

    @FXML
    private TextField edit_txtSongName;

    @FXML
    private Button edit_btnUploadSongImg;

    

    @FXML
    private Button edit_btnUploadSongPath;

    @FXML
    private TextField edit_txt_songPath;

    @FXML
    private TextField edit_txt_instuPath;

    @FXML
    private Button edit_btnUploadSongInstuPath;

    @FXML
    private ComboBox<Artists> edit_comboArtist;

    @FXML
    private ComboBox<Albums> edit_comboAlbum;

    @FXML
    private Button edit_btnSongBack;

    @FXML
    private Button edit_btnSongSave;
     @FXML
    private Button btnSongEdit;

      @FXML
    private PasswordField txtUserPsw;

    @FXML
    private ComboBox<User> comboRole;

    @FXML
    private ComboBox<User> comboUserStatus;
    @FXML
    private Button btnStaffEdit;

    
    @FXML private TextField txt_songPath;
    @FXML private TextField txt_instuPath;
    @FXML private Button btnStaffBack;
    @FXML private Button btnStaffSave;
    @FXML private Label lb_warning;
    @FXML private Label edit_lb_warning;
    @FXML private ComboBox<Artists> comboArtist;
    @FXML private ComboBox<Albums> comboAlbum;
     @FXML
    private AnchorPane editAlbPane;
     
     @FXML
    private AnchorPane adminSettingPane;

    @FXML
    private ImageView edit_imgAlbView;

    @FXML
    private TextField txt_editAlbArtistName;

    @FXML
    private Button edit_btnAlbUpload;

    @FXML
    private Button edit_btnAlbBack;

    @FXML
    private Button edit_btnAlbSave;
    @FXML
    private Button btnAlbEdit;
    
     @FXML
    private ComboBox<String> comboCategoryAlbum;
     
    @FXML
    private AnchorPane EditStaffPane;

    @FXML
    private TextField edit_txtUserName;

    @FXML
    private PasswordField edit_txtUserPsw;

    @FXML
    private ComboBox<User> edit_comboRole;

    @FXML
    private Button edit_btnStaffBack;

    @FXML
    private Button edit_btnStaffSave;

    @FXML
    private Button btnRefreshAlbum;

    @FXML
    private ComboBox<String> comboCategoryArtist;

    @FXML
    private Button btnRefreshArtist;
    @FXML
    private ComboBox<String> comboCategorySong;

    @FXML
    private Button btnRefreshSong;

    private ImageView[] allArtistImages;
    private Label[] allArtistLabels;
    private ImageView[] allAlbumImages;
    private Label[] allAlbumLabels;
    private ImageView[] allSongImages;
    private Label[] allSongLabels;
    private List<Artists> allArtists = new ArrayList<>();
    private List<Albums> allAlbums = new ArrayList<>();
    private List<Songs> allSongs = new ArrayList<>();
    private String imgName;
    private String imgAlbName;
    private String imgSongName;
    private int currentArtistPage = 0;
    private int currentAlbumPage = 0;
    private int currentSongPage = 0;
    private final int ARTISTS_PER_PAGE = 12;
    private final int ALBUMS_PER_PAGE = 12;
    private final int SONGS_PER_PAGE = 12;
    private int selectedIndex = -1;
    private int selectedAlbIndex = -1;
    private int selectedSongIndex = -1;
    private String tempUploadedImageFilePath;
    private List<Songs> filteredSongs;        
    private boolean isSongSearchActive = false;   
    private Button currentActiveButtonForAdmin;
        private List<Albums> albumList = new ArrayList<>(); 

    private List<Albums> filteredAlbums;
    private boolean isAlbumSearchActive = false;

    private List<Artists> filteredArtists;
    private boolean isArtistSearchActive = false;
     @FXML
    private Label incomeLabel; 
    @FXML
    private Label selectedMonthLabel;
     @FXML
    private Label vipincomeLabel;
      @FXML
    private Label mediumincomeLabel;
       @FXML
    private Label smallincomeLabel;
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
     @FXML private ComboBox<String> Monthcombox; 
    @FXML private BarChart<String,Number> MonthlyBarChart;
    @FXML
    private LineChart<String, Number> progressChart;

    @FXML
    private TableView<Room_Details> tbRoomDetail;

    @FXML
    private TableColumn<Room_Details, String> col_roomtype;

    @FXML
    private TableColumn<Room_Details, String> col_roomStatus;

    @FXML
    private TableColumn<Room_Details, String> col_customer;

    @FXML
    private TableColumn<Room_Details, String> col_phone;

    @FXML
    private TableColumn<Room, Integer> col_staff;

    @FXML
    private TableColumn<Room_Details, String> col_statTime;

    @FXML
    private TableColumn<Room_Details, String> col_endTime;

    @FXML
    private TableColumn<Room_Details, Integer> col_total;

    @FXML
    private Button btnRoom1;

    @FXML
    private Button btnRoom2;

    @FXML
    private Button btnRoom3;

    @FXML
    private Button btnRoom4;

    @FXML
    private Button btnRoom5;

    @FXML
    private Button btnRoom6;

    @FXML
    private Button btnRoom7;
    

    @FXML
    private Button btnRoom8;

    @FXML
    private Button btnRoom9;

    @FXML
    private Button btnRoom10;

    @FXML
    private Button btnRoom12;

    @FXML
    private Button btnRoom11;
     @FXML
    private AnchorPane bookingacceptPane;
      @FXML
    private TextField txtSearchBooking;

    @FXML
    private Button btnRefeshBooking;


    @FXML
    private Label lb_customer;
    
    @FXML
    private Label lb_adminName;

    @FXML
    private Label lb_phonenumber;

    @FXML
    private Label lb_date;

    @FXML
    private Label lb_startTime;

    @FXML
    private Label lb_endTime;

    @FXML
    private TextField txt_customer;

    @FXML
    private TextField txt_phone;



    @FXML
    private DatePicker txt_date;

    

    @FXML
    private Label lb_status;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private Button btn_roomSave;
    @FXML
    private ComboBox<String> comboStartTime;

    @FXML
    private ComboBox<String> comboEndTime;
   @FXML
    private Button btnDeleteBooking;
    @FXML
    private Button btn_changePassword;
    @FXML
    private Button btn_roomback;
     private ObservableList<Room> roomList = FXCollections.observableArrayList(); // To store all rooms
    private Room selectedRoom; // To store the room selected by the user for booking
    private Button[] roomButtons; // Array to easily manage room buttons

    // ObservableList for the tbRoomDetail TableView
    private ObservableList<Room_Details> roomDetailsList = FXCollections.observableArrayList(); 
    private Timeline searchDelayTimeline;
    
    private int selectedArtistIndex  = -1;
    private Artists selectedArtist;
    private String artistImgName;
private MusicDAO musicDAO ;
private String selectedSongImgPath;
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String IMAGE_DIRECTORY_NAME = "Image_Video";
    private static final String IMAGE_BASE_PATH = Paths.get(PROJECT_ROOT, "src", IMAGE_DIRECTORY_NAME).toString() + File.separator;
    private static final String DEFAULT_SONG_IMAGE_FILENAME = "mb2.jpg";
 @FXML
private TableView<User> userTable; // Replace 'userTable' with the actual fx:id of your staff TableView

// To keep track of the selected user (similar to selectedSongIndex)
private User selectedUser;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            musicDAO=new MusicDAO();
            lb_adminName.setText(Model.User.getInstance().getUsername());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
//        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        userTable.setItems(userList);
        try {
            loadRolesAndStatuses();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadArtistsAndAlbums();
        
        try {
            loadUsersFromDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadUsersFromDatabase();
        }  catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User currentUser = User.getInstance();
         
         if (currentUser != null && "Main Admin".equals(currentUser.getUsername())) {
            // If "Main Admin", enable and show both
            btnStaffAddPane.setDisable(false);
            btnReport.setDisable(false);
            ReportPane.setVisible(true);
//            btnRoom.setVisible(false);
              btnRoom.setDisable(true);
            setActiveButtonForAdmin(btnReport);
            
        } else {
            // If not "Main Admin", disable and hide both
          
            btnRoom.setDisable(false);
            btnStaffAddPane.setDisable(true);
            btnReport.setDisable(true);
            staffAddPane.setVisible(false);
            ReportPane.setVisible(false);
            RoomPane.setVisible(true);
            setActiveButtonForAdmin(btnRoom);
        }
        
        populateGender();
        //for artist images and labels
        allArtistImages = new ImageView[]{
            ad_img_artist1, ad_img_artist2, ad_img_artist3, ad_img_artist4,
            ad_img_artist5, ad_img_artist6, ad_img_artist7, ad_img_artist8,
            ad_img_artist9, ad_img_artist10, ad_img_artist11, ad_img_artist12
        };

        allArtistLabels = new Label[]{
            ad_lb_artist1, ad_lb_artist2, ad_lb_artist3, ad_lb_artist4,
            ad_lb_artist5, ad_lb_artist6, ad_lb_artist7, ad_lb_artist8,
            ad_lb_artist9, ad_lb_artist10, ad_lb_artist11, ad_lb_artist12
        };
        
        allArtistPanes = new StackPane[]{
            pane1, pane2, pane3, pane4,
            pane5, pane6, pane7, pane8,
            pane9, pane10, pane11, pane12
        };
        //for album images and labels
        
        allAlbumImages = new ImageView[]{
            alb_img_artist1, alb_img_artist2, alb_img_artist3, alb_img_artist4,
            alb_img_artist5, alb_img_artist6, alb_img_artist7, alb_img_artist8,
            alb_img_artist9, alb_img_artist10, alb_img_artist11, alb_img_artist12
        };
        allAlbumLabels = new Label[]{
            alb_lb_artist1, alb_lb_artist2, alb_lb_artist3, alb_lb_artist4,
            alb_lb_artist5, alb_lb_artist6, alb_lb_artist7, alb_lb_artist8,
            alb_lb_artist9, alb_lb_artist10, alb_lb_artist11, alb_lb_artist12
        };
        allAlbumPanes = new StackPane[]{
            albpane1, albpane2, albpane3, albpane4,
            albpane5, albpane6, albpane7, albpane8,
            albpane9, albpane10, albpane11, albpane12
        };
       
        
        //for song images and labels
         allSongImages = new ImageView[]{
            so_img_artist1, so_img_artist2, so_img_artist3, so_img_artist4,
            so_img_artist5, so_img_artist6, so_img_artist7, so_img_artist8,
            so_img_artist9, so_img_artist10, so_img_artist11, so_img_artist12
        };
        allSongLabels = new Label[]{
            so_lb_artist1, so_lb_artist2, so_lb_artist3, so_lb_artist4,
            so_lb_artist5, so_lb_artist6, so_lb_artist7, so_lb_artist8,
            so_lb_artist9, so_lb_artist10, so_lb_artist11, so_lb_artist12
        };
        allSongPanes = new StackPane[]{
            sopane1, sopane2, sopane3, sopane4,
            sopane5, sopane6, sopane7, sopane8,
            sopane9, sopane10, sopane11, sopane12
        };
         populateSongCategoriesForAdd();
   
   populateSongCategoriesForEdit(); 
        //for selecting artist
       int start = currentArtistPage*ARTISTS_PER_PAGE;
        
        for (int i = 0; i < ARTISTS_PER_PAGE; i++) {
            final int index = start+i;
            int localSlot = i;
            allArtistPanes[i].setOnMouseClicked(event->{
               selectedIndex = index;
                highlightSelected(localSlot);
            });  
        }
        
        loadArtistsFromDB();
        updateArtistPane(allArtists);
        showArtistPage(currentArtistPage);
       
        //user table
        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        selectedUser = newSelection;
    } else {
        selectedUser = null; // No user selected
    }
});
        
        //for selecting album
        int albstart = currentAlbumPage*ALBUMS_PER_PAGE;
        for (int i = 0; i < ALBUMS_PER_PAGE; i++) {
            final int albindex = albstart+i;
            int localalbSlot = i;
            allAlbumPanes[i].setOnMouseClicked(event->{
               selectedAlbIndex = albindex;
                highlightAlbSelected(localalbSlot);
            });   
        }
   
        loadAlbumsFromDB();
        updateAlbumsPane(allAlbums);
        showAlbumPage(currentAlbumPage);
        
        //for selecting song
        int sostart = currentSongPage*SONGS_PER_PAGE;
        for (int i = 0; i < SONGS_PER_PAGE; i++) {
            final int songindex = sostart+i;
            int localSongSlot = i;
            allSongPanes[i].setOnMouseClicked(event->{
               selectedSongIndex = songindex;
                highlightSongSelected(localSongSlot);
            });
        }
        
        loadSongsFromDB();
        updateSongPane(allSongs);
        showSongPage(currentSongPage);
       
        txt_artistsearch.textProperty().addListener((obs, oldValue, newValue) -> {
            String keyword = newValue.trim();
            try {
                searchArtists(keyword);
            } /**/ catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         populateAlbumCategories();
       comboCategoryAlbum.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            filterAlbumsByCategory(newValue); // Call the filtering method
        }
    });

    // Optionally: Select "All Categories" by default when the application starts
    comboCategoryAlbum.getSelectionModel().select("All Categories");
    
    populateArtistCategories(); // Populate artist categories
        comboCategoryArtist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterArtistsByCategory(newValue); // Filter artists by selected category
            }
        });
        comboCategoryArtist.getSelectionModel().select("All Categories"); // Select "All Categories" by default
  
    populateSongCategories();
    
    comboCategorySong.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterSongsByCategory(newValue); // Filter artists by selected category
            }
        });
        comboCategoryArtist.getSelectionModel().select("All Categories"); // Select "All Categories" by default
   
      
        txt_albumsearch.textProperty().addListener((obs, oldValue, newValue)->{
            String keyword = newValue.trim();
            try{
                searchAlbums(keyword);
            } /**/ catch (ClassNotFoundException ex){
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE,null,ex);
            }
        });
        
       txt_songsearch.textProperty().addListener((obs, oldValue, newValue)->{
            String keyword = newValue.trim();
            try{
                searchSongs(keyword);
            } /**/ catch (ClassNotFoundException ex){
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }); 
         if (txt_date != null) { // Defensive check to ensure DatePicker is initialized
            txt_date.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();

                    // Disable dates before today
                    if (date.isBefore(today)) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;"); // Light pink for disabled dates
                    }
                }
            });
        } else {
            System.err.println("Error: txt_date DatePicker is not initialized. Check FXML fx:id.");
        }
       
       
       try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/musicboxservice", "root", "");
        } catch (SQLException ex) {
            //System.getLogger(AdminDashboardController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
         ObservableList<String> months = FXCollections.observableArrayList(
           "01 - January", "02 - February", "03 - March", "04 - April",
            "05 - May", "06 - June", "07 - July", "08 - August",
            "09 - September", "10 - October", "11 - November", "12 - December"
        );
        Monthcombox.setItems(months);
        // Get the current month and format it to match your list ("07 - July")
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM - MMMM");
    String currentMonthString = currentDate.format(formatter);

    // Set the current month as the default selected value
    Monthcombox.setValue(currentMonthString);

    // Initial update for the current month when the screen loads
        updateUIForMonth(currentMonthString);


int monthNumber = Integer.parseInt(currentMonthString.substring(0, 2));
    int year = LocalDate.now().getYear();
    int previousMonth = (monthNumber == 1) ? 12 : monthNumber - 1;
    int previousYear = (monthNumber == 1) ? year - 1 : year;
    updateLineChart(monthNumber, previousMonth, year);

    // Listener for month selection
    Monthcombox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
        if (newValue != null) {
            updateUIForMonth(newValue);

            int selectedMonth = Integer.parseInt(newValue.substring(0, 2));
            int selectedYear = LocalDate.now().getYear();
            int prevMonth = (selectedMonth == 1) ? 12 : selectedMonth - 1;
            int prevYear = (selectedMonth == 1) ? selectedYear - 1 : selectedYear;
            updateLineChart(selectedMonth, prevMonth, selectedYear);
        }
    });
        
        roomButtons = new Button[]{
            btnRoom1, btnRoom2, btnRoom3, btnRoom4, btnRoom5, btnRoom6,
            btnRoom7, btnRoom8, btnRoom9, btnRoom10, btnRoom11, btnRoom12
        };
        for (Button btn : roomButtons) {
        btn.setPrefWidth(50); 
        btn.setPrefHeight(50); 
    
        btn.setMaxWidth(50);
        btn.setMaxHeight(50);
}
        
        ObservableList<String> timeOptions = FXCollections.observableArrayList();
    LocalTime time = LocalTime.of(0, 0); // Start from 00:00
    while (time.isBefore(LocalTime.of(23, 59))) { // Go up to 23:59
        timeOptions.add(time.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))); 
        time = time.plusMinutes(30); 
        if (time.getHour() == 0 && time.getMinute() == 0 && !timeOptions.isEmpty()) { 
            break; 
        }
    }
    timeOptions.clear(); 
    for (int hour = 9; hour < 22; hour++) {
        for (int minute = 0; minute < 60; minute += 30) {
            timeOptions.add(String.format("%02d:%02d", hour, minute));
        }
    }
    comboStartTime.setItems(timeOptions);
    comboEndTime.setItems(timeOptions);

   
    if (comboStartTime.getPromptText() == null || comboStartTime.getPromptText().isEmpty()) {
        comboStartTime.setPromptText("Select Start Time");
    }
    if (comboEndTime.getPromptText() == null || comboEndTime.getPromptText().isEmpty()) {
        comboEndTime.setPromptText("Select End Time");
    }
    

        
        bookingacceptPane.setVisible(false);
        try {
          
            loadRoomsAndSetupButtons();
            
            // Populate status ComboBox
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
          comboStatus.setItems(FXCollections.observableArrayList("Booked", "Using"));


        // Set up TableView for room details
        col_roomtype.setCellValueFactory(new PropertyValueFactory<>("roomType")); 
        col_roomStatus.setCellValueFactory(new PropertyValueFactory<>("status")); 
        col_customer.setCellValueFactory(new PropertyValueFactory<>("customerName")); 
        col_phone.setCellValueFactory(new PropertyValueFactory<>("customerPhonenumber")); 
        col_staff.setCellValueFactory(new PropertyValueFactory<>("userId")); 
        col_statTime.setCellValueFactory(new PropertyValueFactory<>("startTime")); 
        col_endTime.setCellValueFactory(new PropertyValueFactory<>("endTime")); 
        col_total.setCellValueFactory(new PropertyValueFactory<>("total")); 
        tbRoomDetail.setItems(roomDetailsList);
       searchDelayTimeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.3), e -> {
            performSearchBooking();
        }));
        searchDelayTimeline.setCycleCount(1);

        txtSearchBooking.textProperty().addListener((observable, oldValue, newValue) -> {
            searchDelayTimeline.stop();
            searchDelayTimeline.play();
        });

    
        try {
            // Load existing room details into the TableView
            loadRoomDetailsFromDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
                   }
    
    //FOR ARTIST  //FOR ARTIST  //FOR ARTIST  //FOR ARTIST   //FOR ARTIST  //FOR ARTIST   //FOR ARTIST  //FOR ARTIST  


    //for color highlight mouse 
    private void highlightSelected(int localIndex) {
    for (int i = 0; i < allArtistImages.length; i++) {
        allArtistImages[i].setStyle("");
    }
    if (localIndex >= 0 && localIndex < allArtistImages.length) {
        allArtistImages[localIndex].setStyle("-fx-effect: dropshadow(gaussian, red, 10, 0, 0, 0);");
    }
}

    private void searchArtists(String keyword) throws ClassNotFoundException {
        List<Artists> filtered;

    if (keyword.isEmpty()) {
        isArtistSearchActive = false;
        filtered = allArtists;
    } else {
        isArtistSearchActive = true;
        filtered = allArtists.stream()
            .filter(artist -> artist.getArtistName().toLowerCase().contains(keyword.toLowerCase()))
            .toList();

        if (filtered.isEmpty()) {
            filtered = searchArtistsFromDB(keyword);  // Optional fallback method
        }
    }

    filteredArtists = filtered;
    currentArtistPage = 0;
    showArtistPage(currentArtistPage);
    }

    
   private void updateArtistPane(List<Artists> fullArtistList) {
        int start = currentArtistPage * ARTISTS_PER_PAGE;
        int end = Math.min(start + ARTISTS_PER_PAGE, fullArtistList.size());
        List<Artists> pageArtists = fullArtistList.subList(start, end);

        for (int i = 0; i < ARTISTS_PER_PAGE; i++) {
            if (i < pageArtists.size()) {
                Artists artist = pageArtists.get(i);
               
                String artistImageFileName = artist.getArtistImg(); 

                if (artistImageFileName != null && !artistImageFileName.isEmpty()) { 
                    // Construct the path relative to the project root for loading
                    File imageFile = new File(artistImageFileName);

                    if (imageFile.exists() && !imageFile.isDirectory()) {
                        try {
                            Image image = new Image(imageFile.toURI().toString());
                            allArtistImages[i].setImage(image);
                        } catch (Exception e) {
                            System.err.println("Error loading image for artist: " + artist.getArtistName() + " - " + imageFile.getAbsolutePath() + " - " + e.getMessage());
                            allArtistImages[i].setImage(null); 
                        }
                    } else {
                        System.err.println("Artist image file not found or is a directory: " + imageFile.getAbsolutePath());
                        allArtistImages[i].setImage(null); 
                    }
                } else {
                    System.err.println("Artist image filename is null or empty for artist: " + artist.getArtistName());
                    allArtistImages[i].setImage(null); 
                }
                
                allArtistLabels[i].setText(artist.getArtistName());
                allArtistPanes[i].setVisible(true);

                final int globalIndex = start + i;
                final int localIndex = i;
    allArtistPanes[i].setOnMouseClicked(e -> {
    System.out.println("Artist pane clicked: " + globalIndex);
    // CHANGE THIS LINE:
    selectedArtistIndex = globalIndex; // 
    if (fullArtistList != null && globalIndex >= 0 && globalIndex < fullArtistList.size()) {
       
        selectedArtist = fullArtistList.get(globalIndex);
    } else {
        System.err.println("Error: Cannot set selectedArtist. globalIndex " + globalIndex + " out of bounds or fullArtistList is null.");
        selectedArtist = null;
    }
    highlightSelected(localIndex);
});

            } else {
                allArtistImages[i].setImage(null);
                allArtistLabels[i].setText("");
                allArtistPanes[i].setVisible(false);
            }
        }
    }

    private List<Artists> searchArtistsFromDB(String keyword) throws ClassNotFoundException {
        List<Artists> artistList = new ArrayList<>();
        String query = "SELECT * FROM artists WHERE artist_name LIKE ?";

        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("artist_id");
                String name = rs.getString("artist_name");
                String country = rs.getString("country");
                String gender = rs.getString("gender");
                String img = rs.getString("artist_img");

                artistList.add(new Artists(id, name, country, gender, img));
            }
        } /**/ catch (SQLException e) {
            e.printStackTrace();
        }
        return artistList;
    }

    private void showArtistPage(int pageIndex) {
     List<Artists> sourceList = isArtistSearchActive ? filteredArtists : allArtists;
    int totalPages = (int) Math.ceil((double) sourceList.size() / ARTISTS_PER_PAGE);

    updateArtistPane(sourceList);
    
    lbartistPagelabel.setText("Page " + (pageIndex + 1) + " of " + totalPages);
    }

    private void loadArtistsFromDB() {
        
        allArtists.clear();
        try (Connection con = new DbConnection().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM artists")) {

            allArtists.clear();
            while (rs.next()) {
                Artists artist = new Artists(
                    rs.getInt("artist_id"),
                    rs.getString("artist_name"),
                    rs.getString("gender"),
                    rs.getString("country"),
                    rs.getString("artist_img"));
                allArtists.add(artist);
            }

            showArtistPage(currentArtistPage);

        } /**/ catch (Exception e) {
            e.printStackTrace();
        }
        showArtistPage(currentArtistPage);
    }

    
    @FXML
    private void handleArtistAction(ActionEvent event) {
        setVisiblePane(artistListPane);
        showArtistPage(currentArtistPage); 
        txt_artistsearch.setText("");
        setActiveButtonForAdmin(btnArtistList);
    }

    @FXML
    private void handleReportAction(ActionEvent event) {
        setVisiblePane(ReportPane);
        setActiveButtonForAdmin(btnReport);
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {
        Stage stage = MusicBoxService.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleSongLIstAction(ActionEvent event) {
        setVisiblePane(songListPane);
        showSongPage(currentSongPage); 
        txt_songsearch.setText("");
        setActiveButtonForAdmin(btnSongLIsts);
        
    }

    @FXML
    private void handleAlbumList(ActionEvent event) {
       // albumPane.setVisible(true);
       setVisiblePane(albumPane); 
        showAlbumPage(currentAlbumPage); 
        txt_albumsearch.setText("");
        setActiveButtonForAdmin(btnAlbumList);
    }
    

    @FXML
    private void handleRoomAction(ActionEvent event) {
        setVisiblePane(RoomPane);
        setActiveButtonForAdmin(btnRoom);
    }

    @FXML
    private void handleArtistSearchAction(ActionEvent event) throws ClassNotFoundException {
         searchArtists(txt_artistsearch.getText());
    }

    //artist 
    @FXML
    void handleAddAction(ActionEvent event) {
        uploadArtistPane.setVisible(true);
        txtArtistName.clear();    
        combo_txtGender.setValue(null);    
        txtCountry.clear();         
        imgView.setImage(null); 
        
        String defaultImagePathInResources = "/Image_Video/psy.jpg";
        URL imageUrl = getClass().getResource(defaultImagePathInResources);

        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm());
            imgView.setImage(image);
            imgView.setPreserveRatio(true);
            System.out.println("DEBUG: Default image loaded successfully from: " + imageUrl);
        } else {
            System.err.println("ERROR: Default image file NOT found in resources at: " + defaultImagePathInResources);
            imgView.setImage(null);
            
        }
        
    }

    //artist upload
    @FXML
    private void handleUploadAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif",".*wepb")
        );
        File file = fileChooser.showOpenDialog(MusicBoxService.stage);
        if (file != null) {
            File destDir = new File("src/Image_Video/");
            if (!destDir.exists()) destDir.mkdirs();

            File destFile = new File(destDir, file.getName());
            Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            imgName = file.getName();
            Image image = new Image(destFile.toURI().toString());
            imgView.setImage(image);
            imgView.setPreserveRatio(true);
        }
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
        setVisiblePane(artistListPane);
        txtArtistName.clear();
        combo_txtGender.setValue(null); 
        txtCountry.clear();
        imgView.setImage(null);
      
    }

    //artist save action
   @FXML
private void handelSaveAction(ActionEvent event) {
    String name = txtArtistName.getText();
    String gender = combo_txtGender.getValue().toString();
    String country = txtCountry.getText();
    String imagePathToSave; 
    
   
    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out artist name!");
        return;
    } else if (gender.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out gender!");
        return;
    } else if (country.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out country!");
        return;
    }

    if (imgName == null || imgName.isEmpty()) {
        imagePathToSave = "src\\Image_Video\\psy.jpg"; // Default path for DB
    } else {
        imagePathToSave = "src\\Image_Video\\" + imgName; // Path for DB, assuming imgName is just the filename
    }

    try (Connection con = new DbConnection().getConnection()) {
        String sql = "INSERT INTO artists (artist_name, gender, country, artist_img) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, name);
        pst.setString(2, gender);
        pst.setString(3, country);
        pst.setString(4, imagePathToSave); // Use the determined imagePathToSave for DB
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Artist added successfully");

       
        txtArtistName.clear();
          combo_txtGender.setValue(null);  
        txtCountry.clear();
        imgView.setImage(null);
        uploadArtistPane.setVisible(false);

       
        currentArtistPage = 0;
        loadArtistsFromDB();
        showArtistPage(currentArtistPage);
        loadArtistsAndAlbums();
      

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error adding artist: " + e.getMessage()); // Provide better error feedback
    }
}
@FXML
private void handleUploadVocalAction(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose MP4 Vocal File");
  
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("MP4 Files", "*.mp4"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
    );
    File file = fileChooser.showOpenDialog(MusicBoxService.stage);

    if (file != null) {
        try {
            
            File destDir = new File("src/Image_Video/");
            
         
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            File destFile = new File(destDir, file.getName());
            Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        
            String vocalPath = destFile.getAbsolutePath();
            txtSongPath.setText(vocalPath); 

            JOptionPane.showMessageDialog(null, "MP4 file uploaded successfully to: " + vocalPath);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to upload MP4 file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
    @FXML
    private void handleNextArtistAction(ActionEvent event) {
         List<Artists> source = isArtistSearchActive ? filteredArtists : allArtists;
    int totalPages = (int) Math.ceil((double) source.size() / ARTISTS_PER_PAGE);
     
    if (currentArtistPage < totalPages - 1) {
        currentArtistPage++;
        showArtistPage(currentArtistPage);
        

        
    }
    }

    @FXML
    private void handlePevArtistAction(ActionEvent event) {
        if (currentArtistPage > 0) {
            currentArtistPage--;
            showArtistPage(currentArtistPage);
        }
        
    }
     

    //for artists removal
@FXML
void handleRemoveAction(ActionEvent event) throws ClassNotFoundException {
   
    List<Artists> currentSourceList = isArtistSearchActive ? filteredArtists : allArtists;
    if (selectedArtistIndex >= 0 && selectedArtistIndex < currentSourceList.size()) {
   
        Artists artistToDelete = currentSourceList.get(selectedArtistIndex);
        String artistName = artistToDelete.getArtistName();
        int artistId = artistToDelete.getArtistId();

        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete \"" + artistName + "\" and all its associated songs and albums?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection con = new DbConnection().getConnection()) {

            
                Set<Integer> albumIdsAffected = new HashSet<>();
                String selectSongsSql = "SELECT song_img, file_path, instrumental_file_path, album_id FROM songs WHERE artist_id = ?";
                try (PreparedStatement songPst = con.prepareStatement(selectSongsSql)) {
                    songPst.setInt(1, artistId);
                    ResultSet rsSongs = songPst.executeQuery();
                    while (rsSongs.next()) {
                        String songImagePath = rsSongs.getString("song_img");
                        String songFilePath = rsSongs.getString("file_path");
                        String instrumentalFilePath = rsSongs.getString("instrumental_file_path");

                        int albumId = rsSongs.getInt("album_id");
                        if (!rsSongs.wasNull()) {
                            albumIdsAffected.add(albumId);
                        }

                        String defaultSongImagePath = "src\\Image_Video\\mb2.jpg"; 
                        deleteFile(songImagePath, defaultSongImagePath);
                        deleteFile(songFilePath, null); 

                        if (instrumentalFilePath != null && !instrumentalFilePath.isEmpty()) {
                            deleteFile(instrumentalFilePath, null); 
                        }
                    }
                }

              
                String artistImagePath = artistToDelete.getArtistImg();
                String defaultArtistImagePath = "src\\Image_Video\\psy.jpg";
                deleteFile(artistImagePath, defaultArtistImagePath);
                deleteArtistFromDatabase(artistId);

                allArtists.removeIf(artist -> artist.getArtistId() == artistId);
                if (isArtistSearchActive) {
                    filteredArtists.removeIf(artist -> artist.getArtistId() == artistId);
                }
                for (Integer albumId : albumIdsAffected) {
                    String checkAlbumSongsSql = "SELECT COUNT(*) FROM songs WHERE album_id = ?";
                    try (PreparedStatement checkPst = con.prepareStatement(checkAlbumSongsSql)) {
                        checkPst.setInt(1, albumId);
                        ResultSet countRs = checkPst.executeQuery();
                        if (countRs.next() && countRs.getInt(1) == 0) {
                
                            String selectAlbumImgSql = "SELECT album_img FROM albums WHERE album_id = ?";
                            try (PreparedStatement selectAlbumImgPst = con.prepareStatement(selectAlbumImgSql)) {
                                selectAlbumImgPst.setInt(1, albumId);
                                ResultSet albumImgRs = selectAlbumImgPst.executeQuery();
                                if (albumImgRs.next()) {
                                    String albumImagePath = albumImgRs.getString("album_img");
                                    String defaultAlbumImagePath = "src\\Image_Video\\mb2.jpg"; 
                                    deleteFile(albumImagePath, defaultAlbumImagePath);
                                }
                            }

                            String deleteAlbumSql = "DELETE FROM albums WHERE album_id = ?";
                            try (PreparedStatement deleteAlbumPst = con.prepareStatement(deleteAlbumSql)) {
                                deleteAlbumPst.setInt(1, albumId);
                                deleteAlbumPst.executeUpdate();
                                System.out.println("Album deleted as it became empty: ID " + albumId);
                            }

    
                            allAlbums.removeIf(album -> album.getAlbumId() == albumId);
                            if (isAlbumSearchActive) { 
                                filteredAlbums.removeIf(album -> album.getAlbumId() == albumId);
                            }
                          
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Artist and all associated files/data deleted successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting artist or associated files: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

         
            loadSongsFromDB();
            loadAlbumsFromDB(); 

      
            if (currentArtistPage >= getTotalPages()) { 
                currentArtistPage = Math.max(0, getTotalPages() - 1);
            }

          
            selectedArtistIndex = -1; 
            refreshArtistUI(); 

         
            refreshAlbumUI(); 
        
        }

    } else {
        JOptionPane.showMessageDialog(null, "Please select an artist that you want to delete.");
    }
}
    
    private void refreshArtistUI() {
      updateArtistPane(allArtists);
      showArtistPage(currentArtistPage);
      highlightSelected(-1);
    }
 private void deleteFile(String filePath, String defaultPath) {
        if (filePath != null && !filePath.isEmpty()) {
            File fileToDelete = new File(filePath);
            if (fileToDelete.exists()) {
                if (defaultPath == null || !filePath.equals(defaultPath)) {
                    if (fileToDelete.delete()) {
                        System.out.println("File deleted successfully: " + filePath);
                    } else {
                        System.err.println("Failed to delete file: " + filePath);
                    }
                } else {
                    System.out.println("Skipping deletion: File uses default path: " + filePath);
                }
            } else {
                System.out.println("File not found, no deletion needed: " + filePath);
            }
        }
    }


    private int getTotalPages() {
       return (int) Math.ceil((double) allArtists.size() / ARTISTS_PER_PAGE);
    }

    private void deleteArtistFromDatabase(int artistId) throws ClassNotFoundException {
      
        try (Connection con = new DbConnection().getConnection()) {
            String sql = "DELETE FROM artists WHERE artist_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, artistId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
    }

    private void setVisiblePane(AnchorPane visiblePane) {
        RoomPane.setVisible(false);
        songListPane.setVisible(false);
        albumPane.setVisible(false);
        ReportPane.setVisible(false);
        artistListPane.setVisible(false);
        uploadArtistPane.setVisible(false);
        uploadAlbPane.setVisible(false);
        staffAddPane.setVisible(false);
        adminSettingPane.setVisible(false);
        visiblePane.setVisible(true);
    }
   
     private void populateGender() {
        ObservableList<String> genders = FXCollections.observableArrayList(
            "Male",
            "Female",
            "Other"
        );
        
       if (combo_txtGender != null) {
        combo_txtGender.setItems(genders);
//      combo_txtGender.getSelectionModel().selectFirst();
        combo_txtGender.setPromptText("Choose Gender"); 
    }
    
     if (combo_edit_txtGender != null) {
        combo_edit_txtGender.setItems(genders);
//      combo_txtGender.getSelectionModel().selectFirst();
        combo_txtGender.setPromptText("Choose Gender"); 
    }

   
}  
     private void populateSongCategories() {
        ObservableList<String> categories = FXCollections.observableArrayList(
            "All Categories",
            "Myanmar",
            "English",
            "Korea",
            "Japan"
        );
       if (comboCategorySong != null) {
        comboCategorySong.setItems(categories);
        comboCategorySong.getSelectionModel().selectFirst();
    }

   
}
    private void populateSongCategoriesForAdd() {
    ObservableList<String> stringCategories = FXCollections.observableArrayList();

    try {
        Set<String> distinctDbCategories = musicDAO.getDistinctSongCategories();
        stringCategories.addAll(distinctDbCategories);
    } catch (SQLException e) {
        Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, "Error populating song categories from DB", e);
        System.err.println("Error populating song categories from DB: " + e.getMessage());
    }

    if (txtSongCategory != null) {
        ObservableList<Songs> songObjectsForCategories = FXCollections.observableArrayList();

        for (String categoryName : stringCategories) {
            songObjectsForCategories.add(new Songs(categoryName)); 
        }

        txtSongCategory.setItems(songObjectsForCategories);
        txtSongCategory.getSelectionModel().selectFirst(); 
    } else {
        System.err.println("ERROR: txtSongCategory is NULL! Cannot populate.");
    }
}
       private void populateSongCategoriesForEdit() {
    ObservableList<String> stringCategories = FXCollections.observableArrayList();

    try {
        Set<String> distinctDbCategories = musicDAO.getDistinctSongCategories();
        stringCategories.addAll(distinctDbCategories);
    } catch (SQLException e) {
        Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, "Error populating song categories from DB", e);
        System.err.println("Error populating song categories from DB: " + e.getMessage());
    }

    if (edit_txtSongCategory != null) {
        ObservableList<Songs> songObjectsForCategories = FXCollections.observableArrayList();

        for (String categoryName : stringCategories) {
            songObjectsForCategories.add(new Songs(categoryName)); 
        }

        edit_txtSongCategory.setItems(songObjectsForCategories);
        edit_txtSongCategory.getSelectionModel().selectFirst(); 
    } else {
        System.err.println("ERROR: edit_txtSongCategory is NULL! Cannot populate.");
    }
}

   private void filterSongsByCategory(String category) {
        List<Songs> filtered;
        if (category == null || "All Categories".equals(category)) {
           
            filtered = new ArrayList<>(allSongs);
            isSongSearchActive = false; 
        } else {
        
            filtered = allSongs.stream()
                               .filter(song -> song.getCategory() != null && song.getCategory().equalsIgnoreCase(category))
                               .collect(Collectors.toList());
            isSongSearchActive = true;
        }

        filteredSongs = filtered; 
        currentSongPage = 0;
        showSongPage(currentSongPage); 
    }
    
    
    //FOR ALBUMS  //FOR ALBUMS  //FOR ALBUMS   //FOR ALBUMS   //FOR ALBUMS  //FOR ALBUMS   //FOR ALBUMS  //FOR ALBUMS
    private void populateAlbumCategories() {
        ObservableList<String> categories = FXCollections.observableArrayList(
            "All Categories",
            "Myanmar",
            "English",
            "Korea",
            "Japan"
        );
        comboCategoryAlbum.setItems(categories);
        comboCategoryAlbum.getSelectionModel().selectFirst(); 
    }
   
private void filterAlbumsByCategory(String category) {
    if (category == null || "All Categories".equals(category)) {
      
        filteredAlbums = new ArrayList<>(allAlbums);
        isAlbumSearchActive = false; 
    } else {
       
        filteredAlbums = allAlbums.stream()
                              
                                .filter(album -> album.getCategory() != null && album.getCategory().contains(category))
                                .collect(Collectors.toList());
        isAlbumSearchActive = true; 
    }

    currentAlbumPage = 0; 
    showAlbumPage(currentAlbumPage); 
}

  //
   private void populateArtistCategories() {
        ObservableList<String> categories = FXCollections.observableArrayList(
            "All Categories", // Option to view all artists
            "Myanmar",
            "English",
            "Korea",
            "Japan"
        );
        comboCategoryArtist.setItems(categories);
        comboCategoryArtist.getSelectionModel().selectFirst(); // Select "All Categories" by default
    }

    //filtering artists by category
    private void filterArtistsByCategory(String category) {
        List<Artists> filtered;
        if ("All Categories".equals(category)) {
            filtered = allArtists; // No filtering, show all artists
        } else {
           
            filtered = new ArrayList<>();
            try (Connection con = new DbConnection().getConnection()) {
              
                if (con == null) {
                    System.err.println("Database connection is null in filterArtistsByCategory.");
                 
                    filtered = allArtists;
                } else {
                    String sql = "SELECT DISTINCT a.* FROM artists a JOIN songs s ON a.artist_id = s.artist_id WHERE s.category = ?";
                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, category);
                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {
                            Artists artist = new Artists(
                                rs.getInt("artist_id"),
                                rs.getString("artist_name"),
                                rs.getString("gender"),
                                rs.getString("country"),
                                rs.getString("artist_img"));
                            filtered.add(artist);
                        }
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("Error filtering artists by song category: " + e.getMessage());
             
                filtered = allArtists; 
            }
        }
        isArtistSearchActive = true;
        filteredArtists = filtered;
        currentArtistPage = 0;
        showArtistPage(currentArtistPage);
    }
        
    
    private void searchAlbums(String keyword) throws ClassNotFoundException {
        List<Albums> filtered;

    if (keyword.isEmpty()) {
        isAlbumSearchActive = false;
        filtered = allAlbums;
    } else {
        isAlbumSearchActive = true;
        filtered = allAlbums.stream()
            .filter(album -> album.getAlbumName().toLowerCase().contains(keyword.toLowerCase()))
            .toList();

        if (filtered.isEmpty()) {
            filtered = searchAlbumsFromDB(keyword);  
        }
    }

    filteredAlbums = filtered;
    currentAlbumPage = 0;
    showAlbumPage(currentAlbumPage);

    }
    
     private List<Albums> searchAlbumsFromDB(String keyword) throws ClassNotFoundException {
        List<Albums> albumList = new ArrayList<>();
        String query = "SELECT * FROM albums WHERE album_name LIKE ?";

        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("album_id");
                String name = rs.getString("album_name");
                String img = rs.getString("album_img");
                albumList.add(new Albums(id, name, img));
            }

        } /**/ catch (SQLException e) {
            e.printStackTrace();
        }

        return albumList;
    }
     
    private void updateAlbumsPane(List<Albums> fullAlbumList) {
    int albstart = currentAlbumPage * ALBUMS_PER_PAGE;
    int albend = Math.min(albstart + ALBUMS_PER_PAGE, fullAlbumList.size());
    List<Albums> pageAlbums = fullAlbumList.subList(albstart, albend);

    for (int i = 0; i < ALBUMS_PER_PAGE; i++) {
        if (i < pageAlbums.size()) {
            Albums album = pageAlbums.get(i);
           
            String fileName = album.getAlbumImg(); 

            if (fileName != null && !fileName.isEmpty()) { 
              
                File imageFile = new File(fileName); 

                if (imageFile.exists() && !imageFile.isDirectory()) {
                    try {
                        Image image = new Image(imageFile.toURI().toString());
                        allAlbumImages[i].setImage(image);
                    } catch (Exception e) {
                        System.err.println("Error loading image from file system: " + imageFile.getAbsolutePath() + " - " + e.getMessage());
                        allAlbumImages[i].setImage(null); 
                    }
                } else {
                    System.err.println("Image file not found or is a directory: " + imageFile.getAbsolutePath());
                    allAlbumImages[i].setImage(null); 
                }
            } else {
                System.err.println("Album image path is null or empty for album: " + album.getAlbumName());
                allAlbumImages[i].setImage(null); 
            }
            
            allAlbumLabels[i].setText(album.getAlbumName());
            allAlbumPanes[i].setVisible(true);

            final int globalIndex = albstart + i;
            final int localIndex = i;

            allAlbumPanes[i].setOnMouseClicked(e -> {
                selectedAlbIndex = globalIndex;
                highlightAlbSelected(localIndex);
            });

        } else {
            allAlbumImages[i].setImage(null);
            allAlbumLabels[i].setText("");
            allAlbumPanes[i].setVisible(false);
        }
    }
}
     
      private void showAlbumPage(int pageIndex) {
       List<Albums> sourceList = isAlbumSearchActive ? filteredAlbums : allAlbums;
    int totalPages = (int) Math.ceil((double) sourceList.size() / ALBUMS_PER_PAGE);

    updateAlbumsPane(sourceList);
    lbAlbartistPagelabel.setText("Page " + (pageIndex + 1) + " of " + totalPages);}

      
   private void loadAlbumsFromDB() {
    allAlbums.clear();
    
    Map<Integer, Albums> albumsMap = new HashMap<>();

    try (Connection con = new DbConnection().getConnection()) {
        // 1. Load all albums first
        String albumSql = "SELECT album_id, album_name, album_img FROM albums";
        try (PreparedStatement albumPst = con.prepareStatement(albumSql);
             ResultSet albumRs = albumPst.executeQuery()) {
            while (albumRs.next()) {
                Albums album = new Albums(
                    albumRs.getInt("album_id"),
                    albumRs.getString("album_name"),
                    albumRs.getString("album_img")
                );
                allAlbums.add(album);
                albumsMap.put(album.getAlbumId(), album); 
            }
        }

        
        String songCategorySql = "SELECT s.album_id, s.category FROM songs s JOIN albums a ON s.album_id = a.album_id";
        try (PreparedStatement songPst = con.prepareStatement(songCategorySql);
             ResultSet songRs = songPst.executeQuery()) {
            while (songRs.next()) {
                int albumId = songRs.getInt("album_id");
                String songCategory = songRs.getString("category"); 

                Albums album = albumsMap.get(albumId);
                if (album != null && songCategory != null && !songCategory.trim().isEmpty()) {
                   
                    album.getCategory().add(songCategory);
                }
            }
        }

        filteredAlbums = new ArrayList<>(allAlbums);
        isAlbumSearchActive = false; // No search/filter active initially
        currentAlbumPage = 0; // Reset current page for initial display

        showAlbumPage(currentAlbumPage); // Display the initially loaded (all) albums

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading albums and song categories: " + e.getMessage());
    }
}
      //for highlight album
      private void highlightAlbSelected(int localalbIndex) {
        for (int i = 0; i < allAlbumImages.length; i++) {
             allAlbumImages[i].setStyle("");
        }
        if (localalbIndex >= 0 && localalbIndex < allAlbumImages.length) {
             allAlbumImages[localalbIndex].setStyle("-fx-effect: dropshadow(gaussian, red, 10, 0, 0, 0);");
    }
    }
    
    @FXML
    void handleAlbAddAction(ActionEvent event) {
        uploadAlbPane.setVisible(true);
        String defaultImagePathInResources = "/Image_Video/mb2.jpg";
        URL imageUrl = getClass().getResource(defaultImagePathInResources);

        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm());
            imgAlbView.setImage(image);
            imgAlbView.setPreserveRatio(true);
            System.out.println("DEBUG: Default image loaded successfully from: " + imageUrl);
        } else {
            System.err.println("ERROR: Default image file NOT found in resources at: " + defaultImagePathInResources);
            imgAlbView.setImage(null);
            
        }
        
    }

    @FXML
    void handleAlbNextArtistAction(ActionEvent event) {
        List<Albums> source = isAlbumSearchActive ? filteredAlbums : allAlbums;
    int totalPages = (int) Math.ceil((double) source.size() / ALBUMS_PER_PAGE);

    if (currentAlbumPage < totalPages - 1) {
        currentAlbumPage++;
        showAlbumPage(currentAlbumPage);
    }

    }
    private void refreshAlbumUI() {
      updateAlbumsPane(allAlbums);
      showAlbumPage(currentAlbumPage);
        highlightAlbSelected(-1);
    }

    private int getTotalAlbumPages() {
       return (int) Math.ceil((double) allAlbums.size() / ALBUMS_PER_PAGE);
    }

    @FXML
    void handleAlbPevArtistAction(ActionEvent event) {
        if (currentAlbumPage > 0) {
            currentAlbumPage--;
            showAlbumPage(currentAlbumPage);
        }

    }
    
    @FXML
    void handleUploadSongInstuPathAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select MP3 Instrumental File");
        // Set extension filter for MP3 files
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));

        File selectedFile = fileChooser.showOpenDialog(null); 

        if (selectedFile != null) {
            try {
               
                File destinationDirectory = new File("src/Image_Video");
                if (!destinationDirectory.exists()) {
                    destinationDirectory.mkdirs(); 
                }

                Path sourcePath = selectedFile.toPath();
                Path destinationPath = Paths.get(destinationDirectory.getAbsolutePath(), selectedFile.getName());

             
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

           
                txt_instuPath.setText(destinationPath.toString());
                System.out.println("MP3 Instrumental file saved to: " + destinationPath.toString());

            } catch (IOException e) {
                System.err.println("Error saving MP3 instrumental file: " + e.getMessage());
                e.printStackTrace();
          
                // JOptionPane.showMessageDialog(null, "Error saving MP3 instrumental file: " + e.getMessage());
            }
        }
    }

    @FXML
    void handleUploadSongPathAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select MP4 Song File");
    
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP4 Files", "*.mp4"));

        File selectedFile = fileChooser.showOpenDialog(null); 
        if (selectedFile != null) {
            try {
              
                File destinationDirectory = new File("src/Image_Video");
                if (!destinationDirectory.exists()) {
                    destinationDirectory.mkdirs(); 
                }

                Path sourcePath = selectedFile.toPath();
                Path destinationPath = Paths.get(destinationDirectory.getAbsolutePath(), selectedFile.getName());

              
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                
             
                txt_songPath.setText(destinationPath.toString());
                System.out.println("MP4 Song file saved to: " + destinationPath.toString());

            } catch (IOException e) {
                System.err.println("Error saving MP4 song file: " + e.getMessage());
                e.printStackTrace();
               
                // JOptionPane.showMessageDialog(null, "Error saving MP4 song file: " + e.getMessage());
            }
        }
    }

    @FXML
void handleAlbRemoveAction(ActionEvent event) throws ClassNotFoundException {
    List<Albums> currentSourceList = isAlbumSearchActive ? filteredAlbums : allAlbums;

  
    if (selectedAlbIndex >= 0 && selectedAlbIndex < currentSourceList.size()) {
      
        Albums albumToDelete = currentSourceList.get(selectedAlbIndex);
        String albumName = albumToDelete.getAlbumName();
        int albumId = albumToDelete.getAlbumId(); // Get the album ID

        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete \"" + albumName + "\" and all its associated songs?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection con = new DbConnection().getConnection()) {

                String selectSongsSql = "SELECT song_img, file_path, instrumental_file_path FROM songs WHERE album_id = ?";
                try (PreparedStatement songPst = con.prepareStatement(selectSongsSql)) {
                    songPst.setInt(1, albumId);
                    ResultSet rsSongs = songPst.executeQuery();
                    while (rsSongs.next()) {
                        String songImagePath = rsSongs.getString("song_img");
                        String songFilePath = rsSongs.getString("file_path");
                        String instrumentalFilePath = rsSongs.getString("instrumental_file_path");

                        // Delete Song Image (if not default)
                        String defaultSongImagePath = "src\\Image_Video\\mb2.jpg"; // <--- ADJUST THIS PATH
                        deleteFile(songImagePath, defaultSongImagePath);

                        // Delete Main Audio/Video File
                        deleteFile(songFilePath, null); // No default for MP3/MP4

                        // Delete Instrumental File (if it exists)
                        if (instrumentalFilePath != null && !instrumentalFilePath.isEmpty()) {
                            deleteFile(instrumentalFilePath, null);
                        }
                    }
                }

             
                deleteAlbumFromDatabase(albumToDelete.getAlbumId());

                allAlbums.removeIf(album -> album.getAlbumId() == albumId);

          
                if (isAlbumSearchActive) {
                    filteredAlbums.removeIf(album -> album.getAlbumId() == albumId);
                }
              
                String imagePath = albumToDelete.getAlbumImg(); // Re-get path if needed, or use the one from top
                String defaultAlbumImagePath = "src\\Image_Video\\mb2.jpg"; // <--- ADJUST THIS PATH
                deleteFile(imagePath, defaultAlbumImagePath);

                JOptionPane.showMessageDialog(null, "Album \"" + albumName + "\" and all associated songs/files deleted successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting album or associated files: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

         
            if (currentAlbumPage >= getTotalAlbumPages()) {
                currentAlbumPage = Math.max(0, getTotalAlbumPages() - 1);
            }

            selectedAlbIndex = -1; 
            refreshAlbumUI(); 

            
            loadSongsFromDB();
            updateSongPane(allSongs);
            showSongPage(currentSongPage); 
           
        }

    } else {
        JOptionPane.showMessageDialog(null, "Please select an album that you want to delete.");
    }
}
    
   private void deleteAlbumFromDatabase(int albumId) throws ClassNotFoundException {
        try (Connection con = new DbConnection().getConnection()) {
            String sql = "DELETE FROM albums WHERE album_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, albumId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
    
    @FXML
    void handleAlbumSearchAction(ActionEvent event) throws ClassNotFoundException {
        searchAlbums(txt_albumsearch.getText());

    }
    @FXML
    void handleAlbBackAction(ActionEvent event) {
          setVisiblePane(albumPane);
    }
    
    @FXML
    void handleAlbUploadAction(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif","*.wepb")
        );
        File file = fileChooser.showOpenDialog(MusicBoxService.stage);
        if (file != null) {
            File destDir = new File("src/Image_Video/");
            if (!destDir.exists()) destDir.mkdirs();

            File destFile = new File(destDir, file.getName());
            Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            imgAlbName = file.getName();
            Image image = new Image(destFile.toURI().toString());
            imgAlbView.setImage(image);
            imgAlbView.setPreserveRatio(true);
        }
    }

    
  @FXML
void handelAlbSaveAction(ActionEvent event) {
    String name = txtAlbArtistName.getText();
    String albImagePathToSave;

    if(name.isEmpty()){
        JOptionPane.showMessageDialog(null, "Please fill out album name!");
        return;
    }

    if (imgAlbName == null || imgAlbName.isEmpty()) { 
        albImagePathToSave = "src\\Image_Video\\mb2.jpg"; 
    } else {
       albImagePathToSave = "src\\Image_Video\\" + imgAlbName;
    }
 

    try (Connection con = new DbConnection().getConnection()) {
        String sql = "INSERT INTO albums ( album_name , album_img) VALUES ( ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, name);
        pst.setString(2, albImagePathToSave); 
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Album added successfully");
        currentAlbumPage = 0;
        loadAlbumsFromDB();
        showAlbumPage(currentAlbumPage);
        txtAlbArtistName.clear();
        imgAlbView.setImage(null);
        uploadAlbPane.setVisible(false);
        loadArtistsAndAlbums();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    //FOR SONG //FOR SONG //FOR SONG //FOR SONG //FOR SONG //FOR SONG //FOR SONG //FOR SONG //FOR SONGv//FOR SONG //FOR SONG 
   private void highlightSongSelected(int localSongIndex) {
    for (int i = 0; i < allSongImages.length; i++) {
        allSongImages[i].setStyle("");
    }
    if (localSongIndex >= 0 && localSongIndex < allSongImages.length) {
        allSongImages[localSongIndex].setStyle("-fx-effect: dropshadow(gaussian, red, 10, 0, 0, 0);");
    }
}
    
     private void searchSongs(String keyword) throws ClassNotFoundException {
        List<Songs> filtered;

    if (keyword.isEmpty()) {
        isSongSearchActive = false;
        filtered = allSongs;
    } else {
        isSongSearchActive = true;
        filtered = allSongs.stream()
                .filter(song -> song.getSongName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if (filtered.isEmpty()) {
            filtered = searchSongsFromDB(keyword);
        }
    }

    filteredSongs = filtered;
    currentSongPage = 0;
    updateSongPane(filteredSongs);

    }
    
     
    private void updateSongPane(List<Songs> fullSongList) {
    int sostart = currentSongPage * SONGS_PER_PAGE;
    int soend = Math.min(sostart + SONGS_PER_PAGE, fullSongList.size());
    List<Songs> pageSongs = fullSongList.subList(sostart, soend);

   
    String defaultImagePath = "src\\Image_Video\\mb2.jpg"; 

    for (int i = 0; i < SONGS_PER_PAGE; i++) {
        if (i < pageSongs.size()) {
            Songs song = pageSongs.get(i);

          
            String fullImagePathFromDb = song.getSongImg(); 

            if (fullImagePathFromDb != null && !fullImagePathFromDb.isEmpty()) {
                File file = new File(fullImagePathFromDb);
                if (file.exists()) {
                    try {
                     
                        Image image = new Image(file.toURI().toString());
                        allSongImages[i].setImage(image);
                    } catch (Exception e) {
                        System.err.println("Error loading image for song '" + song.getSongName() + "' from path: " + fullImagePathFromDb + " - " + e.getMessage());
                        
                        try {
                            allSongImages[i].setImage(new Image(new File(defaultImagePath).toURI().toString()));
                        } catch (Exception ex) {
                             System.err.println("Error loading default image: " + ex.getMessage());
                        }
                    }
                } else {
                    System.out.println("Image file not found for song '" + song.getSongName() + "': " + fullImagePathFromDb);
                   
                    try {
                        allSongImages[i].setImage(new Image(new File(defaultImagePath).toURI().toString()));
                    } catch (Exception ex) {
                        System.err.println("Error loading default image: " + ex.getMessage());
                    }
                }
            } else {
                System.out.println("Image path is null or empty for song '" + song.getSongName() + "'. Using default image.");
              
                try {
                    allSongImages[i].setImage(new Image(new File(defaultImagePath).toURI().toString()));
                } catch (Exception ex) {
                    System.err.println("Error loading default image: " + ex.getMessage());
                }
            }
        

            allSongLabels[i].setText(song.getSongName());
            allSongPanes[i].setVisible(true);

            final int globalIndex = sostart + i;
            final int localIndex = i;

            allSongPanes[i].setOnMouseClicked(e -> {
                selectedSongIndex = globalIndex;
                highlightSongSelected(localIndex);
            });

        } else {
            allSongImages[i].setImage(null);
            allSongLabels[i].setText("");
            allSongPanes[i].setVisible(false);
        }
    }
}

      
      private List<Songs> searchSongsFromDB(String keyword) throws ClassNotFoundException {
        List<Songs> songList = new ArrayList<>();
        String query = "SELECT * FROM songs WHERE song_name LIKE ?";

        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("song_id");
                String name = rs.getString("song_name");
                int artist_id = rs.getInt("artist_id");
                int album_id = rs.getInt("album_id");
                String category = rs.getString("category");
                String img = rs.getString("song_img");
                String filepath = rs.getString("file_path");
                String instrumentalfilepath = rs.getString("instrumental_file_path");
                
                Artists artist = new Artists();
                artist.setArtistId(artist_id);

                Albums album = new Albums();
                album.setAlbumId(album_id);

                songList.add(new Songs(id, name, artist_id, album_id, category, img, filepath, instrumentalfilepath));
            }

        } /**/ catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }
      

   private void showSongPage(int pageIndex) {
    List<Songs> sourceList = isSongSearchActive ? filteredSongs : allSongs;
    int totalPages = (int) Math.ceil((double) sourceList.size() / SONGS_PER_PAGE);

    updateSongPane(sourceList);
    lbSongPagelabel.setText("Page " + (pageIndex + 1) + " of " + totalPages);
}
       
      private void loadSongsFromDB() {
    allSongs.clear(); 

    try (Connection con = new DbConnection().getConnection();
         Statement stmt = con.createStatement();
       
         ResultSet rs = stmt.executeQuery(
             "SELECT s.song_id, s.song_name, s.file_path, s.instrumental_file_path, s.song_img, s.category, " +
             "a.artist_id, a.artist_name, " + // Select artist details
             "al.album_id, al.album_name " +  // Select album details
             "FROM songs s " +
             "JOIN artists a ON s.artist_id = a.artist_id " +
             "JOIN albums al ON s.album_id = al.album_id"
         )) {

        while (rs.next()) {
            int songId = rs.getInt("song_id");
            String songName = rs.getString("song_name");

           
            int artistId = rs.getInt("artist_id");
            String artistName = rs.getString("artist_name");
            Artists artist = new Artists(artistId, artistName, null, null, null); 

            
            int albumId = rs.getInt("album_id");
            String albumName = rs.getString("album_name");
            Albums album = new Albums(albumId, albumName); 

            String category = rs.getString("category");
            String songImg = rs.getString("song_img");
            String filePath = rs.getString("file_path");
            String instrumentalFilePath = rs.getString("instrumental_file_path");

         
            Songs song = new Songs(
                songId,
                songName,
                artist, // Pass the Artist object
                album,  // Pass the Album object
                category,
                songImg,
                filePath,
                instrumentalFilePath
            );
            allSongs.add(song);
        }

     
        showSongPage(currentSongPage);

    } catch (Exception e) {
        e.printStackTrace();
    }
  
    // showSongPage(currentSongPage);
}    @FXML
    void handleSongSearchAction(ActionEvent event) throws ClassNotFoundException {
         searchSongs(txt_songsearch.getText());
    }
    
   @FXML
    void handleSongAddAction(ActionEvent event) {
        System.out.println("DEBUG: handleSongAddAction triggered.");

     
        if (uploadSongPane == null) {
            System.err.println("ERROR: uploadSongPane is NULL! Check FXML fx:id and @FXML annotation.");
            return; 
        }

        
        artistListPane.setVisible(false);
        albumPane.setVisible(false);
        uploadArtistPane.setVisible(false);
        uploadAlbPane.setVisible(false);
        ReportPane.setVisible(false);
        RoomPane.setVisible(false);
        System.out.println("DEBUG: Panes other than songListPane set to invisible.");

     
        uploadSongPane.setVisible(true);
        uploadSongPane.toFront(); 
        System.out.println("DEBUG: uploadSongPane set visible and brought to front.");

    
        System.out.println("DEBUG: uploadSongPane.isVisible() after set: " + uploadSongPane.isVisible());
        if (uploadSongPane.getParent() != null) {
            System.out.println("DEBUG: uploadSongPane parent: " + uploadSongPane.getParent().getClass().getSimpleName());
            System.out.println("DEBUG: uploadSongPane parent is visible: " + uploadSongPane.getParent().isVisible());
        } else {
            System.out.println("DEBUG: uploadSongPane has no parent!");
        }

    
        txtSongName.clear();
        comboArtist.setValue(null);
        comboAlbum.setValue(null);
        txtSongCategory.setValue(null);
        txt_songPath.clear();  
        txt_instuPath.clear(); 

      
        String defaultImagePathInResources = "/Image_Video/mb2.jpg";
        URL imageUrl = getClass().getResource(defaultImagePathInResources);

        if (imageUrl != null) {
            try {
                imgSongName = new File(imageUrl.toURI()).getAbsolutePath();
                Image image = new Image(imageUrl.toExternalForm());
                imgSongView.setImage(image);
                imgSongView.setPreserveRatio(true);
                System.out.println("DEBUG: Default image loaded successfully from: " + imageUrl);
            } catch (java.net.URISyntaxException e) {
                System.err.println("ERROR: Problem converting URL to URI for default image: " + e.getMessage());
                imgSongView.setImage(null);
                imgSongName = null;
            }
        } else {
            System.err.println("ERROR: Default image file NOT found in resources at: " + defaultImagePathInResources);
            imgSongView.setImage(null);
            imgSongName = null;
        }
    }
    @FXML
    void handleSongBackAction(ActionEvent event) {
        loadArtistsAndAlbums();
        uploadSongPane.setVisible(false);
        songListPane.setVisible(true);
    }
    
@FXML
void handleRemoveSongAction(ActionEvent event) throws ClassNotFoundException {
   
    List<Songs> currentSourceList = isSongSearchActive ? filteredSongs : allSongs;

 
    if (selectedSongIndex >= 0 && selectedSongIndex < currentSourceList.size()) {
        // Get the song to delete from the current source list
        Songs songToDelete = currentSourceList.get(selectedSongIndex);
        String songName = songToDelete.getSongName();

        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete \"" + songName + "\"?", 
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
          
            String imagePath = songToDelete.getSongImg();
            String vocalFilePath = songToDelete.getFilePath(); // MP4 file path
            String instrumentalFilePath = songToDelete.getInstrumentalFilePath(); // MP3 file path

          
            String defaultImagePath = "src\\Image_Video\\mb2.jpg";

          
            deleteSongFromDatabase(songToDelete.getId());
          
            allSongs.removeIf(song -> song.getId() == songToDelete.getId());
          
            if (isSongSearchActive) {
                filteredSongs.removeIf(song -> song.getId() == songToDelete.getId());
            }
          
            deletePhysicalFile(imagePath, defaultImagePath, "Song image");
            deletePhysicalFile(vocalFilePath, null, "Song vocal MP4"); 
            deletePhysicalFile(instrumentalFilePath, null, "Song instrumental MP3"); 
            
            if (currentSongPage >= getTotalSongPages()) {
                currentSongPage = Math.max(0, getTotalSongPages() - 1);
            }
            selectedSongIndex = -1; 
            refreshSongUI(); 
            JOptionPane.showMessageDialog(null, "Song \"" + songName + "\" deleted successfully!"); 
        }

    } else {
        JOptionPane.showMessageDialog(null, "Please select a song that you want to delete.");
    }
}


private void deletePhysicalFile(String filePath, String defaultPath, String fileType) {
    if (filePath == null || filePath.isEmpty()) {
        System.out.println(fileType + " path is null or empty, skipping physical file deletion.");
        return;
    }

   
    File fileToDelete = new File(filePath);

    
    if (defaultPath != null && fileToDelete.getAbsolutePath().equalsIgnoreCase(new File(defaultPath).getAbsolutePath())) {
        System.out.println("Skipping deletion of default " + fileType + ": " + filePath);
        return;
    }

    if (fileToDelete.exists()) {
        if (fileToDelete.delete()) {
            System.out.println(fileType + " file deleted successfully: " + filePath);
        } else {
            System.err.println("Failed to delete " + fileType + " file: " + filePath);
            JOptionPane.showMessageDialog(null, "Could not delete " + fileType + " file: " + fileToDelete.getName() + ". You may need to delete it manually.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        System.out.println(fileType + " file not found, no deletion needed: " + filePath);
    }
}
    
    @FXML
    void handleNextSongAction(ActionEvent event) {
        List<Songs> sourceList = isSongSearchActive ? filteredSongs : allSongs;
    int totalPages = (int) Math.ceil((double) sourceList.size() / SONGS_PER_PAGE);

    if (currentSongPage < totalPages - 1) {
        currentSongPage++;                      
        showSongPage(currentSongPage);          
    }
    }
    
    
    @FXML
    void handlePevSongAction(ActionEvent event) {
        if (currentSongPage > 0) {
            currentSongPage--;
            showSongPage(currentSongPage);
        }
    }
    
     private int getTotalSongPages() {
    
    return (int) Math.ceil((double) allSongs.size() / SONGS_PER_PAGE);
}
    
 @FXML
    private void handleUploadSongImgAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
      
        File selectedFile = fileChooser.showOpenDialog(btnUploadSongImg != null ? btnUploadSongImg.getScene().getWindow() : null);

        if (selectedFile != null) {
            try {
               
                String originalFileName = selectedFile.getName();
               
                String fileNameToSave = originalFileName; 

                Path destDir = Paths.get("src\\Image_Video\\");
                if (!Files.exists(destDir)) {
                    Files.createDirectories(destDir); 
                }

               
                Path targetPath = destDir.resolve(fileNameToSave);

              
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            
                Image image = new Image(new FileInputStream(selectedFile));
                if (imgSongView != null) {
                    imgSongView.setImage(image);
                } else {
                    System.err.println("imgSongView ImageView is not initialized or null.");
                }

             
                this.tempUploadedImageFilePath = targetPath.toAbsolutePath().toString();

          

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error uploading image: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

@FXML
void handleSongSaveAction(ActionEvent event) {
    String name = txtSongName.getText();
    String path = txt_songPath.getText();
    String instrupath = txt_instuPath.getText();
    Songs category = txtSongCategory.getValue();
    String songImagePathToSave; 
    Artists selectedArtist = comboArtist.getValue();
    Albums selectedAlbum = comboAlbum.getValue();

   
    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Song Name is required!");
        return;
    } else if(selectedArtist == null){
        JOptionPane.showMessageDialog(null, "Artist Name is required!");
        return;
    } else if(selectedAlbum == null){
        JOptionPane.showMessageDialog(null, "Album Name is required!");
        return;
    } else if(category==null){
        JOptionPane.showMessageDialog(null, "Category required!");
        return;
    } else if(path.isEmpty()){
        JOptionPane.showMessageDialog(null, "Song mp4 path required!");
        return;
    } else if(instrupath.isEmpty()){
        JOptionPane.showMessageDialog(null, "Song instrupath mp3 path required!");
        return;
    }

    int artist_id = selectedArtist.getArtistId();
    int album_id = selectedAlbum.getAlbumId();

   
    if (this.tempUploadedImageFilePath != null && !this.tempUploadedImageFilePath.isEmpty()) {
        songImagePathToSave = this.tempUploadedImageFilePath;
    } else {
      
        songImagePathToSave = "src\\Image_Video\\mb2.jpg";
    }

    try (Connection con = new DbConnection().getConnection()) {
        String sql = "INSERT INTO songs (song_name, artist_id, album_id, category, song_img, file_path, instrumental_file_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, name);
        pst.setInt(2, artist_id);
        pst.setInt(3, album_id);
        pst.setString(4, category.toString());
        pst.setString(5, songImagePathToSave); 
        pst.setString(6, path);
        pst.setString(7, instrupath);

        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Song added successfully");

       
        txtSongName.clear();
        comboArtist.setValue(null);
        comboAlbum.setValue(null);
        txtSongCategory.setValue(null);
        txt_songPath.clear();
        txt_instuPath.clear();
        imgSongView.setImage(null); 

       
        this.tempUploadedImageFilePath = null;
      

        uploadSongPane.setVisible(false);

    } catch (SQLException e) {
        System.err.println("Database error saving song: " + e.getMessage());
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Database Driver Error: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
    }
    
    loadSongsFromDB();   
    updateSongPane(allSongs); 
    showSongPage(currentSongPage);
}

    private void refreshSongUI() {
        updateSongPane(allSongs);
        showSongPage(currentSongPage);
        highlightSongSelected(-1);
    }
    
   private void deleteSongFromDatabase(int songId) throws ClassNotFoundException {
        try (Connection con = new DbConnection().getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM songs WHERE song_id = ?")) {
            stmt.setInt(1, songId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Song with ID " + songId + " deleted from database successfully.");
            } else {
                System.out.println("No song found with ID " + songId + " to delete from database.");
            }
        } catch (SQLException e) {
            System.err.println("Database error when deleting song with ID " + songId + ":");
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "Error deleting song from database: " + e.getMessage());
        }
    }
    //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF //FOR STAFF   
    @FXML
    void handleStaffAddAction(ActionEvent event) {
           uploadStaffPane.setVisible(true);
             setVisiblePane(staffAddPane);
        
        txtUserPsw.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() < 8) {
                lb_warning.setVisible(true);
            }
                else{
                lb_warning.setVisible(false);
            }
        });
    }

    @FXML
    private void handleStaffDeleteAction(ActionEvent event) throws ClassNotFoundException {
     User selectedUser = userTable.getSelectionModel().getSelectedItem();

    if (selectedUser != null) {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to deactivate this user?", // Changed message
            "Confirm Deactivation", // Changed title
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try (
                Connection conn = new DbConnection().getConnection();
               
                PreparedStatement pst = conn.prepareStatement(
                    "UPDATE users SET status = 'inactive' WHERE user_id = ?"
                )
            ) {
                pst.setInt(1, selectedUser.getUserId() );

                int affectedRows = pst.executeUpdate();

                if (affectedRows > 0) {
                    userList.remove(selectedUser); 
                    JOptionPane.showMessageDialog(null, "User status changed to 'inactive' successfully."); // Changed message
                  
                    
                } else {
                    JOptionPane.showMessageDialog(null, "User status update failed in database."); // Changed message
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error occurred while deactivating user."); // Changed message
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select a user to deactivate."); // Changed message
    }
}

    
    @FXML
    void handleStaffAddPaneAction(ActionEvent event) {
        setVisiblePane(staffAddPane);
        setActiveButtonForAdmin(btnStaffAddPane);
      
        
    }
    @FXML
    void handleStaffBackAction(ActionEvent event) {
        uploadStaffPane.setVisible(false);
        staffAddPane.setVisible(true);
    }
    
     @FXML
    void handleStaffSaveAction(ActionEvent event) throws ClassNotFoundException {

    String userName = txtUserName.getText();
    String password = txtUserPsw.getText();
    User role =  comboRole.getValue();
    String status = "active";
   
    
    if (userName.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out username!");
        return; 
    } else if (password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out password!");
        return; 
    } else if (role==null) {
        JOptionPane.showMessageDialog(null, "Please fill out role!");
        return; 
    } 

    String sql = "INSERT INTO users (user_name, user_password, role,status) VALUES (?, ?, ?, ?)";

    try (Connection con = new DbConnection().getConnection();
         PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, userName);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        pstmt.setString(2, hashedPassword);
        pstmt.setString(3, role.toString());
        pstmt.setString(4, status);
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(null, "Staff Successfully added");
        txtUserName.clear();
        txtUserPsw.clear();
       comboRole.setValue(null);
  
        uploadStaffPane.setVisible(false);

        loadUsersFromDatabase();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error adding staff: " + e.getMessage()); // Provide feedback on database errors
    }
}

   public void loadUsersFromDatabase() throws ClassNotFoundException {
    userList.clear(); 
    try (Connection conn = new DbConnection().getConnection();
       
         PreparedStatement pst = conn.prepareStatement("SELECT user_id, user_name, user_password, role, status FROM users WHERE status = 'active'");
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            int userId = rs.getInt("user_id");
            String userName = rs.getString("user_"
                    + "name");
            String password = rs.getString("user_password");
            String role = rs.getString("role");
            String status = rs.getString("status"); 

            userList.add(new User(userId, userName, password, role));
        }
    } catch (SQLException e) {
        e.printStackTrace();
     
        JOptionPane.showMessageDialog(null, "Error loading users from database: " + e.getMessage());
    }
}
    
    public void loadArtistsAndAlbums() {
    try (Connection con = new DbConnection().getConnection()) {
        // Load artists
        String artistQuery = "SELECT artist_id, artist_name FROM artists";
        PreparedStatement artistStmt = con.prepareStatement(artistQuery);
        ResultSet rsArtist = artistStmt.executeQuery();
        while (rsArtist.next()) {
            int id = rsArtist.getInt("artist_id");
            String name = rsArtist.getString("artist_name");
            comboArtist.getItems().add(new Artists(id, name)); 
        }

        // Load albums
        String albumQuery = "SELECT album_id, album_name FROM albums";
        PreparedStatement albumStmt = con.prepareStatement(albumQuery);
        ResultSet rsAlbum = albumStmt.executeQuery();
        while (rsAlbum.next()) {
            int id = rsAlbum.getInt("album_id");
            String name = rsAlbum.getString("album_name");
            comboAlbum.getItems().add(new Albums(id, name)); 
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
     private void updateUIForMonth(String selectedValue) {
        String monthName = selectedValue.split(" - ")[1];
        selectedMonthLabel.setText(monthName.toUpperCase());

        
        int monthNumber = Integer.parseInt(selectedValue.substring(0, 2));
        int year = LocalDate.now().getYear(); // Use the current year (2025)

        String sql = "SELECT SUM(total) FROM room_details WHERE MONTH(STR_TO_DATE(date, '%Y-%m-%d')) = ? AND YEAR(STR_TO_DATE(date, '%Y-%m-%d')) = ?";
        double totalIncome = 0.0;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, monthNumber);
            pst.setInt(2, year);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
              
                totalIncome = rs.getDouble(1);
            }
        } catch (SQLException ex) {
           
        }

      
        incomeLabel.setText(String.format("Ks %.1f", totalIncome));
        MonthlyShowBarchart(monthNumber, year);
        updateDetailedIncome(monthNumber, year);

    }
    
    private void MonthlyShowBarchart(int monthNumber, int year) {
        // Clear previous data
        MonthlyBarChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weekly Sales");

      
        LocalDate firstDayOfMonth = LocalDate.of(year, monthNumber, 1);
       
        LocalDate firstMonday = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        if (firstMonday.isAfter(firstDayOfMonth)) { 
            firstMonday = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        }


      
        for (int week = 1; week <= 5; week++) { 
            LocalDate weekStart = firstMonday.plusWeeks(week - 1);
            LocalDate weekEnd = weekStart.plusDays(6); 

        
            if (weekStart.getMonthValue() != monthNumber && weekEnd.getMonthValue() != monthNumber && weekStart.getYear() != year && weekEnd.getYear() != year) {
             
                continue;
            }

            
            if (weekEnd.getMonthValue() != monthNumber || weekEnd.getYear() != year) {
                weekEnd = LocalDate.of(year, monthNumber, firstDayOfMonth.lengthOfMonth());
            }
            if (weekStart.getMonthValue() != monthNumber || weekStart.getYear() != year) {
                weekStart = LocalDate.of(year, monthNumber, 1);
            }


            String sql = "SELECT SUM(total) FROM room_details WHERE STR_TO_DATE(date, '%Y-%m-%d') >= ? AND STR_TO_DATE(date, '%Y-%m-%d') <= ?";
            double weeklyTotal = 0.0;

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, weekStart.toString());
                pst.setString(2, weekEnd.toString());

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    weeklyTotal = rs.getDouble(1);
                }
            } catch (SQLException ex) {
               
            }
            series.getData().add(new XYChart.Data<>("Week " + week, weeklyTotal));
        }
        MonthlyBarChart.getData().add(series);
    }
   



 @FXML
    void RefreshAlbum(ActionEvent event) {
        txt_albumsearch.setText("");
        
        currentSongPage = 0;
       updateAlbumsPane(allAlbums);
       comboCategoryAlbum.getSelectionModel().select("All Categories"); 
        
        

    }
      @FXML
    void RefreshArtist(ActionEvent event) {
        txt_artistsearch.setText("");
        
        currentSongPage = 0;
          updateArtistPane(allArtists);
       comboCategoryArtist.getSelectionModel().select("All Categories"); 
        
    }
    @FXML
    void RefreshSong(ActionEvent event) {
    txt_songsearch.setText("");
        
        currentSongPage = 0;
          updateSongPane(allSongs);
       comboCategorySong.getSelectionModel().select("All Categories"); 
        
    }
    private void loadRoomsAndSetupButtons() throws ClassNotFoundException, SQLException {
    roomList.clear();
        try (Connection con = new DbConnection().getConnection()) {
            String roomQuery = "SELECT room_id, room_type, price FROM room";
            try (PreparedStatement roomStmt = con.prepareStatement(roomQuery); ResultSet rsRooms = roomStmt.executeQuery()) {
                while (rsRooms.next()) {
                    int id = rsRooms.getInt("room_id");
                    String type = rsRooms.getString("room_type");
                    Integer price = rsRooms.getInt("price");
                    roomList.add(new Room(id, type, price));
                }
            }

            for (int i = 0; i < roomButtons.length; i++) {
                if (i < roomList.size()) {
                    Room room = roomList.get(i);
                    Button currentButton = roomButtons[i];
                    String dbStatus = "Available"; 
                    String calculatedStatus = "Available"; 
                    int roomDetailsId = -1; 
                    LocalDate bookingDate = null;
                    LocalTime bookingStartTime = null;
                    LocalTime bookingEndTime = null;

                    String statusQuery = "SELECT " +
                                         "rd.detail_id, " +
                                         "rd.status AS db_status, " +
                                         "rd.date, " +
                                         "rd.start_time, " +
                                         "rd.end_time " +
                                         "FROM room_details rd " +
                                         "WHERE rd.room_id = ? " +
                                         "AND rd.date = CURDATE() " + 
                                         "AND (CURTIME() BETWEEN rd.start_time AND rd.end_time " + 
                                         "     OR rd.start_time > CURTIME()) " + 
                                         "ORDER BY " +
                                         "rd.start_time ASC " + 
                                         "LIMIT 1";

                    try (PreparedStatement statusStmt = con.prepareStatement(statusQuery)) {
                        statusStmt.setInt(1, room.getRoomId());
                        try (ResultSet rsStatus = statusStmt.executeQuery()) {
                            if (rsStatus.next()) {
                               
                                roomDetailsId = rsStatus.getInt("detail_id");
                                dbStatus = rsStatus.getString("db_status");
                                String bookingDateStr = rsStatus.getString("date");
                                String bookingStartTimeStr = rsStatus.getString("start_time");
                                String bookingEndTimeStr = rsStatus.getString("end_time");

                                try {
                                    bookingDate = LocalDate.parse(bookingDateStr);
                                    bookingStartTime = LocalTime.parse(bookingStartTimeStr);
                                    bookingEndTime = LocalTime.parse(bookingEndTimeStr);

                                
                                    LocalDateTime bookingStartDateTime = LocalDateTime.of(bookingDate, bookingStartTime);
                                    LocalDateTime bookingEndDateTime = LocalDateTime.of(bookingDate, bookingEndTime);
                                    LocalDateTime currentDateTime = LocalDateTime.now();

                                  
                                    if (currentDateTime.isEqual(bookingStartDateTime) ||
                                        (currentDateTime.isAfter(bookingStartDateTime) && currentDateTime.isBefore(bookingEndDateTime))) {
                                        calculatedStatus = "Using";
                                    } else {
                                     
                                        calculatedStatus = "Booked";
                                    }
                                } catch (DateTimeParseException e) {
                                    System.err.println("Error parsing date/time for room " + room.getRoomId() + ": " + e.getMessage());
                                 
                                    calculatedStatus = "Booked";
                                }
                            } else {
                             
                                calculatedStatus = "Available";
                                dbStatus = "Available"; 
                                roomDetailsId = -1;
                            }
                        }
                    }

                  
                    if (roomDetailsId != -1 && !dbStatus.equalsIgnoreCase(calculatedStatus)) {
                        String updateStatusQuery = "UPDATE room_details SET status = ? WHERE detail_id = ?";
                        try (PreparedStatement updateStmt = con.prepareStatement(updateStatusQuery)) {
                            updateStmt.setString(1, calculatedStatus);
                            updateStmt.setInt(2, roomDetailsId);
                            updateStmt.executeUpdate();
                            System.out.println("Updated room " + room.getRoomId() + " status from " + dbStatus + " to " + calculatedStatus);
                        }
                    }
                    
                 
                    currentButton.setVisible(true);
                    String buttonColorStyle = "";
                    switch (calculatedStatus.toLowerCase()) {
                        case "booked":
                            buttonColorStyle = "-fx-background-color: #FF6347;"; // Tomato Red
                            break;
                        case "available":
                            buttonColorStyle = "-fx-background-color: #3CB371;"; // MediumSeaGreen
                            break;
                        case "using":
                            buttonColorStyle = "-fx-background-color: #FFD700;"; // Gold / Yellow
                            break;
                        case "used": 
                            buttonColorStyle = "-fx-background-color: #808080;"; // Grey for used/completed
                            break;
                        default:
                            buttonColorStyle = "-fx-background-color: #3CB371;"; // Default to green
                            break;
                    }
                    currentButton.setStyle(buttonColorStyle);
                    final int roomIndex = i;
                    currentButton.setOnAction(event -> handleRoomButtonClick(roomList.get(roomIndex)));
                } else {
                    roomButtons[i].setVisible(false); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading rooms: " + e.getMessage());
        }
}
    
    private void handleRoomButtonClick(Room room) {
        selectedRoom = room; // Store the selected room

        
        System.out.println("\n--- Room Button Clicked ---");
        System.out.println("Selected Room: " + (selectedRoom != null ? selectedRoom.getRoomType() : "NULL"));

        if (RoomPane == null) {
            System.err.println("ERROR: RoomPane is NULL. FXML injection failed or fx:id mismatch.");
            JOptionPane.showMessageDialog(null, "Error: RoomPane not initialized. Check FXML.");
            return; 
        }
        if (bookingacceptPane == null) {
            System.err.println("ERROR: bookingacceptPane is NULL. FXML injection failed or fx:id mismatch.");
            JOptionPane.showMessageDialog(null, "Error: Booking Acceptance Pane not initialized. Check FXML.");
            return; 
        }

        System.out.println("Before change: RoomPane visible: " + RoomPane.isVisible());
        System.out.println("Before change: bookingacceptPane visible: " + bookingacceptPane.isVisible());

        
        bookingacceptPane.setVisible(true);

       
        System.out.println("After change: RoomPane visible: " + RoomPane.isVisible());
        System.out.println("After change: bookingacceptPane visible: " + bookingacceptPane.isVisible());
        System.out.println("--- End Room Button Clicked ---\n");
        


        
       

       

        txt_customer.clear();
        txt_phone.clear();
        txt_date.setValue(null);
        comboStartTime.getSelectionModel().clearSelection();
        comboEndTime.getSelectionModel().clearSelection();
        comboStatus.getSelectionModel().clearSelection();
    }
 
    private boolean isRoomBooked(int roomId, LocalDate newBookingDate,
                                 LocalTime newCheckInTime, LocalTime newCheckOutTime) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = new DbConnection().getConnection();

       
            String newCheckInTimeString = newCheckInTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            String newCheckOutTimeString = newCheckOutTime.format(DateTimeFormatter.ofPattern("HH:mm"));

            
            String sql = "SELECT COUNT(*) FROM room_details " +
                         "WHERE room_id = ? " +
                         "AND date = ? " + 
                         "AND ( " +
                         "    STR_TO_DATE(TRIM(start_time), '%H:%i') < STR_TO_DATE(TRIM(?), '%H:%i') " + // existing_start < new_end
                         "    AND " +
                         "    STR_TO_DATE(TRIM(end_time), '%H:%i') > STR_TO_DATE(TRIM(?), '%H:%i') " +   // existing_end > new_start
                         ")";

            pst = con.prepareStatement(sql);

            pst.setInt(1, roomId);
            pst.setString(2, newBookingDate.toString()); // Date as string "YYYY-MM-DD"
            pst.setString(3, newCheckOutTimeString);     // New booking's end time
            pst.setString(4, newCheckInTimeString);      // New booking's start time

            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, an overlapping booking exists
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, "Database driver not found.", ex);
            JOptionPane.showMessageDialog(null, "Database driver not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, "Database error during overlap check.", ex);
            JOptionPane.showMessageDialog(null, "Database error during booking conflict check: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, "Error closing resources in isRoomBooked.", ex);
            }
        }
        return false; 
    }

    @FXML
    private void handleRoomSaveAction(ActionEvent event) {
       if (selectedRoom == null) {
            JOptionPane.showMessageDialog(null, "Please select a room first.");
            return;
        }

        String startTimeString = comboStartTime.getValue();
        String endTimeString = comboEndTime.getValue();
        String customerName = txt_customer.getText();
        String customerPhonenumber = txt_phone.getText();
        String match2=txt_phone.getText().substring(0, 2);
        String status = null;
        if (comboStatus != null && comboStatus.getSelectionModel().getSelectedItem() != null) {
            status = comboStatus.getSelectionModel().getSelectedItem();
        }
        LocalDate date = txt_date.getValue();

        String phoneRegex = "{11}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(customerPhonenumber);

    
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the customer's name.");
            return;
        }
        if (customerPhonenumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the customer's phone number.");
            return;
        } else if ((!matcher.matches()) && (!match2.equals("09"))) {
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number format (e.g., 09123456789 ).");
            return;
        }
        if (date == null) {
            JOptionPane.showMessageDialog(null, "Please select a booking date.");
            return;
        }
        if (startTimeString == null) {
            JOptionPane.showMessageDialog(null, "Please select a start time.");
            return;
        }
        if (endTimeString == null ) {
            JOptionPane.showMessageDialog(null, "Please select an end time.");
            return;
        }else if(startTimeString.equals(endTimeString)){
            JOptionPane.showMessageDialog(null, " End time  and start time  are the  same.Please select valid time");
            return;
            
        }
        if (status == null) {
            JOptionPane.showMessageDialog(null, "Please select a booking status.");
            return;
        }
     
        LocalTime startTime;
        LocalTime endTime;
        try {
            startTime = LocalTime.parse(startTimeString, DateTimeFormatter.ofPattern("HH:mm"));
            endTime = LocalTime.parse(endTimeString, DateTimeFormatter.ofPattern("HH:mm"));

            if (endTime.isBefore(startTime)) {
                JOptionPane.showMessageDialog(null, "End time cannot be before start time on the same day. Please check your times.");
                return;
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid time format. Please ensure times are in HH:MM format (e.g., 09:00 or 17:30).");
            return;
        }

        
        if (isRoomBooked(selectedRoom.getRoomId(), date, startTime, endTime)) {
            JOptionPane.showMessageDialog(null, "This room is already booked for the selected date and time slot. Please choose different times or another room.", "Booking Conflict", JOptionPane.WARNING_MESSAGE);
            return; 
        }
       

        try (Connection con = new Database.DbConnection().getConnection()) {

         int currentUserId = Model.User.getInstance().getUserId(); 
         System.out.println("Attempting to save booking with user_id: " + currentUserId); // Add this line


    String sql = "INSERT INTO room_details (room_id, customer_name, customer_phonenumber, date, start_time, end_time, status, total, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
          double total = 0.0;
            try {
                Duration duration = Duration.between(startTime, endTime);
                long minutes = duration.toMinutes();
                double hours = minutes / 60.0;
                total = selectedRoom.getPrice() * hours;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error calculating total: " + e.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        pst.setInt(1, selectedRoom.getRoomId());
        pst.setString(2, customerName);
        pst.setString(3, customerPhonenumber);
        pst.setString(4, date.toString());
        pst.setString(5, startTimeString);
        pst.setString(6, endTimeString);
        pst.setString(7, status);
        pst.setDouble(8, total);
        pst.setInt(9, currentUserId);

        pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room booking saved successfully! Total: Ks" + String.format("%.2f", total));

      
        loadRoomDetailsFromDatabase();
         loadRoomsAndSetupButtons();
        txt_customer.clear();
        txt_phone.clear();
        txt_date.setValue(null);
        comboStartTime.setValue(null);
        comboEndTime.setValue(null);
        comboStatus.setValue(null);
        bookingacceptPane.setVisible(false);

    } catch (SQLException e) {
        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, "Error saving room booking.", e);
        JOptionPane.showMessageDialog(null, "Error saving room booking: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Database driver not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
     @FXML
    private void handleRoomBackAction(ActionEvent event) {
        bookingacceptPane.setVisible(false);
        RoomPane.setVisible(true);
    }

private void loadRoomDetailsFromDatabase() throws ClassNotFoundException, SQLException {
    roomDetailsList.clear();

 
    String updateQuery = "UPDATE room_details SET status = 'used' WHERE end_time < CURRENT_TIME AND status != 'used'"; 

    try (Connection con = new DbConnection().getConnection();
         PreparedStatement pstUpdate = con.prepareStatement(updateQuery)) {
        pstUpdate.executeUpdate();
        System.out.println("Room statuses updated successfully based on end time.");
    } catch (SQLException e) {
        System.err.println("Error updating room details before loading: " + e.getMessage());
    }
  
    String query = "SELECT rd.detail_id, r.room_id, r.room_type, r.price, rd.status, rd.date, rd.start_time, rd.end_time, rd.customer_name, rd.customer_phonenumber, rd.total,rd.user_id " +
                   "FROM room_details rd JOIN room r ON rd.room_id = r.room_id WHERE DATE(rd.date) = CURDATE() "; // Added rd.user_id = ?
    PreparedStatement pst = con.prepareStatement(query);
    
    

        ResultSet rs = pst.executeQuery();
        roomDetailsList.clear();
        while (rs.next()) {
            Room room = new Room(rs.getInt("room_id"), rs.getString("room_type"), rs.getInt("price"));
         
            Room_Details detail = new Room_Details(
                rs.getInt("detail_id"),
                room,
                
                rs.getString("status"),
                rs.getString("date"),
                rs.getString("start_time"),
                rs.getString("end_time"),
                   
                rs.getString("customer_name"),
                rs.getString("customer_phonenumber"),
                    
                rs.getInt("total")
                
                   
            
            );
            detail.setUserId(rs.getInt("user_id"));

            

            roomDetailsList.add(detail);
        }
    
    tbRoomDetail.setItems(roomDetailsList);
    }
      
        @FXML
    void handleEditAlbBackAction(ActionEvent event) {
//        setVisiblePane(albumPane);
        editAlbPane.setVisible(false);
        if (txt_editAlbArtistName != null) {
        txt_editAlbArtistName.clear();
    }
    if (edit_imgAlbView != null) {
        edit_imgAlbView.setImage(null);
    }
    
    imgAlbName = null;
    refreshAlbumUI();
    }
    
    @FXML
void handleEditAlbSaveAction(ActionEvent event) {
    String name = txt_editAlbArtistName.getText();

    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out album name!");
        return;
    }

    if (selectedAlbIndex == -1) {
        JOptionPane.showMessageDialog(null, "No album selected for update. Please select an album first.");
        return;
    }

    List<Albums> sourceList = isAlbumSearchActive ? filteredAlbums : allAlbums;
    if (selectedAlbIndex < 0 || selectedAlbIndex >= sourceList.size()) {
        JOptionPane.showMessageDialog(null, "Selected album is no longer valid. Please re-select.");
        return;
    }

    Albums selectedAlbum = sourceList.get(selectedAlbIndex);
    int albumId = selectedAlbum.getAlbumId();

    if (albumId <= 0) {
        JOptionPane.showMessageDialog(null, "Invalid Album ID obtained. Cannot update.");
        return;
    }

   
    String albImagePathToSave;

 
    String baseImageDir = "src\\Image_Video\\"; 

    if (imgAlbName == null || imgAlbName.isEmpty()) {
       
        albImagePathToSave = selectedAlbum.getAlbumImg();
        if (albImagePathToSave == null || albImagePathToSave.isEmpty()) {
           
             albImagePathToSave = baseImageDir + "mb2.jpg";
        }
    } else {
     
        albImagePathToSave = baseImageDir + imgAlbName; 
    }
   

    try (Connection con = new DbConnection().getConnection()) {
        String sql = "UPDATE albums SET album_name = ?, album_img = ? WHERE album_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, name);
        pst.setString(2, albImagePathToSave); 
        pst.setInt(3, albumId);

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Album updated successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Album not found or no changes made. (rowsAffected = 0)");
        }

        currentAlbumPage = 0;
        loadAlbumsFromDB();
        showAlbumPage(currentAlbumPage);
        txt_editAlbArtistName.clear();
        edit_imgAlbView.setImage(null);
        editAlbPane.setVisible(false);
        loadArtistsAndAlbums();

       
        imgAlbName = null;
        refreshAlbumUI();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating album: " + e.getMessage());
    }
}
    
    
@FXML
void handleEditUploadAction(ActionEvent event) throws IOException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.webp")
    );
    File file = fileChooser.showOpenDialog(MusicBoxService.stage); 
    if (file != null) {
        File destDir = new File("src\\Image_Video\\"); 
        if (!destDir.exists()) destDir.mkdirs();

        File destFile = new File(destDir, file.getName());
        Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        artistImgName = file.getName(); 
        Image image = new Image(destFile.toURI().toString());

        edit_imgView.setImage(image); 
        edit_imgView.setPreserveRatio(true);
    }
}
    
    @FXML
private void handleEditBackAction(ActionEvent event) {
    editArtistPane.setVisible(false); 

    if (artistListPane != null) { 
        artistListPane.setVisible(true);
    }
 
    edit_txtArtistName.clear();
    combo_edit_txtGender.setValue(null);
    edit_txtCountry.clear();
    edit_imgView.setImage(null);
    artistImgName = null;
    refreshArtistUI();
}
    
    @FXML
void handelEditSaveAction(ActionEvent event) { 
    String artistName = edit_txtArtistName.getText();
    String gender = combo_edit_txtGender.getValue().toString();
    String country = edit_txtCountry.getText();

  
    if (artistName.isEmpty() || gender.isEmpty() || country.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out all artist details!");
        return;
    }

   
    if (selectedArtistIndex == -1) {
        JOptionPane.showMessageDialog(null, "No artist selected for update. Please select an artist first.");
        return;
    }

    List<Artists> sourceList = isArtistSearchActive ? filteredArtists : allArtists;
    if (selectedArtistIndex < 0 || selectedArtistIndex >= sourceList.size()) {
        JOptionPane.showMessageDialog(null, "Selected artist is no longer valid. Please re-select.");
        return;
    }

    Artists selectedArtist = sourceList.get(selectedArtistIndex);
    int artistId = selectedArtist.getArtistId(); // Assuming Artists model has getArtistId()

    if (artistId <= 0) {
        JOptionPane.showMessageDialog(null, "Invalid Artist ID obtained. Cannot update.");
        return;
    }

  
    String artistImagePathToSave;
    String baseImageDir = "src\\Image_Video\\"; 

    if (artistImgName == null || artistImgName.isEmpty()) {
       
        artistImagePathToSave = selectedArtist.getArtistImg(); 
        if (artistImagePathToSave == null || artistImagePathToSave.isEmpty()) {
            artistImagePathToSave = baseImageDir + "default_artist.jpg"; 
        }
    } else {
      
        artistImagePathToSave = baseImageDir + artistImgName;
    }


    try (Connection con = new DbConnection().getConnection()) {
        String sql = "UPDATE artists SET artist_name = ?, gender = ?, country = ?, artist_img = ? WHERE artist_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, artistName);
        pst.setString(2, gender);
        pst.setString(3, country);
        pst.setString(4, artistImagePathToSave); 
        pst.setInt(5, artistId);

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Artist updated successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Artist not found or no changes made. (rowsAffected = 0)");
        }

        
        loadArtistsFromDB(); 
       

        edit_txtArtistName.clear();
         combo_edit_txtGender.setValue(null);
        edit_txtCountry.clear();
        edit_imgView.setImage(null);
        editArtistPane.setVisible(false); 

      
        artistImgName = null;
        refreshArtistUI();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating artist: " + e.getMessage());
    }
}
    
     @FXML
    void handleArtistEditAction(ActionEvent event) {

        if (selectedArtistIndex != -1) {
     
        List<Artists> sourceList = isArtistSearchActive ? filteredArtists : allArtists; 

        if (selectedArtistIndex >= 0 && selectedArtistIndex < sourceList.size()) {
            Artists selectedArtist = sourceList.get(selectedArtistIndex);

          
            edit_txtArtistName.setText(selectedArtist.getArtistName());
            combo_edit_txtGender.setValue(selectedArtist.getGender());
            edit_txtCountry.setText(selectedArtist.getCountry());

         
            String imagePath = selectedArtist.getArtistImg(); 
            if (imagePath != null && !imagePath.isEmpty()) {
                File file = new File(imagePath);
                if (file.exists() && !file.isDirectory()) {
                    try {
                        Image image = new Image(file.toURI().toString());
                        edit_imgView.setImage(image);
                    } catch (Exception e) {
                        System.err.println("Error loading image for artist: " + selectedArtist.getArtistName() + " - " + e.getMessage());
                        edit_imgView.setImage(null);
                    }
                } else {
                    System.err.println("Artist image file not found: " + imagePath);
                    edit_imgView.setImage(null);
                }
            } else {
                edit_imgView.setImage(null);
            }

        
           
            editArtistPane.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Selected artist index is out of bounds.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select an artist to edit.");
    }
    }
    
      @FXML
void handleEditAlbUploadAction(ActionEvent event) throws IOException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif","*.wepb")
    );
   
    File file = fileChooser.showOpenDialog(MusicBoxService.stage);
    if (file != null) {
        File destDir = new File("src/Image_Video/");
        if (!destDir.exists()) destDir.mkdirs();

        File destFile = new File(destDir, file.getName());
       
        Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

       
        imgAlbName = file.getName(); 

       
        Image image = new Image(destFile.toURI().toString());

      
        edit_imgAlbView.setImage(image); 
        edit_imgAlbView.setPreserveRatio(true);
    }
}
     @FXML
    void handleEditUploadSongImgAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Song Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.webp")
        );
        File file = fileChooser.showOpenDialog(MusicBoxService.stage); 
        if (file != null) {
            try {
              
                File destDir = new File("src/Image_Video/");
                if (!destDir.exists()) destDir.mkdirs();

                File destFile = new File(destDir, file.getName());
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

               
                tempUploadedImageFilePath = destFile.getAbsolutePath(); 
                Image image = new Image(destFile.toURI().toString());
                edit_imgSongView.setImage(image);
                edit_imgSongView.setPreserveRatio(true);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Failed to upload song image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleEditUploadSongInstuPathAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Instrumental Audio File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac", "*.flac"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showOpenDialog(MusicBoxService.stage); 
        if (file != null) {
            try {
                File destDir = new File("src/Instrumental_Video/"); 
                if (!destDir.exists()) destDir.mkdirs();

                File destFile = new File(destDir, file.getName());
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                edit_txt_instuPath.setText(destFile.getAbsolutePath());
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Failed to upload instrumental audio file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }


    @FXML
    void handleEditUploadSongPathAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Song Audio File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp4", "*.wav", "*.aac", "*.flac"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showOpenDialog(MusicBoxService.stage); 
        if (file != null) {
            try {
                File destDir = new File("src/Music_Video/");
                if (!destDir.exists()) destDir.mkdirs();

                File destFile = new File(destDir, file.getName());
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                edit_txt_songPath.setText(destFile.getAbsolutePath());
               
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Failed to upload song audio file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }


    
    @FXML
    void handleEditSongBackAction(ActionEvent event) {
        setVisiblePane(songListPane); 
      
        edit_txtSongName.clear();
        edit_txtSongCategory.setValue(null);
        edit_txt_songPath.clear();
        edit_txt_instuPath.clear();
        edit_imgSongView.setImage(null);
        ((ComboBox<Artists>) edit_comboArtist).getSelectionModel().clearSelection();
        ((ComboBox<Albums>) edit_comboAlbum).getSelectionModel().clearSelection();
        
        editSongPane.setVisible(false);
        refreshSongUI();
    }

    @FXML
    void handleEditSongSaveAction(ActionEvent event) {
        Songs selectedSong = null;
        List<Songs> currentSongList = isSongSearchActive ? filteredSongs : allSongs;

        if (selectedSongIndex >= 0 && selectedSongIndex < currentSongList.size()) {
            selectedSong = currentSongList.get(selectedSongIndex);
        }

        if (selectedSong == null) {
            JOptionPane.showMessageDialog(null, "No song selected for editing.");
            return;
        }

        String songName = edit_txtSongName.getText();
        Songs songCategory = edit_txtSongCategory.getValue();
        String songPath = edit_txt_songPath.getText();
        String instruPath = edit_txt_instuPath.getText();
        Artists selectedArtist = (Artists) edit_comboArtist.getSelectionModel().getSelectedItem();
        Albums selectedAlbum = (Albums) edit_comboAlbum.getSelectionModel().getSelectedItem();

     
        if (songName.isEmpty() || songCategory==null || songPath.isEmpty() || instruPath.isEmpty() || selectedArtist == null || selectedAlbum == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all song details, select an artist and an album.");
            return;
        }

        String imagePathToSave = selectedSong.getSongImg(); 
     
        if (tempUploadedImageFilePath != null) {
          
            imagePathToSave = tempUploadedImageFilePath;
        }

        try (Connection con = new DbConnection().getConnection()) {
         
            String sql = "UPDATE songs SET song_name = ?, category = ?, file_path = ?, instrumental_file_path = ?, song_img = ?, artist_id = ?, album_id = ? WHERE song_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, songName);
            pst.setString(2, songCategory.toString());
            pst.setString(3, songPath);
            pst.setString(4, instruPath);
            pst.setString(5, imagePathToSave);
            pst.setInt(6, selectedArtist.getArtistId()); 
            pst.setInt(7, selectedAlbum.getAlbumId()); 
            pst.setInt(8, selectedSong.getId()); 

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Song updated successfully!");
                
                loadSongsFromDB();
                updateSongPane(allSongs);
                showSongPage(currentSongPage);
                editSongPane.setVisible(false);
                refreshSongUI();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update song.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating song: " + e.getMessage());
        }
    }
    
    
    @FXML
    void handleEditSongAction(ActionEvent event) {
        Songs selectedSong = null;
        List<Songs> currentSongList = isSongSearchActive ? filteredSongs : allSongs;

      
        if (selectedSongIndex >= 0 && selectedSongIndex < currentSongList.size()) {
            selectedSong = currentSongList.get(selectedSongIndex);
        }
       

        if (selectedSong != null) {
            System.out.println("DEBUG: Selected Song Name: " + selectedSong.getSongName()); 

      
        if (selectedSong.getArtist() != null) {
            System.out.println("DEBUG: Selected Song Artist (from selectedSong): ID=" + selectedSong.getArtist().getArtistId() + ", Name=" + selectedSong.getArtist().getArtistName());
        } else {
            System.out.println("DEBUG: Selected Song Artist (from selectedSong) IS NULL.");
        }

        if (selectedSong.getAlbum() != null) {
            System.out.println("DEBUG: Selected Song Album (from selectedSong): ID=" + selectedSong.getAlbum().getAlbumId() + ", Name=" + selectedSong.getAlbum().getAlbumName());
        } else {
            System.out.println("DEBUG: Selected Song Album (from selectedSong) IS NULL.");
        }
           
            edit_txtSongName.setText(selectedSong.getSongName());
            edit_txt_songPath.setText(selectedSong.getFilePath());
            edit_txt_instuPath.setText(selectedSong.getInstrumentalFilePath());
            
           
          

            
           String songImgFromDb = selectedSong.getSongImg(); 

        if (songImgFromDb != null && !songImgFromDb.isEmpty()) {
        
            songImgFromDb = songImgFromDb.trim().replace("\\", "/");

         
            if (songImgFromDb.endsWith(DEFAULT_SONG_IMAGE_FILENAME.replace("\\", "/"))) {
                try {
                  
                    URL defaultImageUrl = getClass().getResource("/" + IMAGE_DIRECTORY_NAME + "/" + DEFAULT_SONG_IMAGE_FILENAME);
                    if (defaultImageUrl != null) {
                        Image image = new Image(defaultImageUrl.toExternalForm());
                        edit_imgSongView.setImage(image);
                        edit_imgSongView.setPreserveRatio(true);
                        System.out.println("DEBUG: Loaded default song image from resources: " + defaultImageUrl.toExternalForm());
                        selectedSongImgPath = defaultImageUrl.getPath(); 
                    } else {
                        System.err.println("ERROR: Default song image (" + DEFAULT_SONG_IMAGE_FILENAME + ") not found in resources at /" + IMAGE_DIRECTORY_NAME + "/");
                        edit_imgSongView.setImage(null);
                        selectedSongImgPath = null;
                    }
                } catch (Exception e) {
                    System.err.println("ERROR loading default song image from resources: " + e.getMessage());
                    edit_imgSongView.setImage(null);
                    selectedSongImgPath = null;
                }
            } else {
               
                String finalImagePath;
                Path dbPath = Paths.get(songImgFromDb);

               
                if (dbPath.isAbsolute()) {
                    finalImagePath = songImgFromDb;
                    System.out.println("Image path from DB is already absolute: " + finalImagePath);
                } else {
                   
                    finalImagePath = IMAGE_BASE_PATH + songImgFromDb; 
                    System.out.println("Image path from DB is relative/filename, constructing full path: " + finalImagePath);
                }

                File imageFile = new File(finalImagePath);
                if (imageFile.exists() && !imageFile.isDirectory()) {
                    try {
                        Image image = new Image(imageFile.toURI().toString());
                        edit_imgSongView.setImage(image);
                        edit_imgSongView.setPreserveRatio(true);
                        System.out.println("DEBUG: Loaded specific song image from file: " + imageFile.getAbsolutePath());
                        selectedSongImgPath = imageFile.getAbsolutePath(); 
                    } catch (Exception e) {
                        System.err.println("ERROR loading specific song image from path: " + finalImagePath + " - " + e.getMessage());
                        edit_imgSongView.setImage(null);
                        selectedSongImgPath = null;
                    }
                } else {
                    System.err.println("ERROR: Specific song image file not found or is a directory at path: " + finalImagePath);
                    edit_imgSongView.setImage(null);
                    selectedSongImgPath = null; 
                 
                    try {
                         URL defaultImageUrl = getClass().getResource("/" + IMAGE_DIRECTORY_NAME + "/" + DEFAULT_SONG_IMAGE_FILENAME);
                         if (defaultImageUrl != null) {
                             edit_imgSongView.setImage(new Image(defaultImageUrl.toExternalForm()));
                             System.out.println("DEBUG: Fallback to default image as specific not found.");
                             selectedSongImgPath = defaultImageUrl.getPath();
                         }
                    } catch (Exception e) {
                        System.err.println("ERROR loading fallback default image: " + e.getMessage());
                    }
                }
            }
        } else {
         
            System.out.println("DEBUG: No song image path in DB, loading default image.");
            try {
                URL defaultImageUrl = getClass().getResource("/" + IMAGE_DIRECTORY_NAME + "/" + DEFAULT_SONG_IMAGE_FILENAME);
                if (defaultImageUrl != null) {
                    Image image = new Image(defaultImageUrl.toExternalForm());
                    edit_imgSongView.setImage(image);
                    edit_imgSongView.setPreserveRatio(true);
                    selectedSongImgPath = defaultImageUrl.getPath();
                } else {
                    System.err.println("ERROR: Default song image (" + DEFAULT_SONG_IMAGE_FILENAME + ") not found in resources when DB path is empty!");
                    edit_imgSongView.setImage(null);
                    selectedSongImgPath = null;
                }
            } catch (Exception e) {
                System.err.println("ERROR loading default song image (DB path empty): " + e.getMessage());
                edit_imgSongView.setImage(null);
                selectedSongImgPath = null;
            }
        }



       
            try (Connection con = new DbConnection().getConnection()) {
                System.out.println("DEBUG: Database Connection established.");

            
                ObservableList<Artists> artists = FXCollections.observableArrayList();
                String artistSql = "SELECT artist_id, artist_name FROM artists";
                PreparedStatement artistPst = con.prepareStatement(artistSql);
                ResultSet artistRs = artistPst.executeQuery();
                System.out.println("DEBUG: Executed Artist SQL query.");

                Artists selectedArtistForCombo = null;
                int artistCount = 0;
                while (artistRs.next()) {
                    int artistId = artistRs.getInt("artist_id");
                    String artistName = artistRs.getString("artist_name");
                
                    Artists artist = new Artists(artistId, artistName, null, null, null); 
                    artists.add(artist);
                    artistCount++;
                    System.out.println("DEBUG: Loaded Artist - ID: " + artistId + ", Name: " + artistName);
                  
                    if (selectedSong.getArtist() != null && selectedSong.getArtist().getArtistId() == artist.getArtistId()) {
                        selectedArtistForCombo = artist;
                    }
                }
                System.out.println("DEBUG: Total Artists loaded: " + artistCount);
                if (artists.isEmpty()) {
                    System.out.println("DEBUG: The 'artists' list is EMPTY. No artists fetched from DB.");
                }

                ((ComboBox<Artists>) edit_comboArtist).setItems(artists);

            
                ((ComboBox<Artists>) edit_comboArtist).setCellFactory(lv -> new ListCell<Artists>() {
                    @Override
                    protected void updateItem(Artists artist, boolean empty) {
                        super.updateItem(artist, empty);
                        setText(empty ? "" : (artist != null ? artist.getArtistName() : ""));
                    }
                });

                ((ComboBox<Artists>) edit_comboArtist).setConverter(new StringConverter<Artists>() {
                    @Override
                    public String toString(Artists artist) {
                        return artist != null ? artist.getArtistName() : "";
                    }

                    @Override
                    public Artists fromString(String string) {
                        
                
                        return artists.stream()
                                      .filter(a -> a.getArtistName().equals(string))
                                      .findFirst()
                                      .orElse(null);
                    }
                });
           

                if (selectedArtistForCombo != null) {
                    ((ComboBox<Artists>) edit_comboArtist).getSelectionModel().select(selectedArtistForCombo);
                    System.out.println("DEBUG: Selected Artist in ComboBox: " + selectedArtistForCombo.getArtistName());
                } else {
                    System.out.println("DEBUG: No artist selected in ComboBox (either selectedSong.getArtist() is null or no match found).");
                }


                // --- For Albums ComboBox ---
                ObservableList<Albums> albums = FXCollections.observableArrayList();
                String albumSql = "SELECT album_id, album_name FROM albums";
                PreparedStatement albumPst = con.prepareStatement(albumSql);
                ResultSet albumRs = albumPst.executeQuery();
                System.out.println("DEBUG: Executed Album SQL query.");

                Albums selectedAlbumForCombo = null;
                int albumCount = 0;
                while (albumRs.next()) {
                    int albumId = albumRs.getInt("album_id");
                    String albumName = albumRs.getString("album_name");
                  
                    Albums album = new Albums(albumId, albumName); 
                    albums.add(album);
                    albumCount++;
                    System.out.println("DEBUG: Loaded Album - ID: " + albumId + ", Name: " + albumName);
                  
                    if (selectedSong.getAlbum() != null && selectedSong.getAlbum().getAlbumId() == album.getAlbumId()) {
                        selectedAlbumForCombo = album;
                    }
                }
                System.out.println("DEBUG: Total Albums loaded: " + albumCount);
                if (albums.isEmpty()) {
                    System.out.println("DEBUG: The 'albums' list is EMPTY. No albums fetched from DB.");
                }

                ((ComboBox<Albums>) edit_comboAlbum).setItems(albums);

              
                ((ComboBox<Albums>) edit_comboAlbum).setCellFactory(lv -> new ListCell<Albums>() {
                    @Override
                    protected void updateItem(Albums album, boolean empty) {
                        super.updateItem(album, empty);
                        setText(empty ? "" : (album != null ? album.getAlbumName() : ""));
                    }
                });

                ((ComboBox<Albums>) edit_comboAlbum).setConverter(new StringConverter<Albums>() {
                    @Override
                    public String toString(Albums album) {
                        return album != null ? album.getAlbumName() : "";
                    }

                    @Override
                    public Albums fromString(String string) {
                      
                        return albums.stream()
                                     .filter(a -> a.getAlbumName().equals(string))
                                     .findFirst()
                                     .orElse(null);
                    }
                });
            

                if (selectedAlbumForCombo != null) {
                    ((ComboBox<Albums>) edit_comboAlbum).getSelectionModel().select(selectedAlbumForCombo);
                    System.out.println("DEBUG: Selected Album in ComboBox: " + selectedAlbumForCombo.getAlbumName());
                } else {
                    System.out.println("DEBUG: No album selected in ComboBox (either selectedSong.getAlbum() is null or no match found).");
                }
                 populateSongCategoriesForEdit(); 
            if (selectedSong.getCategory() != null && !selectedSong.getCategory().isEmpty()) {
             
                Songs categoryToSelect = null;
                for (Songs s : edit_txtSongCategory.getItems()) {
                    if (s.getCategory() != null && s.getCategory().equalsIgnoreCase(selectedSong.getCategory())) {
                        categoryToSelect = s;
                        break;
                    }
                }
                if (categoryToSelect != null) {
                    edit_txtSongCategory.getSelectionModel().select(categoryToSelect);
                } else {
                    edit_txtSongCategory.getSelectionModel().clearSelection();
                }
            } else {
                edit_txtSongCategory.getSelectionModel().clearSelection();
            }


            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(null, "Error loading artists/albums for editing: " + e.getMessage());
                System.err.println("ERROR: Exception caught during database operation: " + e.getMessage());
            }

         
            editSongPane.setVisible(true);
            System.out.println("DEBUG: Edit song pane set to visible.");

        } else {
            JOptionPane.showMessageDialog(null, "Please select a song to edit.");
            System.out.println("DEBUG: No song selected for editing.");
        }
    }


    
    @FXML
    void handleAlbEditAction(ActionEvent event) {
      
         if (selectedAlbIndex != -1) {
            List<Albums> sourceList = isAlbumSearchActive ? filteredAlbums : allAlbums;

            if (selectedAlbIndex >= 0 && selectedAlbIndex < sourceList.size()) {
                Albums selectedAlbum = sourceList.get(selectedAlbIndex);

            
                txt_editAlbArtistName.setText(selectedAlbum.getAlbumName());

               
                String imagePath = selectedAlbum.getAlbumImg();
                if (imagePath != null && !imagePath.isEmpty()) {
                    File file = new File(imagePath);
                    if (file.exists() && !file.isDirectory()) {
                        try {
                            Image image = new Image(file.toURI().toString());
                            edit_imgAlbView.setImage(image);
                        } catch (Exception e) {
                            System.err.println("Error loading image for album: " + selectedAlbum.getAlbumName() + " - " + e.getMessage());
                            edit_imgAlbView.setImage(null);
                        }
                    } else {
                        System.err.println("Album image file not found: " + imagePath);
                        edit_imgAlbView.setImage(null);
                    }
                } else {
                    edit_imgAlbView.setImage(null);
                }

                editAlbPane.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Selected album index is out of bounds.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select an album to edit.");
        }
    }
    
     @FXML
void handleStaffEditAction(ActionEvent event) {
    if (selectedUser != null) {
     
        edit_txtUserName.setText(selectedUser.getUsername());

      
        try (Connection con = new DbConnection().getConnection()) {
            ObservableList<User> roles = FXCollections.observableArrayList();
           
            
            String roleSql = "SELECT DISTINCT role FROM users"; 
            PreparedStatement rolePst = con.prepareStatement(roleSql);
            ResultSet roleRs = rolePst.executeQuery();
            User selectedRoleForCombo = null;

            while (roleRs.next()) {
               
                User roleUser = new User(roleRs.getString("role"), null); 
                roles.add(roleUser);

                if (selectedUser.getRole() != null && selectedUser.getRole().equals(roleUser.getRole())) {
                    selectedRoleForCombo = roleUser;
                }
            }
            edit_comboRole.setItems(roles);
            if (selectedRoleForCombo != null) {
                edit_comboRole.getSelectionModel().select(selectedRoleForCombo);
            } else {
                edit_comboRole.getSelectionModel().clearSelection(); // No matching role found
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading roles for staff editing: " + e.getMessage());
        }

   
        EditStaffPane.setVisible(true);
//       edit_txtUserPsw.textProperty().addListener((obs, oldText, newText) -> {
//            if (newText.length() < 8) {
//                edit_lb_warning.setVisible(true);
//            }
//                else{
//                edit_lb_warning.setVisible(false);
//            }
//        });
    } else {
        JOptionPane.showMessageDialog(null, "Please select a staff member to edit.");
    }
}
    
    @FXML
void handleEditStaffBackAction(ActionEvent event) {
    EditStaffPane.setVisible(false); 
   
    edit_txtUserName.clear();
    edit_comboRole.getSelectionModel().clearSelection();
    selectedUser = null; 
}
    
@FXML
void handleEditStaffSaveAction(ActionEvent event) {
    if (selectedUser == null) {
        JOptionPane.showMessageDialog(null, "No staff member selected for editing.");
        return;
    }

    String newUserName = edit_txtUserName.getText().trim();
    User selectedRole = edit_comboRole.getSelectionModel().getSelectedItem();

    if (newUserName.isEmpty() || selectedRole == null) {
        JOptionPane.showMessageDialog(null, "User Name and Role cannot be empty.");
        return;
    }

    try (Connection con = new DbConnection().getConnection()) {
        String sql = "UPDATE users SET user_name = ?, role = ? WHERE user_id = ?"; // No password column update

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, newUserName);
        pst.setString(2, selectedRole.getRole()); 
        pst.setInt(3, selectedUser.getUserId()); 

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Staff details updated successfully!");
         
            loadUsersFromDatabase();
            handleEditStaffBackAction(null); 
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update staff details.");
        }

    } catch (SQLException | ClassNotFoundException e) { 
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database error during staff update: " + e.getMessage());
    }
   
}

private void setActiveButtonForAdmin(Button newActiveButton) {
        if (currentActiveButtonForAdmin != null) {
            currentActiveButtonForAdmin .getStyleClass().remove("active"); 
        }
        newActiveButton.getStyleClass().add("active"); 
        currentActiveButtonForAdmin  = newActiveButton; 
    }
     
   private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256"); 
    byte[] hash = md.digest(password.getBytes());
    StringBuilder hexString = new StringBuilder();
    for (byte b : hash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) hexString.append('0');
        hexString.append(hex);
    }
    return hexString.toString();
}

   @FXML
private void handleMinimize(ActionEvent event) {
    Stage stage = (Stage) btnMinimize.getScene().getWindow();
    stage.setIconified(true);
}

@FXML
private void handleClose(ActionEvent event) {
    Stage stage = (Stage) btnClose.getScene().getWindow();
    stage.close();
}

   private void loadRolesAndStatuses() throws ClassNotFoundException {
    try (Connection con = new DbConnection().getConnection()) {

        comboRole.getItems().clear();
//        comboUserStatus.getItems().clear();

        Set<String> roleSet = new HashSet<>();
//        Set<String> statusSet = new HashSet<>();

        String sql = "SELECT DISTINCT role FROM users";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String role = rs.getString("role");
//            String status = rs.getString("status");

            if (role != null && !role.trim().isEmpty()) {
                roleSet.add(role.trim());
            }
//            if (status != null && !status.trim().isEmpty()) {
//                statusSet.add(status.trim());
//            }
        }

        for (String role : roleSet) {
            comboRole.getItems().add(new User(role, null)); 
        }

//        for (String status : statusSet) {
//            comboUserStatus.getItems().add(new User(null, status)); 
//        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading role: " + e.getMessage());
    }
}
   private double fetchWeeklyTotal(int month, int year, int weekNumber) {
    
    LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
    LocalDate firstMonday = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    if (firstMonday.isAfter(firstDayOfMonth)) {
        firstMonday = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

   
    LocalDate weekStart = firstMonday.plusWeeks(weekNumber - 1);
    LocalDate weekEnd = weekStart.plusDays(6);

   
    if (weekStart.getMonthValue() != month ||  weekStart.getYear() != year) {
        weekStart = firstDayOfMonth;
    }
    if (weekEnd.getMonthValue() != month || weekEnd.getYear() != year) {
        weekEnd = LocalDate.of(year, month, firstDayOfMonth.lengthOfMonth());
    }

   
    String sql = "SELECT SUM(total) FROM room_details WHERE STR_TO_DATE(date, '%Y-%m-%d') BETWEEN ? AND ?";
    double weeklyTotal = 0.0;

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, weekStart.toString());
        pst.setString(2, weekEnd.toString());

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            weeklyTotal = rs.getDouble(1);
        }
    } catch (SQLException ex) {
        //System.getLogger(AdminDashboardController.class.getName())
          //  .log(System.Logger.Level.ERROR, "Failed to fetch weekly total for week " + weekNumber, ex);
    }

    return weeklyTotal;
}
   private void updateLineChart(int currentMonth, int previousMonth, int year) {
    progressChart.getData().clear();

    XYChart.Series<String, Number> currentSeries = new XYChart.Series<>();
    currentSeries.setName("Current Month");

    XYChart.Series<String, Number> previousSeries = new XYChart.Series<>();
    previousSeries.setName("Previous Month");

    for (int week = 1; week <= 5; week++) {
        double currentWeekTotal = fetchWeeklyTotal(currentMonth, year, week);
        double previousWeekTotal = fetchWeeklyTotal(previousMonth, (currentMonth == 1 ? year - 1 : year), week);

        currentSeries.getData().add(new XYChart.Data<>("Week " + week, currentWeekTotal));
        previousSeries.getData().add(new XYChart.Data<>("Week " + week, previousWeekTotal));
    }

    progressChart.getData().addAll(previousSeries, currentSeries);
}
   
   
   private void updateDetailedIncome(int monthNumber, int year) {
  
    String sql = "SELECT r.price, SUM(rd.total) AS type_income " +
                 "FROM room_details rd " +
                 "JOIN room r ON rd.room_id = r.room_id " +
                 "WHERE MONTH(STR_TO_DATE(rd.date, '%Y-%m-%d')) = ? AND YEAR(STR_TO_DATE(rd.date, '%Y-%m-%d')) = ? " +
                 "GROUP BY r.price ";

    // Reset labels to a default state before populating them
    vipincomeLabel.setText("KS 0.0");
    mediumincomeLabel.setText("Ks 0.0");
    smallincomeLabel.setText("KS 0.0");

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, monthNumber);
        pst.setInt(2, year);

        ResultSet rs = pst.executeQuery();

      
        while (rs.next()) {
            String roomPrice = rs.getString("price");
            double income = rs.getDouble("type_income");
            String formattedIncome = String.format("KS %,.1f", income);
            
            switch (roomPrice) {
                case "100000":
                    vipincomeLabel.setText(formattedIncome);
                    break;
                case "40000":
                    mediumincomeLabel.setText(formattedIncome);
                    break;
                case "25000":
                    smallincomeLabel.setText(formattedIncome);
                    break;
            }
        }
    } catch (SQLException ex) {
        System.getLogger(AdminDashboardController.class.getName())
              .log(System.Logger.Level.ERROR, "Failed to fetch detailed monthly income", ex);
    }
}

   @FXML
void SearchBooking(ActionEvent event) {
    performSearchBooking();
    
}
 private void performSearchBooking() {
        final String searchText = txtSearchBooking.getText().trim().toLowerCase();

        Task<ObservableList<Room_Details>> searchTask = new Task<>() {
            @Override
            protected ObservableList<Room_Details> call() throws Exception {
                ObservableList<Room_Details> filteredResults = FXCollections.observableArrayList();

                if (searchText.isEmpty()) {
                    filteredResults.addAll(roomDetailsList);
                } else {
                    for (Room_Details detail : roomDetailsList) {
                        boolean nameMatches = detail.getCustomerName() != null && detail.getCustomerName().toLowerCase().contains(searchText);
                        boolean phoneMatches = detail.getCustomerPhonenumber() != null && detail.getCustomerPhonenumber().toLowerCase().contains(searchText);

                        if (nameMatches || phoneMatches) {
                            filteredResults.add(detail);
                        }
                    }

                    
                    if (filteredResults.isEmpty()) {
                        String fallbackQuery = "SELECT rd.detail_id, r.room_id, r.room_type, r.price, rd.status, rd.date, rd.start_time, rd.end_time, rd.customer_name, rd.customer_phonenumber, rd.total " +
                                               "FROM room_details rd JOIN room r ON rd.room_id = r.room_id " +
                                               "WHERE rd.customer_name LIKE ? OR rd.customer_phonenumber LIKE ? And  DATE(rd.date) = CurrentDateTime()"; 

                        try (Connection con = new DbConnection().getConnection();
                             PreparedStatement pst = con.prepareStatement(fallbackQuery)) {

                            pst.setString(1, "%" + searchText + "%");
                            pst.setString(2, "%" + searchText + "%");

                            try (ResultSet rs = pst.executeQuery()) {
                                while (rs.next()) {
                                    Room room = new Room(rs.getInt("room_id"), rs.getString("room_type"), rs.getInt("price"));
                                    filteredResults.add(new Room_Details(
                                        rs.getInt("detail_id"), room, rs.getString("status"), rs.getString("date"),
                                        rs.getString("start_time"), rs.getString("end_time"), rs.getString("customer_name"),
                                        rs.getString("customer_phonenumber"), rs.getInt("total")
                                    ));
                                }
                            }
                        } catch (SQLException e) {
                          
                        }
                    }
                }
                return filteredResults;
            }
            @Override
            protected void succeeded() {
                ObservableList<Room_Details> fetchedList = getValue(); 

                
                tbRoomDetail.setItems(fetchedList);

               
            }

          
        };

      
        new Thread(searchTask).start();
    }

    @FXML
    void RefreshBooking(ActionEvent event) throws SQLException {
        try {
            loadRoomDetailsFromDatabase();
            loadRoomsAndSetupButtons();
            txtSearchBooking.setText("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error refreshing room details: " + e.getMessage());
        }
    }
     @FXML
    void BookingDelete(ActionEvent event) {
         Room_Details selectedBooking = tbRoomDetail.getSelectionModel().getSelectedItem();

        if (selectedBooking != null) {
           
            int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete the booking for " + selectedBooking.getCustomerName() + "?",
                "Confirm Booking Deletion",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                   
                    Connection con = new DbConnection().getConnection(); 
                    String sql = "DELETE FROM room_details WHERE detail_id = ?"; 
                    PreparedStatement pst = con.prepareStatement(sql);

                    
                    pst.setInt(1, selectedBooking.getDetailId()); 

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Booking deleted successfully!");
                        
                        loadRoomDetailsFromDatabase(); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete booking. Booking not found in database.");
                    }

                 
                    pst.close();
                    con.close();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Database driver not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            
            JOptionPane.showMessageDialog(null, "Please select a booking to delete.");
        }
    

    }
    
    @FXML
    void handleSettingAction(ActionEvent event) {
            setVisiblePane(adminSettingPane);
            setActiveButtonForAdmin(btnSetting);
    }

    
    @FXML
    private void handleChangePasswordAction(ActionEvent event) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ChangePassword.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.setTitle("Change Password");
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.initOwner(btn_changePassword.getScene().getWindow()); 
            stage.showAndWait(); 

        } catch (IOException e) {
            System.err.println("Failed to load ChangePassword.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
       
    
}











