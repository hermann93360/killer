package service;

import lombok.Getter;
import transfert.Transfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Getter
public class AccessServer {

    private long id;
    private Socket socket;
    private String name;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Processing processing;

    private AccessServer(int port) throws IOException {
        this.socket = new Socket("44.212.55.66", port);
        this.inStream = new ObjectInputStream(socket.getInputStream());
        this.outStream = new ObjectOutputStream(socket.getOutputStream());
        this.processing = Processing.init();
    }

    protected static AccessServer on(int port) throws IOException {
        return new AccessServer(port);
    }

    public void start(){
        new Thread(() -> {
            try{
                Transfer transfer;

                do{

                    transfer = (Transfer) inStream.readObject();
                    System.out.println("Transfer -> Type : " + transfer.getType() + ", path : " + transfer.getPath());
                    System.out.println("DTO : " + transfer.getDto() + "\n");

                    this.processing.go(transfer);

                }while(!(transfer.getPath().equals("/stop")));

                close();

            }catch(Exception e){
                System.out.println(e);
            }
        }).start();
    }

    protected void send(Transfer transfer) throws IOException {
        this.outStream.writeObject(transfer);
        this.outStream.flush();
    }

    public void setId(long id){
        this.id = id;
    }

    private void close() throws IOException {
        inStream.close();
        outStream.close();
        socket.close();
    }
}
