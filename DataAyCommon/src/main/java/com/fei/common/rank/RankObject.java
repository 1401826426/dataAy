package com.fei.common.rank;

public class RankObject <K extends Comparable<K>,V>{

	private K k ; 
	
	private V v;

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	public RankObject(K k, V v) {
		super();
		this.k = k;
		this.v = v;
	}

	public RankObject() {
		super();
	}

	
	
}

