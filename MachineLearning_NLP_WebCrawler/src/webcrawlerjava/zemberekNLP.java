/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawlerjava;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SentenceAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.normalization.TurkishSentenceNormalizer;

/**
 *
 * @author ysfrdvn
 */
public class zemberekNLP {
    
    private void editSentence(List<String> word) throws IOException{
        Path lookupRoot = Paths.get("C:\\Users\\ysfrdvn\\Desktop\\Engineering Tools\\Artificial intelligence\\java\\normalization");
        Path lmFile = Paths.get("C:\\Users\\ysfrdvn\\Desktop\\Engineering Tools\\Artificial intelligence\\java\\lm.2gram.slm");
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        TurkishSentenceNormalizer normalizer = new TurkishSentenceNormalizer(morphology, lookupRoot, lmFile);       
        for(int i=0; i<word.size()-1; i++){
            word.set(i, normalizer.normalize(word.get(i)));
            
        }
    }
    
    public void editList(List<List<String>> docs) throws IOException{
        int i = 0;
        for(List<String> doc : docs){
            editSentence(doc);
            System.out.println(++i);
        }
            
        
    }
    
    private void findBody(List<String> sentence){ // bir listenin kelimelerini gövdeleriyle degistiren fonksiyon.!!
        TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
        List<WordAnalysis> analysis = new ArrayList<>();
        for(int i=0; i<sentence.size(); i++){
            final int c = i; // burdaki i degeri after.bestanalysis in içindeki bölgede okunamıyor. o yüzden tanımladık c degerini. 5 alt satırda kullanıyoruz!
            analysis = morphology.analyzeSentence(sentence.get(i));
            SentenceAnalysis after = morphology.disambiguate(sentence.get(i), analysis);
            after.bestAnalysis().forEach( e ->{
                sentence.set(c, e.getLemmas().get(0)); // kök halini getirmek için e.getlemmas().get sıfırıncısı gerekiyor!!!
                
            });
        }
        
    }
    
    public void findBodyToList(List<List<String>> listOflist){ // cümleler listesinin köklerini degistiren fonksiyon
        for(int i=0; i<listOflist.size(); i++)
            findBody(listOflist.get(i));
    }
    
}
