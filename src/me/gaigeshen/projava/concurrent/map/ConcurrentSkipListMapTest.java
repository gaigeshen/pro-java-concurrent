package me.gaigeshen.projava.concurrent.map;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author gaigeshen
 */
public class ConcurrentSkipListMapTest {
  public static void main(String[] args) {

    ConcurrentNavigableMap<Integer, String> map = new ConcurrentSkipListMap<>();

    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");
    map.put(4, "four");
    map.put(5, "five");

    // 小于或等于指定key的map
    System.out.println(map.headMap(2, true));

    // 大于指定key的map
    System.out.println(map.tailMap(3, false));

    // 指定key之间（包含）的map
    System.out.println(map.subMap(2, true, 4, true));

  }

}
