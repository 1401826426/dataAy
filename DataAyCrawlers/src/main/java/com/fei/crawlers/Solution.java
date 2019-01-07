package com.fei.crawlers;

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



public class Solution {
    public TreeNode Convert(TreeNode root) {
    	if(root == null){
    		return null ; 
    	}
    	return convert(root)[0] ; 
    }

	private TreeNode[] convert(TreeNode root) {
		if(root == null){
			return null ;  
		}
		TreeNode[] leftNodes = convert(root.left) ; 
		TreeNode[] rightNodes = convert(root.right) ; 
		TreeNode head = root; 
		TreeNode tail = root ; 
		if(leftNodes != null){
			leftNodes[1].right = root ; 
			root.left = leftNodes[1] ; 
			head = leftNodes[0] ; 
		}
		if(rightNodes != null){
			root.right = rightNodes[0] ; 
			rightNodes[0].left = root ; 
			tail = rightNodes[1] ; 
		}
		return new TreeNode[]{head,tail};
	}
	
	public static void main(String[] args){
		int[] a = new int[]{10,6,14,4,8,12,16} ; 
		TreeNode[] nodes = new TreeNode[a.length+1] ; 
		for(int i = 0;i < a.length;i++){
			nodes[i+1] = new TreeNode(a[i]) ; 
		}
		for(int i = 1;i <= a.length/2;i++){
			int ll = i*2 ; 
			if(ll <= a.length){
				nodes[i].left = nodes[ll] ; 
			}
			int rr = i*2+1 ; 
			if(rr <= a.length){
				nodes[i].right = nodes[rr] ; 
			}
		}
		TreeNode head = new Solution().Convert(nodes[1]) ; 
		TreeNode pre = null ; 
		while(head != null){
			System.out.println(head.val);
			pre = head ; 
			head = head.right ; 
		}
		System.out.println("======================");
		while(pre != null){
			System.out.println(pre.val);
			pre = pre.left; 
		}
	}
}




































