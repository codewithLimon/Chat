package com.example.chatsystem.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        //Socket is an endpoint of communication between two computers.
        //An endpoint us a combination of an ip address and a port number.
        //Sockets are used to send and receive data.

        Socket socket=null;

        // read character from server
        InputStreamReader inputStreamReader=null;

        OutputStreamWriter outputStreamWriter=null;


        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;

        try{
            socket=new Socket("localhost",22222);

            inputStreamReader=new InputStreamReader(socket.getInputStream());
            outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());

            bufferedReader=new BufferedReader(inputStreamReader);
            bufferedWriter=new BufferedWriter(outputStreamWriter);


            Scanner sc=new Scanner(System.in);


            while(true){
                String message=sc.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();


                String receivedMessage=bufferedReader.readLine();

                System.out.println("From server: "+receivedMessage);

                if(message.equalsIgnoreCase("BYE")){
                    break;
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(socket!=null){
                    socket.close();
                }
                if(inputStreamReader!=null){
                    inputStreamReader.close();
                }
                if(outputStreamWriter!=null){
                    outputStreamWriter.close();
                }
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }



    }
}
