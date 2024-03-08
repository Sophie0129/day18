package image;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller implements Initializable {
	@FXML public ListView<String> listView;
	@FXML public ImageView imageView;
	
	ObservableList<String> imageArr;//listview.setItem인자값으로 사용
	String path;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		path = Paths.get("").toAbsolutePath().toString();
		System.out.println( "path : " + path );
		path += "/src/resources/images";
		File fileList = new File(path);
		
		imageArr = FXCollections.observableArrayList();
		
		for(String p : fileList.list() ) {
			System.out.println( p );
			imageArr.add(p);
		}
		listView.setItems(imageArr);
		setListEvent();
	}
	private void setListEvent() {
		/*
		ChangeListener ch = ( arg0,  arg1,  arg2) -> {
				System.out.println(arg0);
				System.out.println(arg1);
				System.out.println(arg2);
		};
		listView.getSelectionModel().selectedIndexProperty().addListener(ch);
		*/
		listView.getSelectionModel().selectedIndexProperty().addListener( (a,b,c)->{
			System.out.println("객체 : "+a);
			System.out.println("이전 인덱스 : "+b);
			System.out.println("현재 인덱스 : "+c);
			setImageView( (int)c );
		} );
	}
	private void setImageView(int index) {
		//"file:/"+path+"/"+ imageArr.get(index)
		File file = new File(path +"/" +imageArr.get(index)  );
		System.out.println( file.toURI().toString() );
		
		//Image img = new Image( file.toURI().toString() );
		Image img = new Image( "file:/"+path+"/"+ imageArr.get(index) );
		imageView.setImage( img );
	}
	public void indexFunc() {
		System.out.println( listView.getSelectionModel().getSelectedIndex() );
		
		int index = listView.getSelectionModel().getSelectedIndex() ;
		System.out.println( imageArr.get(index) );
		System.out.println( path );
		
		setImageView( index );
	}
}









