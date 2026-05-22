package lk.kaushalya.bcd;

import lk.kaushalya.bcd.client.Message;
import lk.kaushalya.bcd.client.UserService;
import lk.kaushalya.bcd.model.Data;
import lk.kaushalya.bcd.model.User;

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
            System.out.println( "\n=============================\n"+message.send());

            Data data = message.getData();

            System.out.println("\n");
            System.out.println("=============================");
            System.out.println(data.getId());
            System.out.println(data.getName());
            System.out.println(data.getRole());
            System.out.println("=============================");

            System.out.println(message.getResult(10,20));
            System.out.println(message.getResult(200,10));
            System.out.println("=============================");

            /// lookup user_service
            UserService userService =(UserService) registry.lookup("user_service");

            ///  New Users data
            User user = new User(1,"Sahan Kaushalya","sahan@gmail.com",23,"Developer");
            User user1 = new User(2,"Nimal Perera","nimal@gmail.com",25,"Tester");
            User user2 = new User(3,"Naveen Thikshan","naveena12@gmail.com",20,"Client");
            User user3 = new User(4,"Kasun Perera","kasun54@gmail.com",30,"UI/UX Designer");
            User user4 = new User(5,"Nilaksha silva","nilaksh@gmail.com",30,"Developer");

            System.out.println("=============================");
            System.out.println(userService.addUser(user));
            System.out.println(userService.addUser(user1));
            System.out.println(userService.addUser(user2));
            System.out.println(userService.addUser(user3));
            System.out.println(userService.addUser(user4));
            System.out.println("=============================");
            userService.getAllUsers().forEach(u->{
                System.out.println(u.getId()+","+u.getName());
                System.out.println("+++++++++++++++");
            });
            System.out.println("=============================");
            System.out.println(userService.getUserByID(3));
            System.out.println("=============================");
            User findUser = userService.getUserByID(1);
            System.out.println(findUser.getName());
            System.out.println(findUser.getEmail());
            System.out.println(findUser.getRole());
            System.out.println("=============================");
            findUser = userService.getUserByID(5);
            System.out.println(findUser.getName());
            System.out.println(findUser.getEmail());
            System.out.println(findUser.getRole());
            System.out.println("=============================");
            User update = new User(5,"yohan silva","yohansi@gmail.com",23,"Developer");
            System.out.println(userService.updateUser(update));

            findUser = userService.getUserByID(5);
            System.out.println(findUser.getName());
            System.out.println(findUser.getEmail());
            System.out.println(findUser.getRole());
            System.out.println("=============================");
            System.out.println(userService.deleteUser(4));
            System.out.println("=============================");
            userService.getAllUsers().forEach(u->{
                System.out.println(u.getId()+","+u.getName());
                System.out.println("+++++++++++++++");
            });
            System.out.println("=============================");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
