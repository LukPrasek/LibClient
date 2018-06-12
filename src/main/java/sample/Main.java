package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        System.out.println(Utils.makeRequest("http://localhost:8080/books"));
        //*************ODCZYT DANYCH Z SERWERA****************
//        RestTemplate restTemplate=new RestTemplate();
//        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
//        BookEntity bookEntity=restTemplate.getForObject("http://localhost:8080/book/2", BookEntity.class);
//        System.out.println(bookEntity.getTitle());
//        launch(args);
        //****************WYSYŁKA DANYCH NA SERWER**********
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
//        BookEntity bookEntity=restTemplate.getForObject("http://localhost:8080/book/2", BookEntity.class);
//        System.out.println(bookEntity.getTitle());

        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setTitle("Weszlo");
        bookEntity1.setAuthor("Stanowski");
        bookEntity1.setPages(11111);
        //MUSIMY UTWORZYC HEADER, KTORY PODALISMY DLA BAZY DANYCH JAKO KLUCZ
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("key", "212434152");

        HttpEntity<BookEntity> entity = new HttpEntity<>(bookEntity1, httpHeaders);//wysyła to do metody @Post
      // restTemplate.postForObject("http://localhost:8080/book", entity, String.class);

//**********USUNIECIE KSIAZKI O PODANYM ID**************//
        int pathId = 3;
        HttpEntity entity2 = new HttpEntity( httpHeaders);
        restTemplate.exchange("http://localhost:8080/book/" + pathId, HttpMethod.DELETE, entity2, String.class);

    }
}
