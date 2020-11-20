package hk.edu.polyu.comp.comp2021.g17.cvfs.model.criterion;

import hk.edu.polyu.comp.comp2021.g17.cvfs.model.file.File;

class NameCri extends Criterion{
	public NameCri(String criName, String checkName) throws IllegalArgumentException {
		super(criName, AttrName.name, Op.contains, checkName);
	}

	public boolean assertCri(File file) {
		// TODO 
		//return true if file name contains checkName
		//false otherwise
		
		//mc~ contains意思是不是就是包含这一段字符就可以 但是可以比checkname长
		String fileName=file.getName();
		int length=fileName.length();
		String checkName=(String) this.val;
		for(int i=0; i<length; i++){
			if(fileName.startsWith(this.val,i)) return true;
		}
		return false;
	}

}
