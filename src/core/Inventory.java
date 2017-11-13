package core;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory{
	private ArrayList<Item> items;
	
	public Inventory() {
		items = new ArrayList<Item>();
	}
	
	public void add(Inventory inventory) {
		Iterator it = inventory.iterator();
		while(it.hasNext()) {
			items.add((Item) it.next());
		}
	}
	
	public void remove(Inventory inventory) {
		Iterator it = inventory.iterator();
		while(it.hasNext()) {
			this.remove((Inventory) it.next());
		}
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public boolean remove(Item item) {
		return items.remove(item);
	}
	
	public boolean contains(Item item) {
		return items.contains(item);
	}
	
	public int size() {
		return items.size();
	}
	
	public Iterator iterator() {
		return new InventoryIterator();
	}
	
	public class InventoryIterator implements Iterator{
		
		int counter;
		
		@Override
		public boolean hasNext() {
			return (counter < items.size());
		}

		@Override
		public Object next() {
			if(hasNext()) {
				return items.get(counter++);
			}else {
				return null;
			}
		}
		
	}
}
