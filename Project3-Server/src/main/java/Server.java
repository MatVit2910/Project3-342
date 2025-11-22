import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;


public class Server{

    int count = 1;
    public final ArrayList<ClientThread> clients = new ArrayList<>();
    TheServer server;
    private final Consumer<Serializable> logCallback;
    private final Consumer<Serializable> clientCallback;
    private boolean running = false;


    Server(Consumer<Serializable> call1, Consumer<Serializable> call2){

        logCallback = call1;
        logCallback.accept("Server started");
        running = true;
        clientCallback = call2;
        server = new TheServer();
        server.start();
    }

    public void stop() throws IOException {
        synchronized (clients) {
            for (ClientThread client : clients) {
                try {
                    client.connection.close();
                } catch (IOException e) {
                }
            }
            clients.clear();
        }
        server.socketRef.close();
    }

    public class TheServer extends Thread{
        ServerSocket socketRef;
        public void run() {

            try(ServerSocket mysocket = new ServerSocket(IntroController.getPortNum())){
                System.out.println("Server is waiting for a client!");
                socketRef = mysocket;

                while(clients.size() < 8) {

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    synchronized (clients) {
                        clients.add(c);
                    }
                    c.start();
                    logCallback.accept("Client has connected to server: " + "Client #" + count);
                    count++;
                }
            }//end of try
            catch(Exception e) {
                logCallback.accept("Server stopped.");
            }
        }//end of while
    }


    class ClientThread extends Thread{


        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
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
                    clientCallback.accept("Client #" +count+ " is playing a new hand.");
                    //create and start new game
                    Game game = new Game();
                    game.startGame();

                    //send client initial game data
                    PokerInfo sendData = new PokerInfo();
                    sendData.setDealerHand(game.getDealerHand());
                    sendData.setPlayerHand(game.getPlayerHand());
                    sendData. setDealerStr(game.evaluateHand(game.getDealerHand()));
                    sendData.setPlayerStr(game.evaluateHand(game.getPlayerHand()));
                    sendData.setDealerQualifies(game.dealerQualifies());
                    out.writeObject(sendData);

                    PokerInfo readData = (PokerInfo) in.readObject();
                    clientCallback.accept("Client #" + count + " bet: $" + readData.getAnteBet() + " for Ante Bet and $"
                            + readData.getPairPlus() + " for Pair Plus"
                    );

                    if (readData.getFold()){
                        clientCallback.accept("Client #" + count + " folded and lost $" + readData.getAnteBet());
                        sendData.setWinningsAmt(0);
                    }
                    else{
                        sendData.setWinningsAmt(game.compareHands(game.getDealerHand(),
                        game.getPlayerHand(), readData.getAnteBet(), readData.getPlayBet()));
                    }
                    sendData.setPairPlus(game.calculatePairPlus(game.getPlayerHand(), readData.getPairPlus()));

                    if (!readData.getFold()){
                        clientCallback.accept("Client #" + count +"'s Hand: " + sendData.getPlayerStr() + " vs. Dealer's Hand: " + sendData.getDealerStr()
                        );
                        if (sendData.getWinningsAmt() == 0){
                            clientCallback.accept("Client #" + count + " lost $" + (readData.getAnteBet() + readData.getPlayBet()));
                        }
                        else if(!game.dealerQualifies()){
                            clientCallback.accept("Client #" + count + " got their money back. Dealer didn't qualify");
                        }
                        else if (sendData.getWinningsAmt() > 0){
                            clientCallback.accept("Client #" + count + " won $" + (readData.getAnteBet() + readData.getPlayBet()));
                        }
                        else{
                            clientCallback.accept("Client #" + count + " and Dealer's hands are the same??!! They got their money back");
                        }
                    }
                    if (readData.getPairPlus() != 0){
                        clientCallback.accept("Client #" + count + " won $" + sendData.getPairPlus() + " for Pair Plus");
                    }
                    out.writeObject(sendData);

                }
                catch(Exception e) {
                    synchronized (clients) {
                        clients.remove(this);
                    }
                    logCallback.accept("Client #" + count + " has left the server.");

                    break;
                }
            }
        }//end of run


    }//end of client thread
}
