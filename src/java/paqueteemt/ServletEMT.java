/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteemt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author luis
 */
@WebServlet(name = "ServletEMT", urlPatterns = {"/ServletEMT"})
public class ServletEMT extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     String longitude=request.getParameter("longitude");
     String latitude=request.getParameter("latitude");
     String Radius=request.getParameter("Radius");
     String idClient="WEB.SERV.ataraxa@hotmail.com";
     String passKey="83D88CD0-8A9B-4CE6-B976-B922B61FAE6D";
     PeticionPost peticion=new PeticionPost("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetStopsFromXY.php");
     peticion.add("passKey", passKey);
     peticion.add("idClient", idClient);
     peticion.add("latitude", latitude);
     peticion.add("longitude", longitude);
     peticion.add("Radius", Radius);
     String respuesta=peticion.getRespueta();
     ArrayList<ObjetoParada> lista_paradas=tratarJSON(respuesta);
     request.setAttribute("lista_paradas", lista_paradas);
     request.getRequestDispatcher("verparadas.jsp").forward(request, response);
             
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private ArrayList<ObjetoParada> tratarJSON(String respuesta) {
        ArrayList<ObjetoParada> lista_obj_paradas=new ArrayList<>();
         try {       
        JSONParser parseador=new JSONParser();
        JSONObject objeto_completo;
        
            objeto_completo = (JSONObject)parseador.parse(respuesta);
       
        JSONArray lista_paradas=(JSONArray)objeto_completo.get("stop");
        
                    for (int i=0; i<lista_paradas.size(); i++)
                    {
                        ArrayList<String> lista_lineId=new ArrayList<>();
                        JSONObject objeto_parada=(JSONObject)lista_paradas.get(i);
                        String stopId=(String)objeto_parada.get("stopId");
                        String name=(String)objeto_parada.get("name");
                        String postalAddress=(String)objeto_parada.get("postalAddress");
                        Object objeto_desconocido=objeto_parada.get("line");
                        /*Para tratar las liíneas, que a veces será un JSONObject y 
                            a veces un JSONArray*/
                                            if (objeto_desconocido instanceof JSONArray)
                                            {
                                                System.out.println("Es un array");
                                                //Es un JSONArray
                                                    JSONArray lista_lineas=(JSONArray)objeto_parada.get("line");
                                                    for(int j=0;j<lista_lineas.size(); j++)
                                                    {
                                                        JSONObject objeto_linea=(JSONObject) lista_lineas.get(j);
                                                        String lineId=(String)objeto_linea.get("line");
                                                        lista_lineId.add(lineId);
                                                    }

                                            }
                                            else
                                            {
                                                System.out.println("Es un object");
                                                //Es un JSONObject
                                                JSONObject  objeto_linea=(JSONObject) objeto_parada.get("line");
                                                String lineId=(String)objeto_linea.get("line");
                                                lista_lineId.add(lineId);

                                             }
                        ObjetoParada objeto=new ObjetoParada(stopId, name, postalAddress, lista_lineId);
                        lista_obj_paradas.add(objeto);


                    }
         } catch (ParseException ex) {
            Logger.getLogger(ServletEMT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista_obj_paradas;
    }

}
