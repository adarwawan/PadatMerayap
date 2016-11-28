/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlptexttweet;

import PreProcess.preprocess;
import static PreProcess.preprocess.normalizeData;
import static PreProcess.preprocess.readCSV;
import static PreProcess.preprocess.writeCSV;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class NLPTextTweet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        List<MyInstance> result = new ArrayList<MyInstance>();
        
        try {
            data = (ArrayList<String>) readCSV("preprocessed_data.csv");
            
            for (int i = 1 ; i < data.size() ; i++){
                result.add(getInformation(data.get(i)));
                
            }
            
            for (int i = 1 ; i < data.size() ; i++){
                
                System.out.print(result.get(i).getWaktu());System.out.print(" ");
                System.out.print(result.get(i).getTempat());System.out.print(" ");
                System.out.print(result.get(i).getArah());System.out.print(" ");
                System.out.println(result.get(i).getKondisi());
            }
        } catch (IOException ex) {
            Logger.getLogger(preprocess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error 1");
        } catch (Exception ex) {
            System.out.println("error 2");
        }
    }
    
    public static MyInstance getInformation(String input){
        MyInstance result;        
        InformationExtraction IE = new InformationExtraction(input);
        IE.extractTime();
        IE.extractPlace();
        IE.extractDestination();
        IE.extractOrigin();
        IE.extractCondition();
        result = IE.getOutput();
//        System.out.print(IE.getOutput().getWaktu());System.out.print(" ");
//        System.out.print(IE.getOutput().getTempat());System.out.print(" ");
//        System.out.print(IE.getOutput().getArah());System.out.print(" ");
//        System.out.println(IE.getOutput().getKondisi());
        return result;
    }
    
}
