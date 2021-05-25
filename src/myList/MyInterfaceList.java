package myList;

import java.util.Collection;
import java.util.List;

public interface MyInterfaceList <O> extends List<O>{
	int searchByValue(O valor);
	boolean add(O arg0);
	void add(int arg0, O arg1);
	boolean addAll(Collection<? extends O> arg0);
}
