package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;
	private boolean check = false;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		int x, y;
		int i1 = 0;
		int i2 = 0;
		
		if (!check){
			check = true;
			Tuple t1 = leftChild.next();
			while (t1 != null){
				tuples1.add(t1);
				t1 = leftChild.next();
			}
		}
		
		Tuple t2 = rightChild.next();
		if (t2 == null){
			return null;
		}
		for (x=0; x<tuples1.size(); x++){
			for (y=0; y<t2.getAttributeList().size(); y++){
				if (tuples1.get(y).getAttributeName(x).equals(t2.getAttributeName(y))){
					i1 = x;
					i2 = y;
					break;
				}
			}
		}
		
		
		
		while (t2 != null){
			for (x=0; x<tuples1.size(); x++){
				Tuple t = tuples1.get(x);
				
				if (t.getAttributeValue(i1).equals(t2.getAttributeValue(i2))){
					ArrayList<Attribute> a_list = new ArrayList<Attribute>();
					a_list = t.attributeList;
					for (Attribute new_a : t2.getAttributeList()){
						if (!new_a.attributeName.equals(t2.getAttributeName(i2))){
							a_list.add(new_a);
						}
					}

					Tuple new_t = new Tuple(a_list);
					return new_t;
				}
				
			}
			t2 = rightChild.next();
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}