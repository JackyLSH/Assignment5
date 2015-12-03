package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();
	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		if(!whereTablePredicate.equals(child.from)){
			return child.next();
		}

		Tuple t = child.next();
		
		if (t == null){
			return null;
		}
		
		int i;
		while (t != null){
			for (i=0; i<t.getAttributeList().size(); i++){
				Attribute a = t.getAttributeList().get(i);
				
				if (a.getAttributeName().equals(whereAttributePredicate) && a.getAttributeValue().equals(whereValuePredicate)){
					Tuple new_t = new Tuple(t.getAttributeList());
					return new_t;
				}
			}
			t = child.next();
		}
		return null;
			
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}