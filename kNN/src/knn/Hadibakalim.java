
package knn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Hadibakalim {
    
    static String tahmini_tur;
    public static void bubbleSort(double arr[]) { 
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1]) { 
                    double temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
    }

    public static void IndiseGoreSil(int indis,ArrayList<Cicek> cicek_liste, ArrayList<double []> liste_tutucu){
        cicek_liste.remove(indis);
        liste_tutucu.remove(indis);
    }
    
    public static void HepsiniSil(ArrayList<Cicek> cicek_liste, ArrayList<double []> liste_tutucu){
        for(int i=0;i<cicek_liste.size();i++){
            cicek_liste.clear();
            liste_tutucu.clear();
        }
    }
    
    public static void CicekEkle(ArrayList<Cicek> cicek_liste,ArrayList<double []>double_arrayler){
        System.out.println("Yeni çicek ekleme ekranı................");
        Scanner scanner2= new Scanner(System.in);
        double [] input_array= new double[4];
        System.out.print("Çanak yaprak uzunluğu: ");
        input_array[0]=scanner2.nextDouble();
        System.out.print("Çanak yaprak genişliği: ");
        input_array[1]=scanner2.nextDouble();
        System.out.print("Taç yaprak uzunluğu: ");
        input_array[2]=scanner2.nextDouble();
        System.out.print("Taç yaprak genişliği:");
        input_array[3]=scanner2.nextDouble();
        System.out.print("Türünü giriniz:");
        scanner2.nextLine();
        String yeni_tur=scanner2.nextLine();
        Cicek yeni_cicek=new Cicek(input_array[0], input_array[1], input_array[2], input_array[3],yeni_tur);
        cicek_liste.add(yeni_cicek);
        double_arrayler.add(input_array);
        
        System.out.println("Yeni çiçek başarıyla eklendi !!!");
    }
    
    public static void ListeyiGoruntule(ArrayList<Cicek> cicek_liste){
        for (int i = 0; i < cicek_liste.size(); i++) {
            System.out.println(cicek_liste.get(i));
        }
    }
    
    
    
    public static int []  knn_ayari(ArrayList<Cicek> cicek_liste,ArrayList<double []> liste_tutucu,double [] input_array, Cicek cicek,int k_degeri){
      
        double[] uzaklik_deger=new double[cicek_liste.size()];
        
        if (cicek.getCanak_genislik()==0) {
           for(int i=0;i<cicek_liste.size();i++){
                double uzaklik=0;
                for(int j=0;j<input_array.length;j++){
                    uzaklik+=Math.pow((liste_tutucu.get(i)[j+2]-input_array[j]), 2);
                }
                uzaklik_deger[i]=Math.sqrt(uzaklik);
            } 
        }
        else{
             for(int i=0;i<cicek_liste.size();i++){
                double uzaklik=0;
                for(int j=0;j<input_array.length;j++){
                    uzaklik+=Math.pow((liste_tutucu.get(i)[j]-input_array[j]), 2);
                }
                uzaklik_deger[i]=Math.sqrt(uzaklik);
            }
        }
        double [] sirali_liste=new double[uzaklik_deger.length];
        for (int i = 0; i < uzaklik_deger.length; i++) {
            sirali_liste[i]=uzaklik_deger[i];
        }
        bubbleSort(sirali_liste);
        
        double [] k_listesi=new double[k_degeri];
        for(int i=0;i<k_degeri;i++){
            k_listesi[i]=sirali_liste[i];
        }
        int [] indis_listesi=new int[k_degeri];
        for(int i=0;i<k_degeri;i++){
            for(int j=0;j<uzaklik_deger.length;j++){
                if(k_listesi[i]==uzaklik_deger[j]){
                        indis_listesi[i]=j;
                    System.out.println("---------------------------");
                }
            }
        }
        for (int i = 0; i < indis_listesi.length; i++) {
            System.out.println(cicek_liste.get(indis_listesi[i]));
        }
        String [] benzer_turler=new String[k_degeri];
        for(int i=0;i<k_degeri;i++){
            benzer_turler[i]=cicek_liste.get(indis_listesi[i]).getTur();
        }
        
        int sayac_versicolor=0;
        int sayac_virginica=0;
        int sayac_setosa=0;
        
        for (int i=0;i<benzer_turler.length;i++){
            if (benzer_turler[i].equals("Iris-setosa")){
                sayac_setosa++;
            }
            if (benzer_turler[i].equals("Iris-versicolor")){
                sayac_versicolor++;
            }
            if (benzer_turler[i].equals("Iris-virginica")){
                sayac_virginica++;
            }
        }
        
        if (sayac_setosa>(sayac_versicolor)&& sayac_setosa>sayac_virginica){
            System.out.println("Tahmin edilen Çiçeğin türü: Iris-setosa");
            tahmini_tur=cicek_liste.get(indis_listesi[0]).getTur();
        }
        else if (sayac_versicolor>sayac_setosa && sayac_versicolor>sayac_virginica){
            System.out.println("Tahmin edilen Çiçeğin türü: Iris-versicolor");
            tahmini_tur=cicek_liste.get(indis_listesi[0]).getTur();
        }
        else if (sayac_virginica>sayac_setosa && sayac_virginica>sayac_versicolor){
            System.out.println("Tahmin edilen Çiçeğin türü: Iris-virginica");
            tahmini_tur=cicek_liste.get(indis_listesi[0]).getTur();
        }
        else if (sayac_setosa==sayac_versicolor && sayac_setosa>sayac_virginica){
            System.out.println("Tahmin edilen Çiçeğin türü: "+cicek_liste.get(indis_listesi[0]).getTur());
            tahmini_tur=cicek_liste.get(indis_listesi[0]).getTur();
        }
        else if (sayac_setosa==sayac_virginica && sayac_setosa>sayac_versicolor){
            System.out.println("Tahmin edilen Çiçeğin türü: "+cicek_liste.get(indis_listesi[0]).getTur());
            tahmini_tur=cicek_liste.get(indis_listesi[0]).getTur();
        }
        else if (sayac_versicolor==sayac_virginica && sayac_versicolor>sayac_setosa){
            System.out.println("Tahmin edilen Çiçeğin türü: "+cicek_liste.get(indis_listesi[0]).getTur());
            tahmini_tur=cicek_liste.get(indis_listesi[0]).getTur();
        }
        else if (sayac_setosa==sayac_versicolor && sayac_setosa==sayac_virginica){
            System.out.println("Tahmin edilen Çiçeğin türü: "+cicek_liste.get(indis_listesi[0]).getTur());
            tahmini_tur=cicek_liste.get(indis_listesi[0]).getTur();
        }
        return indis_listesi;
    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Scanner FileIn=null;
        ArrayList<Cicek> cicek_liste_2=new ArrayList<>();
        ArrayList<double []> liste_tutucu_2=new ArrayList<>();
        ArrayList<double []> liste_tutucu=new ArrayList<>();
        ArrayList<Cicek> cicek_liste=new ArrayList<>();
        ArrayList<Double> canak_uzunluk=new ArrayList<>();
        ArrayList<Double> canak_genislik=new ArrayList<>();
        ArrayList<Double> tac_uzunluk=new ArrayList<>();
        ArrayList<Double> tac_genislik=new ArrayList<>();
        ArrayList<String> bitki_turu=new ArrayList<>();
        try {
            FileIn=new Scanner(new FileInputStream("iris.txt"));
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        while (FileIn.hasNextLine()) {
            
            String bitki_bilgileri=FileIn.nextLine();
            String [] bitki_array=bitki_bilgileri.split(",");
            double [] ozellik_array=new double[bitki_array.length-1];
            for (int i = 0; i < ozellik_array.length; i++) {
                ozellik_array[i]=Double.valueOf(bitki_array[i]);
            }
            
            canak_uzunluk.add(Double.valueOf(bitki_array[0]));
            canak_genislik.add(Double.valueOf(bitki_array[1]));
            tac_uzunluk.add(Double.valueOf(bitki_array[2]));
            tac_genislik.add(Double.valueOf(bitki_array[3]));
            bitki_turu.add(bitki_array[4]);
            
            Cicek cicek=new Cicek(Double.valueOf(bitki_array[0]), Double.valueOf(bitki_array[1]), Double.valueOf(bitki_array[2]), Double.valueOf(bitki_array[3]), bitki_array[4]);
            
            cicek_liste.add(cicek);
            cicek_liste_2.add(cicek);
            
            liste_tutucu.add(ozellik_array);
            liste_tutucu_2.add(ozellik_array);
            
        }
        
        
        System.out.print("k değerini giriniz:");
        Scanner scanner2= new Scanner(System.in);
        int k_degeri=scanner2.nextInt();
        double [] input_array= new double[4];
        System.out.print("Çanak yaprak uzunluğu: ");
        input_array[0]=scanner2.nextDouble();
        System.out.print("Çanak yaprak genişliği: ");
        input_array[1]=scanner2.nextDouble();
        System.out.print("Taç yaprak uzunluğu: ");
        input_array[2]=scanner2.nextDouble();
        System.out.print("Taç yaprak genişliği:");
        input_array[3]=scanner2.nextDouble();
        
        
        Cicek cicek=new Cicek(input_array[0], input_array[1],input_array[2],input_array[3]);
        
        
        
        int [] indis_listesi=knn_ayari(cicek_liste, liste_tutucu, input_array, cicek, k_degeri);
        
        String [][] tablo_icin=new String[k_degeri][5];
        
        
        for (int i = 0; i < k_degeri; i++) {
            tablo_icin[i][0]=Double.toString(cicek_liste.get(indis_listesi[i]).getCanak_uzunluk());
            tablo_icin[i][1]=Double.toString(cicek_liste.get(indis_listesi[i]).getCanak_genislik());
            tablo_icin[i][2]=Double.toString(cicek_liste.get(indis_listesi[i]).getTac_uzunluk());
            tablo_icin[i][3]=Double.toString(cicek_liste.get(indis_listesi[i]).getTac_genislik());
                        tablo_icin[i][4]=cicek_liste.get(indis_listesi[i]).getTur();

        }
        
        String [] baslıklar={"Çanak uzunluğu","Çanak genişliği","Taç uzunluğu","Taç genişliği","Tür"};
        
        JFrame frame=new JFrame("4 özelliğin kullanıcıdan alındığı");
        frame.setSize(750,500);
        frame.setLocation(500, 500);
        JTable table=new JTable(tablo_icin,baslıklar);
        frame.getContentPane().add(new JScrollPane(table));  
        frame.setVisible(true);
        
        
        double [] canak_array=new double[2];
        System.out.print("Çanak yaprak uzunluğu: ");
        canak_array[0]=scanner2.nextDouble();
        System.out.print("Çanak yaprak genişliği: ");
        canak_array[1]=scanner2.nextDouble();
        
        Cicek cicek2=new Cicek(canak_array[0], canak_array[1]);
        
        int [] indis_liste_2=knn_ayari(cicek_liste, liste_tutucu, canak_array, cicek2, k_degeri);
        
        
        for (int i = 0; i < k_degeri; i++) {
            tablo_icin[i][0]=Double.toString(cicek_liste.get(indis_liste_2[i]).getCanak_uzunluk());
            tablo_icin[i][1]=Double.toString(cicek_liste.get(indis_liste_2[i]).getCanak_genislik());
            tablo_icin[i][2]=Double.toString(cicek_liste.get(indis_liste_2[i]).getTac_uzunluk());
            tablo_icin[i][3]=Double.toString(cicek_liste.get(indis_liste_2[i]).getTac_genislik());
                        tablo_icin[i][4]=cicek_liste.get(indis_liste_2[i]).getTur();

        }
        
        JFrame frame_2=new JFrame("Sadece çanak yaprak özelliklerinin alındığı");
        frame_2.setSize(750,500);
        frame_2.setLocation(500, 500);
        JTable table_2=new JTable(tablo_icin,baslıklar);
        frame_2.getContentPane().add(new JScrollPane(table_2));  
        frame_2.setVisible(true);
        
        
        double [] tac_array=new double[2];
        System.out.println("-------------Taç yaprak özelliklerine göre----------------");
        System.out.print("Taç yaprak uzunluğu: ");
        tac_array[0]=scanner2.nextDouble();
        System.out.print("Taç yaprak genişliği: ");
        tac_array[1]=scanner2.nextDouble();
        
        
        Cicek cicek_3=new Cicek(tac_array[0], tac_array[1], "taç");
        
        int [] indis_liste_3=knn_ayari(cicek_liste, liste_tutucu, tac_array, cicek_3, k_degeri);
        
        for (int i = 0; i < k_degeri; i++) {
            tablo_icin[i][0]=Double.toString(cicek_liste.get(indis_liste_3[i]).getCanak_uzunluk());
            tablo_icin[i][1]=Double.toString(cicek_liste.get(indis_liste_3[i]).getCanak_genislik());
            tablo_icin[i][2]=Double.toString(cicek_liste.get(indis_liste_3[i]).getTac_uzunluk());
            tablo_icin[i][3]=Double.toString(cicek_liste.get(indis_liste_3[i]).getTac_genislik());
                        tablo_icin[i][4]=cicek_liste.get(indis_liste_3[i]).getTur();

        }
        
        JFrame frame_3=new JFrame("Sadece taç yaprak özelliklerinin alındığı");
        frame_3.setSize(750,500);
        frame_3.setLocation(500, 500);
        JTable table_3=new JTable(tablo_icin,baslıklar);
        frame_3.getContentPane().add(new JScrollPane(table_3));
        frame_3.setVisible(true);
        
        
        ArrayList<Cicek> basari_olcumu=new ArrayList<>();
        int sayac1=0,sayac2=0,sayac3=0;
        for(int i=cicek_liste.size()-1;i>-1;i--){
            
            if (cicek_liste_2.get(i).getTur().equals("Iris-virginica") && sayac1<10) {
                basari_olcumu.add(cicek_liste_2.get(i));
                cicek_liste_2.remove(i);
                liste_tutucu_2.remove(i);
                sayac1++;
            }
            
            else if (cicek_liste_2.get(i).getTur().equals("Iris-versicolor") && sayac2<10) {
                basari_olcumu.add(cicek_liste_2.get(i));
                cicek_liste_2.remove(i);
                liste_tutucu_2.remove(i);
                sayac2++;
            }
            
            else if(cicek_liste_2.get(i).getTur().equals("Iris-setosa") && sayac3<10){
                basari_olcumu.add(cicek_liste_2.get(i));
                cicek_liste_2.remove(i);
                liste_tutucu_2.remove(i);
                sayac3++;
            }
        }
        
        
        
        Scanner scanner4 = new Scanner(System.in);
        System.out.println("++++++++++++++++++++++++++++");
        System.out.print("Başarı ölçümü için k değerini giriniz:");
        int k_degeri_basari=scanner4.nextInt();
        double dogru_tahmin_sayisi = 0;
        for(int j=29;j>-1;j--){
                double temp[]=new double[4];
                temp[0]=basari_olcumu.get(j).getCanak_uzunluk();
                temp[1]=basari_olcumu.get(j).getCanak_genislik();
                temp[2]=basari_olcumu.get(j).getTac_uzunluk();
                temp[3]=basari_olcumu.get(j).getTac_genislik();
                knn_ayari(cicek_liste_2, liste_tutucu_2, temp, new Cicek(temp[0], temp[1], temp[2], temp[3]), k_degeri_basari);
                System.out.println("Verisetinde bulunan gerçek tür: " +basari_olcumu.get(j).getTur());
                if (basari_olcumu.get(j).getTur().equals(tahmini_tur)) {
                    dogru_tahmin_sayisi++;
                }
                
        }
        System.out.println("Başarı Oranı:"+dogru_tahmin_sayisi/30*100);
        System.out.println("-----Ekleme Silme işlemleri-----");
        CicekEkle(cicek_liste, liste_tutucu);
        System.out.println("-----Eklemeden sonraki yeni liste-----");
        ListeyiGoruntule(cicek_liste);
        IndiseGoreSil(sayac3, cicek_liste, liste_tutucu);
        System.out.println("-----Sildikten sonraki yeni liste-----");
        ListeyiGoruntule(cicek_liste);
        HepsiniSil(cicek_liste, liste_tutucu);
        System.out.println("-----Hepsini sildikten sonraki liste-----");
        ListeyiGoruntule(cicek_liste);
        
        
        System.exit(0);
    }
    
}