/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final int DEFAULT_PORT = 5000;
    
    public static void main(String[] args){
        String host = "localhost";
        int port = DEFAULT_PORT;
        
        if(args.length > 0){
            host = args[0];
        }
        
        if(args.length > 1){
            try{
                port = Integer.parseInt(args[1]);
            }catch (NumberFormatException ignored){
                System.out.println("Invalid port. Using default: " + DEFAULT_PORT + ".");
            }
        }
        
        new Client().start(host, port);
    }
    
    private void start(String host, int port){
        System.out.println("Connecting to " + host + ":" + port  + "....");
        try(Socket socket = new Socket(host, port);
                BufferedReader serverIn  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))){
            
            Thread listener = new Thread(() -> {
                try{
                    String line;
                    while((line = serverIn.readLine()) != null){
                        System.out.println(line);
                    }
                }catch(IOException e){
                    System.out.println("Disconnected from server.");
                }
            });
            
            listener.setDaemon(true);
            listener.start();
            
            String input;
            while((input = userIn.readLine()) != null){
                serverOut.println(input);
                if(input.equalsIgnoreCase("q")){
                    break;
                }
            }
        }catch(IOException e){
            System.out.println("Unable to Connect: " + e.getMessage());
        }
    }
}
