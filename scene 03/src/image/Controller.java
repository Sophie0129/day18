package image;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller implements Initializable{
	@FXML public ListView<String> listView;
	@FXML public ImageView imageView;

	ObservableList<String> imageArr; //listview.setItem 인자값으로 사용
	String path;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		path = Paths.get("").toAbsolutePath().toString();
		System.out.println("path : "+path);
		path += "/src/resources/images";
		File fileList = new File(path);

		imageArr = FXCollections.observableArrayList();

		for(String p : fileList.list()) {
			System.out.println(p);
			imageArr.add(p);
		}
		listView.setItems(imageArr);
		setListEvent();
		
	}
	private void setListEvent() {
		//해당하는 기능의 메소드가 하나일때, 메소드명, 자료형, 오버라이딩 같은것을 생략하고 요소들의 변수만 적어주는것
		listView.getSelectionModel().selectedIndexProperty().addListener((a,b,c)->{
			System.out.println("객체 : "+a);
			System.out.println("이전 인텍스 : "+b);
			System.out.println("현재 인덱스 : "+c);
			setImageView((int)c); //리스트 누르자마자 이미지 뜨게함
		});
	}
	private void setImageView(int index) {
		/*
		 cici33_35_lommel2003.jpg
		 D:\0_김보선_핀테크\Java\workspace-java\scene 03/src/resources/images
		 */
		
		//"file:/"+path+"/"+imageArr.get(index)
		File file = new File(path+"/"+imageArr.get(index));
		System.out.println(file.toURI().toString()); 
		//위의 식은 file:/을 자동으로 붙혀주는 기능 안써도 됨

		//image img = new image(file.toURI().toString());
		Image img = new Image("file:/"+path+"/"+imageArr.get(index));
		imageView.setImage(img);

	}
	public void indexFunc() {
		System.out.println(listView.getSelectionModel().getSelectedIndex()); //목록의 인덱스값을 불러옴
		int index = listView.getSelectionModel().getSelectedIndex();
		System.out.println(imageArr.get(index));// 그 인덱스 값의 이미지를 불러옴
		System.out.println(path);//그 이미지의 경로를 불러옴

		setImageView(index);




	}
}
