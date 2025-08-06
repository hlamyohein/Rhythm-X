package Controller;

import Controller.MediaPopUpController;
import Database.DbConnection;
import Database.MusicDAO;
import Model.Songs;
import Model.Artists;
import Model.Albums;
import Model.PlayableMediaItem;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import musicboxservice.MusicBoxService;
import javafx.stage.WindowEvent; 


public class UserDashboardController implements Initializable {

    @FXML private Button btnMinimize,  btnClose;
    @FXML
    private AnchorPane trendingPane;

    @FXML
    private AnchorPane center;

    @FXML
    private AnchorPane homePane;

    @FXML
    private AnchorPane songsPane;

    @FXML
    private AnchorPane albumPane;

    @FXML
    private AnchorPane settingPane;

    @FXML
    private AnchorPane artistsPane; 

    @FXML
    private MediaView trend_vid1;

    @FXML
    private Label song1;

    @FXML
    private MediaView trend_vid2;

    @FXML
    private Label song2;

    @FXML
    private MediaView trend_vid3;

    @FXML
    private Label song3;

    @FXML
    private MediaView trend_song1;

    @FXML
    private Label lb_song1;

    @FXML
    private MediaView trend_song2;

    @FXML
    private Label lb_song2;

    @FXML
    private MediaView trend_song3;

    @FXML
    private Label lb_song3;

    @FXML
    private MediaView trend_song4;

    @FXML
    private Label lb_song4;

    @FXML
    private MediaView trend_song5;

    @FXML
    private Label lb_song5;

    @FXML
    private MediaView trend_song6;

    @FXML
    private Label lb_song6;

    @FXML
    private MediaView trend_song7;
    @FXML
    private Label lb_song7;

    @FXML
    private MediaView trend_song8;

    @FXML
    private Label lb_song8;

    @FXML
    private MediaView trend_song9;

    @FXML
    private Label lb_song9;

    @FXML
    private MediaView trend_song10;

    @FXML
    private Label lb_song10;

    @FXML
    private MediaView trend_song11;

    @FXML
    private Label lb_song11;

    @FXML
    private MediaView trend_song12;

    @FXML
    private Label lb_song12;
    @FXML
    private Label lb_changeToJapanese;
    
    @FXML
    private Label lb_changePassword;
    
    @FXML
    private Label lb_trendingSongs;
    
    @FXML
    private Label lb_popularSingers;
   
    @FXML
    private Label lb_allTrendingSongs;
    
    @FXML
    private Label lb_favSongs;
    @FXML
    private ComboBox<String> combo_artistCategory;
    @FXML
    private ComboBox<String> combo_songCategory;
    @FXML
    private ComboBox<String> combo_albumCategory;

  
    @FXML private TextField txt_artistsearch;
    @FXML private GridPane artistGrid;
    @FXML private Button btnpevArtist;
    @FXML private Button btnNextArtist;
    @FXML private Label lbartistPagelabel;
    @FXML private ImageView ap_img_artist1;
    @FXML private Label ap_lb_artist1;
    @FXML private ImageView ap_img_artist2;
    @FXML private Label ap_lb_artist2;
    @FXML private ImageView ap_img_artist3;
    @FXML private Label ap_lb_artist3;
    @FXML private ImageView ap_img_artist4;
    @FXML private Label ap_lb_artist4;
    @FXML private ImageView ap_img_artist5;
    @FXML private Label ap_lb_artist5;
    @FXML private ImageView ap_img_artist6;
    @FXML private Label ap_lb_artist6;
     @FXML
    private ImageView ap_img_artist7;

    @FXML
    private Label ap_lb_artist7;

    @FXML
    private ImageView ap_img_artist8;

    @FXML
    private Label ap_lb_artist8;

    @FXML
    private ImageView ap_img_artist9;

    @FXML
    private Label ap_lb_artist9;

    @FXML
    private ImageView ap_img_artist10;

    @FXML
    private Label ap_lb_artist10;

    @FXML
    private ImageView ap_img_artist11;

    @FXML
    private Label ap_lb_artist11;

    @FXML
    private ImageView ap_img_artist12;

    @FXML
    private Label ap_lb_artist12;
    @FXML
    private Button btn_back;

    @FXML
    private Button toTrendingSongs;

    @FXML
    private AnchorPane popularSingerPane;

    @FXML
    private AnchorPane left_nav;

    @FXML
    private Button btn_Home;

    @FXML
    private FontAwesomeIcon homeIcon;

    @FXML
    private Button btnArtists; 

    @FXML
    private FontAwesomeIcon artistsIcon;

    @FXML
    private Button btnSongs;

    @FXML
    private FontAwesomeIcon songsIcon;

    @FXML
    private Button btnAlbum;

    @FXML
    private FontAwesomeIcon albumIcon;

    @FXML
    private Button btnSettings;

    @FXML
    private FontAwesomeIcon settingsIcon;
    @FXML
    private ImageView img_artist1;

    @FXML
    private Label lb_artist1;

    @FXML
    private ImageView img_artist2;

    @FXML
    private Label lb_artist2;

    @FXML
    private ImageView img_artist3;

    @FXML
    private Label lb_artist3;

    @FXML
    private ImageView img_artist4;

    @FXML
    private Label lb_artist4;

    @FXML
    private ImageView img_artist5;

    @FXML
    private Label lb_artist5;

    @FXML
    private ImageView img_artist6;

    @FXML
    private Label lb_artist6;

    @FXML
    private ImageView img_artist7;

    @FXML
    private Label lb_artist7;

    @FXML
    private ImageView img_artist8;

    @FXML
    private Label lb_artist8;
        
    @FXML
    private Button btnlogout;
    
    @FXML
    private Button btn_removeAll;
   
    @FXML
    private FontAwesomeIcon logoutIcon1;
     
    @FXML
    private TextField txt_searchSong;
     
    @FXML
    private TableView<Songs> tbSongView;

    @FXML
    private TableColumn<Songs, Boolean> col_radioButton;

    @FXML
    private TableColumn<Songs, String> col_SongImg;

    @FXML
    private TableColumn<Songs, String> col_SongName;

    @FXML
    private TableColumn<Songs, String> col_ArtistName; 

    @FXML
    private TableView<Songs> tosongqueue;

    @FXML
    private TableColumn<Songs, String> colsongqueueimg;

    @FXML
    private TableColumn<Songs, String> colsongqueuename;

    @FXML
    private TableColumn<Songs, Void> col_removeButton; 

    @FXML
    private Label lbPageLabel2;
    
    @FXML
    private Button btnSongPrevious;

    @FXML
    private Button btnSongNext;
     @FXML
    private Label lb_setting;

    @FXML
    private FontAwesomeIcon settingsIcon2;

    @FXML
    private FontAwesomeIcon edit_icon1;

    @FXML
    private Button btn_changeLanguage;

    @FXML
    private FontAwesomeIcon edit_icon2;

    @FXML
    private Button btn_changePassword;
    
 
    @FXML 
    private Button btnRefreshArtist;
    //for album
    @FXML 
    private Button btnRefreshAlbum;
    @FXML 
    private Button btnNextAlbum;

    @FXML
    private TextField txt_albumsearch;

    @FXML
    private Button btnpevAlbum;

    @FXML
    private GridPane albumGrid;

    @FXML
    private ImageView ap_img_album1;

    @FXML
    private Label ap_lb_album1;

    @FXML
    private ImageView ap_img_album2;

    @FXML
    private Label ap_lb_album2;

    @FXML
    private ImageView ap_img_album3;

    @FXML
    private Label ap_lb_album3;

    @FXML
    private ImageView ap_img_album4;

    @FXML
    private Label ap_lb_album4;

    @FXML
    private ImageView ap_img_album5;

    @FXML
    private Label ap_lb_album5;

    @FXML
    private ImageView ap_img_album6;

    @FXML
    private Label ap_lb_album6;

    @FXML
    private ImageView ap_img_album7;

    @FXML
    private Label ap_lb_album7;

    @FXML
    private ImageView ap_img_album8;

    @FXML
    private Label ap_lb_album8;

    @FXML
    private ImageView ap_img_album9;

    @FXML
    private Label ap_lb_album9;

    @FXML
    private ImageView ap_img_album10;

    @FXML
    private Label ap_lb_album10;

    @FXML
    private ImageView ap_img_album11;

    @FXML
    private Label ap_lb_album11;

    @FXML
    private ImageView ap_img_album12;

    @FXML
    private Label ap_lb_album12;

    @FXML
    private Label lbalbumPagelabel;
  
    @FXML
    private Label lb_artistCategory;
     
    @FXML
    private Label lb_songCategory;
    
    @FXML
    private Label lb_albumCategory;

    private MusicDAO musicDAO;

    private List<PlayableMediaItem> allPlayableMediaItems = new ArrayList<>();
    private List<Artists> allArtists = new ArrayList<>(); // Keep this for home page display
//    private List<Songs> songsList = new ArrayList<>();
    private ObservableList<Songs> songsList = FXCollections.observableArrayList();
    private List<Songs> filteredSongsList = new ArrayList<>();
   
 
   
    private List<Artists> artistList = new ArrayList<>(); 
    private List<Artists> filteredArtistList = new ArrayList<>(); 
    private final int artistsPerPage = 12; 
    private int currentArtistPage = 0; 
    private PauseTransition searchArtistPause;

      
    // For Songs
    private ObservableList<Songs> masterSongList;
    private FilteredList<Songs> filteredSongList;
    private SortedList<Songs> sortedSongList;
    private int currentSongPage = 0;
    private final int ITEMS_PER_SONG_PAGE = 5;
    private PauseTransition searchSongPane;
    private ToggleGroup songSelectionToggleGroup = new ToggleGroup(); 
    private ObservableList<Songs> songQueue = FXCollections.observableArrayList();

// Album-specific variables for pagination
        private List<Albums> allAlbums = new ArrayList<>(); 

    private List<Albums> albumList = new ArrayList<>(); 
    private List<Albums> filteredAlbumList = new ArrayList<>(); 
    private final int albumsPerPage = 12;
    private int currentAlbumPage = 0;
    private PauseTransition searchAlbumPause;

    private Connection con = null;
    Boolean japanese=false;
    private Button currentActiveButton;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
              DbConnection dbConnect = new DbConnection();
            con = dbConnect.getConnection(); 

            if (con == null) {
                System.err.println("ERROR: Database connection 'con' is NULL after calling DbConnection.getConnection().");
                System.err.println("Please check your DbConnection.java file for connection details and ensure MySQL server is running.");
                
            } else {
                System.out.println("DEBUG: Database connection 'con' initialized successfully.");
            }
            musicDAO = new MusicDAO();

         searchArtistPause = new PauseTransition(Duration.seconds(0.3)); 
        txt_artistsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchArtistPause.setOnFinished(event -> filterArtists(newValue));
            searchArtistPause.playFromStart(); 
           
        });
            
          // Initialize song table
            setupSongsTable();
            loadSongs();
            setupSongQueueTable();
            updateSongQueueListView();  
          
            setActiveButton(btn_Home);
            
        // Fetch all playable media items 
            allPlayableMediaItems = musicDAO.getAllPlayableMediaItems();
            

            List<Albums> fetchedAlbums = musicDAO.getAllAlbums();
            if (fetchedAlbums != null) {
                  albumList.addAll(fetchedAlbums);
                  filteredAlbumList.addAll(fetchedAlbums);
                  
                  // Fetch all song categories associated with albums
                  Map<Integer, Set<String>> albumSongCategoriesMap = musicDAO.getAllAlbumSongCategories();
                  
                  // Populate the songCategories for each Album object
                  for (Albums album : albumList) {
                      Set<String> categories = albumSongCategoriesMap.getOrDefault(album.getAlbumId(), new HashSet<>());
                      album.setSongCategories(categories);
                  }
                  
                  currentAlbumPage = 0;
                  updateAlbumDisplay(); // Initial display of albums
              } else {
                  System.out.println("No albums found in the database or getAllAlbums returned null.");
              }
            
            populateSongCategories();
           combo_songCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue != null) {
        filterSongsByCategory(newValue);
    } else {
      
        filteredSongList.setPredicate(song -> true); 
        currentSongPage = 0;
        updateSongsDisplay();
    }
});
            
            List<Songs> initialFetchedSongs = musicDAO.getAllSongs(); // Fetch all songs initially
            if (initialFetchedSongs != null) {
                songsList.clear();
                filteredSongsList.clear();
                songsList.addAll(initialFetchedSongs);
                filteredSongsList.addAll(initialFetchedSongs);
                updateSongsDisplay(); // Display all songs initially
            } else {
                System.out.println("No songs found during initial load.");
            }

        searchAlbumPause = new PauseTransition(Duration.seconds(0.3));
        txt_albumsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchAlbumPause.setOnFinished(event -> filterAlbums(newValue));
            searchAlbumPause.playFromStart();
        });
         
        
        
     filteredArtistList = new ArrayList<>(artistList); // Initialize filtered list with all artists
     List<Artists> fetchedArtists = musicDAO.getAllArtists();
     if (fetchedArtists != null) {
         allArtists.clear(); 
         artistList.clear();
         filteredArtistList.clear();
         
         allArtists.addAll(fetchedArtists); 
         artistList.addAll(fetchedArtists);
         filteredArtistList.addAll(fetchedArtists); 
         
        
         Map<Integer, Set<String>> artistSongCategoriesMap = musicDAO.getAllArtistSongCategories();
         
       
         for (Artists artist : artistList) {
             Set<String> categories = artistSongCategoriesMap.getOrDefault(artist.getArtistId(), new HashSet<>());
             artist.setSongCategories(categories);
         }
         
         currentArtistPage = 0;
         updateArtistDisplay();
     } else {
         System.out.println("No artists found in the database or getAllArtists returned null.");
     }
        updateArtistDisplay();


          
            List<PlayableMediaItem> videoItems = new ArrayList<>();
            List<PlayableMediaItem> songItems = new ArrayList<>();  
            for (PlayableMediaItem item : allPlayableMediaItems) {
                if (item.getVocalMp4Path() != null && !item.getVocalMp4Path().isEmpty()) {
                    videoItems.add(item);
                } else if (item.getVocalMp3Path() != null && !item.getVocalMp3Path().isEmpty()) {
                    songItems.add(item);
                }
            }

           
            Collections.sort(videoItems, Comparator.comparingInt(PlayableMediaItem::getSongId));

            


                musicDAO.deleteOldSongsFromQueue();
            List<Songs> popularSongs = musicDAO.getPopularSongs(); // Fetch popular songs
            MediaView[] popularSongMediaViews = {trend_vid1, trend_vid2, trend_vid3}; 
            Label[] popularSongLabels = {song1, song2, song3};
            List<PlayableMediaItem> allPopularPlayableMediaItems = new ArrayList<>();
            for (Songs song : popularSongs) {
            allPopularPlayableMediaItems.add(new PlayableMediaItem(
            song.getId(),
            song.getId(), 
            song.getSongName(),
            song.getFilePath(),         
            null,                       
            song.getInstrumentalFilePath(),
            null                       
            )); 
            }

            for (int i = 0; i < popularSongMediaViews.length && i < popularSongs.size(); i++) {
            final int currentSongDisplayIndex = i; 
            Songs popularSong = popularSongs.get(currentSongDisplayIndex); 
    
            setupVideo(popularSongMediaViews[currentSongDisplayIndex], popularSong.getFilePath());
          
            popularSongLabels[currentSongDisplayIndex].setText(popularSong.getSongName());


       
            popularSongMediaViews[currentSongDisplayIndex].setOnMouseClicked(event -> {
        
            if (popularSongMediaViews[currentSongDisplayIndex].getMediaPlayer() != null) {
            popularSongMediaViews[currentSongDisplayIndex].getMediaPlayer().stop();
            }

        
            int startIndexForPopup = currentSongDisplayIndex; 

        
            openMediaPopup(allPopularPlayableMediaItems, startIndexForPopup, popularSongMediaViews[currentSongDisplayIndex].getScene().getWindow());

        
            });
            }
            
            //artist category
            populateArtistCategories();
            combo_artistCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    filterArtistsByCategory(newValue);
                } else {
                   
                    filteredArtistList = new ArrayList<>(artistList);
                    currentArtistPage = 0;
                    updateArtistDisplay();
                }
            });
            
             populateAlbumCategories();
        combo_albumCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterAlbumsByCategory(newValue);
            } else {
                filteredAlbumList = new ArrayList<>(albumList);
                currentAlbumPage = 0;
                updateAlbumDisplay();
            }
        });
        
              

            
          // Bind trending song MediaViews and Labels 
          MediaView[] allTrendVidMediaViews = {trend_song1, trend_song2, trend_song3, trend_song4, trend_song5, trend_song6,
                                                 trend_song7, trend_song8, trend_song9, trend_song10, trend_song11, trend_song12};
          Label[] allTrendVidLabels = {lb_song1, lb_song2, lb_song3, lb_song4, lb_song5, lb_song6,
                                         lb_song7, lb_song8, lb_song9, lb_song10, lb_song11, lb_song12};

          List<Songs> popularSongsList = musicDAO.getPopularSongs();
          List<PlayableMediaItem> allPopularSongsPlayableMediaItems = new ArrayList<>();
          for (Songs song : popularSongsList) {
            allPopularSongsPlayableMediaItems.add(new PlayableMediaItem(
            song.getId(),
            song.getId(), 
            song.getSongName(),
            song.getFilePath(),         
            null,                       
            song.getInstrumentalFilePath(),
            null                       
        )); 
        }
        
          for (int i = 0; i < allTrendVidMediaViews.length && i < popularSongsList.size(); i++) {
          final int currentSongDisplayIndex = i; // This is the index within the displayed MediaViews
          Songs popularSong = popularSongsList.get(currentSongDisplayIndex); // The actual Song object
    
          setupVideo(allTrendVidMediaViews[currentSongDisplayIndex], popularSong.getFilePath());
          // Display song name on the label
          allTrendVidLabels[currentSongDisplayIndex].setText(popularSong.getSongName());


           
            allTrendVidMediaViews[currentSongDisplayIndex].setOnMouseClicked(event -> {
        
            if (allTrendVidMediaViews[currentSongDisplayIndex].getMediaPlayer() != null) {
            allTrendVidMediaViews[currentSongDisplayIndex].getMediaPlayer().stop();
           
            }

        
            int startIndexForPopup = currentSongDisplayIndex; 

        
            openMediaPopup(allPopularSongsPlayableMediaItems, startIndexForPopup, allTrendVidMediaViews[currentSongDisplayIndex].getScene().getWindow());

        
            });
            }

           
            List<Artists> popularArtists = musicDAO.getPopularArtists(); 
            ImageView[] artistImages = {img_artist1, img_artist2, img_artist3, img_artist4,
                                         img_artist5, img_artist6, img_artist7, img_artist8};
            Label[] artistNames = {lb_artist1, lb_artist2, lb_artist3, lb_artist4,
                                   lb_artist5, lb_artist6, lb_artist7, lb_artist8};

            for (int i = 0; i < artistImages.length && i < popularArtists.size(); i++) {
                Artists artist = popularArtists.get(i);
                if (artist.getArtistImg() != null && !artist.getArtistImg().isEmpty()) {
                    try {
                        URL imageUrl = getClass().getResource(artist.getArtistImg());
                        if (imageUrl != null) {
                            Image image = new Image(imageUrl.toExternalForm());
                            if (!image.isError()) {
                                artistImages[i].setImage(image);
                            } else {
                                System.err.println("ERROR: Image loading failed for " + artist.getArtistName() + ". Exception: " + image.exceptionProperty().get());
                            }
                        } else {
                            File imageFile = new File(artist.getArtistImg());
                            if (imageFile.exists()) {
                                try {
                                    Image image = new Image(imageFile.toURI().toString());
                                    if (!image.isError()) {
                                        artistImages[i].setImage(image);
                                    } else {
                                        System.err.println("ERROR: File system image loading failed for " + artist.getArtistName() + ". Exception: " + image.exceptionProperty().get());
                                    }
                                } catch (Exception ex) {
                                    System.err.println("EXCEPTION: Error creating Image from file system path for " + artist.getArtistName() + ": " + ex.getMessage());
                                }
                            } else {
                                System.err.println("ERROR: Neither classpath resource nor file system file found for artist " + artist.getArtistName() + " at path: " + artist.getArtistImg());
                            }
                             
                        }
                    
                    
                            final int artistId = artist.getArtistId();
                     
                         artistImages[i].setOnMouseClicked(event -> {
                        setActiveButton(btnSongs);
                        toSongAction(event,artistId,-1);
                        });       

                    } catch (Exception e) {
                        System.err.println("EXCEPTION: An unexpected error occurred while processing image for artist " + artist.getArtistName() + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("DEBUG: Artist " + artist.getArtistName() + " has no image path or it's empty in the database.");
                }
                artistNames[i].setText(artist.getArtistName());
            }
            
               // Bind artist images and names for artist page
              ImageView[] allArtistImages = {ap_img_artist1, ap_img_artist2, ap_img_artist3, ap_img_artist4,
                                         ap_img_artist5, ap_img_artist6, ap_img_artist7, ap_img_artist8,
                                         ap_img_artist9, ap_img_artist10, ap_img_artist11, ap_img_artist12};
              Label[] allArtistNames = {ap_lb_artist1,ap_lb_artist2,ap_lb_artist3, ap_lb_artist4,
                                          ap_lb_artist5, ap_lb_artist6,ap_lb_artist7, ap_lb_artist8,ap_lb_artist9,
                                          ap_lb_artist10,ap_lb_artist11,ap_lb_artist12};

             for (int i = 0; i < allArtistImages.length && i < allArtists.size(); i++) {
                Artists artist = allArtists.get(i);
                if (artist.getArtistImg() != null && !artist.getArtistImg().isEmpty()) {
                    try {
                        URL imageUrl = getClass().getResource(artist.getArtistImg());
                        if (imageUrl != null) {
                            Image image = new Image(imageUrl.toExternalForm());
                            if (!image.isError()) {
                                allArtistImages[i].setImage(image);
                            } else {
                                System.err.println("ERROR: Image loading failed for " + artist.getArtistName() + ". Exception: " + image.exceptionProperty().get());
                            }
                        } else {
                            File imageFile = new File(artist.getArtistImg());
                            if (imageFile.exists()) {
                                try {
                                    Image image = new Image(imageFile.toURI().toString());
                                    if (!image.isError()) {
                                        allArtistImages[i].setImage(image);
                                    } else {
                                        System.err.println("ERROR: File system image loading failed for " + artist.getArtistName() + ". Exception: " + image.exceptionProperty().get());
                                    }
                                } catch (Exception ex) {
                                    System.err.println("EXCEPTION: Error creating Image from file system path for " + artist.getArtistName() + ": " + ex.getMessage());
                                }
                            } else {
                                System.err.println("ERROR: Neither classpath resource nor file system file found for artist " + artist.getArtistName() + " at path: " + artist.getArtistImg());
                            }
                            
                        }
                        
                  final int artistId = artist.getArtistId();
                    
                     allArtistImages[i].setOnMouseClicked(event -> toSongAction(event, artistId,-1));
                        
                    } catch (Exception e) {
                        System.err.println("EXCEPTION: An unexpected error occurred while processing image for artist " + artist.getArtistName() + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("DEBUG: Artist " + artist.getArtistName() + " has no image path or it's empty in the database.");
                }
                allArtistNames[i].setText(artist.getArtistName());
            }
             
             searchSongPane = new PauseTransition(Duration.seconds(0.3)); // Initialize PauseTransition for songs
            txt_searchSong.textProperty().addListener((observable, oldValue, newValue) -> {
            searchSongPane.setOnFinished(event -> filterSongs(newValue)); // CORRECTED: Call filterSongs
            searchSongPane.play();
        });
         
             ImageView[] allAlbumImages = new ImageView[]{ap_img_album1, ap_img_album2, ap_img_album3, ap_img_album4,
                                            ap_img_album5, ap_img_album6, ap_img_album7, ap_img_album8,
                                            ap_img_album9, ap_img_album10, ap_img_album11, ap_img_album12};
           Label[] allAlbumNames = new Label[]{ap_lb_album1, ap_lb_album2, ap_lb_album3, ap_lb_album4,
                                        ap_lb_album5, ap_lb_album6, ap_lb_album7, ap_lb_album8,
                                        ap_lb_album9, ap_lb_album10, ap_lb_album11, ap_lb_album12};
      
    for (int i = 0; i < allAlbumImages.length && i < allAlbums.size(); i++) {
                Albums album= allAlbums.get(i);
                if (album.getAlbumImg() != null && !album.getAlbumImg().isEmpty()) {
                    try {
                        URL imageUrl = getClass().getResource(album.getAlbumImg());
                        if (imageUrl != null) {
                            Image image = new Image(imageUrl.toExternalForm());
                            if (!image.isError()) {
                                allAlbumImages[i].setImage(image);
                            } else {
                                System.err.println("ERROR: Image loading failed for " + album.getAlbumName() + ". Exception: " + image.exceptionProperty().get());
                            }
                        } else {
                            File imageFile = new File(album.getAlbumImg());
                            if (imageFile.exists()) {
                                try {
                                    Image image = new Image(imageFile.toURI().toString());
                                    if (!image.isError()) {
                                        allAlbumImages[i].setImage(image);
                                    } else {
                                        System.err.println("ERROR: File system image loading failed for " + album.getAlbumName() + ". Exception: " + image.exceptionProperty().get());
                                    }
                                } catch (Exception ex) {
                                    System.err.println("EXCEPTION: Error creating Image from file system path for " + album.getAlbumName() + ": " + ex.getMessage());
                                }
                            } else {
                                System.err.println("ERROR: Neither classpath resource nor file system file found for album" + album.getAlbumName() + " at path: " + album.getAlbumImg());
                            }
                            
                        }
                        
                  final int albumId= album.getAlbumId();
                    
                     allAlbumImages[i].setOnMouseClicked(event -> toSongAction(event,-1,albumId));
                        
                    } catch (Exception e) {
                        System.err.println("EXCEPTION: An unexpected error occurred while processing image for album" + album.getAlbumName() + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("DEBUG: Artist " +album.getAlbumName() + " has no image path or it's empty in the database.");
                }
                allAlbumNames[i].setText(album.getAlbumName());
            }
      } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, "Database driver not found.", ex);
            System.err.println("Connection error: Database driver not found. Please ensure your JDBC driver is in the classpath.");
        } catch (SQLException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, "Database connection error.", ex);
            System.err.println("Connection error: Could not establish database connection or SQL query failed. Check database server and credentials.");
        }
    }

   
    private void populateAlbumCategories() {
        ObservableList<String> categories = FXCollections.observableArrayList(
            "All Categories",
            "Myanmar",
            "English",
            "Korea",
            "Japan"
        );
        combo_albumCategory.setItems(categories);
        combo_albumCategory.getSelectionModel().selectFirst();
    }

    private void populateSongCategories() {
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.add("All Categories"); 

     
        try {
            Set<String> distinctDbCategories = musicDAO.getDistinctSongCategories();
            categories.addAll(distinctDbCategories);
        } catch (SQLException e) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, "Error populating song categories from DB", e);
            System.err.println("Error populating song categories from DB: " + e.getMessage());
           
        }

        combo_songCategory.setItems(categories);
        combo_songCategory.getSelectionModel().selectFirst(); 
    }

    private void populateArtistCategories() {
        ObservableList<String> categories = FXCollections.observableArrayList(
            "All Categories",
            "Myanmar",
            "English",
            "Korea",
            "Japan"
        );
        combo_artistCategory.setItems(categories);
        combo_artistCategory.getSelectionModel().selectFirst(); 
    }
    
    private void filterSongsByCategory(String category) {
    System.out.println("DEBUG: Filtering for category: '" + category + "'");

    if ("All Categories".equals(category)) {
     
        filteredSongList.setPredicate(song -> true);
    } else {
    
        filteredSongList.setPredicate(song -> {
            boolean matches = song.getCategory() != null &&
                              song.getCategory().equalsIgnoreCase(category);
            System.out.println("DEBUG: Song: '" + song.getSongName() + "', Category: '" + song.getCategory() + "' vs Target: '" + category + "' -> Match: " + matches);
            return matches;
        });
    }

   
    currentSongPage = 0; 
    updateSongsDisplay();
}
    
   private void updateSongsDisplay() {
    if (tbSongView == null) {
        System.err.println("Error: tbSongView is null in updateSongsDisplay. Ensure FXML is loaded correctly.");
        return;
    }

  
    int fromIndex = currentSongPage * ITEMS_PER_SONG_PAGE;
    int toIndex = Math.min(fromIndex + ITEMS_PER_SONG_PAGE, filteredSongList.size()); 

 
    ObservableList<Songs> songsOnCurrentPage = FXCollections.observableArrayList();
    if (fromIndex < toIndex) { 
        songsOnCurrentPage.addAll(filteredSongList.subList(fromIndex, toIndex));
    }

    tbSongView.setItems(songsOnCurrentPage); 

   
    updateSongPaginationControls();

    System.out.println("DEBUG: updateSongsDisplay() called. Showing " + songsOnCurrentPage.size() +
                       " songs on page " + (currentSongPage + 1) +
                       ". Total filtered songs: " + filteredSongList.size());
}
    
   private void updateSongPaginationControls() {
    int totalPages = (int) Math.ceil((double) filteredSongList.size() / ITEMS_PER_SONG_PAGE);
    if (totalPages == 0 && filteredSongList.isEmpty()) {
        lbPageLabel2.setText("No songs");
    } else {
        lbPageLabel2.setText("Page " + (currentSongPage + 1) + " of " + Math.max(1, totalPages));
    }

    btnSongPrevious.setDisable(currentSongPage == 0);
    btnSongNext.setDisable((currentSongPage + 1) * ITEMS_PER_SONG_PAGE >= filteredSongList.size());
}
    private void filterAlbumsByCategory(String category) {
        if ("All Categories".equals(category)) {
            filteredAlbumList = new ArrayList<>(albumList);
        } else {
        
            filteredAlbumList = albumList.stream()
                                    .filter(album -> {
                                        
                                        return album.getCategory() != null &&
                                               album.getCategory().stream()
                                                      .anyMatch(cat -> cat.equalsIgnoreCase(category));
                                    })
                                    .collect(Collectors.toList());
        }
        currentAlbumPage = 0; 
        updateAlbumDisplay(); 
    }
     private void filterArtistsByCategory(String category) {
        if ("All Categories".equals(category)) {
            filteredArtistList = new ArrayList<>(artistList);
        } else {
       
            filteredArtistList = artistList.stream()
                                    .filter(artist -> {
                                        
                                        return artist.getCategory() != null &&
                                               artist.getCategory().stream()
                                                      .anyMatch(cat -> cat.equalsIgnoreCase(category));
                                    })
                                    .collect(Collectors.toList());
        }
        currentArtistPage = 0;
        updateArtistDisplay(); 
    }
    
     private void setActiveButton(Button newActiveButton) {
        if (currentActiveButton != null) {
            currentActiveButton.getStyleClass().remove("active"); 
        }
        newActiveButton.getStyleClass().add("active"); 
        currentActiveButton = newActiveButton; 
    }
     
   
    public void playOpeningSong() {
        try {
           
            URL audioUrl = getClass().getResource("/Image_Video/opening_song.mp3");

            if (audioUrl != null) {
                Media media = new Media(audioUrl.toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                System.out.println("Playing opening song from resource: " + audioUrl.toExternalForm());
            } else {
                System.err.println("Opening song resource not found: /Image_Video/opening_song.mp3");
            }
        } catch (Exception e) {
            System.err.println("Error playing opening song: " + e.getMessage());
            e.printStackTrace();
        }
    }

        
    private void openMediaPopup(List<PlayableMediaItem> playlistItems, int startIndex, javafx.stage.Window ownerWindow) {
           
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MediaPopUp.fxml")); 
            Parent root = loader.load();

            MediaPopUpController mediaPopUpController = loader.getController();
            mediaPopUpController.setMedia(playlistItems, startIndex);

          
            Stage popUpStage = new Stage();
            popUpStage.initOwner(ownerWindow);
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.setScene(new Scene(root));
            popUpStage.setTitle("Now Playing"); 

           
            popUpStage.setOnCloseRequest(closeEvent -> {
                System.out.println("DEBUG: Media PopUp window is closing from UserDashboardController.");
         
                mediaPopUpController.stopAllMediaPlayers();
                mediaPopUpController.stopClapMediaPlayer(); 
            });

          
            popUpStage.show();

        } catch (IOException e) {
            System.err.println("Failed to load MediaPopUp FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    void toTrendingAction(ActionEvent event) throws IOException {
        homePane.setVisible(false);
        artistsPane.setVisible(false); 
        songsPane.setVisible(false);
        albumPane.setVisible(false);
        settingPane.setVisible(false);
        trendingPane.setVisible(true);
    }

    @FXML
    void toAlbumAction(ActionEvent event) {
        homePane.setVisible(false);
        artistsPane.setVisible(false); 
        songsPane.setVisible(false);
        albumPane.setVisible(true);
        settingPane.setVisible(false);
        trendingPane.setVisible(false);
        updateAlbumDisplay();
        txt_albumsearch.setText("");
         setActiveButton(btnAlbum);
        
    }
     @FXML
    void handleAlbumSearchAction(ActionEvent event) {
        filterAlbums(txt_albumsearch.getText());

    }
      @FXML
    void handleNextAlbumAction(ActionEvent event) {
        if (currentAlbumPage < (getMaxAlbumPages() - 1)) {
            currentAlbumPage++;
            updateAlbumDisplay();
        }

    }
     @FXML
    void handlePevAlbumAction(ActionEvent event) {
        if (currentAlbumPage > 0) {
            currentAlbumPage--;
            updateAlbumDisplay();
        }

    }
     private void filterAlbums(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            filteredAlbumList = new ArrayList<>(albumList); 
        } else {
            String lowerCaseSearchText = searchText.toLowerCase().trim();
            filteredAlbumList = albumList.stream()
                    .filter(album -> album.getAlbumName().toLowerCase().contains(lowerCaseSearchText))
                    .collect(Collectors.toList());
           
        }
        currentAlbumPage = 0; 
        updateAlbumDisplay();
    }

    private void updateAlbumDisplay() {
        int fromIndex = currentAlbumPage * albumsPerPage;
        int toIndex = Math.min(fromIndex + albumsPerPage, filteredAlbumList.size());

       
        if (fromIndex >= filteredAlbumList.size() && filteredAlbumList.size() > 0) {
            currentAlbumPage = (int) Math.ceil((double) filteredAlbumList.size() / albumsPerPage) - 1;
            if (currentAlbumPage < 0) currentAlbumPage = 0;
            fromIndex = currentAlbumPage * albumsPerPage;
            toIndex = Math.min(fromIndex + albumsPerPage, filteredAlbumList.size());
        } else if (filteredAlbumList.isEmpty()) {
            fromIndex = 0;
            toIndex = 0;
        }

        List<Albums> albumsToDisplay = filteredAlbumList.subList(fromIndex, toIndex);

        ImageView[] allAlbumImages = {ap_img_album1, ap_img_album2, ap_img_album3, ap_img_album4,
                                     ap_img_album5, ap_img_album6, ap_img_album7, ap_img_album8,
                                     ap_img_album9, ap_img_album10, ap_img_album11, ap_img_album12};
        Label[] allAlbumNames = {ap_lb_album1, ap_lb_album2, ap_lb_album3, ap_lb_album4,
                                  ap_lb_album5, ap_lb_album6, ap_lb_album7, ap_lb_album8,
                                  ap_lb_album9, ap_lb_album10, ap_lb_album11, ap_lb_album12};

       
        for (int i = 0; i < allAlbumImages.length; i++) {
            allAlbumImages[i].setImage(null);
            allAlbumNames[i].setText("");
            allAlbumImages[i].setVisible(false);
            allAlbumNames[i].setVisible(false); 
        }

        for (int i = 0; i < albumsToDisplay.size(); i++) {
            Albums album = albumsToDisplay.get(i);
            ImageView currentImageView = allAlbumImages[i];
            Label currentLabel = allAlbumNames[i];

            currentImageView.setVisible(true); 
            currentLabel.setVisible(true);    


if (album.getAlbumImg() != null && !album.getAlbumImg().isEmpty()) {
            try {
                Image image = null;
                URL imageUrl = getClass().getResource(album.getAlbumImg());
                if (imageUrl != null) {
                    image = new Image(imageUrl.toExternalForm());
                } else {
                    File imageFile = new File(album.getAlbumImg());
                    if (imageFile.exists()) {
                        image = new Image(imageFile.toURI().toString());
                    } else {
                        System.err.println("ERROR: Neither classpath resource nor file system file found for artist " + album.getAlbumName() + " at path: " + album.getAlbumImg());
                    }
                }

                if (image != null && !image.isError()) {
                    currentImageView.setImage(image);
                } else if (image != null) {
                    System.err.println("ERROR: Image loading failed for " + album.getAlbumName() + ". Exception: " + image.exceptionProperty().get());
                }
            } catch (Exception e) {
                System.err.println("EXCEPTION: An unexpected error occurred while processing image for artist " + album.getAlbumName() + ": " + e.getMessage());
            }
        } else {
            System.out.println("DEBUG: Artist " + album.getAlbumName() + " has no image path or it's empty in the database.");
            currentImageView.setImage(null); // Clear image if path is null/empty
        }
        currentLabel.setText(album.getAlbumName());
        
        
       

    

          
           final int albumId = album.getAlbumId();
           currentImageView.setOnMouseClicked(event -> {
            setActiveButton(btnSongs);
             toSongAction(event, -1, albumId);
            });
        }
        updateAlbumPaginationButtons();
        lbalbumPagelabel.setText("Page " + (currentAlbumPage + 1) + " of " + getMaxAlbumPages());
    }

    private int getMaxAlbumPages() {
        if (filteredAlbumList.isEmpty()) {
            return 1;
        }
        return (int) Math.ceil((double) filteredAlbumList.size() / albumsPerPage);
    }

    private void updateAlbumPaginationButtons() {
        btnpevAlbum.setDisable(currentAlbumPage == 0);
        btnNextAlbum.setDisable(currentAlbumPage >= (getMaxAlbumPages() - 1));
    }

  
    @FXML
    void toArtistAction(ActionEvent event) {
        homePane.setVisible(false);
        artistsPane.setVisible(true); 
        songsPane.setVisible(false);
        albumPane.setVisible(false);
        settingPane.setVisible(false);
        trendingPane.setVisible(false);
        updateArtistDisplay();
        txt_artistsearch.setText("");
        setActiveButton(btnArtists);
        
        
    }

    @FXML
    void handleArtistSearchAction(ActionEvent event) {
        filterArtists(txt_artistsearch.getText());
        
    }

    @FXML
    void handleNextArtistAction(ActionEvent event) {
      if (currentArtistPage < (getMaxArtistPages() - 1)) {
        currentArtistPage++;
        updateArtistDisplay();
    }
    }

    @FXML
    void handlePevArtistAction(ActionEvent event) {
        if (currentArtistPage > 0) {
        currentArtistPage--;
        updateArtistDisplay();
    }
        
    }
private void filterArtists(String searchText) {
    if (searchText == null || searchText.trim().isEmpty()) {
        filteredArtistList = new ArrayList<>(artistList); 
    } else {
        String lowerCaseSearchText = searchText.toLowerCase().trim();
        filteredArtistList = artistList.stream()
                .filter(artist -> artist.getArtistName().toLowerCase().contains(lowerCaseSearchText))
                .collect(Collectors.toList());
    }
    currentArtistPage = 0; 
    updateArtistDisplay();
}


private void updateArtistDisplay() {
  
    int fromIndex = currentArtistPage * artistsPerPage;
    int toIndex = Math.min(fromIndex + artistsPerPage, filteredArtistList.size());

    if (fromIndex >= filteredArtistList.size() && filteredArtistList.size() > 0) {
        currentArtistPage = (int) Math.ceil((double) filteredArtistList.size() / artistsPerPage) - 1;
        if (currentArtistPage < 0) currentArtistPage = 0; // Ensure page doesn't go below 0
        fromIndex = currentArtistPage * artistsPerPage;
        toIndex = Math.min(fromIndex + artistsPerPage, filteredArtistList.size());
    } else if (filteredArtistList.isEmpty()) {
        fromIndex = 0;
        toIndex = 0;
    }

    List<Artists> artistsToDisplay = filteredArtistList.subList(fromIndex, toIndex);

    ImageView[] allArtistImages = {ap_img_artist1, ap_img_artist2, ap_img_artist3, ap_img_artist4,
                                     ap_img_artist5, ap_img_artist6, ap_img_artist7, ap_img_artist8,
                                     ap_img_artist9, ap_img_artist10, ap_img_artist11, ap_img_artist12};
    Label[] allArtistNames = {ap_lb_artist1, ap_lb_artist2, ap_lb_artist3, ap_lb_artist4,
                                  ap_lb_artist5, ap_lb_artist6, ap_lb_artist7, ap_lb_artist8,
                                  ap_lb_artist9, ap_lb_artist10, ap_lb_artist11, ap_lb_artist12};

    
    for (int i = 0; i < allArtistImages.length; i++) {
        allArtistImages[i].setImage(null);
        allArtistNames[i].setText("");
        allArtistImages[i].setVisible(false); 
        allArtistNames[i].setVisible(false); 
    }

    for (int i = 0; i < artistsToDisplay.size(); i++) {
        Artists artist = artistsToDisplay.get(i);
        ImageView currentImageView = allArtistImages[i];
        Label currentLabel = allArtistNames[i];

        currentImageView.setVisible(true); 
        currentLabel.setVisible(true);     

        if (artist.getArtistImg() != null && !artist.getArtistImg().isEmpty()) {
            try {
                Image image = null;
                URL imageUrl = getClass().getResource(artist.getArtistImg());
                if (imageUrl != null) {
                    image = new Image(imageUrl.toExternalForm());
                } else {
                    File imageFile = new File(artist.getArtistImg());
                    if (imageFile.exists()) {
                        image = new Image(imageFile.toURI().toString());
                    } else {
                        System.err.println("ERROR: Neither classpath resource nor file system file found for artist " + artist.getArtistName() + " at path: " + artist.getArtistImg());
                    }
                }

                if (image != null && !image.isError()) {
                    currentImageView.setImage(image);
                } else if (image != null) {
                    System.err.println("ERROR: Image loading failed for " + artist.getArtistName() + ". Exception: " + image.exceptionProperty().get());
                }
            } catch (Exception e) {
                System.err.println("EXCEPTION: An unexpected error occurred while processing image for artist " + artist.getArtistName() + ": " + e.getMessage());
            }
        } else {
            System.out.println("DEBUG: Artist " + artist.getArtistName() + " has no image path or it's empty in the database.");
            currentImageView.setImage(null);
        }
        currentLabel.setText(artist.getArtistName());
        
         final int artistID = artist.getArtistId();
          currentImageView.setOnMouseClicked(event -> {
            setActiveButton(btnSongs);
            toSongAction(event,artistID,-1);
        });       

    }
    updateArtistPaginationButtons();
    lbartistPagelabel.setText("Page " + (currentArtistPage + 1) + " of " + getMaxArtistPages());
}


private int getMaxArtistPages() {
    if (filteredArtistList.isEmpty()) {
        return 1;
    }
    return (int) Math.ceil((double) filteredArtistList.size() / artistsPerPage);
}


private void updateArtistPaginationButtons() {
    btnpevArtist.setDisable(currentArtistPage == 0);
    btnNextArtist.setDisable(currentArtistPage >= (getMaxArtistPages() - 1));
}
   

    @FXML
    void toHomeAction(ActionEvent event) {
        homePane.setVisible(true);
        artistsPane.setVisible(false); 
        songsPane.setVisible(false);
        albumPane.setVisible(false);
        settingPane.setVisible(false);
        trendingPane.setVisible(false);
        setActiveButton(btn_Home);
        
    }

    @FXML
    void toSettingAction(ActionEvent event) {
        homePane.setVisible(false);
        artistsPane.setVisible(false); 
        songsPane.setVisible(true);
        albumPane.setVisible(false);
        settingPane.setVisible(true);
        trendingPane.setVisible(false);
        setActiveButton(btnSettings);
    }
//song section
    @FXML
    void toSongAction(ActionEvent event) {
        homePane.setVisible(false);
        artistsPane.setVisible(false); 
        songsPane.setVisible(true);
        albumPane.setVisible(false);
        settingPane.setVisible(false);
        trendingPane.setVisible(false);
        filteredSongList.setPredicate(s -> true); 
        updateSongsTable();
        txt_searchSong.setText("");
        setActiveButton(btnSongs);
        
      
    }
    

    
    public void toSongAction(javafx.scene.input.MouseEvent event, int artistId ,int albumId) {
        toSongActionInternal(artistId  , albumId);
       
    }

   
    private void toSongActionInternal(int artistId, int albumId) {
        homePane.setVisible(false);
        artistsPane.setVisible(false);
        songsPane.setVisible(true); 
        albumPane.setVisible(false);
        settingPane.setVisible(false);
        trendingPane.setVisible(false);

       
        if (artistId != -1) {
            filteredSongList.setPredicate(song -> song.getArtist() != null && song.getArtist().getArtistId() == artistId);
            txt_searchSong.setText("");
            currentSongPage = 0;
            updateSongsTable();
        } else if (albumId != -1) {
            filteredSongList.setPredicate(song -> song.getAlbum() != null && song.getAlbum().getAlbumId() == albumId);
            txt_searchSong.setText("");
            currentSongPage = 0;
            updateSongsTable();
        }
        else {
            filteredSongList.setPredicate(p -> true); 
            txt_searchSong.setText("");
            currentSongPage = 0;
            updateSongsTable();
        }
    }
     @FXML
    void nextSongAction(ActionEvent event) {
        if (currentSongPage < (getMaxSongPages() - 1)) {
            currentSongPage++;
            updateSongsTable();
        }
    }

    @FXML
    void pevSongaction(ActionEvent event) {
         if (currentSongPage > 0) {
            currentSongPage--;
            updateSongsTable();
        }
    }

    @FXML
    void searchSongAction(ActionEvent event) {
    filterSongs(txt_searchSong.getText());
    }
    private void setupSongsTable() {
    col_SongName.setCellValueFactory(new PropertyValueFactory<>("songName"));
//    col_SongImg.setCellValueFactory(new PropertyValueFactory<>("songImg"));
    col_ArtistName.setCellValueFactory(cellData -> {
        Songs song = cellData.getValue();
        if (song != null && song.getArtist() != null) {
            return new SimpleStringProperty(song.getArtist().getArtistName());
        }
        return new SimpleStringProperty(""); 
    });

    col_SongImg.setCellFactory(column -> new TableCell<Songs, String>() {
        private final ImageView imageView = new ImageView();
        private Image defaultImage = null;
        {
          
            String imageFileName = "mb2.jpg"; 

          
            File imageFile = new File("src/Image_Video/" + imageFileName); 
            try {
                if (imageFile.exists()) {
                    System.out.println("DEBUG: Image file found at: " + imageFile.getAbsolutePath());
                    defaultImage = new Image(imageFile.toURI().toString(), 100, 100, true, true);
                    if (defaultImage.isError()) {
                        System.err.println("DEBUG: Error loading image from URI: " + imageFile.toURI().toString() + " - " + defaultImage.exceptionProperty().get().getMessage());
                    } else {
                        System.out.println("DEBUG: Image loaded successfully from file: " + imageFile.getName());
                    }
                } else {
                    System.err.println("DEBUG: Image file NOT FOUND at: " + imageFile.getAbsolutePath());

                    String resourcePath = "/Image_Video/" + imageFileName; 
                    try {
                        if (getClass().getResource(resourcePath) != null) {
                             System.out.println("DEBUG: Trying to load image as resource from: " + resourcePath);
                             defaultImage = new Image(getClass().getResource(resourcePath).toExternalForm(), 100, 100, true, true);
                             if (defaultImage.isError()) {
                                System.err.println("DEBUG: Error loading image as resource from: " + resourcePath + " - " + defaultImage.exceptionProperty().get().getMessage());
                             } else {
                                System.out.println("DEBUG: Image loaded successfully as resource: " + resourcePath);
                             }
                        } else {
                            System.err.println("DEBUG: Image resource NOT FOUND at: " + resourcePath);
                        }
                    } catch (Exception resourceEx) {
                        System.err.println("DEBUG: Exception while trying to load image as resource: " + resourceEx.getMessage());
                    }
                }
            } catch (Exception e) {
                System.err.println("DEBUG: Generic error during image initialization: " + e.getMessage());
                defaultImage = null; 
            }
        }
        
        
        
        
       @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || defaultImage == null || defaultImage.isError()) {
                setGraphic(null);
            } else {
                imageView.setImage(defaultImage);
                imageView.setFitHeight(90);
                imageView.setFitWidth(90);
                setGraphic(imageView);
            }
        }
    });
// Set up the RadioButton column
    col_radioButton.setCellValueFactory(new PropertyValueFactory<>("selected")); 

    col_radioButton.setCellFactory(tc -> new TableCell<Songs, Boolean>() {
        private final RadioButton radioButton = new RadioButton();
        {
             radioButton.setToggleGroup(songSelectionToggleGroup);
            radioButton.setOnAction(event -> {
                Songs song = getTableView().getItems().get(getIndex());
                System.out.println("DEBUG: Radio button action for song: " + (song != null ? song.getSongName() : "null"));

                if (radioButton.isSelected()) {
                    System.out.println("DEBUG: Radio button is selected for " + song.getSongName());
                    if (!songQueue.contains(song)) {
                        songQueue.add(song);
                        System.out.println("DEBUG: Added to in-memory songQueue: " + song.getSongName());

                        
                            System.out.println("DEBUG: Attempting to add song to database queue: " + song.getSongName() + " (ID: " + song.getId() + ")");
                            addSongToQueue(song);
                      
                        
                    } else {
                        System.out.println("DEBUG: Song " + song.getSongName() + " is already in in-memory queue.");
                    }

                    for (Songs s : masterSongList) {
                        if (s != song && s.isSelected()) {
                            s.setSelected(false);
                        }
                    }
                }
                updateSongQueueListView();
            });
        }
 @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                radioButton.setSelected(item != null && item); 
                setGraphic(radioButton);
            }
        }
    });
}
    @FXML
    void RefreshSongAction(ActionEvent event) {
        
      
        txt_searchSong.setText("");
        filteredSongList.setPredicate(s -> true); 
        currentSongPage = 0;
        updateSongsTable();
       combo_songCategory.getSelectionModel().select("All Categories"); 
        
     
    }
private void loadSongs() throws SQLException {
        List<Songs> songsFromDB = musicDAO.getAllSongs();
        masterSongList = FXCollections.observableArrayList(songsFromDB);
        filteredSongList = new FilteredList<>(masterSongList, p -> true);
        sortedSongList = new SortedList<>(filteredSongList);
        sortedSongList.comparatorProperty().bind(tbSongView.comparatorProperty());
        updateSongsTable();
    }
private void filterSongs(String searchText) {
    String lowerCaseFilter = searchText.toLowerCase();

    filteredSongList.setPredicate(song -> {
        if (searchText == null || searchText.isEmpty()) {
            return true; 
        }

        String artistName = (song.getArtist() != null) ? song.getArtist().getArtistName() : null;
        String albumName = (song.getAlbum() != null) ? song.getAlbum().getAlbumName() : null;

        
        if (song.getSongName().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        }
       
        if (artistName != null && artistName.toLowerCase().contains(lowerCaseFilter)) {
            return true;
        }
      
        if (albumName != null && albumName.toLowerCase().contains(lowerCaseFilter)) {
            return true;
        }

        return false; 
    });

    currentSongPage = 0;
    updateSongsTable();
}


private void updateSongsTable() {
        int fromIndex = currentSongPage * ITEMS_PER_SONG_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_SONG_PAGE, sortedSongList.size());

        if (fromIndex > toIndex) {
            currentSongPage = (int) Math.ceil((double) sortedSongList.size() / ITEMS_PER_SONG_PAGE) - 1;
            if (currentSongPage < 0) currentSongPage = 0;
            fromIndex = currentSongPage * ITEMS_PER_SONG_PAGE;
            toIndex = Math.min(fromIndex + ITEMS_PER_SONG_PAGE, sortedSongList.size());
        }

        if (fromIndex < 0) fromIndex = 0;
        if (toIndex < fromIndex) toIndex = fromIndex;

        tbSongView.setItems(FXCollections.observableArrayList(sortedSongList.subList(fromIndex, toIndex)));
        updateSongPaginationButtons();
        lbPageLabel2.setText("Page " + (currentSongPage + 1) + " of " + getMaxSongPages());
        setupSongsTable();
    }

    private void updateSongPaginationButtons() {
        btnSongPrevious.setDisable(currentSongPage == 0);
        btnSongNext.setDisable(currentSongPage >= (getMaxSongPages() - 1));
    }

    private int getMaxSongPages() {
        return (int) Math.ceil((double) sortedSongList.size() / ITEMS_PER_SONG_PAGE);
    }

 private void addSongToQueue(Songs song) {

 System.out.println("DEBUG: Entering addSongToQueue method for song: " + song.getSongName());
    if (song == null) {
        System.err.println("ERROR: Attempted to add a null song to the queue. Aborting database operation.");
        return;
    }

    if (con == null) {
        System.err.println("ERROR: Database connection 'con' is null in addSongToQueue. Cannot save to database.");
        
    }

    String sql = "INSERT INTO song_queue (song_id) VALUES (?)";

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, song.getId());
       
        System.out.println("DEBUG: Executing SQL: " + sql + " with song_id: " + song.getId() + ", status: playing");

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("DEBUG: Song '" + song.getSongName() + "' (ID: " + song.getId() + ") successfully added to database queue. Rows affected: " + rowsAffected);
        } else {
            System.err.println("WARNING: Failed to add song '" + song.getSongName() + "' to database queue. No rows affected.");
            System.err.println("This might indicate a unique constraint violation if song_id is a primary key or unique.");
        }

    } catch (SQLException e) {
        System.err.println("CRITICAL ERROR: SQL Error while adding song to database queue for '" + song.getSongName() + "': " + e.getMessage());
        e.printStackTrace();

    }
    System.out.println("DEBUG: Exiting addSongToQueue method.");
}


 private void removeSongFromQueue(Songs song) {
        songQueue.remove(song);
        System.out.println("Removed from queue: " + song.getSongName());
        updateSongQueueListView(); 
    }

    public ObservableList<Songs> getSongQueue() {
        return songQueue;
    }
    private void updateSongQueueListView() {
    if (tosongqueue != null) {
        tosongqueue.setItems(songQueue);
        tosongqueue.refresh(); 
    }
}
    public void playSelectedSong() {
        Songs selectedSong = null;
        for (Songs song : filteredSongList) {
            if (song.isSelected()) {
                selectedSong = song;
                break;
            }
        }

        if (selectedSong != null) {
            int globalIndex = -1;
            for (int i = 0; i < allPlayableMediaItems.size(); i++) {
                PlayableMediaItem item = allPlayableMediaItems.get(i);
                if (item.getTitle() != null && item.getTitle().equals(selectedSong.getSongName()) &&
                    item.getVocalMp3Path() != null && item.getVocalMp3Path().equals(selectedSong.getFilePath())) {
                    globalIndex = i;
                    break;
                }
            }

            if (globalIndex != -1) {
                openMediaPopup(allPlayableMediaItems, globalIndex, tbSongView.getScene().getWindow());
            } else {
                System.err.println("Selected song not found in allPlayableMediaItems. Cannot play.");
            }
        } else {
            System.out.println("No song selected to play.");
        }
    }

   
public void playSongQueue() {
    if (!songQueue.isEmpty()) {
        List<PlayableMediaItem> queueAsPlayableMedia = new ArrayList<>();
        for (Songs song : songQueue) {
            queueAsPlayableMedia.add(new PlayableMediaItem(
                song.getId(),             
                song.getId(),             
                song.getSongName(),       
                song.getFilePath(),       
                null,                   
                song.getInstrumentalFilePath(), 
                null                      
            ));
        }

        openMediaPopup(queueAsPlayableMedia, 0, tbSongView.getScene().getWindow());

        songQueue.clear(); 
        updateSongQueueListView(); 
        for (Songs s : masterSongList) {
            s.setSelected(false);
        }

    } else {
        System.out.println("Song queue is empty. No songs to play.");
    }
}
    private void setupSongQueueTable() {
    colsongqueuename.setCellValueFactory(new PropertyValueFactory<>("songName"));
    colsongqueueimg.setCellValueFactory(new PropertyValueFactory<>("songImg"));
    col_removeButton.setCellFactory(tc -> new TableCell<Songs, Void>() {
        final Button btn = new Button("Remove");
        {
            btn.setOnAction(event -> {
                Songs song = getTableView().getItems().get(getIndex());
                removeSongFromQueue(song);
            });
            
            btn.setStyle("-fx-background-color: #f454ff;");
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(btn);
            }
        }
    });

    colsongqueueimg.setCellFactory(column -> new TableCell<Songs, String>() {
        private final ImageView imageView = new ImageView();
        private Image defaultImage = null;
        {
         
            String imageFileName = "mb2.jpg"; 
            File imageFile = new File("src/Image_Video/" + imageFileName); 
            try {
                if (imageFile.exists()) {
                    System.out.println("DEBUG: Image file found at: " + imageFile.getAbsolutePath());
                    defaultImage = new Image(imageFile.toURI().toString(), 100, 100, true, true);
                    if (defaultImage.isError()) {
                        System.err.println("DEBUG: Error loading image from URI: " + imageFile.toURI().toString() + " - " + defaultImage.exceptionProperty().get().getMessage());
                    } else {
                        System.out.println("DEBUG: Image loaded successfully from file: " + imageFile.getName());
                    }
                } else {
                    System.err.println("DEBUG: Image file NOT FOUND at: " + imageFile.getAbsolutePath());

                    
                    String resourcePath = "/Image_Video/" + imageFileName; 
                    try {
                        if (getClass().getResource(resourcePath) != null) {
                             System.out.println("DEBUG: Trying to load image as resource from: " + resourcePath);
                             defaultImage = new Image(getClass().getResource(resourcePath).toExternalForm(), 100, 100, true, true);
                             if (defaultImage.isError()) {
                                System.err.println("DEBUG: Error loading image as resource from: " + resourcePath + " - " + defaultImage.exceptionProperty().get().getMessage());
                             } else {
                                System.out.println("DEBUG: Image loaded successfully as resource: " + resourcePath);
                             }
                        } else {
                            System.err.println("DEBUG: Image resource NOT FOUND at: " + resourcePath);
                        }
                    } catch (Exception resourceEx) {
                        System.err.println("DEBUG: Exception while trying to load image as resource: " + resourceEx.getMessage());
                    }
                }
            } catch (Exception e) {
                System.err.println("DEBUG: Generic error during image initialization: " + e.getMessage());
                defaultImage = null; 
            }
        }
        
        
        
        
       @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || defaultImage == null || defaultImage.isError()) {
                setGraphic(null);
            } else {
                imageView.setImage(defaultImage);
                imageView.setFitHeight(90);
                imageView.setFitWidth(90);
                setGraphic(imageView);
            }
        }
    });

   tosongqueue.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) { 
            Songs selectedSong = tosongqueue.getSelectionModel().getSelectedItem();
            if (selectedSong != null) {
                playSongFromQueue(selectedSong);
            }
        }
    });

}

private void playSongFromQueue(Songs songToPlay) { 
    if (!songQueue.isEmpty()) {
        List<PlayableMediaItem> queueAsPlayableMedia = new ArrayList<>();
       
        int startIndex = 0; 
        for (int i = 0; i < songQueue.size(); i++) {
            Songs song = songQueue.get(i);
            queueAsPlayableMedia.add(new PlayableMediaItem(
                song.getId(),
                song.getId(),
                song.getSongName(),
                song.getFilePath(),
                null,                   
                song.getInstrumentalFilePath(),
                null
            ));
            if (song.equals(songToPlay)) {
                startIndex = i; 
            }
        }

        openMediaPopup(queueAsPlayableMedia, startIndex, tosongqueue.getScene().getWindow()); 

      
        updateSongQueueListView();
        for (Songs s : masterSongList) {
            s.setSelected(false);
        }
    } else {
        System.out.println("Song queue is empty. No songs to play.");
    }
}


      @FXML
    void tologoutAction(ActionEvent event) throws IOException {
Stage stage=MusicBoxService.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    


    @FXML
    void backAction(ActionEvent event) throws IOException {
        homePane.setVisible(true);
        artistsPane.setVisible(false); 
        songsPane.setVisible(false);
        albumPane.setVisible(false);
        settingPane.setVisible(false);
        trendingPane.setVisible(false);
    }



    private void setupVideo(MediaView mediaView, String resourcePath) {
        try {
            URL mediaUrl = null;
            if (resourcePath != null && !resourcePath.isEmpty()) {
                mediaUrl = getClass().getResource(resourcePath);
            }

            if (mediaUrl == null && resourcePath != null) {
                File file = new File(resourcePath);
                if (file.exists() && file.isFile()) {
                    mediaUrl = file.toURI().toURL();
                }
            }

            if (mediaUrl != null) {
                Media media = new Media(mediaUrl.toExternalForm());
                MediaPlayer player = new MediaPlayer(media);
                mediaView.setMediaPlayer(player);

                mediaView.setFitWidth(200);
                mediaView.setFitHeight(200);
                mediaView.setPreserveRatio(true);

                player.setMute(false); 
                player.setAutoPlay(false);
                player.setCycleCount(MediaPlayer.INDEFINITE);
            } else {
                System.err.println("Failed to load background media: " + resourcePath + ". Please check path and existence.");
                mediaView.setMediaPlayer(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error setting up background media for " + resourcePath + ": " + e.getMessage());
            mediaView.setMediaPlayer(null);
        }
    }
    
     @FXML
    void removeAllAction(ActionEvent event) {
        songQueue.clear();
        tosongqueue.setItems(songQueue);
    }
    
    @FXML
    void changeLanguageAction(ActionEvent event) {
 if(japanese){
            
            btn_Home.setText("Home");
            btnArtists.setText("Artists");
            btnSongs.setText("Songs");
            btnAlbum.setText("Albums");
            btnSettings.setText("Setting");
            btnlogout.setText("Logout");
            lb_changeToJapanese.setText("Change to Japanese");
            lb_changePassword.setText("Change Password");
            lb_trendingSongs.setText("Trending Songs");
            lb_popularSingers.setText("Popular Singers");
            lb_allTrendingSongs.setText("All Trending Songs");
            btn_back.setText("Back");
            toTrendingSongs.setText("See all > ");
            lb_setting.setText("Setting");
            lb_favSongs.setText("Favourite Song Lists");
            btn_changeLanguage.setText("Off");
            lb_artistCategory.setText("Category");
            lb_songCategory.setText("Category");
            lb_albumCategory.setText("Category");
          
           
        }
        else{
           
            btn_Home.setText("ホーム");
            btnArtists.setText("アーティスト");
            btnSongs.setText("歌");
            btnAlbum.setText("アルバム");
            btnSettings.setText("設定");
            btnlogout.setText("ログアウト");
            lb_changeToJapanese.setText("日本語に変更");
            lb_changePassword.setText("パスワードを変更する");
            lb_trendingSongs.setText("トレンドソング");
            lb_popularSingers.setText("人気歌手");
            lb_allTrendingSongs.setText("全てのトレンドソング");
            btn_back.setText("戻る");
            toTrendingSongs.setText("全て見る >");
            lb_setting.setText("設定");
            lb_favSongs.setText("お気に入りの歌リスト");
            btn_changeLanguage.setText("On");
            lb_artistCategory.setText("カテゴリ");
            lb_songCategory.setText("カテゴリ");
            lb_albumCategory.setText("カテゴリ");
        }
        
        japanese = !japanese;
        
    }

    @FXML
    void RefreshAlbumAction(ActionEvent event) {
       txt_albumsearch.setText("");
       filteredAlbumList = new ArrayList<>(albumList);
       currentAlbumPage = 0;
       updateAlbumDisplay();
       combo_albumCategory.getSelectionModel().select("All Categories"); 
    }

    @FXML
    void RefreshArtistAction(ActionEvent event) {
    
      txt_artistsearch.setText("");
      filteredArtistList = new ArrayList<>(artistList);
      currentArtistPage = 0;
      updateArtistDisplay();
       combo_artistCategory.getSelectionModel().select("All Categories"); 
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
    
    @FXML
    void changePasswordAction(ActionEvent event) {
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