/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlptexttweet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class InformationExtraction {
    public static final String REGEX_TIME = "\\d{1,2}[:\\.]\\d{1,2}";
    public static final String REGEX_PLACE = "(jalan|persimpangan|tol|daerah|jl.|perempatan)\\s+\\w+";
    public static final String REGEX_DESTINATION = "(menuju|ke|arah|-)\\s+\\w+";
    public static final String REGEX_ORIGIN = "(dari)\\s+\\w+";
    public static final String REGEX_CONDITION = "(macet|padat merayap|lancar|padat|semrawut|tidak bergerak|diam|tersendat)";
    
    private String input;
    private MyInstance output;
    
    public InformationExtraction(String n_input){
        this.input = n_input;
        this.output = new MyInstance("","","","");
    }
    
    public void setInput(String in){
        input = in;
    }
    
    public void setOutput(MyInstance out){
        output = out;
    }
    
    public String getInput(){
        return input;
    }
    
    public MyInstance getOutput(){
        return output;
    }
    
    public void extractTime(){
        String extractedString;
        Pattern pattern = Pattern.compile(REGEX_TIME);
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()){
            extractedString = matcher.group(0);
        } else {
            extractedString = "null";
        }
        
        output.setWaktu(extractedString);
    }
    
    public void extractPlace(){
        String extractedString;
        Pattern pattern = Pattern.compile(REGEX_PLACE);
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()){
            extractedString = matcher.group(0);
        } else {
            extractedString = "null";
        }
        
        output.setTempat(extractedString);
    }
    
    public void extractDestination(){
        String extractedString;
        Pattern pattern = Pattern.compile(REGEX_DESTINATION);
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()){
            extractedString = matcher.group(0);
        } else {
            extractedString = "null";
        }
        
        output.setArah(extractedString);
    }
    
    public void extractOrigin(){
        String extractedString;
        Pattern pattern = Pattern.compile(REGEX_ORIGIN);
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()){
            extractedString = matcher.group(0);
            output.setArah(output.getTempat());
            output.setTempat(extractedString);
        } else {
            extractedString = "null";
        }
        
    }
    
    public void extractCondition(){
        String extractedString;
        Pattern pattern = Pattern.compile(REGEX_CONDITION);
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()){
            extractedString = matcher.group(0);
        } else {
            extractedString = "null";
        }
        
        output.setKondisi(extractedString);
    }
}

