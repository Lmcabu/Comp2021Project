package hk.edu.polyu.comp.comp2021.g17.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.g17.cvfs.model.file.File;

public abstract class Criterion{
	String name;
	Object attrname;;
	Op op;
	Object val;
	
	protected Criterion(String name, Object attrname, Op op, Object val) throws IllegalArgumentException {
		if (!isValidCriName(name) || !isValidCri(attrname, op, val)) throw new IllegalArgumentException("Invalid argument combination is passed on creating criterion");
		this.name = name;
		this.attrname = attrname;
		this.op = op;
		this.val = val;
	}
	
	/**
	 * check if a character variable is letter 
	 * @param temp
	 * @return true if character is letter, false otherwise
	 */
	private boolean isLetter(char character) {
		int code = (int)character;
		if ((code >= 65 && code <=90) || (code >= 97 && code <= 122)) {
			return true;
		}
		return false;
	}
	
	/**
	 * check if name is valid
	 * @param name
	 * @return true if name is valid, false otherwise
	 */
	private boolean isValidCriName(String name) {
		//TODO 
		if (name.length() == 2) {
			if (isLetter(name.charAt(0)) && isLetter(name.charAt(1))) {return true;}
			}
		return false;
	}
	
	/**
	 * check if AttrName,Op,Val constitute a valid criterion
	 * @param an
	 * @param op
	 * @param val
	 * @return true if it does, false otherwise
	 */
	private boolean isValidCri(Object an, Op op, Object val) {
		//TODO
		if(an==AttrName.size){
			if(op==Op.G||Op.GE||Op.E||Op.S||Op.SE||Op.NE) {
				if(var instanceof Integer) return true;
			}
		}
		else if(an==AttrName.type){
			if(op==Op.equals) {
				if(var instanceof String) return true;
			}
		}
		else if(an==AttrName.name){
			if(op==Op.contains){
				if(var instanceof String) return true;
			}
		}
		else if(an instanceof Criterion){
			if(op==Op.AND||op==Op.OR){
				if(var instanceof Criterion) return true;
			}
		}
		return false;
	
	}
	
	/**
	 * Assert whether the file satisfies the criterion
	 * @param file
	 * @return
	 */
	
	public abstract boolean assertCri(File file);
	
	public static Criterion newSimpleCri(String name, String attrname, String op, String val){
		//TODO
		//An interface to create a criterion
		//first convert the string to the correct type,then call the corresponding constructor(TypeCri, NameCri, SizeCri)
		
		//convert the attrname to AttrName
		for(AttrName i: AttrName.values()){
			if(attrname.equals(i.toStrings())){
				AttrName attrNamec=i;
			}

		}

		//convert the op to Op
		switch (op){
			case ">":
				Op opc=Op.G;
				break;
			case ">=":
				Op opc=Op.GE;
				break;
			case "<":
				Op opc=Op.S;
				break;
			case "<=":
				Op opc=Op.SE;
				break;
			case "==":
				Op opc=Op.E;
				break;
			case "!=":
				Op opc=Op.NE;
				break;
		}

		//convert the val
		if(attrNamec==AttrName.size) {
			int valc = Integer.valueOf(val).intValue();
			return new SizeCri(name,opc,valc);
		}
		else if(attrNamec==AttrName.name){
			String valc=val;
			return new NameCri(name,valc);
		}
		else{
			String valc=val;
			return new TypeCri(name,valc);
		}
		
	
	}
	
	public static Criterion newNegation(String name, Criterion cri) throws IllegalArgumentException {
		return new Criterion(name, cri.attrname, cri.op.negate(), cri.name){
			public boolean assertCri(File file) {
				return !cri.assertCri(file);
			}
		};
	}
	
	public static Criterion newBinaryCri(String name, Criterion c1, Op bo, Criterion c2) throws IllegalArgumentException {
		return new Criterion(name, c1, bo, c2) {
			public boolean assertCri(File file) {
			//TODO
			//return the right composition of c1 and c2
				if(bo==Op.AND) return c1.assertCri(file) && c2.assertCri(file);
				else return c1.assertCri(file)||c2.assertCri(file);
			}
		};
	}
	
	//mc  这个感觉有点问题 比如 ci&&cj (都是复合)
	//ci=c1||c2  cj=c3||c4
	//用我写的这个打印就是   c1||c2&&c3||c4
	//这个应该不等价于 (c1||c2)&&(c3||c4)吧
	//还是说不用考虑复合的呢
	public void printCri() {
		//TODO
		//print the information about this criterion
		
		//convert object attrname
		String attrNames=new String();
		if(attrname instanceof AttrName){
			attrNames=this.attrname.toString();
		}
		else{
			attrname.printCri();
		}

		//convert op
		String ops=new String();
		switch (this.op){
			case contains:
				ops="contains";
				break;
			case equals:
				ops="equals";
				break;
			case G:
				ops=">";
				break;
			case GE:
				ops=">=";
				break;
			case E:
				ops="==";
				break;
			case NE:
				ops="!=";
				break;
			case S:
				ops="<";
				break;
			case SE:
				ops="<=";
				break;
			case AND:
				ops="&&";
				break;
			case OR:
				ops="||";
				break;
			case not_contains:
				ops="not contains";
				break;
			case not_equals:
				ops="not equals";
				break;
		}
		String vals=new String();
		if(!val instanceof Criterion){
			vals=this.val.toString();
		}
		else{
			while (val instanceof Criterion){
				val.printCri();
			}
		}

		String printInformation=attrNames+" "+ops+" "+vals;
		System.out.println(printInformation);


	}
}
