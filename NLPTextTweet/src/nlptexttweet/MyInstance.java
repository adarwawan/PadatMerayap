/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlptexttweet;

/**
 *
 * @author User
 */
public class MyInstance {
    private String waktu;
    private String tempat;
    private String arah;
    private String kondisi;
    
    public MyInstance(String n_waktu, String n_tempat, String n_arah, String n_kondisi){
        this.waktu = n_waktu;
        this.tempat = n_tempat;
        this.arah = n_arah;
        this.kondisi = n_kondisi;
        
    }
    
    public void setWaktu (String n_waktu){
        waktu = n_waktu;
    }
    
    public void setTempat (String n_tempat){
        tempat = n_tempat;
    }
    
    public void setArah (String n_arah){
        waktu = n_arah;
    }
    
    public void setKondisi (String n_kondisi){
        waktu = n_kondisi;
    }
    
    public String getWaktu (){
        return waktu;
    }
    
    public String getTempat (){
        return tempat;
    }
    
    public String getArah (){
        return arah;
    }
    
    public String getKondisi (){
        return kondisi;
    }
}
