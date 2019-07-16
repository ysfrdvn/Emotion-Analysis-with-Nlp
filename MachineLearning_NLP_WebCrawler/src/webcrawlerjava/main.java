/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawlerjava;
  /*Scanner sc = new Scanner(System.in);
        System.out.println("lutfen kullanici adinizi giriniz");
        String kullaniciAdi = sc.nextLine();
        System.out.println("lutfen sifrenizi giriniz");
        String sifre = sc.nextLine();
        System.out.println("lutfen aranacak kelimeyi giriniz");
        String ara = sc.nextLine();
        */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import zemberek.core.logging.Log;
import zemberek.core.turkish.PrimaryPos;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.AnalysisFormatters;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.SentenceWordAnalysis;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.generator.WordGenerator.Result;
import zemberek.morphology.lexicon.DictionaryItem;
import zemberek.normalization.TurkishSentenceNormalizer;

/**
 *
 * @author ysfrdvn
 */
public class main {

    public static void main(String[] args) throws InterruptedException, IOException{
      
        List<String> tweetler = new ArrayList<>();
        
        getTweetAndListWrite tw = new getTweetAndListWrite();
        /*
        tweetler = tw.tweetGetList("faketwit500", "faketwit1", "secimler");
        tw.writeListToFile(tweetler, "dosya.txt"); //temizlenmemiş tweetlerin dosyaya yazımı
        
        tweetler = tw.stopWordTemizle(tweetler); // listenin temizlenip tekrar listeye yazılması
        tw.writeListToFile(tweetler, "dosya2.txt"); //temizlenen listenin dosyaya yazımı
        
        
        tweetler.add("Cumhur İttifakı’nın AKP’li Ankara Büyükşehir Belediyesi Başkan Adayı Mehmet Özhaseki’nin, yerel seçimler için çekilen reklam filmlerinde, mekân olarak CHP’nin yönettiği belediyeleri kullandığı ortaya çıktı.");
        
        /*List<String> cumle = new ArrayList<>();
        String [] a = tweetler.get(0).split(" ");
        cumle = Arrays.asList(a);
        double tf = tfidf.tf(cumle, "cumhur");
        System.out.println("tf cumhur = " + tf);
        */
    /*  List<List<String>> listOfLists =  tw.createListOfList("dosya2.txt");
        TF_IDF tfidf = new TF_IDF();   
        System.out.println(tfidf.getTfIdf(listOfLists.get(7), listOfLists, "yaklaştı"));
      
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        String sentence = "bana";
        System.out.println("Sentence  = " + sentence);
        List<WordAnalysis> analysis = morphology.analyzeSentence(sentence);

        System.out.println("\nAfter disambiguation.");
        SentenceAnalysis after = morphology.disambiguate(sentence, analysis);
        after.bestAnalysis().forEach(s-> System.out.println(s.getLemmas().get(0)));
       */
       // List<List<String>> olumlular =  tw.createListOfList("olumlular.txt");
       //List<List<String>> olumsuz =  tw.createListOfList("olumsuz.txt");
        List<List<String>> test =  tw.createListOfList("test.txt");
        
        //.forEach(e->System.out.println(e));
     //   zemberekNLP z = new zemberekNLP();
      //  z.editList(test);
      //  z.findBodyToList(test);
        //tw.addToFile(olumsuz, "olumsuzEgitim.txt");

        //z.editList(test);
     //test.forEach(e->System.out.println(e));
       /* z.editList(olumsuz);
        System.out.println("olumsuzlar editlistten gecti");
        z.editList(test);
        System.out.println("test editten gecti gecti");
        z.findBodyToList(olumlular);
        System.out.println("olumlular find body gecti");
        z.findBodyToList(olumsuz);
        System.out.println("olumsuzlar find body gecti");
        */
       /* z.findBodyToList(test);
        System.out.println("test find body gecti");
        */
       /*test.forEach(e -> System.out.println(e));
         List<List<String>> olumlular =  tw.createListOfList("olumluEgitim.txt");
         List<List<String>> olumsuz =  tw.createListOfList("olumsuzEgitim.txt");
        TF_IDF tfidf = new TF_IDF();
        System.out.println(tfidf.emotionAnalyses(test, olumlular, olumsuz));
        */
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        List<WordAnalysis> analysis = new ArrayList<>();
        analysis = morphology.analyzeSentence("sorgulamak");
        SentenceAnalysis after = morphology.disambiguate("sorgulamak", analysis);
        after.bestAnalysis().forEach( e ->{
                System.out.println(e.getLemmas().get(0)); // kök halini getirmek için e.getlemmas().get sıfırıncısı gerekiyor!!!
                
            });
    }
    }

