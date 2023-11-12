
import main.view.LoginView;

import java.net.UnknownHostException;
import java.net.Socket;
import java.io.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import main.utility.ConnectionStream;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket serverConnection = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            serverConnection = new Socket("127.0.0.1", 6789);
            System.out.println("connected to: " + serverConnection);
        } catch (UnknownHostException e) {
            System.err.println("Don't know host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for " + "the connection to: sv.");
            System.err.println(e.fillInStackTrace());
            System.exit(1);
        }

        System.out.println("connected");
        try {

            out = new PrintWriter(serverConnection.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
            ConnectionStream.setStream(out,in);

            System.out.println("login in");
            LoginView l = new LoginView();
            l.login();
        }
        finally {
            //mapOutputStream.close();
        }


//        while ((fromServer = in.readLine()) != null) {
//
//            boolean isServerAskForInput = fromServer.equals("@r#");
//
//            if (isServerAskForInput) {
//                System.out.print("you> ");
//                toServer = stdIn.readLine();
//                if (toServer.equals("exit()")) {
//                    out.close();
//                    in.close();
//                    stdIn.close();
//                    serverConnection.close();
//                    System.exit(1);
//                }
//                out.println(toServer);
//            }else
//                System.out.println(":: " + fromServer);
//        }


    }
}
