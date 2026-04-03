# Chat_Application

Company: CODTECH IT SOLUTIONS

Name: Akash Kumar

Intern ID : CTIS7110

Domain: Java Programming

Duration: 4 Weeks

Mentor: Neela Santosh

**Project Description:**

This project is a multi-client real-time chat application built using Java Socket Programming. It demonstrates how to design and implement a scalable client-server architecture that enables multiple users to communicate simultaneously over a network. The system consists of two main components: a Server that manages connections and message broadcasting, and a Client that allows users to send and receive messages in real time.

🚀 **Overview**

The chat application operates on a TCP-based communication model where the server listens for incoming client connections on a specified port. Once connected, each client can send messages that are instantly broadcasted to all other connected clients. The system supports multiple concurrent users, ensuring smooth and responsive communication.

🔑 **Key Features**

**1. Multi-Client Support**

The server is capable of handling multiple clients simultaneously using a thread pool (ExecutorService). Each client connection is managed independently, ensuring scalability and responsiveness.

**2. Real-Time Message Broadcasting**

Messages sent by a client are instantly broadcasted to all other connected users, creating a seamless chat experience.

**3. Unique Username Assignment**

Each user is assigned a unique name upon joining the chat. If a duplicate name is entered, the server automatically generates a unique variation to avoid conflicts.

**4. Concurrent Client Management**

The server uses a thread-safe collection (ConcurrentHashMap) to maintain active client connections, ensuring safe access in a multi-threaded environment.

**5. Graceful Connection Handling**

The application handles client disconnections gracefully. When a user leaves, all other participants are notified, maintaining chat awareness.

**6. Bidirectional Communication**

The client application uses separate threads to handle incoming messages from the server and outgoing messages from the user simultaneously.

**7. Command-Based Exit**

Users can exit the chat by typing a specific command (q), allowing for controlled termination of the session.

🛠️ **Technical Highlights**

* Built using Java’s java.net package (Socket, ServerSocket) for network communication
* Utilizes BufferedReader and PrintWriter for efficient text-based data transmission
* Implements multithreading using ExecutorService and custom Runnable handlers
* Ensures thread safety with ConcurrentHashMap and AtomicInteger
* Follows modular design with separation between server logic and client logic
* Uses daemon threads on the client side for continuous message listening
  
📚 **Learning Outcomes**

This project provides hands-on experience with:

* Client-server architecture design
* TCP socket communication
* Multithreading and concurrency in Java
* Synchronization and thread-safe data structures
* Real-time data exchange systems
  
✅ **Use Cases**

* Educational project for networking and distributed systems
* Foundation for building chat systems or messaging platforms
* Prototype for real-time collaboration tools
* Base implementation for extending features like private messaging, GUI, or encryption

**Output: **

<img width="500" height="300" alt="Screenshot 2026-04-03 124145" src="https://github.com/user-attachments/assets/6237b3f8-a585-4067-8b62-ccb8f5a88b9b" />

<img width="500" height="300" alt="Screenshot 2026-04-03 124155" src="https://github.com/user-attachments/assets/59335e53-3224-40b8-85c6-2e54dfc531d1" />

<img width="500" height="300" alt="Screenshot 2026-04-03 124203" src="https://github.com/user-attachments/assets/186fac9d-2d92-4031-8b12-d59d1de75187" />

<img width="500" height="300" alt="Screenshot 2026-04-03 124213" src="https://github.com/user-attachments/assets/3d7b8dbb-5353-4271-92bf-32d747c8afe1" />
