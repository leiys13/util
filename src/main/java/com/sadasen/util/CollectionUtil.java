package com.sadasen.util;

import java.util.Collection;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class CollectionUtil {
	
	public static boolean isEmpty(Collection<?> coll) {
		if(null==coll || coll.size()==0)
			return true;
		return false;
	}

}
