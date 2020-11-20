package hk.edu.polyu.comp.comp2021.g17.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.g17.cvfs.model.file.File;

class SizeCri extends Criterion{
	public SizeCri(String criName, Op op, int size) throws IllegalArgumentException {
		super(criName, AttrName.size, op, size);
	}

	@Override
	public
	boolean assertCri(File file) {
		// TODO Auto-generated method stub
		int fileSize=file.size;
		Op op=this.op;
		int checkSize=(Integer) this.val;
		if(op==Op.G){
			if(fileSize>checkSize) return true;
			return false
		}
		else if(op===Op.GE){
			if(fileSize>=checkSize) return true;
			return false;
		}
		else if(op==Op.E){
			if(fileSize==checkSize) return true;
			return false;
		}
		else if(op=Op.NE){
			if(fileSize!=checkSize) return true;
			return false;
		}
		else if(op==Op.S){
			if(fileSize<checkSize) return true;
			return false;
		}
		else {
			if(fileSize<=checkSize) return true;
			return false;
		}
	}
	
}
