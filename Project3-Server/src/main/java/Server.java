import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;


public class Server{

    int count = 1;
    public final ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;
    private final Consumer<Serializable> callback;


    Server(Consumer<Serializable> call){

        callback = call;
        server = new TheServer();
        server.start();
    }

    public void stop() throws IOException {
        server.socketRef.close();
    }

    public class TheServer extends Thread{
        ServerSocket socketRef;
        public void run() {

            try(ServerSocket mysocket = new ServerSocket(IntroController.getPortNum());){
                System.out.println("Server is waiting for a client!");
                socketRef = mysocket;

                while(clients.size() < 8) {

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    synchronized (clients) {
                        clients.add(c);
                    }
                    c.start();
                    count++;
                    callback.accept("Client has connected to server: " + "Client #" + count);
                }
            }//end of try
            catch(Exception e) {
                callback.accept("Server socket stopped.");
            }
        }//end of while
    }


    class ClientThread extends Thread{


        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;
        ArrayList<GameHistory> gameHistory = new ArrayList<>();

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }

        public void updateClients(String message) {
            synchronized (clients) {
                for (ClientThread t : clients) {
                    try {
                        t.out.writeObject(message);
                    } catch (Exception e) {}
                }
            }
        }

        public void run(){

            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

            updateClients("new client on server: client #"+count);

            while(true) {
                try {
                    callback.accept("Client #" +count+ " is playing a new hand.");
//                    //create and start new game
//                    Game game = new Game();
//                    game.startGame();
//
//                    //send player hand
//                    ArrayList<Object> response = new ArrayList<>();
//                    response.add(game.getPlayerHand());
//                    response.add(game.evaluateHand(game.getPlayerHand()));
//                    out.writeObject(response);
//
//                    //read player actions
//                    ArrayList<Integer> actions = (ArrayList<Integer>) in.readObject();
//                    response.clear();
//                    response.add(game.getDealerHand());
//
//                    //check if player folded or not
//                    if (actions.get(0) == 0){
//                        response.add(game.compareHands(game.getDealerHand(), game.getPlayerHand(), actions.get(1), actions.get(2)));
//                    }
//                    else{
//                        response.add(0);
//                    }
//                    response.add(game.calculatePairPlus(game.getPlayerHand(), actions.get(3)));
//
//                    //send results
//                    out.writeObject(response);
//
//
//                    String data = in.readObject().toString();
//                    updateClients("client #"+count+" said: "+data);

                    //Testing el game history
                    GameHistory curr = new GameHistory(0, 0, 0, "Dealer Hand: " + "Player Hand: ", 0);
                    gameHistory.add(curr);

                    //ignore this shi
                    Object clientData = in.readObject();
                    updateClients("Client #"+count+" sent: "+clientData.toString());

                }
                catch(Exception e) {
                    updateClients("Client #"+count+" has left the server!");
                    synchronized (clients) {
                        clients.remove(this);
                    }
                    callback.accept("Client #" + count + " has left the server.");
                    break;
                }
            }
        }//end of run


    }//end of client thread
}
