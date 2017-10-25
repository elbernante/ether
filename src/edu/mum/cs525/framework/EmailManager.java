package edu.mum.cs525.framework;

import java.util.ArrayList;

public class EmailManager {
   
    private volatile static EmailManager instance;
   
    ArrayList<String> emails = new ArrayList<String>();
   
    private EmailManager (){}
   
    public static EmailManager getSingleton() {
        if (instance == null) {                         
            synchronized (EmailManager.class) {
                if (instance == null) {       
                    instance = new EmailManager();
                }
            }
        }
        return instance;
    }
   
    public void printEmail(String email){
	    System.out.println(email);
    }
}
