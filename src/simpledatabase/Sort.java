package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	private int x;
	private boolean check = false;
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	public void init(){
		Tuple t = child.next();
		int i;
		for (i=0; i<t.getAttributeList().size(); i++){
			if (t.getAttributeName(i).equals(orderPredicate)){
				x = i;
				break;
			}
		}
		
		while (t != null){
			tuplesResult.add(t);
			t = child.next();
		}
		
		Collections.sort(tuplesResult, new Comparator<Tuple>(){
			public int compare(Tuple t1, Tuple otherT){
				return String.valueOf(t1.getAttributeValue(x)).compareTo(String.valueOf(otherT.getAttributeValue(x)));
			}
		});
	}
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if (!check){
			check = true;
			init();
		}
		if (tuplesResult.isEmpty()){
			return null;
		}else{
			return tuplesResult.remove(0);
		}
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}