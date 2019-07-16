/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawlerjava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;

/**
 *
 * @author ysfrdvn
 */
public class getTweetAndListWrite {
    
    
    public List tweetGetList(String kullanici,String sifre,String ara) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ysfrdvn\\Desktop\\Engineering Tools\\Artificial intelligence\\java\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
        driver.get("http://www.twitter.com/login");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement id = driver.findElement(By.className("js-username-field"));
        id.sendKeys(kullanici);
        TimeUnit.SECONDS.sleep(1);
        
        WebElement pass = driver.findElement(By.className("js-password-field"));
        pass.sendKeys(sifre);
        TimeUnit.SECONDS.sleep(1);
        
        WebElement login = driver.findElement(By.className("clearfix"));
        login.submit();
        TimeUnit.SECONDS.sleep(1);
        
        driver.findElement(By.className("search-input")).sendKeys(ara);
        driver.findElement(By.className("search-icon")).submit();
        Thread.sleep(5000);
        
        System.out.println(driver.getCurrentUrl());
        
       /* for(int i=0; i<20; i++){
            js.executeScript("window.scrollBy(0,1000)");
            Thread.sleep(1000);
        }*/
       
        Document doc = Jsoup.connect(driver.getCurrentUrl()).maxBodySize(0).get();  
        Elements tweet = doc.getElementsByClass("tweet-text");//twitleri elemente yazdık

        List<String> tweetler = new ArrayList<String>();
      
        for(Element element : tweet)   
            tweetler.add(element.text()) ;//System.out.println(element.text());*/
       
        driver.close();
        
        return tweetler;
      
   }

    public List stopWordTemizle(List<String> wordList){
        String [] stopWords = {"http","https","twitter","pic.","pic.twitter","@","#","{","[","(","www",".com"};
        String [] cumle;
        for (int k=0; k< wordList.size(); k++){
        
         
         cumle  = wordList.get(k).split(" ");
         for(int i=0; i<cumle.length; i++){ //merhaba dünyalılar http #fenerbahce
             for(int j=0; j<stopWords.length; j++){
                 if(cumle[i].contains(stopWords[j]) || cumle[i].equalsIgnoreCase(stopWords[j])){
                     cumle[i]=" ";
                 }
             }
         }
         wordList.remove(k);
         String b ="";
         for(int i = 0; i<cumle.length; i++){
            b = b + cumle[i] + " ";
         } 
            wordList.add(k, b);
        
        }
        
        
        return wordList;
    }

    public void writeListToFile(List<String> alist, String fileName) throws IOException{
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        alist.forEach(e -> {
            try {
                bWriter.write(e + "\n");
                
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        bWriter.close();
    }
    
    public List<List<String>> createListOfList(String fileName) throws IOException{
        
        FileReader fileReader = new FileReader(fileName);
        String line;

        BufferedReader br = new BufferedReader(fileReader);
        List<String> uzunCumle = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            uzunCumle.add(line);
        }
        br.close();
        List<List<String>> listOfList = new ArrayList<>();
        String [] temp;
        for(String e : uzunCumle){
            temp = e.split(" ");
            listOfList.add(Arrays.asList(temp));
        }
        return listOfList;
    }
  
    public void addToFile(List<List<String>> docs, String fileName){
            try{
                  File dosya = new File(fileName);
                  FileWriter yazici = new FileWriter(dosya,true);
                  BufferedWriter yaz = new BufferedWriter(yazici);
                  yaz.write("\n");
                  for(List<String> doc: docs){
                      int i =1;
                      for(String e: doc){
                          if(doc.size() != i)
                            yaz.write(e+ " ");
                          else
                            yaz.write(e);
                          i++;
                      }
                      yaz.write("\n");
                  }
                  
                  yaz.close();
                  System.out.println("Ekleme İşlemi Başarılı");
            }
            catch (IOException hata){
                  hata.printStackTrace();
            }
      }
}
