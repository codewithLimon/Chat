package com.example.chatsystem.Client;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextAlignment;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ClientController implements Initializable{

    private Socket socket;
    private PrintWriter writer;
    private Scanner scanner;


    @FXML
    private TextArea msgSendBox;

    @FXML
    private TextArea msgShowArea;

    @FXML
    private MFXButton sendButton;

    @FXML
    void sendClicked(ActionEvent event) {
        writer.println(msgSendBox.getText());
        String msg = msgSendBox.getText();
        msgShowArea.appendText(msg+"\n");
        msgSendBox.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost", 22222);
            writer = new PrintWriter(socket.getOutputStream(), true);
            scanner = new Scanner(socket.getInputStream());
            Thread t = new Thread(() -> {
                while(true){
                    String input = scanner.nextLine();
                    msgShowArea.appendText("Server: "+input+"\n");
//                        msgShowArea.setStyle("-fx-text-fill: red");
                }
            });
            t.start();
        } catch (Exception e) {
        }
    }
}
