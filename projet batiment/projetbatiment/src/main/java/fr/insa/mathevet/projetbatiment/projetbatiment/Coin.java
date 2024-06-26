/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;

/**
 *
 * @author emma0
 */
public class Coin {
    
 private int  id;
    double  x;
    private double y;
    //static int Nextid = 1;

    public Coin(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coin{" + "id=" + id + ", x=" + x + ", y=" + y + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public static int rayon = 4;
   
    
    public static Coin fromString(String str) {
        String[] parts = str.split(",");
        int idCoin = Integer.parseInt(parts[0].trim());
        double x = Double.parseDouble(parts[1].trim());
        double y = Double.parseDouble(parts[2].trim());
        return new Coin(idCoin, x, y);
    }
    
    
}
