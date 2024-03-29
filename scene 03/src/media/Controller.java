package media;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Controller implements Initializable {
	@FXML public ListView<String> listView;
	@FXML public MediaView mediaView;
	@FXML public Button btnPlay, btnPause, btnStop;
	
	@FXML public Label labelTime;
	@FXML public Slider sliderVolume;
	@FXML public ProgressBar progressBar;
	@FXML public ProgressIndicator progressIndicator;

	MediaPlayer mediaPlay;
	ObservableList<String> mediaList;
	String path;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mediaList = FXCollections.observableArrayList();
		path = Paths.get("").toAbsolutePath().toString();
		path += "/src/resources/media";
		File file = new File(path);
		for (String s : file.list()) {
			mediaList.add(s);
		}
		listView.setItems(mediaList);
		setMedia();

	}
	private void setMedia() {
		listView.getSelectionModel().selectedIndexProperty().addListener(
				(obj, preValue, newValue)->{
					//path += path+"/"+mediaList.get((int)newValue); // 패스의 값이 계속 누적되어 오류가 난다

					File file = new File(path+"/"+mediaList.get((int)newValue));

					System.out.println("path : "+path);
					System.out.println("file.toURI()"+file.toURI().toString());
					Media media = new Media(file.toURI().toString()); //미디어(영상)를 불러오는 역할

					mediaPlay = new MediaPlayer(media);
					mediaView.setMediaPlayer(mediaPlay);

					/*Runnable fn = new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub

						}*/

					mediaPlay.setOnReady(()->{
						btnPlay.setDisable(false); //활성화
						btnPause.setDisable(true);
						btnStop.setDisable(true); //비 활성화	
						setTime();
					});
					mediaPlay.setOnPlaying(()->{	
						btnPlay.setDisable(true);
						btnPause.setDisable(false);
						btnStop.setDisable(false);
					});

					mediaPlay.setOnPaused(()->{
						btnPlay.setDisable(false);
						btnPause.setDisable(true);
						btnStop.setDisable(false);
					});
					mediaPlay.setOnStopped(()->{
						btnPlay.setDisable(false);
						btnPause.setDisable(true);
						btnStop.setDisable(true);
					});
					mediaPlay.setOnEndOfMedia(()->{
						btnPlay.setDisable(false);
						btnPause.setDisable(true);
						btnStop.setDisable(true);
						mediaPlay.stop(); // 영상이 다 끝날경우 재생이 안되어서
						mediaPlay.seek(mediaPlay.getStartTime());
					});
				});
	}
	private void setTime() {
		mediaPlay.currentTimeProperty().addListener((obj, preValue, newValue)->{
			double currentTime = mediaPlay.getCurrentTime().toSeconds();
			double totalTime = mediaPlay.getTotalDuration().toSeconds();
			//흐르는 시간 (현재시간) / 최종시간
			double mediaTime = currentTime/totalTime;
			System.out.println(mediaTime);
			progressBar.setProgress(mediaTime);
			progressIndicator.setProgress(mediaTime);
			labelTime.setText((int)currentTime+"/"+(int)totalTime+"sec");
			
		});
	}
	public void playFunc() { mediaPlay.play();}
	public void pauseFunc() { mediaPlay.pause();}
	public void stopFunc() { mediaPlay.stop();}
	
	public void sliderFunc() {
		System.out.println(sliderVolume.getValue()/100);
		mediaPlay.setVolume(sliderVolume.getValue()/100);
	}
	
	
}

