package com.fei.crawlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
 }

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
       TreeNode(int x) { val = x; }
}

class Solution {
	
	class State{
		TreeNode node ; 
		int state ; 
		State(TreeNode node,int state){
			this.node = node ; 
			this.state = state; 
		}
	}
	
	 public List<Integer> postorderTraversal(TreeNode root) {
    	Stack<State> stack = new Stack<>() ; 
    	List<Integer> list = new ArrayList<Integer>() ;
    	stack.push(new State(root,0)) ; 
    	while(!stack.isEmpty()){
    		State state = stack.pop() ; 
    		if(state.node == null){
    			continue; 
    		}
    		if(state.state == 0){
    			stack.push(new State(state.node,1)) ;
    			stack.push(new State(state.node.left,0)) ; 
    		}else if(state.state == 1){
    			stack.push(new State(state.node,2)) ; 
    			stack.push(new State(state.node.right,0)) ;
    		}else{
    			list.add(state.node.val) ; 
    		}
    	}
        return list ; 
    }
    
    public static void main(String[] args){
    	TreeNode root = new TreeNode(1) ;
    	TreeNode left = new TreeNode(4) ; 
    	TreeNode right = new TreeNode(2) ;
    	TreeNode rr = new TreeNode(3) ; 
    	root.left = left ; 
    	root.right = right ; 
    	right.right = rr ; 
    	System.out.println(new Solution().postorderTraversal(root));
    }
}







