
import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colección map
 * La clave del map es el mes (un enumerado festivales.modelo.Mes)
 * Cada mes tiene asociados en una colección ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen algún festival
 *
 * Las claves se recuperan en orden alfabéico
 *
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;
    
    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * añade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se creará una nueva entrada
     * con dicha clave y la colección formada por ese único festival
     *
     * Si la clave (el mes) ya existe se añade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insertándolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el método de ayuda
     * obtenerPosicionDeInsercion()
     *
     */
    public void addFestival(Festival festival) {
        ArrayList<Festival> miFestival = new ArrayList<>();
        if (agenda.containsKey(festival.getMes()))
        {
           miFestival = agenda.get(festival.getMes());
           int pos = obtenerPosicionDeInsercion(miFestival, festival);
           miFestival.add(pos, festival);
        }
        else
        {
            miFestival.add(festival);
            agenda.put(festival.getMes(), miFestival);
        }
    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posición en la que debería ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales, Festival festival)
    {
        int i = 0;
        for (Festival miFestival : festivales)
        {
            if (miFestival.getNombre().compareTo(festival.getNombre()) > 0)
            {
                i++;
            }
        }
        return i;
    }

    /**
     * Representación textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas para recorrer el map
     */
    @Override
    public String toString() {
        String i = "";
        ArrayList<Mes> misMeses = new ArrayList<>(agenda.keySet());
        ArrayList<Festival> miFestival;
        for (Mes miMes : misMeses)
        {
            miFestival = new ArrayList<>(agenda.get(miMes));
            i = i.concat("\n\n" + miMes + " (" + this.festivalesEnMes(miMes) + " festival/es)\n");
            for (Festival festival : miFestival)
            {
                i = i.concat(festival.toString());
            }
        }
        return i;
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
           return -1;
       }
    }

    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colección
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     * <p>
     * Identifica el tipo exacto del valor de retorno
     */
    public Map<Estilo, TreeSet<String>> festivalesPorEstilo() {
        Map<Estilo, TreeSet<String>> festivalEstiloso = new HashMap<>();
        for (ArrayList<Festival> festivales : agenda.values()) {
            for (Festival miFestival : festivales) {
                for (Estilo estilo : miFestival.getEstilos()) {

                    if (!festivalEstiloso.containsKey(estilo)) {
                        festivalEstiloso.put(estilo, new TreeSet<>());
                    }
                    else
                    {
                        festivalEstiloso.get(estilo).add(miFestival.getNombre());
                    }
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
