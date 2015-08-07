package com.wudoumi.batter.view.listview.listviewfilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by qianjujun on 2015/6/29 0029 16:56.
 * qianjujun@163.com
 */
public class ConvertData {


    public static <T extends IAutoConvertLetter> List<T> convert(List<T> data, ListHashMap<String, Integer> map, Class<T> clazz) {
        Collections.sort(data, new SortIgnoreCase());
        List<T> list = new ArrayList<>();
        String prev_section = "";
        String current_section;
        int index = 0;
        for (T item : data) {

            item.setTitleType(true);
            current_section = item.getFirstLetter();

            if (!prev_section.equals(current_section)) {
                list.add(newInstance(clazz, current_section));

                map.put(current_section, index);

                index++;

                list.add(item);

                index++;

                prev_section = current_section;
            } else {
                list.add(item);
                index++;
            }
        }

        return list;
    }


    private static <T extends IAutoConvertLetter> T newInstance(Class<T> clazz, String firstLetter) {
        try {
            T t = clazz.newInstance();
            t.setFirstLetter(firstLetter);
            t.setTitleType(true);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private static class SortIgnoreCase<T extends IAutoConvertLetter> implements Comparator<T> {


        @Override
        public int compare(T t1, T t2) {
            return t1.getFullLetter().compareToIgnoreCase(t2.getFullLetter());
        }
    }



}
