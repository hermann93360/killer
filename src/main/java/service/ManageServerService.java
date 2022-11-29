package service;

import lombok.Data;
import transfert.Transfer;

import java.io.IOException;

@Data
public abstract class ManageServerService {

    private static AccessServer accessServer;

    protected void startServer() throws IOException {
        accessServer = AccessServer.on(8888);
        accessServer.start();
    }

    protected void sendToServer(Transfer transfer) throws IOException {
        accessServer.send(transfer);
    }

    protected void setIdClientServer(long id){
        accessServer.setId(id);
    }

    public long getIdServer(){
        return accessServer.getId();
    }

}
