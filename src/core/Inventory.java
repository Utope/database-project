package core;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory extends ArrayList<ItemInstance> {
	
	
	public class InventoryIterator implements Iterator{
		
		int counter;
		
		@Override
		public boolean hasNext() {
			return (counter < size());
		}

		@Override
		public Object next() {
			if(hasNext()) {
				return get(counter++);
			}else {
				return null;
			}
		}
		
	}
}
