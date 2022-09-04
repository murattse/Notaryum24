/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaryum24;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author murat.secilmis
 */
public class Veritabani {
    
    private Connection con;
    private Statement sta;
    private PreparedStatement pre;
    
    public Veritabani(){
        
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con=DriverManager.getConnection("jdbc:sqlite:Veritabani/verim.sqlite");
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        String sorgu="Create Table "+"dillerin_listesi"+" "+"(id integer Not null unique, dil_ismi varchar(100) NOT Null, Primary Key (id AUTOINCREMENT))";
        try {
            sta=con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    //dilin kendi tablosunu yaratır
    public void DilTablosuYarat(String dilismi){
        String sorgu="Create Table "+dilismi+" "+"(id integer Not null unique, notturu_ismi varchar(200) NOT Null, Primary Key (id AUTOINCREMENT))";
        try {
            sta=con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DilTablosunuSil(String dilismi){
        String sorgu ="Drop Table "+dilismi;
        try {
            sta= con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DilListesindenSil(String dil_ismi){
        String sorgu = "Delete from dillerin_listesi where dil_ismi= "+"'"+dil_ismi+"'";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //genel dil listesine eleman ekleme
    public void DilListesineDilEkle(String dilismi){
        String sorgu = "Insert into dillerin_listesi (dil_ismi) values (?)";
        try {
            pre=con.prepareStatement(sorgu);
            pre.setString(1,dilismi);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //genel dil listesini listeleme
    public ArrayList<String> DilListesiniListele(){
        String sorgu = "Select * From dillerin_listesi";
        ArrayList<String> dillistesi = new ArrayList<String>();
        try {
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            while(rs.next()){
                String isim = rs.getString("dil_ismi");
                dillistesi.add(isim);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dillistesi;
    }
    //dilin kendi tablosuna not türü ekleme
    public void DilTablosunaEkle(String dilismi,String noturu){
        String sorgu = "Insert into "+dilismi+"(notturu_ismi) values (?)";
        try {
            pre = con.prepareStatement(sorgu);
            pre.setString(1, noturu);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //not türünün kendi tablosunu yaratır
    /*
    public void NotTuruYarat(String notturuismi,String dil){
        String sorgu = "Create Table "+dil+"_"+notturuismi+" "+"(id integer Not null unique, baslik_ismi varchar(200) NOT Null, Primary Key (id AUTOINCREMENT))";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    public void NotTuruTablosunuSil(String dilismi, String notturuismi){
        String sorgu = "Drop Table "+"'"+dilismi+"_"+notturuismi+"'";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //not türünü listeler
    public ArrayList<String> NotTurunuListele(String dilismi){
        String sorgu = "Select * From "+dilismi+"";
        ArrayList<String> notturulistesi = new ArrayList<>();
        try {
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            while(rs.next()){
                String notturu = rs.getString("notturu_ismi");
                notturulistesi.add(notturu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notturulistesi;
    }
    
    public void NotTurunuListedindenSil(String dilismi,String notturu){
        String sorgu = "Delete from "+"'"+dilismi+"'"+" where notturu_ismi ="+"'"+notturu+"'";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void NotTuruListesineEkle(String onisim, String baslik1){
        String sorgu = "Insert into "+onisim+" (baslik_ismi) values (?)";
        try {
            pre = con.prepareStatement(sorgu);
            pre.setString(1, baslik1);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void NotTuruListesindeGuncelleme(String onisim, String baslik1, String yeni_baslik){
        String sorgu = "UPDATE "+onisim+" SET "+"baslik_ismi = (?) WHERE baslik_ismi = "+"'"+baslik1+"'";
        try {
            pre = con.prepareStatement(sorgu);
            pre.setString(1, yeni_baslik);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void BaslikYarat(String onisim, String baslik_ismi){
        
        String sorgu = "Create Table "+onisim+"_"+baslik_ismi+" "+"(id integer Not null unique, baslik_ismi varchar(200) , not_ismi varchar(1000) , metod_ismi varchar(2000) , aciklama_ismi varchar(2000) ,Primary Key (id AUTOINCREMENT))";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> BaslikListele(String onisim){
        String sorgu = "Select * From "+"'"+onisim+"' Order By baslik_ismi ASC";
        ArrayList<String>basliklistesi= new ArrayList<String>();
        try {
            sta=con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            while(rs.next()){
                String baslikismi=rs.getString("baslik_ismi");
                basliklistesi.add(baslikismi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        return basliklistesi;
    }
    
    public void BaslikDegistir(String onisim, String baslik_ismi, String yeni_isim){
        String sorgu = "Alter table "+"'"+onisim+"_"+baslik_ismi+"' rename to "+"'"+onisim+"_"+yeni_isim+"'";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void BaslikSil(String onisim, String baslik_ismi){
        String sorgu = "Drop Table "+"'"+onisim+"_"+baslik_ismi+"'";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void BaslikListesindenSil(String onisim, String baslik_ismi){
        String sorgu = "Delete from "+"'"+onisim+"'"+" where baslik_ismi ="+"'"+baslik_ismi+"'";
        try {
            sta= con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void NotEkle(String onisim, String notismi,String metod_ismi, String aciklama_ismi){
        String sorgu = "Insert into "+onisim+" (not_ismi, metod_ismi, aciklama_ismi) values (?,?,?)";
        try {
            pre = con.prepareStatement(sorgu);
            pre.setString(1, notismi);
            pre.setString(2, metod_ismi);
            pre.setString(3, aciklama_ismi);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void NotSil(String onisim,String nottext){
        String sorgu = "Delete from "+onisim+" where not_ismi ="+"'"+nottext+"'";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String>NotListele(String onisim){
        String sorgu = "Select * From "+onisim+" Order By not_ismi ASC";
        ArrayList<String>notlistesi= new ArrayList<String>();
        try {
            sta=con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            while(rs.next()){
                String notismi=rs.getString("not_ismi");
                notlistesi.add(notismi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notlistesi;
    }
    
    public ArrayList<String>MetodListele(String onisim){
        String sorgu = "Select * From "+onisim+" Order By not_ismi ASC";
        ArrayList<String>metodlistesi= new ArrayList<String>();
        try {
            sta=con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            while(rs.next()){
                String metodismi=rs.getString("metod_ismi");
                metodlistesi.add(metodismi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        return metodlistesi;
    }
    
    public ArrayList<String>AciklamaListele(String onisim){
        String sorgu = "Select * From "+onisim+" Order By not_ismi ASC";
        ArrayList<String>aciklamalistesi= new ArrayList<String>();
        try {
            sta=con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            while(rs.next()){
                String aciklamaismi=rs.getString("aciklama_ismi");
                aciklamalistesi.add(aciklamaismi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aciklamalistesi;
    }
    
    public void NotYarat(String nottext){
        String sorgu = "Create Table "+"'"+nottext+"'"+" "+"(resim_aciklamasi varchar(2000), resim blob)";
        try {
            sta=con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void NotTablosunuSil(String nottext){
        String sorgu = "DROP TABLE "+"'"+nottext+"'";
        try {
            sta=con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MetodGuncelle(String onisim,String not_aciklamasi,String yeni_metod){
        String sorgu= "UPDATE "+onisim+" SET "+"metod_ismi=(?) WHERE not_ismi = "+"'"+not_aciklamasi+"'";
        try {
            pre=con.prepareStatement(sorgu);
            pre.setString(1, yeni_metod);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void AciklamaGuncelle(String onisim,String not_aciklamasi,String yeni_aciklama){
        String sorgu= "UPDATE "+onisim+" SET "+"aciklama_ismi = (?) WHERE not_ismi = "+"'"+not_aciklamasi+"'";
        try {
            pre=con.prepareStatement(sorgu);
            pre.setString(1, yeni_aciklama);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Image>ResimleriListele(String nottext){
        ArrayList<Image> resimlistesi = new ArrayList<Image>();
        String sorgu = "Select*from "+nottext;
        try {
            sta=con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            while(rs.next()){
                InputStream is = rs.getBinaryStream("resim");
                Image im = new Image(is);
                resimlistesi.add(im);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resimlistesi;
    }
    
    public ArrayList<String>ResimAciklamalariniListele(String nottext){
        ArrayList<String>aciklamalistesi = new ArrayList<String>();
        String sorgu = "Select*from "+nottext;
        try {
            sta=con.createStatement();
            ResultSet rs = sta.executeQuery(sorgu);
            while(rs.next()){
                String aciklama = rs.getString("resim_aciklamasi");
                aciklamalistesi.add(aciklama);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aciklamalistesi;
    }
    
    public void ImageEkle(byte []in, String nottext,String index){
        String sorgu = "Insert into "+nottext+" "+"(resim_aciklamasi,resim) values (?,?)";
        try {
            pre= con.prepareStatement(sorgu);
            pre.setString(1, (index+".Resim Açıklaması:"));
            pre.setBytes(2, in);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ResimleriVeAciklamalariSil(String nottext,String aciklama){
        String sorgu = "Delete from "+nottext+" where resim_aciklamasi ="+" '"+aciklama+"'";
        try {
            sta = con.createStatement();
            sta.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ResimAciklamalariniEkle(String nottext,String resim_aciklamasi,String yenitext){
        String sorgu = "UPDATE "+nottext+" SET "+"resim_aciklamasi = (?) WHERE resim_aciklamasi = "+"'"+resim_aciklamasi+"'";
        try {
            pre = con.prepareStatement(sorgu);
            pre.setString(1, yenitext);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Veritabani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
