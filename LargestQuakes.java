import java.util.*;

public class LargestQuakes
{
    public static void main(String[] args){
        findLargestQuakes();
    }

    public static void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read data for " + list.size());


        ArrayList<QuakeEntry> ret = getLargest(list, 5);
        for (QuakeEntry r : ret){
            System.out.println(r);
        }
    }

    public static ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany)
    {
        HashMap<QuakeEntry, Double> output = new HashMap<>();

        for (QuakeEntry quake : quakeData)
        {
            Double mag = quake.getMagnitude();
            output.put(quake, mag);
        }

        ArrayList<QuakeEntry> outputSortByMagnitude = getKeysSortedByValue(output);

        for (int i = outputSortByMagnitude.size() - howMany - 1; i >= 0 ; i--)
        {
            outputSortByMagnitude.remove(i);
        }

        return outputSortByMagnitude;

    }

    public static <K, V extends Comparable<? super V>> ArrayList<K>
    getKeysSortedByValue(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        ArrayList<K> result = new ArrayList<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.add(entry.getKey());
        }

        return result;
    }

}

