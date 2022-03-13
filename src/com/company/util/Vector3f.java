package com.company.util;

public class Vector3f {

	private float x=0;
	private  float y=0;
	private  float z=0;
	
	public Vector3f() 
	{  
		setX(0.0f);
		setY(0.0f);
		setZ(0.0f);
	}
	 
	public Vector3f(float x, float y, float z) 
	{ 
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	public Vector3f AddVector(Vector3f V)
	{
		return new Vector3f(this.getX()+V.getX(), this.getY()+V.getY(), this.getZ()+V.getZ());
	}

	//Vector Addition
	//TODO DO WE NEED THIS FUNCTION
	public Vector3f PlusVector(Vector3f V)
	{ 
		return new Vector3f(this.getX()+V.getX(), this.getY()+V.getY(), this.getZ()+V.getZ());
	} 
	
	//Vector Subtraction
	//TODO DO WE NEED THIS FUNCTION
	public Vector3f MinusVector(Vector3f V)
	{ 
		return new Vector3f(this.getX()-V.getX(), this.getY()-V.getY(), this.getZ()-V.getZ());
	}

	//Scaling a Vector
	public Vector3f byScalar(float scale )
	{
		return new Vector3f(this.getX()*scale, this.getY()*scale, this.getZ()*scale);
	}
	
	//Negative of a Vector
	public Vector3f  NegateVector()
	{
		return new Vector3f(-this.getX(), -this.getY(), -this.getZ());
	}
	
	//Magnitude of a vector
	public float length()
	{
	    return (float) Math.sqrt(getX()*getX() + getY()*getY() + getZ()*getZ());
	}
	
	//Normal of a vector
	public Vector3f Normal()
	{
		float LengthOfTheVector=  this.length();
		return this.byScalar(1.0f/ LengthOfTheVector); 
	} 
	
	//Dot Product
	public float dot(Vector3f v)
	{ 
		return ( this.getX()*v.getX() + this.getY()*v.getY() + this.getZ()*v.getZ());
	}
	
	//Cross Product
	public Vector3f cross(Vector3f v)
	{ 
    float u0 = (this.getY()*v.getZ() - getZ()*v.getY());
    float u1 = (getZ()*v.getX() - getX()*v.getZ());
    float u2 = (getX()*v.getY() - getY()*v.getX());
    return new Vector3f(u0,u1,u2);
	}

	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getZ() {
		return z;
	}


	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setZ(float z) {
		this.z = z;
	}
 
}
	 
