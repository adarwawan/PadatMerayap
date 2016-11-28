/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlptexttweet;

import PreProcess.preprocess;
import static PreProcess.preprocess.readCSV;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
            
//            for (int i = 1 ; i < data.size() ; i++){
//                
//                System.out.print(result.get(i).getWaktu());System.out.print(" ");
//                System.out.print(result.get(i).getTempat());System.out.print(" ");
//                System.out.print(result.get(i).getArah());System.out.print(" ");
//                System.out.println(result.get(i).getKondisi());
//            }
            toJSON(result);
            
        } catch (IOException ex) {
            Logger.getLogger(preprocess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error 1");
        } catch (Exception ex) {
            System.out.println("error 2");
        }
    }
    
    public static void toJSON (List<MyInstance> result){
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        for (int i = 1; i < result.size() ; i++){
            JSONObject data = new JSONObject();
            
            data.put("kondisi",result.get(i).getKondisi());
            data.put("arah",result.get(i).getArah());
            data.put("tempat",result.get(i).getTempat());
            data.put("waktu",result.get(i).getWaktu());
            
            list.add(data);
        }
        
        obj.put("aaData",list);
        
        try {
            FileWriter file = new FileWriter("result.json");
            file.write(obj.toJSONString());
		file.flush();
		file.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	System.out.print(obj);

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
