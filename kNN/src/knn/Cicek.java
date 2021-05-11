
package knn;


public class Cicek {
    private double canak_uzunluk;
    private double canak_genislik;
    private double tac_uzunluk;
    private double tac_genislik;
    private String tur;
    private String yaprak_turu;

    public Cicek(double canak_uzunluk, double canak_genislik, double tac_uzunluk, double tac_genislik, String tur) {
        this.canak_uzunluk = canak_uzunluk;
        this.canak_genislik = canak_genislik;
        this.tac_uzunluk = tac_uzunluk;
        this.tac_genislik = tac_genislik;
        this.tur = tur;
    }
    public Cicek(double canak_uzunluk, double canak_genislik, double tac_uzunluk, double tac_genislik) {
        this.canak_uzunluk = canak_uzunluk;
        this.canak_genislik = canak_genislik;
        this.tac_uzunluk = tac_uzunluk;
        this.tac_genislik = tac_genislik;
    }
    public Cicek(double canak_uzunluk, double canak_genislik){
        this.canak_genislik=canak_genislik;
        this.canak_uzunluk=canak_uzunluk;
    }
    
    public Cicek(double tac_uzunluk, double tac_genislik,String yaprak_turu){
        this.tac_genislik=tac_genislik;
        this.tac_uzunluk=tac_uzunluk;
        
    }


    
    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public double getCanak_uzunluk() {
        return canak_uzunluk;
    }

    public void setCanak_uzunluk(double canak_uzunluk) {
        this.canak_uzunluk = canak_uzunluk;
    }

    public double getCanak_genislik() {
        return canak_genislik;
    }

    public void setCanak_genislik(double canak_genislik) {
        this.canak_genislik = canak_genislik;
    }

    public double getTac_uzunluk() {
        return tac_uzunluk;
    }

    public void setTac_uzunluk(double tac_uzunluk) {
        this.tac_uzunluk = tac_uzunluk;
    }

    public double getTac_genislik() {
        return tac_genislik;
    }

    public void setTac_genislik(double tac_genislik) {
        this.tac_genislik = tac_genislik;
    }

    @Override
    public String toString() {
        return "-----Çicek Özellikleri---"+"  Çanak uzunluğu: "+canak_uzunluk+", Çanak Genişliği: "+canak_genislik+", Taç uzunluğu: "+tac_uzunluk+", Taç genişliği: "+ tac_genislik+", Türü: "+tur;
    }
    
}