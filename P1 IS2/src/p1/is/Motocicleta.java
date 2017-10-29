/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.is;

import java.util.ArrayList;

/**
 *
 * @author Lucas Nicolini, Juan Vicente Baixauli 
 */
public class Motocicleta {
    private String matricula;
    private String marca;
    private String modelo;
    private int cilindrada;
    private float coste;
    private int id_moto;
    private static int id = 1;
    private Miembro posesion;
    private Acama a;
    
    Motocicleta(String _matricula, String _marca, String _modelo, int _cilindrada, float _coste)
    {
        matricula = _matricula;
        marca = _marca;
        modelo = _modelo;
        cilindrada = _cilindrada;
        coste = _coste;
        id_moto = id;
        id++;

    }
    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public float getCoste() {
        return coste;
    }

    public void setCoste(float coste) {
        this.coste = coste;
    }

    public int getId_moto() {
        return id_moto;
    }

    public void setId_moto(int id_moto) {
        this.id_moto = id_moto;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Motocicleta.id = id;
    }

    public Miembro getPosesion() {
        return posesion;
    }

    public void setPosesion(Miembro posesion) {
        this.posesion = posesion;
    }

    public Acama getA() {
        return a;
    }

    public void setA(Acama a) {
        this.a = a;
    }
    
    public String mostrarMoto()
    {
        if(id_moto < 10)
            return( "00" + id_moto + " " + matricula + " " + marca + " " + modelo + " de " + cilindrada + " CC, cuyo coste fue de " + coste + " €");
        else if(id_moto < 100)
            return( "0" + id_moto + " " + matricula + " " + marca + " " + modelo + " de " + cilindrada + " CC, cuyo coste fue de " + coste + " €");
        else
            return( id_moto + " " + matricula + " " + marca + " " + modelo + " de " + cilindrada + " CC, cuyo coste fue de " + coste + " €");
    }
    
}
