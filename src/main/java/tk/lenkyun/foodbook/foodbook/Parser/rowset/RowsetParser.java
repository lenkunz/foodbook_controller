package tk.lenkyun.foodbook.foodbook.Parser.rowset;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import tk.lenkyun.foodbook.foodbook.Domain.Data.FoodbookType;

import javax.sql.RowSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenkyun on 5/11/2558.
 */
public abstract class RowsetParser<E extends FoodbookType> {
    public abstract Map<String, Object> parse(E object);
    public abstract E from(SqlRowSet rowSet);

    public static String getNameToValue(Map<String, Object> list){
        ArrayList<String> strings = new ArrayList<String>();
        for(Map.Entry<String, Object> map : list.entrySet()){
            if(map.getValue() != null)
                strings.add(map.getKey() + " = ?");
        }
        return String.join(",", strings);
    }

    public static String getNameList(Map<String, Object> list){
        ArrayList<String> strings = new ArrayList<String>();
        for(Map.Entry<String, Object> map : list.entrySet()){
            if(map.getValue() != null)
                strings.add(map.getKey());
        }
        return String.join(",", strings);
    }

    public static Object[] getValueList(Map<String, Object> list){
        ArrayList<Object> objects = new ArrayList<Object>();
        for(Map.Entry<String, Object> map : list.entrySet()){
            if(map.getKey() != null)
                objects.add(map.getKey());
        }
        return objects.toArray();
    }

    public static String getValueBlanker(Map<String, Object> list){
        ArrayList<String> objects = new ArrayList<String>();
        for(Map.Entry<String, Object> map : list.entrySet()){
            if(map.getKey() != null)
                objects.add("?");
        }
        return String.join(",", objects);
    }
}
