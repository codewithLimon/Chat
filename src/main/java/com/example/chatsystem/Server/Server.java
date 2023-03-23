package com.example.chatsystem.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Socket socket=null;

        // read character from server
        InputStreamReader inputStreamReader=null;

        OutputStreamWriter outputStreamWriter=null;


        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;


        ServerSocket serverSocket=null;


        try{
            serverSocket=new ServerSocket(22222);

            while(true){
                try{
                    socket=serverSocket.accept();

                    inputStreamReader=new InputStreamReader(socket.getInputStream());
                    outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());


                    bufferedReader=new BufferedReader(inputStreamReader);
                    bufferedWriter=new BufferedWriter(outputStreamWriter);


                    while(true){
                        String msgFromClient=bufferedReader.readLine();

                        System.out.println("From client: "+msgFromClient);

                        Scanner sc=new Scanner(System.in);
                        String msgtoClient=sc.nextLine();
                        bufferedWriter.write(msgtoClient);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
