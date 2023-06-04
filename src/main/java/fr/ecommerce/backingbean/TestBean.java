package fr.ecommerce.backingbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ecommerce.common.IConstant;

@ManagedBean
@SessionScoped
public class TestBean implements IConstant {

	private int  intVar ; 
	private double doubleVar ; 
	private float  floatVar ;
	
	
	public TestBean() {
		this.setDoubleVar(10.0/3.0);
		this.setFloatVar(5f/3.0f);
		this.setIntVar(100);
	}
	public int getIntVar() {
		return intVar;
	}
	public void setIntVar(int intVar) {
		this.intVar = intVar;
	}
	public double getDoubleVar() {
		return doubleVar;
	}
	public void setDoubleVar(double doubleVar) {
		this.doubleVar = doubleVar;
	}
	public float getFloatVar() {
		return floatVar;
	}
	public void setFloatVar(float floatVar) {
		this.floatVar = floatVar;
	} 
	
	
	
}
