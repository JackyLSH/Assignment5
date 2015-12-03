package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple t = child.next();
		if (t == null){
			return null;
		}
		
		int i;
		for (i=0; i<t.getAttributeList().size(); i++){
			Attribute a = t.getAttributeList().get(i);
			
			if (a.getAttributeName().equals(attributePredicate)){
				ArrayList<Attribute> a_list = new ArrayList<Attribute>();
				a_list.add(a);
				Tuple new_t = new Tuple(a_list);
				return new_t;
			}
		}
		return null;
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}