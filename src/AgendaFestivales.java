
import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colecci�n map
 * La clave del map es el mes (un enumerado festivales.modelo.Mes)
 * Cada mes tiene asociados en una colecci�n ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen alg�n festival
 *
 * Las claves se recuperan en orden alfab�ico
 *
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;
    
    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * a�ade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se crear� una nueva entrada
     * con dicha clave y la colecci�n formada por ese �nico festival
     *
     * Si la clave (el mes) ya existe se a�ade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insert�ndolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el m�todo de ayuda
     * obtenerPosicionDeInsercion()
     *
     */
    public void addFestival(Festival festival) {
        String miMes = festival.getMes().toString();
        ArrayList<Festival> miFestival = new ArrayList<>();
        if (agenda.containsKey(miMes))
        {
           miFestival = agenda.get(miMes);
           int pos = obtenerPosicionDeInsercion(miFestival, festival);
           miFestival.add(pos, festival);
        }
        else
        {
            miFestival.add(festival);
            agenda.put(Mes.valueOf(miMes), miFestival);
        }
    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posici�n en la que deber�a ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales, Festival festival)
    {
        ListIterator<Festival> it = festivales.listIterator();
        while (it.hasNext())
        {
            if (it.next().getNombre().compareTo(festival.getNombre()) < 0)
            {
                it.previous();
                return it.nextIndex();
            }
        }
        return festivales.size();
    }

    /**
     * Representaci�n textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas para recorrer el map
     */
    @Override
    public String toString() {
        String resul = "";
        for (Map.Entry<Mes, ArrayList<Festival>> i : agenda.entrySet())
        {
            Mes miMes = i.getKey();
            ArrayList<Festival> miFestival = i.getValue();
            resul += miMes + "\n";
            for (Festival miFestival2 : miFestival)
            {
                resul += "\t" + miFestival2.toString();
            }
            resul += "\n";
        }
        return resul;
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes)
    {
       if (agenda.containsKey(mes))
       {
           return agenda.get(mes).size();
       }
       else
       {
           return 0;
       }
    }

    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colecci�n
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     * <p>
     * Identifica el tipo exacto del valor de retorno
     */
    public Map<Estilo, HashSet<String>> festivalesPorEstilo() {
        Map<Estilo, HashSet<String>> festivalEstiloso = new HashMap<>();
        for (ArrayList<Festival> festivales : agenda.values()) {
            for (Festival miFestival : festivales) {
                for (Estilo estilo : miFestival.getEstilos()) {
                    // Verificar si ya existe un conjunto para este estilo en el mapa
                    if (!festivalEstiloso.containsKey(estilo)) {
                        festivalEstiloso.put(estilo, new HashSet<>());
                    }
                    // Agregar el nombre del festival al conjunto correspondiente al estilo
                    festivalEstiloso.get(estilo).add(miFestival.getNombre());
                }
            }
        }
        return festivalEstiloso;
    }

    /**
     * Se cancelan todos los festivales organizados en alguno de los
     * lugares que indica el conjunto en el mes indicado. Los festivales
     * concluidos o que no empezados no se tienen en cuenta
     * Hay que borrarlos de la agenda
     * Si el mes no existe se devuelve -1
     *
     * Si al borrar de un mes los festivales el mes queda con 0 festivales
     * se borra la entrada completa del map
     */
    public int cancelarFestivales(HashSet<String> lugares, Mes mes) {
        if (!agenda.containsKey(mes))
        {
            return -1;
        }
        ArrayList<Festival> festivalesBorrar = new ArrayList<>();
        for (Festival miFestival : agenda.get(mes))
        {
            if (lugares.contains(miFestival.getLugar()) && !miFestival.haConcluido())
            {
                festivalesBorrar.add(miFestival);
            }
        }
        int festivalBorrado = festivalesBorrar.size();
        agenda.get(mes).removeAll(festivalesBorrar);
        if (agenda.get(mes).isEmpty())
        {
            agenda.remove(mes);
        }
        return festivalBorrado;
    }
}
