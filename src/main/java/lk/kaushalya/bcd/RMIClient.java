package lk.kaushalya.bcd;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",6666);
            String[] list = registry.list();

            for (String s:list){
                System.out.println(s);
            }

            Message message = (Message) registry.lookup("message_service");
            message.send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
