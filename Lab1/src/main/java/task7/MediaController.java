package task7;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MediaController {
    public ProgressBar progressBar;
    public Slider volumeSlider;
    public Text volumeText;
    @FXML
    private MediaView mediaViewer;
    @FXML
    private Text currentTime;
    @FXML
    private Text totalTime;
    public void initialize() {
        Thread update = new Thread(new UpdateThread());
        update.start();
        volumeSlider.setValue(1);
        volumeSlider.setMin(0.);
        volumeSlider.setMax(1.);
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if(mediaViewer.getMediaPlayer() != null) {
                    mediaViewer.getMediaPlayer().setVolume(newValue.doubleValue());
                }
                volumeText.setText(String.format("%.2f",newValue.doubleValue()));
            }
        });
    }

    public void openFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Media file", "*.mp4"));
        File file = fc.showOpenDialog(null);
        if (file != null)
        {
            System.out.println(file.getAbsolutePath());
            try
            {
                URL url = new URL("file:///" + file.getAbsolutePath());
                Media media_obj = new Media(url.toString());
                MediaPlayer media_player = new MediaPlayer(media_obj);
                /*
            MediaPlayer mediaPlayer = new MediaPlayer(new Media("file:/" + file.getAbsolutePath().replace("\\", "/")));
            */

                media_player.setCycleCount(1);
                media_player.setVolume(volumeSlider.getValue());
                int currentTimeSeconds = (int) media_player.getTotalDuration().toSeconds();
                System.out.println(currentTimeSeconds);
                int timeMinute = currentTimeSeconds / 60;
                int timeSecond = currentTimeSeconds % 60;
                totalTime.setText(timeMinute + ":" + timeSecond);
                mediaViewer.setMediaPlayer(media_player);
                mediaViewer.getMediaPlayer().seek(Duration.ZERO);
            }
            catch (MalformedURLException exep)
            { exep.printStackTrace(); }
        }
    }

    public void closeFile(){
        if(mediaViewer.getMediaPlayer() != null){
            mediaViewer.setMediaPlayer(null);
        }
    }

    public void playVideo() {
        mediaViewer.getMediaPlayer().play();
    }

    public void pauseVideo() {
        mediaViewer.getMediaPlayer().pause();
    }

    public void seekNextTimeVideo() {
        mediaViewer.getMediaPlayer().seek(mediaViewer.getMediaPlayer().getCurrentTime().add(Duration.seconds(5)));
    }

    public void seekPrevTimeVideo() {
        mediaViewer.getMediaPlayer().seek(mediaViewer.getMediaPlayer().getCurrentTime().add(Duration.seconds(-5)));
    }

    public void exit(){
        closeFile();
        System.exit(0);
    }

    private class UpdateThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (mediaViewer.getMediaPlayer() != null) {
                    //Костыль
                    int totalTimeSeconds = (int) mediaViewer.getMediaPlayer().getTotalDuration().toSeconds();
                    int ttimeMinute = totalTimeSeconds / 60;
                    int ttimeSecond = totalTimeSeconds % 60;
                    totalTime.setText(ttimeMinute + ":" + ttimeSecond);
                    //Конец костыля
                    int currentTimeSeconds = (int) mediaViewer.getMediaPlayer().getCurrentTime().toSeconds();
                    int timeMinute = currentTimeSeconds / 60;
                    int timeSecond = currentTimeSeconds % 60;
                    currentTime.setText(timeMinute + ":" + timeSecond);
                    progressBar.setProgress((double) currentTimeSeconds/totalTimeSeconds); // ещё один костыль
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
