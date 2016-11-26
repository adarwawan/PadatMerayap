/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PreProcess;

import IndonesianNLP.IndonesianSentenceFormalization;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class preprocess {
    
    public static void main(String[] args){
        ArrayList<String> data = new ArrayList<>();
        try {
            data = (ArrayList<String>) readCSV("relevant_tweets.csv");
            
            normalizeData(data);
            
            writeCSV(data, "preprocessed_data");
            
            
        } catch (IOException ex) {
            Logger.getLogger(preprocess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error 1");
        } catch (Exception ex) {
            System.out.println("error 2");
        }
    }
          
    
    public static void normalizeData(List<String> data){
       IndonesianSentenceFormalization sentenceFormalization = new IndonesianSentenceFormalization();
       sentenceFormalization.initStopword();
       String normalizedSentence;
        for (int i = 0; i < data.size(); i++) {
            
//            normalizedSentence = sentenceFormalization.deleteStopword(normalizedSentenc);
            normalizedSentence = removeUser(data.get(i));
            normalizedSentence = removeHashtag(normalizedSentence);
            normalizedSentence = removeRT(normalizedSentence);
            normalizedSentence = removeLink(normalizedSentence);
            normalizedSentence = tagTime(normalizedSentence);
//            normalizedSentence = sentenceFormalization.normalizeSentence(normalizedSentence);
            
            
            data.set(i, normalizedSentence);
       }
    }
    
    public static List<String> readCSV(String file_input) throws FileNotFoundException, IOException {
        List<String> data = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("relevant_tweets.csv"), ',' , '"' , 1);
       
        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
         if (nextLine != null) {
            //Verifying the read data here
            data.add(Arrays.toString(nextLine));
         }
       }
        
        
        return data;
    }
    
    public static void writeCSV (List<String> data, String filename) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filename + ".csv"));
        String[] header = {"hasil"};
        writer.writeNext(header);
        
        for (int i = 0 ; i < data.size() ; i++) {
            String[] instance = {data.get(i)};
            System.out.println(data.get(i));
            writer.writeNext(instance);
        }
        System.out.println("e");
    }
    
    public static String removeUser(String input){
        input = input.toLowerCase();
        String removedUser = input.replaceAll("\\B(\\@[a-zA-Z]+\\b)(?!;)","");
        return removedUser;
    }
    
    public static String removeHashtag(String input){
        input = input.toLowerCase();
        String removedUser = input.replaceAll("\\B(\\#[a-zA-Z]+\\b)(?!;)","");
        return removedUser;
    }
    
    public static String removeRT(String input){
        input = input.toLowerCase();
        String removedUser = input.replaceAll("rt","");
        return removedUser;
    }
    
    public static String tagTime(String input){
        input = input.toLowerCase();
        String removedUser = input.replace("[0-9][0-9].[0-9][0-9]","time_ \\1");
        return removedUser;
    }
    
    public static String removeLink(String input){
        input = input.toLowerCase();
        String removedUser = input.replaceAll("\\B(https?+\\b)(?!;)","_htg_");
        return removedUser;       
    }
}

