

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * La clase contiene méodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 */
public class FestivalesIO {

    
    public static void cargarFestivales(AgendaFestivales agenda) {
        Scanner sc = null;
        try {
            sc = new Scanner(FestivalesIO.class.
                    getResourceAsStream("/festivales.csv"));
            while (sc.hasNextLine()) {
                String lineaFestival = sc.nextLine();
                Festival festival = parsearLinea(lineaFestival);
                agenda.addFestival(festival);
                
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        
    }

    /**
     * se parsea la línea extrayendo sus datos y creando y
     * devolviendo un objeto Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {
        String nombreDefinitivo = "";
        String[] miFestival = lineaFestival.split(":");
        String nombre;
        String lugar = "";
        LocalDate fechaInicio = null;
        int duracion = 0;
        HashSet<Estilo> miEstilo = new HashSet<>();
        for (int i = 0; i < miFestival.length; i++) {
            if (i == 0) {
                int t = 0;
                nombre = miFestival[i].trim();
                for (int n = 0; n < nombre.length(); n++) {
                    if (n == 0) {
                        nombreDefinitivo = nombreDefinitivo + nombre.toUpperCase().charAt(n);
                    } else if (nombre.charAt(n) == ' ') {
                        t = n + 1;
                        nombreDefinitivo = nombreDefinitivo + " " + nombre.toUpperCase().charAt(t);
                    } else if (n != t) {
                        nombreDefinitivo = nombreDefinitivo + nombre.charAt(n);
                    }
                }
            } else if (i == 1) {
                lugar = miFestival[i].trim().toUpperCase();
            } else if (i == 2) {
                fechaInicio = LocalDate.parse(miFestival[i].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } else if (i == 3) {
                duracion = Integer.parseInt(miFestival[i].trim());
            } else {
                Estilo estilito = Estilo.valueOf(miFestival[i].trim().toUpperCase());
                miEstilo.add(estilito);
            }
        }
        return new Festival(nombreDefinitivo, lugar, fechaInicio, duracion, miEstilo);
    }
}
