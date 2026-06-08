package Main;
import GUI.LoginUser;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        LoginUser loginUser= new LoginUser();
        if(loginUser.shouldAllow){
            System.out.println("Successful");
        }
    }
}
