/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

/**
 *
 * @author jlares
 */
public class AutoSender implements Runnable{
    private Thread t;
    private String threadName;

    public AutoSender(String name){
        this.threadName = name;
        this.start();
    }
    
    @Override
    public void run() {
      //TODO this
   }
   
   public void start () {
      //TODO this
   }
    
}
