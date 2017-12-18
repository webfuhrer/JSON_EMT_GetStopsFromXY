package paqueteemt;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luis
 */
public class ObjetoParada {
    private String stopId, name, postalAddress;
    private ArrayList<String> lista_lineas;

    public ObjetoParada(String stopId, String name, String postalAddress, ArrayList<String> lista_lineas) {
        this.stopId = stopId;
        this.name = name;
        this.postalAddress = postalAddress;
        this.lista_lineas = lista_lineas;
    }

    public String getStopId() {
        return stopId;
    }

    public String getName() {
        return name;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public ArrayList<String> getLista_lineas() {
        return lista_lineas;
    }
    
}
