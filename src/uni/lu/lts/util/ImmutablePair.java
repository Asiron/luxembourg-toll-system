/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.util;

/**
 *
 * @author asiron
 */
public class ImmutablePair<A, B> {
    
    private final A first;
    private final B second;
    
    public ImmutablePair(A first, B second) {
    	super();
    	this.first = first;
    	this.second = second;
    }
    
    @Override
    public int hashCode() {
    	int hashFirst = first != null ? first.hashCode() : 0;
    	int hashSecond = second != null ? second.hashCode() : 0;

    	return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }
    
    @Override
    public boolean equals(Object other) {
    	if (other instanceof ImmutablePair) {
    		ImmutablePair otherPair = (ImmutablePair) other;
    		return 
    		((  this.first == otherPair.first ||
    			( this.first != null && otherPair.first != null &&
    			  this.first.equals(otherPair.first))) &&
    		 (	this.second == otherPair.second ||
    			( this.second != null && otherPair.second != null &&
    			  this.second.equals(otherPair.second))) );
    	}

    	return false;
    }
    
    @Override
    public String toString()
    { 
           return "(" + first + ", " + second + ")"; 
    }
    
    public A getFirst() {
    	return first;
    }

    public B getSecond() {
    	return second;
    }
}
