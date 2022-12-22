import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.color.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;
public class JulPan extends JPanel 
{
    BufferedImage image = null;
    int w=600,h=480;
    int[] rgb=new int[3];
    float real = 0f,imaginary=0f;
    String Title01 = "",Title02 = "600X480 Pixels",Title03 = " ",Title04 = " ";
    public JulPan() 
    {
        image = null;
        setPreferredSize(new Dimension(600, 480));
    }
    public JulPan(BufferedImage bi)
    {
        image = bi;
        setPreferredSize(new Dimension(600, 480));
    }
    public void SetTitle(String t3, String t4) 
    {
        Title03=" Real Part = " + t3;
        Title04=" Imaginary Part = " + t4;
    }
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Font f = new Font("Serif", Font.ITALIC,12);
        g2.setFont(f);
        if (image != null) 
        {
            g2.drawString(Title01,10,60);
            g2.drawString(Title02,10,90);
            g2.drawString(Title03.substring(0,20),10,120);
            g2.drawString(Title04.substring(0,25),10,150);
            g2.drawImage(image,190, 40, this);
        }
        else         
        g2.drawString("No Fractal",10,20);        
    }
    public BufferedImage getImage() 
    {
        return image;
    }
    public void setImage(BufferedImage bi) 
    {
        image = bi;
        repaint();
    }
    public void createImage(double cr, double ci, String cmd,double p)
    {
        Complex c = new Complex(cr, ci);
        w = 400;
        h = 400;
        Title01 = "F(z) = " + cmd;
        image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        double xmin=-2,ymin=-2;
        double xscale=4f/w,yscale=4f/h;
        Complex z = new Complex();
        for(int i=0;i<h;i++) 
        {
            for(int j=0;j<w;j++) 
            {
                z.x=xmin+j*xscale;
                z.y=ymin+i*yscale;
                int count = (int) iterCount(z,c,cmd,p);
                /*rgb[0] = ((count << 4) & 0x7333399) << 6 | ((count << 2) & 0x7ffff99) << 3; // blue component
                rgb[1] = ((count << 2) & 0x7ffff99) << 2 ^ ((count >> 2) & 0x7cc4778) << 5; // green component
                rgb[2] = ((count << 4) & 0x7cc4778) << 5 | ((count << 4) & 0x7333399) << 6; // red component*/
                rgb[1] = (count ^ 0x7333399) << 5; 
                rgb[2] = count & 0x7ffff99 >> 5; 
                rgb[0] = count | 0x7cc4778 >> 4; 
                raster.setPixel((w - 1 - j), i,rgb);
            }
        }
    }
    private double iterCount(Complex z, Complex c, String cmd,double p) 
    {
        double t=0;
        if ("Julia".equals(cmd))
        t = julia(z,c,p);
        return t;
    }
    private int julia(Complex z, Complex c,double p) 
    {
        int it=0;        
        for(double lsq=0;(lsq<100.0)&&(it<=300);it++)
        {
            z=z.pow(z,p).scalar(c.scale(1.5));
            lsq=z.magnitude();
        }
        return it;
    }
}