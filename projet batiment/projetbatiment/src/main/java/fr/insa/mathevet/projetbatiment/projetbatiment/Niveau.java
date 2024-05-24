/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mathevet.projetbatiment.projetbatiment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emma0
 */

public class Niveau {
    
    private int idNiveau;
    private double HauteurSousPlafond;
    private List<Appartement> listApparts;

    public Niveau(int idNiveau, double HauteurSousPlafond, List<Appartement> listApparts) {
        this.idNiveau = idNiveau;
        this.HauteurSousPlafond = HauteurSousPlafond;
        this.listApparts = listApparts;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public double getHauteurSousPlafond() {
        return HauteurSousPlafond;
    }

    public void setHauteurSousPlafond(double HauteurSousPlafond) {
        this.HauteurSousPlafond = HauteurSousPlafond;
    }

    public List<Appartement> getListApparts() {
        return listApparts;
    }

    public void setListApparts(List<Appartement> listApparts) {
        this.listApparts = listApparts;
    }

    @Override
    public String toString() {
        return "Niveau{" + "idNiveau=" + idNiveau + ", HauteurSousPlafond=" + HauteurSousPlafond + ", listApparts=" + listApparts + '}';
    }
}