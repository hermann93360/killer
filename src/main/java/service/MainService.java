package service;

import controller.MainController;
import dto.Dto;
import dto.UserDto;
import transfert.Transfer;

import java.io.IOException;

import static transfert.Transfer.Type.*;

public class MainService extends ManageServerService{

    private MainController controller;

    private MainService() {
    }

    public static MainService init() {
        return new MainService();
    }

    // come from interface

    public void start() throws IOException {
        startServer();
    }

    public void setNameUser(String name) throws IOException {
        Transfer transfer = new Transfer("/set/name/user", new UserDto(name, super.getIdServer()), REQUEST, super.getIdServer());
        super.sendToServer(transfer);
    }


    // come from server

    public void setId(Dto dto) {
        super.setIdClientServer(((UserDto)dto).getIdentifier());
    }
}
