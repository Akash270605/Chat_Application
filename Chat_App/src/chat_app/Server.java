/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final int DEFAULT_PORT = 5000;
    
    private final Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger clientCounter = new AtomicInteger(1);
    
    public static void main(String[] args){
        int port = DEFAULT_PORT;
        if(args.length > 0){
            try{
                port = Integer.parseInt(args[0]);
            }catch(NumberFormatException ignored){
                System.out.println("Invalid port. Using default: " + DEFAULT_PORT + ".");
            }
        }
        
        Server server = new Server();
        server.start(port);
    }
    
    private void start(int port){
        System.out.println("Chat server starting on port: " + port + "....");
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                pool.execute(handler);
            }
        }catch(IOException e){
            System.out.println("Server error: " + e.getMessage());
        }finally{
            pool.shutdown();
        }
    }
    
    private void broadcast(String message, ClientHandler sender){
        for(ClientHandler client : clients){
            if(client != sender){
                client.send(message);
            }
        }
    }
    
    private String resolveUniqueName(String requested){
        String base = (requested == null || requested.isBlank())
                ? "User" + clientCounter.getAndIncrement()
                : requested.trim();
        
        String candidate = base;
        int suffix = 1;
        while(nameExists(candidate)){
            candidate = base + "_"  + suffix;
            suffix ++;
        }
        
        return candidate;
    }
    
    private boolean nameExists(String name){
        for(ClientHandler client : clients){
            if(name.equalsIgnoreCase(client.getName())){
                return true;
            }
        }
        
        return false;
    }
    
    private class ClientHandler implements Runnable{
        private final Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;
        
        private ClientHandler(Socket socket){
            this.socket = socket;
        }
        
        @Override
        public void run(){
            try{
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                out.println("=========Welcome to the CHAT ROOM=======");
                out.println("Enter User's Name");
                String requestedName = in.readLine();
                name = resolveUniqueName(requestedName);
                
                clients.add(this);
                out.println("Your are now Known as " + name + ". Type 'q' to exit.");
                broadcast(name + " joined the chat.", this);
                System.out.println(name + " connected from " + socket.getRemoteSocketAddress());
                
                String line;
                while((line = in.readLine()) != null){
                    if(line.equalsIgnoreCase("q")){
                        break;
                    }
                    
                    String message = name + ": " + line;
                    broadcast(message, this);
                }
            }catch(IOException e){
                System.out.println("Connection error: " + e.getMessage());
            }finally {
                cleanup();
            }
        }
        
        private void cleanup(){
            clients.remove(this);
            if(name != null){
                broadcast(name + " left the chat.", this);
                System.out.println(name + " disconnected.");
            }
            try{
                if(socket != null && !socket.isClosed()){
                    socket.close();
                }
            }catch(IOException ignored){
                
            }
        }
        
        private void send(String message){
            if(out != null){
                out.println(message);
            }
        }
        
        private String getName(){
            return name;
        }
    }
}
