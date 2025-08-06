package Controller;

import Model.PlayableMediaItem;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;


public class MediaPopUpController implements Initializable {

    @FXML private Slider slider;
    @FXML private Label lblDuration;
    @FXML private Label lblTitle;
    @FXML private Button playButton;
    @FXML private Button btnPause;
    @FXML private Button stopButton;
    @FXML private Button btnRestart;
    @FXML private Button btnRepeat;
    @FXML private Button btnSlow;
    @FXML private Button btnFast;
    @FXML private Button btnNext;
    @FXML private Button btnPrev;
    @FXML private Button btnVolumePlus;
    @FXML private Button btnVolumeMinus;
    @FXML private Button muteButton; 
    @FXML private Button btnClap;


    @FXML private MediaView mediaVIew;

    private MediaPlayer vocalMediaPlayer;      
    private MediaPlayer instrumentalMediaPlayer;

    private List<PlayableMediaItem> playlist;
    private int currentMediaIndex;
    private double currentVolume = 0.5; 
    private boolean isInstrumentalPlaying = false; 
    private boolean isRepeatMode = false;
    private double currentPlaybackRate = 1.0; 
    private MediaPlayer clapMediaPlayer;
    private boolean isSliderBeingDragged = false;

 
    private MediaPlayer currentUiUpdatingPlayer;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("DEBUG: MediaPopUpController initialized.");

      
        updateVolume();


        //clap sound
       String clapSoundPath = "src/image_video/clapping_sound.mp3";
       File clapFile = new File(clapSoundPath);

        System.out.println("Attempting to load sound from: " + clapFile.getAbsolutePath()); 
        System.out.println("Does file exist? " + clapFile.exists()); 

    if (clapFile.exists()) {
    Media clapMedia = new Media(clapFile.toURI().toString());
    clapMediaPlayer = new MediaPlayer(clapMedia);
    clapMediaPlayer.setVolume(0.5);
    clapMediaPlayer.setAutoPlay(false);
    clapMediaPlayer.setCycleCount(1);
    System.out.println("Clap MediaPlayer successfully initialized.");
    } else {
    System.err.println("ERROR: Clapping sound file not found at: " + clapSoundPath);
    }

      
         slider.valueProperty().addListener((obs, oldVal, newVal) -> {
       
        if (!slider.isValueChanging()) {
          
        }
    });
    }

    @FXML
void sliderReleased(MouseEvent event) {
    isSliderBeingDragged = false;
    MediaPlayer activePlayer = getActivePlayer();
    if (activePlayer != null) {
        activePlayer.seek(Duration.seconds(slider.getValue()));
       
    }
    System.out.println("DEBUG: Slider released. Value: " + slider.getValue());
}

   public void stopClapMediaPlayer() {
    if (clapMediaPlayer != null) {
        clapMediaPlayer.stop();
        clapMediaPlayer.dispose();
        clapMediaPlayer = null;
        System.out.println("DEBUG: Clap MediaPlayer stopped and disposed.");
    }
}

   public void setMedia(List<PlayableMediaItem> playlist, int startIndex) {
    System.out.println("DEBUG: setMedia called. Playlist size: " + (playlist != null ? playlist.size() : "null") + ", startIndex: " + startIndex);
    this.playlist = playlist;
    this.currentMediaIndex = startIndex;
    isInstrumentalPlaying = false; 
    isRepeatMode = false; 

    loadMedia(currentMediaIndex); 

    //for clap sound
    if (lblTitle != null && lblTitle.getScene() != null && lblTitle.getScene().getWindow() != null) {
        lblTitle.getScene().getWindow().setOnCloseRequest(event -> {
            System.out.println("DEBUG: MediaPopUp window closing. Stopping all media players.");
            stopAllMediaPlayers(); 
            stopClapMediaPlayer(); 
        });
    }

 
    if (vocalMediaPlayer != null) {
        vocalMediaPlayer.setVolume(currentVolume); 
        vocalMediaPlayer.play();
        System.out.println("DEBUG: vocalMediaPlayer started playback in setMedia.");
    }
    if (instrumentalMediaPlayer != null) {
        instrumentalMediaPlayer.setVolume(0.0); 
        instrumentalMediaPlayer.play(); 
        System.out.println("DEBUG: instrumentalMediaPlayer started playback (muted) in setMedia.");
    } else {
         System.out.println("DEBUG: No instrumental MediaPlayer to play.");
    }

    updateMuteButtonText(); 
    updateRepeatButtonText(); 
    System.out.println("DEBUG: Initial repeat mode set to: " + isRepeatMode);

   
    setDurationListener(vocalMediaPlayer);
}

    private void loadMedia(int index) {
        System.out.println("DEBUG: loadMedia called for index: " + index);
        if (playlist == null || playlist.isEmpty() || index < 0 || index >= playlist.size()) {
            System.out.println("DEBUG: Playlist is empty or index is out of bounds in loadMedia. Returning.");
            return;
        }

       
        if (vocalMediaPlayer != null) {
            vocalMediaPlayer.stop();
            vocalMediaPlayer.dispose();
            System.out.println("DEBUG: Disposed vocalMediaPlayer.");
            vocalMediaPlayer = null;
        }
        if (instrumentalMediaPlayer != null) {
            instrumentalMediaPlayer.stop();
            instrumentalMediaPlayer.dispose();
            System.out.println("DEBUG: Disposed instrumentalMediaPlayer.");
            instrumentalMediaPlayer = null;
        }

        PlayableMediaItem mediaItem = playlist.get(index);
        lblTitle.setText(mediaItem.getTitle());
        System.out.println("DEBUG: Loading media for title: " + mediaItem.getTitle());

       
        String vocalMediaPath = null;
        if (mediaItem.getVocalMp4Path() != null && !mediaItem.getVocalMp4Path().isEmpty()) {
            vocalMediaPath = mediaItem.getVocalMp4Path(); 
        } else if (mediaItem.getVocalMp3Path() != null && !mediaItem.getVocalMp3Path().isEmpty()) {
            vocalMediaPath = mediaItem.getVocalMp3Path(); 
        }

        if (vocalMediaPath != null) {
            try {
                URL mediaUrl = getClass().getResource(vocalMediaPath);
                if (mediaUrl == null) {
                    File file = new File(vocalMediaPath);
                    if (file.exists() && file.isFile()) {
                        mediaUrl = file.toURI().toURL();
                    } else {
                        System.err.println("ERROR: Vocal media file not found (via File): " + vocalMediaPath);
                    }
                }
                if (mediaUrl != null) {
                    System.out.println("DEBUG: Found vocal media URL: " + mediaUrl.toExternalForm());
                    Media vocalMedia = new Media(mediaUrl.toExternalForm());
                    vocalMediaPlayer = new MediaPlayer(vocalMedia);
                   
                    vocalMediaPlayer.setRate(currentPlaybackRate);

                  vocalMediaPlayer.setOnReady(() -> {
    System.out.println("DEBUG: vocalMediaPlayer ON_READY. Total Duration: " + vocalMediaPlayer.getTotalDuration().toSeconds() + "s");
    if (!isInstrumentalPlaying) {
        slider.setMax(vocalMediaPlayer.getTotalDuration().toSeconds());
        lblDuration.setText("Duration : 0:00/" + formatTime(vocalMediaPlayer.getTotalDuration()));
        setDurationListener(vocalMediaPlayer); // Ensure UI listens to vocal
    }
    vocalMediaPlayer.setCycleCount(isRepeatMode ? MediaPlayer.INDEFINITE : 1);
    System.out.println("DEBUG: vocalMediaPlayer cycleCount set to: " + vocalMediaPlayer.getCycleCount() + " (isRepeatMode: " + isRepeatMode + ")");
});
                    vocalMediaPlayer.setOnEndOfMedia(() -> {
                        System.out.println("DEBUG: vocalMediaPlayer ON_END_OF_MEDIA reached. Repeat Mode: " + isRepeatMode);
                        if (isRepeatMode) {
                            vocalMediaPlayer.seek(Duration.ZERO);
                            vocalMediaPlayer.play();
                            System.out.println("DEBUG: Vocal media repeating.");
                        } else {
                            playNextMedia();
                            System.out.println("DEBUG: Vocal media not repeating, playing next.");
                        }
                    });
                     vocalMediaPlayer.setOnError(() -> {
                        System.err.println("ERROR: Vocal MediaPlayer encountered an error: " + vocalMediaPlayer.getError());
                    });
                } else {
                     System.err.println("ERROR: Could not resolve vocal media URL for path: " + vocalMediaPath);
                }
            } catch (Exception e) {
                System.err.println("ERROR: Error loading vocal media: " + vocalMediaPath + " - " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("DEBUG: No vocal/main media path found for: " + mediaItem.getTitle());
        }

      
        String instrumentalMediaPath = null;
        if (mediaItem.getInstrumentalMp4Path() != null && !mediaItem.getInstrumentalMp4Path().isEmpty()) {
            instrumentalMediaPath = mediaItem.getInstrumentalMp4Path();
        } else if (mediaItem.getInstrumentalMp3Path() != null && !mediaItem.getInstrumentalMp3Path().isEmpty()) {
            instrumentalMediaPath = mediaItem.getInstrumentalMp3Path();
        }

        // Crucial check: only load instrumental if it's a *different* file
        if (instrumentalMediaPath != null && !instrumentalMediaPath.equals(vocalMediaPath)) {
            try {
                URL mediaUrl = getClass().getResource(instrumentalMediaPath);
                if (mediaUrl == null) {
                    File file = new File(instrumentalMediaPath);
                    if (file.exists() && file.isFile()) {
                        mediaUrl = file.toURI().toURL();
                    } else {
                        System.err.println("ERROR: Instrumental media file not found (via File): " + instrumentalMediaPath);
                    }
                }
                if (mediaUrl != null) {
                    System.out.println("DEBUG: Found instrumental media URL: " + mediaUrl.toExternalForm());
                    Media instrumentalMedia = new Media(mediaUrl.toExternalForm());
                    instrumentalMediaPlayer = new MediaPlayer(instrumentalMedia);
                 
                    instrumentalMediaPlayer.setRate(currentPlaybackRate);
                   instrumentalMediaPlayer.setOnReady(() -> {
    System.out.println("DEBUG: instrumentalMediaPlayer ON_READY. Total Duration: " + instrumentalMediaPlayer.getTotalDuration().toSeconds() + "s");
    if (isInstrumentalPlaying) { 
       slider.setMax(instrumentalMediaPlayer.getTotalDuration().toSeconds());
       lblDuration.setText("Duration : 0:00/" + formatTime(instrumentalMediaPlayer.getTotalDuration()));
       setDurationListener(instrumentalMediaPlayer);
    }
    instrumentalMediaPlayer.setCycleCount(isRepeatMode ? MediaPlayer.INDEFINITE : 1);
    System.out.println("DEBUG: instrumentalMediaPlayer cycleCount set to: " + instrumentalMediaPlayer.getCycleCount() + " (isRepeatMode: " + isRepeatMode + ")");
});
                    instrumentalMediaPlayer.setOnError(() -> {
                        System.err.println("ERROR: Instrumental MediaPlayer encountered an error: " + instrumentalMediaPlayer.getError());
                    });
                } else {
                    System.err.println("ERROR: Could not resolve instrumental media URL for path: " + instrumentalMediaPath);
                }
            } catch (Exception e) {
                System.err.println("ERROR: Error loading instrumental media: " + instrumentalMediaPath + " - " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("DEBUG: No separate instrumental media path found or it's the same as vocal for: " + mediaItem.getTitle());
            if (instrumentalMediaPlayer != null) {
                instrumentalMediaPlayer.stop();
                instrumentalMediaPlayer.dispose();
                instrumentalMediaPlayer = null;
            }
        }

      
        if (vocalMediaPlayer != null) {
            mediaVIew.setMediaPlayer(vocalMediaPlayer);
            System.out.println("DEBUG: MediaView set to vocalMediaPlayer.");
        } else {
            mediaVIew.setMediaPlayer(null);
            System.out.println("DEBUG: MediaView set to null as no vocal player is available.");
        }
    }


    private MediaPlayer getActivePlayer() {
        if (isInstrumentalPlaying && instrumentalMediaPlayer != null) {
            return instrumentalMediaPlayer;
        } else if (vocalMediaPlayer != null) { 
            return vocalMediaPlayer;
        }
        return null;
    }

  
    public void stopAllMediaPlayers() {
       
        if (currentUiUpdatingPlayer != null && currentUiUpdatingPlayer.currentTimeProperty() != null) {
            currentUiUpdatingPlayer.currentTimeProperty().removeListener(this::updateSliderAndLabel);
            currentUiUpdatingPlayer = null;
        }

        if (vocalMediaPlayer != null) {
        vocalMediaPlayer.stop();
        vocalMediaPlayer.dispose();
        vocalMediaPlayer = null;
    }
    if (instrumentalMediaPlayer != null) {
        instrumentalMediaPlayer.stop();
        instrumentalMediaPlayer.dispose();
        instrumentalMediaPlayer = null;
    }
    System.out.println("DEBUG: Vocal/Instrumental MediaPlayers stopped and disposed.");


    }

    @FXML
    void play(ActionEvent event) {
        MediaPlayer activePlayer = getActivePlayer();
        if (activePlayer != null) {
            if (activePlayer.getStatus() == MediaPlayer.Status.PAUSED || activePlayer.getStatus() == MediaPlayer.Status.STOPPED) {
                activePlayer.play();
                System.out.println("DEBUG: Play button: Player resumed.");
            } else if (activePlayer.getStatus() == MediaPlayer.Status.READY || activePlayer.getStatus() == MediaPlayer.Status.HALTED) {
                activePlayer.play();
                System.out.println("DEBUG: Play button: Player started.");
            }
        } else {
            System.out.println("DEBUG: Play button: No active player to play.");
        }
    }

    @FXML
    void pause(ActionEvent event) {
        MediaPlayer activePlayer = getActivePlayer();
        if (activePlayer != null && activePlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            activePlayer.pause();
            System.out.println("DEBUG: Pause button: Player paused.");
        } else {
            System.out.println("DEBUG: Pause button: No active player to pause or not playing.");
        }
    }

    @FXML
    void stop(ActionEvent event) {
        MediaPlayer activePlayer = getActivePlayer();
        if (activePlayer != null) {
            activePlayer.stop();
            System.out.println("DEBUG: Stop button: Player stopped.");
            slider.setValue(0);
            lblDuration.setText("Duration : 0:00/" + formatTime(activePlayer.getTotalDuration()));
        } else {
            System.out.println("DEBUG: Stop button: No active player to stop.");
        }
    }

    @FXML
    void restart(ActionEvent event) {
        MediaPlayer activePlayer = getActivePlayer();
        if (activePlayer != null) {
            activePlayer.seek(Duration.ZERO);
            activePlayer.play();
            System.out.println("DEBUG: Restart button: Player restarted from beginning.");
        } else {
            System.out.println("DEBUG: Restart button: No active player to restart.");
        }
    }

    @FXML
    void repeat(ActionEvent event) {
        isRepeatMode = !isRepeatMode;
        updateRepeatButtonText();
        System.out.println("DEBUG: Repeat button clicked. isRepeatMode toggled to: " + isRepeatMode);

        if (vocalMediaPlayer != null) {
            vocalMediaPlayer.setCycleCount(isRepeatMode ? MediaPlayer.INDEFINITE : 1);
            System.out.println("DEBUG: vocalMediaPlayer cycleCount set to: " + vocalMediaPlayer.getCycleCount());
        }
        if (instrumentalMediaPlayer != null) {
            instrumentalMediaPlayer.setCycleCount(isRepeatMode ? MediaPlayer.INDEFINITE : 1);
            System.out.println("DEBUG: instrumentalMediaPlayer cycleCount set to: " + instrumentalMediaPlayer.getCycleCount());
        }
    }

    private void updateRepeatButtonText() {
        btnRepeat.setText("Repeat: " + (isRepeatMode ? "ON" : "OFF"));
    }
@FXML
void slow(ActionEvent event) {
    if (vocalMediaPlayer != null) {
        currentPlaybackRate = vocalMediaPlayer.getRate() * 0.8;
        vocalMediaPlayer.setRate(currentPlaybackRate);
        System.out.println("DEBUG: Slow button: Vocal playback rate set to: " + currentPlaybackRate);
    }
    if (instrumentalMediaPlayer != null) {
        instrumentalMediaPlayer.setRate(currentPlaybackRate);
        System.out.println("DEBUG: Slow button: Instrumental playback rate set to: " + currentPlaybackRate);
    }
    if (vocalMediaPlayer == null && instrumentalMediaPlayer == null) {
        System.out.println("DEBUG: Slow button: No media players available to change rate.");
    }

}

   @FXML
void fast(ActionEvent event) {
    if (vocalMediaPlayer != null) {
        currentPlaybackRate = vocalMediaPlayer.getRate() * 1.25;
        vocalMediaPlayer.setRate(currentPlaybackRate);
        System.out.println("DEBUG: Fast button: Vocal playback rate set to: " + currentPlaybackRate);
    }
    if (instrumentalMediaPlayer != null) {
        instrumentalMediaPlayer.setRate(currentPlaybackRate);
        System.out.println("DEBUG: Fast button: Instrumental playback rate set to: " + currentPlaybackRate);
    }
    if (vocalMediaPlayer == null && instrumentalMediaPlayer == null) {
        System.out.println("DEBUG: Fast button: No media players available to change rate.");
    }

}

    @FXML
    void volumeUp(ActionEvent event) {
        currentVolume = Math.min(1.0, currentVolume + 0.1);
        updateVolume();
        System.out.println("DEBUG: Volume up: currentVolume = " + currentVolume);
    }

    @FXML
    void volumeDown(ActionEvent event) {
        currentVolume = Math.max(0.0, currentVolume - 0.1);
        updateVolume();
        System.out.println("DEBUG: Volume down: currentVolume = " + currentVolume);
    }

    private void updateVolume() {
        if (!isInstrumentalPlaying && vocalMediaPlayer != null) {
            vocalMediaPlayer.setVolume(currentVolume);
        } else if (isInstrumentalPlaying && instrumentalMediaPlayer != null) {
            instrumentalMediaPlayer.setVolume(currentVolume);
        }
    }


    @FXML
void mute(ActionEvent event) {
    System.out.println("DEBUG: Mute/Toggle button clicked. Current instrumental playing: " + isInstrumentalPlaying);

  
    Duration currentTime = Duration.ZERO;
    if (isInstrumentalPlaying && instrumentalMediaPlayer != null) {
        currentTime = instrumentalMediaPlayer.getCurrentTime();
    } else if (!isInstrumentalPlaying && vocalMediaPlayer != null) {
        currentTime = vocalMediaPlayer.getCurrentTime();
    }

  
    isInstrumentalPlaying = !isInstrumentalPlaying;

  
    if (isInstrumentalPlaying) {
      
        if (instrumentalMediaPlayer != null) {
            instrumentalMediaPlayer.setVolume(currentVolume); 
            if (vocalMediaPlayer != null) vocalMediaPlayer.setVolume(0.0); 
            System.out.println("DEBUG: Switched to Instrumental (Muted Vocal).");
            setDurationListener(instrumentalMediaPlayer); 
            if (currentTime != null) instrumentalMediaPlayer.seek(currentTime); 
        } else {
           
            isInstrumentalPlaying = false; 
            if (vocalMediaPlayer != null) vocalMediaPlayer.setVolume(currentVolume); 
            System.out.println("DEBUG: No instrumental track available. Staying on Vocal.");
            setDurationListener(vocalMediaPlayer);
        }
    } else {
       
        if (vocalMediaPlayer != null) {
            vocalMediaPlayer.setVolume(currentVolume); 
            if (instrumentalMediaPlayer != null) instrumentalMediaPlayer.setVolume(0.0); 
            System.out.println("DEBUG: Switched to Vocal (Muted Instrumental).");
            setDurationListener(vocalMediaPlayer); 
            if (currentTime != null) vocalMediaPlayer.seek(currentTime); 
        } else {
          
            isInstrumentalPlaying = true; 
            if (instrumentalMediaPlayer != null) instrumentalMediaPlayer.setVolume(currentVolume); 
            System.out.println("DEBUG: No vocal track available. Staying on Instrumental.");
            setDurationListener(instrumentalMediaPlayer);
        }
    }

  
    if (currentTime != null) {
        if (vocalMediaPlayer != null && vocalMediaPlayer.getCurrentTime().toSeconds() != currentTime.toSeconds()) {
            vocalMediaPlayer.seek(currentTime);
        }
        if (instrumentalMediaPlayer != null && instrumentalMediaPlayer.getCurrentTime().toSeconds() != currentTime.toSeconds()) {
            instrumentalMediaPlayer.seek(currentTime);
        }
        System.out.println("DEBUG: Both players synchronized to " + currentTime.toSeconds() + "s.");
    }

    updateMuteButtonText();
}


    private void updateMuteButtonText() {
        if (muteButton != null) {
            if (isInstrumentalPlaying) {
                muteButton.setText("Instrumental On");
            } else {
                muteButton.setText("Vocal On");
            }
        }
    }

   @FXML
    void next(ActionEvent event) {
        if (isRepeatMode) {
            System.out.println("DEBUG: Next button ignored because repeat mode is ON.");
            return; 
        }
        playNextMedia();
    }

    @FXML
    void prev(ActionEvent event) {
        if (isRepeatMode) {
            System.out.println("DEBUG: Prev button ignored because repeat mode is ON.");
            return; 
        }
        playPreviousMedia();
    }

    private void playNextMedia() {
        if (playlist == null || playlist.isEmpty()) {
            System.out.println("DEBUG: playNextMedia: Playlist is empty.");
            return;
        }

        currentMediaIndex++;
        if (currentMediaIndex >= playlist.size()) {
            currentMediaIndex = 0; 
        }
        System.out.println("DEBUG: playNextMedia: Playing next item at index: " + currentMediaIndex);
        loadAndPlayCurrentMedia();
    }

    private void playPreviousMedia() {
        if (playlist == null || playlist.isEmpty()) {
            System.out.println("DEBUG: playPreviousMedia: Playlist is empty.");
            return;
        }

        currentMediaIndex--;
        if (currentMediaIndex < 0) {
            currentMediaIndex = playlist.size() - 1; 
        }
        System.out.println("DEBUG: playPreviousMedia: Playing previous item at index: " + currentMediaIndex);
        loadAndPlayCurrentMedia();
    }

   private void loadAndPlayCurrentMedia() {
   
    stopAllMediaPlayers();

    loadMedia(currentMediaIndex);

    
    if (vocalMediaPlayer != null) {
        vocalMediaPlayer.setVolume(isInstrumentalPlaying ? 0.0 : currentVolume);
        vocalMediaPlayer.play();
        System.out.println("DEBUG: Vocal player started with volume: " + vocalMediaPlayer.getVolume());
    }
    if (instrumentalMediaPlayer != null) {
        instrumentalMediaPlayer.setVolume(isInstrumentalPlaying ? currentVolume : 0.0);
        instrumentalMediaPlayer.play();
         System.out.println("DEBUG: Instrumental player started with volume: " + instrumentalMediaPlayer.getVolume());
    }

    
    if (isInstrumentalPlaying && instrumentalMediaPlayer != null) {
        setDurationListener(instrumentalMediaPlayer);
    } else if (vocalMediaPlayer != null) { 
        setDurationListener(vocalMediaPlayer);
    } else {
         System.out.println("DEBUG: No media player available to set duration listener.");
    }

    updateMuteButtonText();
    System.out.println("DEBUG: New media loaded and playback attempted. Initial instrumental playing state: " + isInstrumentalPlaying);
}
   
   
    @FXML
void sliderPressed(MouseEvent event) {
    isSliderBeingDragged = true; 
    System.out.println("DEBUG: Slider pressed. Value: " + slider.getValue());
}

    @FXML
void sliderDragged(MouseEvent event) {
    if (isSliderBeingDragged) {
        if (vocalMediaPlayer != null) {
            vocalMediaPlayer.seek(Duration.seconds(slider.getValue()));
        }
        if (instrumentalMediaPlayer != null) {
            instrumentalMediaPlayer.seek(Duration.seconds(slider.getValue()));
        }
    }
}


    private void setDurationListener(MediaPlayer playerToTrack) {
   
    if (currentUiUpdatingPlayer != null && currentUiUpdatingPlayer.currentTimeProperty() != null) {
        currentUiUpdatingPlayer.currentTimeProperty().removeListener(this::updateSliderAndLabel);
        System.out.println("DEBUG: Removed duration listener from previous player.");
    }

   
    if (playerToTrack != null && playerToTrack.currentTimeProperty() != null) {
        playerToTrack.currentTimeProperty().addListener(this::updateSliderAndLabel);
        currentUiUpdatingPlayer = playerToTrack; 
        System.out.println("DEBUG: Duration listener set to: " + (playerToTrack == vocalMediaPlayer ? "vocal" : "instrumental") + " player.");

       
        updateSliderAndLabel(playerToTrack.currentTimeProperty(), Duration.ZERO, playerToTrack.getCurrentTime());
    } else {
        currentUiUpdatingPlayer = null;
        System.out.println("DEBUG: No player to track, currentUiUpdatingPlayer set to null.");
      
        slider.setValue(0);
        lblDuration.setText("Duration : 0:00/0:00");
    }
}
     private void updateSliderAndLabel(javafx.beans.Observable obs, Duration oldTime, Duration newTime) {
  
    if (currentUiUpdatingPlayer != null && obs.equals(currentUiUpdatingPlayer.currentTimeProperty()) && !isSliderBeingDragged) {
        Duration total = currentUiUpdatingPlayer.getTotalDuration();
        if (total != null && !total.isUnknown()) {
            slider.setMax(total.toSeconds());

         
            double roundedTime = Math.round(newTime.toSeconds() * 100.0) / 100.0;
            slider.setValue(roundedTime);

            String elapsed = formatTime(newTime);
            String totalStr = formatTime(total);
            lblDuration.setText("Duration : " + elapsed + "/" + totalStr);
        }
    }
}
     
    private String formatTime(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) duration.toSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @FXML
    void clapAction(ActionEvent event) {
        if (clapMediaPlayer != null) {
           
            clapMediaPlayer.stop();
          
            clapMediaPlayer.play();
            System.out.println("Clapping sound played from src/image_video.");
        } else {
            System.err.println("Clap MediaPlayer is not initialized. Cannot play sound.");
        }
    }
}