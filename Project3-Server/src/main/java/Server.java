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

            while(true) {
                try {
                    callback.accept("Client #" +count+ " is playing a new hand.");
                    //create and start new game
                    Game game = new Game();
                    game.startGame();

                    //send client initial game data
                    PokerInfo sendData = new PokerInfo();
                    sendData.setDealerHand(game.getDealerHand());
                    sendData.setPlayerHand(game.getPlayerHand());
                    sendData. setDealerStr(game.evaluateHand(game.getDealerHand()));
                    sendData.setPlayerStr(game.evaluateHand(game.getPlayerHand()));
                    out.writeObject(sendData);

                    PokerInfo readData = (PokerInfo) in.readObject();

                    if (readData.getFold()){
                        sendData.setWinningsAmt(0);
                    }
                    else{
                        sendData.setWinningsAmt(game.compareHands(game.getDealerHand(),
                        game.getPlayerHand(), readData.getAnteBet(), readData.getPlayBet()));
                    }
                    sendData.setPairPlus(game.calculatePairPlus(game.getPlayerHand(), readData.getPairPlus()));

                    out.writeObject(sendData);

                    //add game results to history
                    GameHistory curr = new GameHistory(readData.getAnteBet(), readData.getPlayBet(), readData.getPairPlus(), "Dealer Hand: " + sendData.getDealerStr() + " Player Hand: " + sendData.getPlayerStr(), 0);
                    gameHistory.add(curr);

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
