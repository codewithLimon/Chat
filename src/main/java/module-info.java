module com.example.chatsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens com.example.chatsystem.Server to javafx.fxml;
    exports com.example.chatsystem.Server;

    opens com.example.chatsystem.Client to javafx.fxml;
    exports com.example.chatsystem.Client;
}