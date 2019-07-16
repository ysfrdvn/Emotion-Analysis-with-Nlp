/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawlerjava;

import java.util.List;
import java.util.Arrays;
/**
 *
 * @author ysfrdvn
 */
public class TF_IDF_AND_setBayes {
    
    public double tf(List<String> doc, String term) {
        double result = 0;
        for(String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }
    
    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        if(n < 1)
            n += 1;
        return Math.log(docs.size() / n);
        
    }
    
    public double getTfIdf(List<String> doc, List<List<String>> docs, String term) {
        double y = tf(doc, term) * idf(docs, term);
        if(y == 0)
            return 1;
        else
            return y;
    }
    
    public int findWeightToBayes(List<String> doc, List<List<String>> positiveData,List<List<String>> negativeData){
        //parametredeki doc analiz edilecek cümle, docs pozitif veriler, docs2 negatif verileri temsil eder
        double a=1; // a degeri olumlu veri setindeki ağırlık olucak
        double b=1; //b degeri olumsuz veri setindeki ağırlık olucak
        double c =  (double) positiveData.size() / (double) (positiveData.size() + negativeData.size());
        double d =  (double) negativeData.size() / (double)(positiveData.size() + negativeData.size());
        for(int i=0; i<doc.size(); i++){
            a *= getTfIdf(doc, positiveData, doc.get(i)) * c ;
            b *= getTfIdf(doc, negativeData, doc.get(i)) * d ;
            
        }
        System.out.println("a = "+a + "b = "+ b);
        if(a > b)
            return  1;
        return -1;
    }
    
    public String emotionAnalyses(List<List<String>> test, List<List<String>> positiveData,List<List<String>> negativeData ){
        int positive =0,negative=0;
        //duygu analizi artık yapılıyor
        for(int i=0; i<test.size(); i++){
            if( findWeightToBayes(test.get(i) , positiveData, negativeData) == 1 ) 
                positive++;
            else                 
                negative++;
        }
        return "Positive = "+ positive  + " negative = " + negative;
    }
   
    
    
}
