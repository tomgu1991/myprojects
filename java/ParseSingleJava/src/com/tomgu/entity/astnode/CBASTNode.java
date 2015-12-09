package com.tomgu.entity.astnode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;

public class CBASTNode extends AbstractCBASTNode {

	public CBASTNode(ASTNode n) {
		super(n);
	}

	@Override
	public void mapTokens(AbstractCBASTNode tar, Map<String,List> map) {
		// TODO Auto-generated method stub
		if(map.containsKey(node.toString())){
			List list = map.get(toCBString());
			if(!list.contains(tar.toCBString()))
				list.add(tar.toCBString());	
		}
		else{
			List<String> list = new ArrayList<>();
			list.add(tar.toCBString());
			map.put(toCBString(),list);
			
		}
	}
	@Override
	public String toCBString() {
		// TODO Auto-generated method stub
		return node.toString();
	}

}
