/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkchecker;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author E
 */
public class ThreadValidate extends Thread{
         private String link ;
         private int depth;
         private int maxdepth;

    public ThreadValidate(String link, int depth, int maxdepth) {
        this.link = link;
        this.depth = depth;
        this.maxdepth = maxdepth;
    }
         
   
    public void  Run() throws InterruptedException{
       
             try {
                 Validation.validateURL(link, depth, maxdepth);
             } catch (IOException ex) {
                 //Logger.getLogger(ThreadValidate.class.getName()).log(Level.SEVERE, null, ex);
             }
   }

         
        
    
}
