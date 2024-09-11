/** Starter code for P3
 *  @author
 */
// Change to your net id
package jxp230045;

// If you want to create additional classes, place them in this file as subclasses of MDS

import java.security.KeyPair;
import java.util.*;

public class MDS {
    // Add fields of MDS here

    static class Pair<K, V> {
        private final K first;
        private final V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    HashMap<Integer, Pair<Integer, ArrayList<Integer>>> id_map = new HashMap<>();
    // Constructors
    public MDS() {
    }

    /* Public methods of MDS. Do not change their signatures.
       __________________________________________________________________
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated. 
       Returns 1 if the item is new, and 0 otherwise.
    */
    public int insert(int id, int price, java.util.List<Integer> list) {
        if(id_map.containsKey(id)){
            id_map.put(id, new Pair<>(price, new ArrayList<>(list)));
            return 0;
        }
        else{
            id_map.put(id, new Pair<>(price, new ArrayList<>(list)));
            return 1;
        }
    }

    // b. Find(id): return price of item with given id (or 0, if not found).
    public int find(int id) {
	    if(id_map.containsKey(id)){
            Pair<Integer, ArrayList<Integer>> pair = id_map.get(id);
            return pair.first;
        }
        return 0;
    }

    /* 
       c. Delete(id): delete item from storage.  Returns the sum of the
       ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public int delete(int id) {
        int sum = 0;
	    if(id_map.containsKey(id)){
            Pair<Integer, ArrayList<Integer>> pair = id_map.get(id);
            for(int i : pair.second){
                sum = sum + i;
            }
            id_map.remove(id);
        }
        return sum;
    }

    /* 
       d. FindMinPrice(n): given an integer, find items whose description
       contains that number (exact match with one of the ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public List<Integer> key_set(HashMap<Integer, Pair<Integer, ArrayList<Integer>>> map, int value) {
        List<Integer> keys = new ArrayList<>();
        for (Integer id : map.keySet()) {
            if (map.get(id).second.contains(value)){
                keys.add(id);
            }
        }
        return keys;
    }

    public int findMinPrice(int n) {
        List<Integer> keys = key_set(id_map, n);
        int min = Integer.MAX_VALUE;
        for (int key : keys) {
            Pair<Integer, ArrayList<Integer>> pair = id_map.get(key);
            if(pair != null){
                min = Math.min(min, pair.first);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /*
       e. FindMaxPrice(n): given an integer, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public int findMaxPrice(int n) {
        List<Integer> keys = key_set(id_map, n);
        int max = Integer.MIN_VALUE;
        for (int key : keys) {
            Pair<Integer, ArrayList<Integer>> pair = id_map.get(key);
            if(pair != null){
                max = Math.max(max, pair.first);
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    /* 
       f. FindPriceRange(n,low,high): given int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(int n, int low, int high) {
        List<Integer> keys = key_set(id_map, n);
        int count = 0;
        if(low > high){
            return 0;
        }
        for (int i : keys) {
            if (id_map.containsKey(i)) {
                int price = id_map.get(i).first;
                if (price >= low && price <= high) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
      g. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public int removeNames(int id, java.util.List<Integer> list) {
        int sum = 0;
	    if(id_map.containsKey(id)){
            ArrayList<Integer> names = id_map.get(id).second;
            for(Integer i : list){
                if(names.contains(i)){
                    sum += i;
                    names.removeAll(Arrays.asList(i));
                }
            }
        }
        return sum;
    }
}

