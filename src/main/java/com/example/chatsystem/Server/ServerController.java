package com.example.chatsystem.Server;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextAlignment;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ServerController implements Initializable{
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter writer;
    private Scanner scanner;
    @FXML
    private Label label1;


    @FXML
    private TextArea msgSendBox;

    @FXML
    private TextArea msgShowArea;

    @FXML
    private MFXButton sendButton;

    @FXML
    void sendClicked(ActionEvent event) {
        String msg = msgSendBox.getText();
        msgShowArea.appendText(msg+"\n");
        msgShowArea.setStyle("-fx-text-alignment: right");
        writer.println(msg);
        msgSendBox.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            serverSocket = new ServerSocket(22222);
            socket = serverSocket.accept();

            writer = new PrintWriter(socket.getOutputStream(),true);
            scanner = new Scanner(socket.getInputStream());
            label1.setText("Connected to "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
            Thread t = new Thread(() -> {
                while(true){
                    String input = scanner.nextLine();
                    msgShowArea.appendText("Client: "+input+"\n");
//                        msgShowArea.setStyle("-fx-text-fill: Blue");
                }
            });
            t.start();
        } catch (Exception e) {
        }
    }
}
