package hk.edu.polyu.comp.comp2021.g17.cvfs.model.criterion;

public enum Op{
	contains,
	equals,
	G,//greater
	GE,//greater or equal
	
	//丰蕴霖同学   这L,LE我改成S，SE
	S,SE,E,NE,
	not_contains,
	AND, OR,
	not_equals;
	public Op negate() {
		//TODO
		//return the negate version of op
		switch (this){
			case contains:
				return not_contains;
			case equals:
				return not_equals;
			case G:
				return SE;
			case GE:
				return S;
			case S:
				return GE;
			case SE:
				return G;
			case E:
				return NE;
			case NE:
				return E;
			case not_contains:
				return contains;
			case AND:
				return OR;
			case OR:
				return AND;
			case not_equals:
				return equals;
		}
		return null;
	}
		

}
