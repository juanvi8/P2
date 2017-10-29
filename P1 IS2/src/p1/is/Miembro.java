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


public class Miembro {
    private String nombre;
    private int num_motos_poder;
    private float importe;
    private int id_miembro;
    private static int id = 1;
    private Acama a;
    
    private ArrayList<Motocicleta> motos = new ArrayList<Motocicleta>();
    
    Miembro(String _nombre, int _num, float _importe)
    {
        nombre = _nombre;
        num_motos_poder = _num;
        importe = _importe;
        id_miembro = id;
        id++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum_motos_poder() {
        return num_motos_poder;
    }

    public void setNum_motos_poder(int num_motos_poder) {
        this.num_motos_poder = num_motos_poder;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }


    public int getId_miembro() {
        return id_miembro;
    }

    public void setId_miembro(int id_miembro) {
        this.id_miembro = id_miembro;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Miembro.id = id;
    }

    public ArrayList<Motocicleta> getMotos() {
        return motos;
    }

    public void setMotos(ArrayList<Motocicleta> motos) {
        this.motos = motos;
    }
    
    public void anyadirMoto (Motocicleta m)
    {
        motos.add(m);
    }
    
    public String mostrarMiembro()
    {
        if(id_miembro < 10)
            return( "00" + id_miembro + " " + nombre + " tiene " + num_motos_poder + " motos con un valor de " + importe + "€ ");
        else if(id_miembro < 100)
            return( "0" + id_miembro + " " + nombre + " tiene " + num_motos_poder + " motos con un valor de " + importe + "€ ");
        else
            return( id_miembro + " " + nombre + " tiene " + num_motos_poder + " motos con un valor de " + importe + "€ ");
    }
    
}
