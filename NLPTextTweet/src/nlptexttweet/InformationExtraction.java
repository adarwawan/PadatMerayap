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
    public static final String REGEX_PLACE = "(jalan|persimpangan|tol)\\s+\\w+";
    public static final String REGEX_DESTINATION = "(menuju|ke|arah)[, (]*\\d{1,2}([- /\\.])?([a-zA-Z]+|\\d{1,2})([- /\\.])+\\d{0,4}\\)?";
    public static final String REGEX_ORIGIN = "(dari)[, (]*\\d{1,2}([- /\\.])?([a-zA-Z]+|\\d{1,2})([- /\\.])+\\d{0,4}\\)?";
    public static final String REGEX_CONDITION = "(macet|padat merayap|lancar|padat)";
    
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
        System.out.println(input);
        
        if (matcher.find()){
            extractedString = matcher.group(0);
        } else {
            extractedString = "null";
        }
        
        System.out.println(extractedString);
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
        
        System.out.println(extractedString);
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
        
        System.out.println(extractedString);
        output.setArah(extractedString);
    }
    
    public void extractOrigin(){
        String extractedString;
        Pattern pattern = Pattern.compile(REGEX_ORIGIN);
        Matcher matcher = pattern.matcher(input);
        
        if (matcher.find()){
            extractedString = matcher.group(0);
        } else {
            extractedString = "null";
        }
        
        System.out.println(extractedString);
        output.setArah(output.getTempat());
        output.setTempat(extractedString);
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
        
        System.out.println(extractedString);
        output.setKondisi(extractedString);
    }
}

