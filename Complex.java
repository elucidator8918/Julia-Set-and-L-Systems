import java.util.*;
import java.math.*;
public class Complex 
{
    double x; // Real
    double y; // Imaginary
    public Complex() 
    {
        this(-0.7269,0.1889);
    }
    public Complex(double real, double imag)
    {
        x=real;
        y=imag;
    }
    public String toString() 
    {
        if(y==0)
        return Double.toString(x);
        if(x==0)
        return Double.toString(y)+"i";
        return Double.toString(x)+" + "+Double.toString(y)+"i";
    }
    public double magnitude() 
    {
        return Math.hypot(x,y);
    }
    public double phase() 
    {
        return Math.atan2(y,x);
    }
    public Complex scalar(Complex a) 
    {        
        return new Complex(x+a.x,y+a.y);
    }
    public Complex mult(Complex a) 
    {
        return new Complex(x*a.x-y*a.y,x*a.y+y*a.x);
    }
    public Complex pow(Complex a,double p) 
    {        
        double mag = Math.pow(a.magnitude(),p),theta=a.phase();        
        a=new Complex(mag*Math.cos(p*theta),mag*Math.sin(p*theta));
        return a;
    }
    public Complex scale(double alpha) 
    {
        return new Complex(x*alpha,y*alpha);
    }    
}