/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteemt;

import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class CreaHTML {
    public static String crearTabla(ArrayList lista_paradas)
    {
        String html="<table><tr><th>Id parada</th><th>Nombre</th><th>Direccion</th><th>Líneas</th></tr>";
        for (int i=0; i<lista_paradas.size(); i++)
        {
            ObjetoParada p=(ObjetoParada)lista_paradas.get(i);
            html+="<tr><td>"+p.getStopId()+"</td><td>"+p.getName()+"</td><td>"+p.getPostalAddress()+"</td><td>"+p.getLista_lineas()+"</td></tr>";
            
        }
        html+="</table>";
         return html;
    }
   
}
