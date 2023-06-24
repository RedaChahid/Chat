package com.example.borderpane;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatWithServer extends Thread {
    private int ClientNbre;
    private List<Communication> clientconnectes = new ArrayList<>();

    public static void main(String[] args) {
        new ChatWithServer().start();
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("le serveur va demarrer ... ");
            while (true) {
                Socket s = ss.accept();
                ++ClientNbre;
                Communication NewCommunication = new Communication(s, ClientNbre);
                clientconnectes.add(NewCommunication);
                NewCommunication.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Communication extends Thread {
        private Socket s;
        private int ClientNumber;

        Communication(Socket s, int ClientNumber) {
            this.s = s;
            this.ClientNumber = ClientNumber;
        }
        @Override
        public void run() {
            try {
                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = s.getOutputStream();
                String Ip = s.getRemoteSocketAddress().toString();
                System.out.println("Le client numero " + ClientNumber + " et son IP " + Ip);
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("vous etes le client " + ClientNumber);
                pw.println("envoyer le message que vous voulez " + ClientNumber);
                while (true) {
                    String UserRequest = br.readLine();
                    if (UserRequest.contains("=>")) {
                        String[] usermessage = UserRequest.split("=>");
                        if (usermessage.length == 2) {
                            String msg = usermessage[1];
                            int numeroClient = Integer.parseInt(usermessage[0]);
                            Send(msg, s, numeroClient);
                        }
                    }else {
                        Send(UserRequest, s, -1);
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void Send(String UserRequest, Socket socket, int nbre) throws IOException {
            for (Communication client : clientconnectes) {
                if (client.s != socket) {
                    if (client.ClientNumber == nbre || nbre == -1) {
                        PrintWriter pw = new PrintWriter(client.s.getOutputStream(), true);
                        pw.println(UserRequest);
                    }
                }
            }
        }
    }
}


