package festivales.modelo;

import festivales.io.FestivalesIO;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * Un objeto de esta clase almacena los datos de un
 * festival.
 * Todo festival tiene un nombre, se celebra en un lugar
 * en una determinada fecha, dura una serie de días y
 * se engloba en un conjunto determinado de estilos
 *
 */
public class Festival {
    private final String nombre;
    private final String lugar;
    private final LocalDate fechaInicio;
    private final int duracion;
    private final HashSet<Estilo> estilos;
    
    
    public Festival(String nombre, String lugar, LocalDate fechaInicio,
                    int duracion, HashSet<Estilo> estilos) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.estilos = estilos;
        
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getLugar() {
        return lugar;
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
    public int getDuracion() {
        return duracion;
    }
    
    public HashSet<Estilo> getEstilos() {
        return estilos;
    }
    
    public void addEstilo(Estilo estilo) {
        this.estilos.add(estilo);
        
    }

    /**
     * devuelve el mes de celebración del festival, como
     * valor enumerado
     *
     */
    public Mes getMes()
    {
        Mes miMes = null;
        switch (fechaInicio.getMonth())
        {
            case JANUARY:
                miMes = Mes.ENERO;
                break;
            case FEBRUARY:
                miMes = Mes.FEBRERO;
                break;
            case MARCH:
                miMes = Mes.MARZO;
                break;
            case APRIL:
                miMes = Mes.ABRIL;
                break;
            case MAY:
                miMes = Mes.MAYO;
                break;
            case JUNE:
                miMes = Mes.JUNIO;
                break;
            case JULY:
                miMes = Mes.JULIO;
                break;
            case AUGUST:
                miMes = Mes.AGOSTO;
                break;
            case SEPTEMBER:
                miMes = Mes.SEPTIEMBRE;
                break;
            case OCTOBER:
                miMes = Mes.OCTUBRE;
                break;
            case NOVEMBER:
                miMes = Mes.NOVIEMBRE;
                break;
            case DECEMBER:
                miMes = Mes.DICIEMBRE;
                break;
        }
        return miMes;
    }

    /**
     *
     * @param otro
     * @return true si el festival actual empieza
     * en un fecha anterior a otro
     */
    public boolean empiezaAntesQue(Festival otro) {
        return this.fechaInicio.isBefore(otro.fechaInicio);
    }

    /**
     *
     * @param otro
     * @return true si el festival actual empieza
     * en un fecha posteior a otro
     */
    public boolean empiezaDespuesQue(Festival otro) {
       return this.fechaInicio.isAfter(otro.fechaInicio);
    }

    /**
     *
     * @return true si el festival ya ha concluido
     */
    public boolean haConcluido() {
        LocalDate terminado = this.fechaInicio.plusDays(this.duracion);
        return terminado.isBefore(LocalDate.now());
    }

    /**
     * Representación textual del festival, exactamente
     * como se indica en el enunciado
     *
     */
    @Override
    public String toString() {
        String miMes = "";
        int diasQueFaltan = LocalDate.now().getDayOfYear() - fechaInicio.getDayOfYear();
        switch (getMes())
        {
            case Mes.ENERO:
                miMes = "ene.";
                break;
            case Mes.FEBRERO:
                miMes = "feb.";
                break;
            case Mes.MARZO:
                miMes = "mar.";
                break;
            case Mes.ABRIL:
                miMes = "abr.";
                break;
            case Mes.MAYO:
                miMes = "may.";
                break;
            case Mes.JUNIO:
                miMes = "jun.";
                break;
            case Mes.JULIO:
                miMes = "jul.";
                break;
            case Mes.AGOSTO:
                miMes = "ago.";
                break;
            case Mes.SEPTIEMBRE:
                miMes = "sep.";
                break;
            case Mes.OCTUBRE:
                miMes = "oct.";
                break;
            case Mes.NOVIEMBRE:
                miMes = "nov.";
                break;
            case Mes.DICIEMBRE:
                miMes = "dic.";
                break;
        }
        if (getDuracion() == 1)
        {
            if (haConcluido())
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + miMes + " " + fechaInicio.getYear() + "(concluido)" + "\n------------------------------------------------------------\n\n";
            }
            else if (fechaInicio.equals(LocalDate.now()))
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + miMes + " " + fechaInicio.getYear() + "(ON)" + "\n------------------------------------------------------------\n\n";
            }
            else
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + miMes + " " + fechaInicio.getYear() + "(quedan " + diasQueFaltan + " días)" + "\n------------------------------------------------------------\n\n";

            }
        }
        else
        {
            String miMes2 = "";
            LocalDate fechaFin = fechaInicio.plusDays(duracion);
            switch (fechaFin.getMonth())
            {
                case JANUARY:
                    miMes2 = "ene.";
                    break;
                case FEBRUARY:
                    miMes2 = "feb.";
                    break;
                case MARCH:
                    miMes2 = "mar.";
                    break;
                case APRIL:
                    miMes2 = "abr.";
                    break;
                case MAY:
                    miMes2 = "may.";
                    break;
                case JUNE:
                    miMes2 = "jun.";
                    break;
                case JULY:
                    miMes2 = "jul.";
                    break;
                case AUGUST:
                    miMes2 = "ago.";
                    break;
                case SEPTEMBER:
                    miMes2 = "sep.";
                    break;
                case OCTOBER:
                    miMes2 = "oct.";
                    break;
                case NOVEMBER:
                    miMes2 = "nov.";
                    break;
                case DECEMBER:
                    miMes2 = "dic.";
                    break;
            }
            if (haConcluido())
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + miMes + " - " + fechaFin.getYear() + " " + miMes2 + " " + fechaInicio.getYear() + "(concluido)" + "\n------------------------------------------------------------\n\n";
            }
            else if (fechaInicio.isBefore(LocalDate.now()) && fechaFin.isAfter(LocalDate.now()) || fechaInicio.equals(LocalDate.now()) || fechaFin.equals(LocalDate.now()))
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + miMes + " - " + fechaFin.getYear() + " " + miMes2 + " " + fechaFin.getYear() + "(ON)" + "\n------------------------------------------------------------\n\n";
            }
            else
            {
                return nombre + "\t\t\t" + getEstilos() + "\n" + lugar + "\n" + fechaInicio.getDayOfMonth() + " " + miMes + " - " + fechaInicio.plusDays(duracion).getDayOfMonth() + miMes2 + " " + fechaInicio.plusDays(duracion).getYear() + "(quedan " + diasQueFaltan + " días)" + "\n------------------------------------------------------------\n\n";
            }
        }
    }

    /**
     * Código para probar la clase festivales.modelo.Festival
     *
     */
    public static void main(String[] args) {
        System.out.println("Probando clase festivales.modelo.Festival");
        String datosFestival = "Gazpatxo Rock : " +
                "valencia: 28-02-2022  :1  :rock" +
                ":punk " +
                ": hiphop ";
        Festival f1 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f1);
        
        datosFestival = "black sound fest:badajoz:05-02-2022:  21" +
                ":rock" + ":  blues";
        Festival f2 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f2);
    
        datosFestival = "guitar bcn:barcelona: 28-01-2022 :  170" +
                ":indie" + ":pop:fusion";
        Festival f3 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f3);
    
        datosFestival = "  benidorm fest:benidorm:26-01-2022:3" +
                ":indie" + ": pop  :rock";
        Festival f4 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f4);
      
        
        System.out.println("\nProbando empiezaAntesQue() empiezaDespuesQue()" +
                "\n");
        if (f1.empiezaAntesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza antes que " + f2.getNombre());
        } else if (f1.empiezaDespuesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza después que " + f2.getNombre());
        } else {
            System.out.println(f1.getNombre() + " empieza el mismo día que " + f2.getNombre());
        }

        System.out.println("\nProbando haConcluido()\n");
        System.out.println(f4);
        System.out.println(f4.getNombre() + " ha concluido? " + f4.haConcluido());
        System.out.println(f1);
        System.out.println(f1.getNombre() + " ha concluido? " + f1.haConcluido());
 
        
        
    }
}
