/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverYCliente;

/**
 *
 * @author brais.fernandezvazqu
 */
import java.net.Socket;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {
        OutputStream flujoSalida;
        DataOutputStream flujo;
        byte[] ip={(byte)192,(byte)168,1,(byte)129};
        try {
            //InetAddress addr = InetAddress.getByName("localhost");
            //InetAddress addr = InetAddress.getLocalHost();
            InetAddress addr = InetAddress.getByAddress(ip);
            Socket socketCliente = new Socket(addr, 8000);
            flujoSalida = socketCliente.getOutputStream();
            flujo = new DataOutputStream(flujoSalida);
            //crear mensaje que se envía al server
            String frase = "Hola Mundo ";
            frase=frase.concat(addr.getHostAddress());
            frase=frase.concat(" Brais Fernández ");
            //Escribir en consola 
            System.out.println(frase.concat(addr.getCanonicalHostName()));//Concateno al mensaje el FQDN
            //Enviar mensaje
            flujo.writeInt(frase.length());
            flujo.writeBytes(frase);
            socketCliente.close();
        } catch (UnknownHostException e) {
            System.out.println("Referencia a host no resuelta");
        } catch (IOException e) {
            System.out.println("Error en las comunicaciones");
        } catch (SecurityException e) {
            System.out.println("Comunicacion no permitida");
        } 
    }
}
