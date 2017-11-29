package core;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory extends ArrayList<ItemInstance> {
    
     public Iterator ItemNameIterator(String itemName){
         return new ItemNameIterator(itemName);
     }
     
     public Iterator ItemIdIterator(int id){
         return new ItemIdIterator(id);
     }
    
    /*
    Below this is all the iterator classes
    */
    
    //Loop over all items of said name
    public class ItemNameIterator  implements Iterator{

         String itemName;
         int index;

         public ItemNameIterator(String itemName){
             this.itemName = itemName;
             this.index = 0;
         }

        @Override
        public boolean hasNext() {

            while(index < size()){
                ItemInstance itemInstance = get(index);
                if(itemInstance.getItem().getName().equals(itemName)){
                    return true;
                }else{
                    index++;
                }
            }
            return false;
        }

        @Override
        public Object next() {
            return get(index++);
        }


    }
    //loop over all items of said id
    public class ItemIdIterator implements Iterator{
        int id;
        int index;

        public ItemIdIterator(int id){
            this.id = id;
            this.index = 0;
        }

    @Override
        public boolean hasNext() {

            while(index < size()){
                ItemInstance itemInstance = get(index);
                if(itemInstance.getItem().getItemId() == id){
                    return true;
                }else{
                    index++;
                }
            }
            return false;
        }

        @Override
        public Object next() {
            return get(index++);
        }

    }
        
}
