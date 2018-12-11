package com.fei.crawlers.mapper;

public class Solution {
    public String reverseWords(String s) {
    	char[] chs = s.toCharArray() ;
    	int st = 0 ;
    	while(st < chs.length && chs[st] == ' '){
    		st++ ; 
    	}
    	int en = chs.length - 1 ; 
    	while(en > 0 && chs[en] == ' '){
    		en-- ; 
    	}
    	if(st > en){
    		
    		return "" ; 
    	}
    	int preIndex = en ;
    	int p = en ;
    	int pos = en ; 
    	while(p >= st-1){
    		if(p == st-1 || s.charAt(p) == ' '){
    			if(preIndex != -1){
    				for(int i = p+1;i <= preIndex;i++){
    					char tmp = chs[i] ; 
    					chs[i] = chs[preIndex-(i-p-1)] ;
    					chs[preIndex-(i-p-1)] = tmp ; 
    				}
    				for(int i = preIndex;i >= p+1;i--){
    					chs[pos--] = chs[i] ; 
    				}
    				if(p != st-1){
    					chs[pos--] = chs[p] ;
    				}
    			}
    			preIndex = -1 ; 
    		}else{
    			if(preIndex == -1){
    				preIndex = p ; 
    			}
    		}
    		p--;
    	}
    	for(int i = pos+1;i <= en;i++){
    		char tmp = chs[i] ; 
    		chs[i] = chs[en-(pos+1-i)] ; 
    		chs[en-(pos+1-i)] = tmp ; 
    	}
    	int pp = 0 ; 
    	for(int i = pos+1;i <= en;i++){
    		chs[pp++] = chs[i] ; 
    	}
    	return new String(chs,0,pp) ; 
    }
    
    public static void main(String[] args){
    	System.out.println(new Solution().reverseWords("the sky is blue"));
    }
    
}





