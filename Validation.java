/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkchecker;

import static com.sun.javafx.fxml.expression.Expression.set;
import java.io.IOException;
import static java.lang.reflect.Array.set;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static linkchecker.Validation.validate;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author E
 */
public class Validation {
    static ExecutorService  ex= Executors.newFixedThreadPool(4);
    static boolean valid;
    
    
    public static void validateURL(String link ,int depth, int totdepth) throws IOException, InterruptedException{
         
        ThreadValidate t1 ,t2 = null;
        if (validate(link)){

            System.out.println("valid link : "+ link );
            
            if(depth == totdepth ){
                return ;}
            else {

            Document doc = Jsoup.connect(link).get();
            Elements e = doc.select("a[href]");//extrats only links
            URL u = new URL(link);//to get parent website 
                        System.err.println("number of link : "+e.size());

           for (int i=0 ; i<e.size() ; i++){
                

                String a = e.get(i).attr("href");
                
                String MainLink = u.getProtocol()+"://"+u.getHost();
                
                //not all links starts with https some are relative liinks (not a new website just a link to a diff place insamse website ) so we need to add parent link
                 if ( !a.startsWith("http ")){
                    a= MainLink +a; //add to relatives links the parent website 

                 }
                 validateURL(a ,depth+1,totdepth);
                //t1=new ThreadValidate(a,depth + 1,totdepth);
                //ex.execute(t1);
                /*i++;
                if(i<e.size()){
                a = e.get(i).attr("href");
                MainLink = u.getProtocol()+"://"+u.getHost();
                
                //not all links starts with https some are relative liinks (not a new website just a link to a diff place insamse website ) so we need to add parent link
                if ( !a.startsWith("http ")){
                a= MainLink +a; //add to relatives links the parent website
                
                }
                t2=new ThreadValidate(a,depth + 1,totdepth);
                t2.start();
                
                }
                t1.join();
                if(t2!=null){
                t2.join();}
                }*/
           }
            }
        }else { 
        
        System.err.println("invalid link :  "+ link );
        }
    
    }
    
    
    public static boolean validate (String link )  {
        
       valid =false;
       try {
         Document doc = Jsoup.connect(link).get();
           valid = true ;
          
        } catch ( HttpStatusException ex) {//page not found 
           valid=false;
          
       }catch(IOException ex ){//server not found 
           valid=false;
           

       }
        
       return valid ;
       
    }
    
    
}
