package com.mycompany.a3;
import java.util.Vector;

public class GameObjectCollection implements ICollection {
	private Vector<GameObject> theCollection;
	
	public GameObjectCollection(){
		//Vector<GameObject> gObjects = new Vector<GameObject>();
		theCollection = new Vector<GameObject>();
	}

	public void add(GameObject newGameObject) {
		theCollection.add((GameObject)newGameObject);	
	}
	
	public void remove(GameObject newGameObject) {
		theCollection.remove(newGameObject);
	}


	public IIterator getIterator() {
		return new SpaceVectorIterator();
	}
	// UML notation page 17 Design pattern 
	private class SpaceVectorIterator implements IIterator {
		private int currElementIndex;
		
		public SpaceVectorIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (theCollection.size() <=  0)
				return false;
			if (currElementIndex >= theCollection.size() - 1)
				return false;
			return true; 
		}
//design pattern notes
		public GameObject getNext() {
			currElementIndex ++;
			return(theCollection.elementAt(currElementIndex));
		}	

	}	

}
