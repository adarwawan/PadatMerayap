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
        System.out.println("a");
        ArrayList<String> data = new ArrayList<>();
        try {
            System.out.println("b");
            data = (ArrayList<String>) readCSV("preprocessed_data.csv");
            
            System.out.println("c");
            getInformation(data.get(1));
            
            System.out.println("d");

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
        System.out.println(result.getWaktu());
        System.out.println(IE.getOutput().getTempat());
        System.out.println(IE.getOutput().getArah());
        System.out.println(IE.getOutput().getKondisi());
        System.out.println("d");
        return result;
    }
    
}
