package springMVC.NLoveB.po;

import java.io.Serializable;

/**
 * 
 * @ClassName:  Term_relationships_map   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:06:45   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class Term_relationships_map implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int objectid;		//文章id
	private int taxonomyid;		//分类方法id
	
	public Term_relationships_map(){
		
	}
	
	public Term_relationships_map(int objectid,int taxonomyid){
		this.objectid=objectid;
		this.taxonomyid=taxonomyid;
	}
	
	public int getObjectid() {
		return objectid;
	}

	public void setObjectid(int objectid) {
		this.objectid = objectid;
	}

	public int getTaxonomyid() {
		return taxonomyid;
	}

	public void setTaxonomyid(int taxonomyid) {
		this.taxonomyid = taxonomyid;
	}
	
	@Override
	public int hashCode() {  
        final int PRIME = 31;  
        int result = 1;  
        result = PRIME * result + (objectid == 0 ? 0 : String.valueOf(objectid).hashCode());  
        result = PRIME * result + (taxonomyid == 0 ? 0 : String.valueOf(taxonomyid).hashCode());   
        return result;  
    } 
	
	@Override
	public boolean equals(Object obj) {  
        if(this == obj) {
			return true;
		}  
        if(obj == null) {
			return false;
		}  
        if(!(obj instanceof Term_relationships_map)) {
			return false;
		}  
        Term_relationships_map objKey = (Term_relationships_map)obj;  
        if(objectid==(objKey.objectid) && taxonomyid==(objKey.taxonomyid)) {  
            return true;  
        }  
        return false;  
    } 
}
