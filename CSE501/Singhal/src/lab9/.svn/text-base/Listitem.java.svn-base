package lab9;

public class Listitem<K, V> {
	K key;
	V Value;
	Listitem<K,V> next;
	Listitem<K,V> prev;
	
	
	public Listitem (K key, V Value){
		this.key = key;
		this.Value = Value;


	}
/**
 *
 */
	public String toString(){
		if (next == null)
			return "(" + key + ", " + Value + ") ";
		else
			return "(" + key + ", " + Value + ") "+ " --> " + next.toString();
	}

	
	
}
