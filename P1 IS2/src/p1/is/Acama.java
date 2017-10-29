/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.is;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lucas Nicolini, Juan Vicente Baixauli
 */
public class Acama {
    private ArrayList<Miembro> miembros;
    private ArrayList<Cesion> cesiones;
    Acama()
    {
        miembros = new ArrayList<Miembro>();
        cesiones = new ArrayList<Cesion>();
        /*
        motos.add(new Motocicleta("fsd1", 2, "Vespa", "Primavera", 125, 2500));
        motos.add(new Motocicleta("fsd2", 1, "Motobenae Poney", "AG2", 70, 2300));
        motos.add(new Motocicleta("fsd3", 1, "Bultaco", "", 125, 3800));
        motos.add(new Motocicleta("fsd4", 1, "Guzzi", "Cardelino", 73, 1200));
        motos.add(new Motocicleta("fsd5", 1, "Ducati", "Mini", 49, 4000));
        */
    }

    public ArrayList<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(ArrayList<Miembro> miembros) {
        this.miembros = miembros;
    }
    
    public void anyadirMotoMiembro(Motocicleta m, int pos)
    {
        miembros.get(pos).anyadirMoto(m);
    }
    
    public void anyadirMiembro(Miembro m)
    {
        miembros.add(m);
    }
    
    public void anyadirCesion(Cesion c)
    {
        cesiones.add(c);
    }
    

    public boolean comprobarCoste(Miembro m, float coste, int precio)
    {
        float total = 0;
        boolean ok = true;
        
        for(int i = 0; i < m.getMotos().size(); i++)
        {
            total = total + m.getMotos().get(i).getCoste();
        }
        
        if(total + coste > precio)
        {
            System.out.println("No se puede añadir la moto a este miembro porque supera el maximo de coste");
            ok = false;
        }
        
        return ok;
    }
    
    public void miembroConMoto()
    {
        for(int i = 0; i < miembros.size(); i++)
        {
            if(!miembros.get(i).getMotos().isEmpty())
            {
                System.out.println(miembros.get(i).mostrarMiembro());
            }
        }
        
    }
    
    public boolean comprobarOpcionMiembro(int pos)
    {
        boolean ok = true;
        
        if(!(pos >= 0 && pos < miembros.size()))
        {
            ok = false;
            System.out.println("No es valida esa opción \n");
        }
        
        return ok;
    }
    
    public void cambiarImporte(Motocicleta m, int pos)
    {
        float total = 0;
        
        total = miembros.get(pos).getImporte() + m.getCoste();
        miembros.get(pos).setImporte(total);
    }
    
    public void cambiarNumMotos(int pos)
    {
        miembros.get(pos).setNum_motos_poder(miembros.get(pos).getNum_motos_poder()+1);
    }
    
    public boolean sinMotos()
    {
        return miembros.isEmpty();
    }
    /*
        Funcion para mostrar los miembros receptores
    */
    
    
    
    
    /*
        Funcion para que, al realizar una cesion trasladar los datos de la moto que tiene el emisor, al receptor.
    */
    public void cambioMoto(int em, int re, int moto)
    {
        anyadirCesion(new Cesion(miembros.get(em), miembros.get(re), miembros.get(em).getMotos().get(moto)));
        miembros.get(re).anyadirMoto(miembros.get(em).getMotos().get(moto));
        cambiarImporte(miembros.get(em).getMotos().get(moto), re);
        cambiarNumMotos(re);
        miembros.get(em).setNum_motos_poder(miembros.get(em).getMotos().size()-1);
        miembros.get(em).setImporte(miembros.get(em).getImporte() - miembros.get(em).getMotos().get(moto).getCoste());
        miembros.get(em).getMotos().remove(moto);  
    }
    
    /*
        Funcion para comprobar que el emisor de una cesion tiene alguna moto
    */
    public boolean mirarEmisor(int em)
    { 
        return miembros.get(em).getNum_motos_poder() > 0;
    }
    
    /*
        Funcion para buscar la posicion en el array de una moto determinada por el id.
    */
    public int buscarMotoMiembro(Miembro m, int moto)
    {
        int pos = 0;
        for(int i = 0; i < m.getMotos().size(); i++)
        {
            if(m.getMotos().get(i).getId_moto() == moto)
            {
                pos = i;
            }
        }
        
        return pos;
    }
    
    public String mostrarMotosMiembro(int m)
    {
        String s = "";
        
        s = miembros.get(m).mostrarMiembro() + "con las motos: \n";
        
        for(int i = 0; i < miembros.get(m).getMotos().size(); i++)
        {
            s = s + this.mostrarMotoMiembro(i) + "\n";
        }
        
        return s;
    }
    /*
        Funcion para guardar en .txt
    */
    public void guardar (String s) throws IOException
    {
        String fichero = "./src/" + s + ".txt";
      
        File file = new File(fichero);

        file.createNewFile();

        FileWriter writer = new FileWriter(file);
        
        writer.write("Los miembros con sus motos son: \n");
        writer.write("\n");

        for(int i = 0; i < miembros.size(); i++)
        {
            writer.write(this.mostrarMotoMiembro(i) + "\n");
            writer.flush();
        }
        
         
        
        writer.write("\nLas cesiones que han realizado los miembros son: \n");
        writer.write("\n");
        
        
        writer.write(this.mostrarCesiones());
        writer.flush();
        
        
        writer.close();
    }
    
    /*
        Funcion para mostrar, pero con string para guardarlo en el .txt
    */
    public String mostrarMotos()
    {
        
        String mot = "";
        for(int i = 0; i < miembros.size(); i++)
        {
            for(int j = 0; j < miembros.get(i).getMotos().size(); j++)
            {
                mot = mot + miembros.get(i).getMotos().get(j).mostrarMoto() + "\n";
            }
        }
        
        return mot;
    }
    /*
        Funcion para mostrar, pero con string para guardarlo en el .txt
    */
    public String mostrarMiembros()
    {
        String miem = "";
        for(int i = 0; i < miembros.size(); i++)
        {
            miem = miem + miembros.get(i).mostrarMiembro() + "\n";
        }
        
        return miem;
    }
        
    /*
        Funcion para mostrar, pero con string para guardarlo en el .txt
    */
    public String mostrarCesiones()
    {
        String ces = "";

        for(int i = 0; i < cesiones.size(); i++)
        {
            ces = ces + cesiones.get(i).mostrarCesion() + "\n";
        }
        
        return ces;
    }
    /*
        Funcion para comprobar que el dato introducido es un entero
    */
    public int pedirOpcion ()
    {
        int op = 0;
        Scanner teclado = new Scanner (System.in);
        
        try
        {
            op = teclado.nextInt();
        }
        catch(Exception e)
        {
                System.out.println("El valor introducido no es valido");
        }
        
        
        return op;
    }
    /*
        Funcion para comprobar que el dato introducido es un float
    */
    public float pedirOpcionFloat ()
    {
        float op = 0;
        Scanner teclado = new Scanner (System.in);
        
        try
        {
            op = teclado.nextFloat();
        }
        catch(Exception e)
        {
                System.out.println("El valor introducido no es válido.");
        }
        
        
        return op;
    }

    public String mostrarMotoMiembro(int i) {
        
        String moto = "";
        for(int j = 0; j < miembros.get(i).getMotos().size(); j++)
        {
            moto = moto + miembros.get(i).getMotos().get(j).mostrarMoto() + "\n";
        }
        
        return moto;
    }

    public String mostrarReceptores(int i) {
        String s = "";
        
        for(int j = 0; j < miembros.size(); j++)
        {
            if(i != j)
            {
                s = s + miembros.get(j).mostrarMiembro() + "\n";
            }
        }
        
        return s;
    }

    public int getMotos() {
        int total = 0;
        
        for(int i = 0; i < miembros.size(); i++)
        {
            for(int j = 0; j < miembros.get(i).getMotos().size(); j++)
            {
                total++;
            }
        }
        
        
        return total;
    }

    boolean comprobarMoto(int emisor,int moto) {
        boolean ok = false;
        
        
            for(int j = 0; j < miembros.get(emisor).getMotos().size(); j++)
            {
                if(miembros.get(emisor).getMotos().get(j).getId_moto() == moto)
                    ok = true;
            }
        
        
        return ok;
    }
    public void incrementarGastos(int pos, float a)
    {
        for( int i = 0; i < miembros.size(); i++)
        {
            for( int j = 0; j < miembros.get(i).getMotos().size(); j++)
            {
                if(miembros.get(i).getMotos().get(j).getId_moto() == pos)
                {
                    miembros.get(i).getMotos().get(j).setOtros_gastos(miembros.get(i).getMotos().get(j).getOtros_gastos()+a);
                }
            }
        }
    }
    
    public void eliminarMiembro(int emisor, int precio_inicial){
        boolean opmi;
        int receptor, moto, i = 0;
        
        if(miembros.get(emisor-1).getNum_motos_poder()==0)
            miembros.remove(emisor-1);
        else
        {
            while(!miembros.get(emisor-1).getMotos().isEmpty())
            {
                if(getMiembros().size()>1)
                {
                    do
                    {
                        do
                        {
                            
                            System.out.print("Elige el miembro receptor que le quieres ceder la moto: " + miembros.get(emisor-1).getMotos().get(i).mostrarMoto());
                            receptor = pedirOpcion();
                        }
                        while(receptor == 0);
                        opmi = comprobarOpcionMiembro(receptor -1);

                        if(emisor == receptor)
                            System.out.println("El receptor que ha indicado no existe");
                    }
                    while(!opmi || emisor == receptor);
                    
                    if(comprobarCoste(getMiembros().get(receptor-1),getMiembros().get(emisor-1).getMotos().get(buscarMotoMiembro(getMiembros().get(emisor-1),i)).getCoste(),precio_inicial))
                    {
                        int pos = buscarMotoMiembro(getMiembros().get(emisor-1),i);
                        cambioMoto(emisor-1,receptor-1,pos);
                    }
                }
            }
        }
        
        
    }
}
