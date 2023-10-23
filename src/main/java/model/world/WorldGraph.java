package model.world;

import java.util.*;

public class WorldGraph<T> {

    private Map<T, Set<T> > map = new HashMap<>();

    public void addVertex(T s)
    {
        map.put(s, new HashSet<>());
    }

    public void addVertex(T ... s)
    {
        for(T v : s)
            map.put(v, new HashSet<T>());
    }

    public void addEdge(T source, T destination, boolean bidirectional)
    {
        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);

        if (bidirectional == true)
            map.get(destination).add(source);
    }

    public Set<T> getVertex(){
        return map.keySet();
    }

    public T getSingularVertex(T vertex){
        for(T n : map.keySet()){
            if(n.equals(vertex))
                return n;
        }
        return null;
    }

    public Set<T> getEdges(T vertex){
        return map.get(vertex);
    }

    public int getVertexCount()
    {
        return map.keySet().size();
    }

    public int getEdgesCount(boolean bidirection)
    {
        int count = 0;

        for (T v : map.keySet())
            count += map.get(v).size();

        if (bidirection == true)
            count = count / 2;

        return count;
    }

    public boolean hasVertex(T s) { return map.containsKey(s); }

    public boolean hasEdge(T s, T d)
    {
        return map.get(s).contains(d);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + " : ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}