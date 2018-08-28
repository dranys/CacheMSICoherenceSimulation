/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

class ThreadDemo extends Thread {
   private Thread t;
   private String threadName;
   
   ThreadDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   @Override
   public void run() {
      System.out.println("Running " +  threadName );
     
            //Thread.sleep(50);
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   @Override
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this);
         t.start ();
      }
   }
}