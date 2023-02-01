import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.color.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;
public class Julia extends JFrame implements ActionListener
{
    String title = "",fractalName = "",cmd="";
    Container cp = null;
    float cr=-0.7269f,ci=0.1889f,p=2;
    JMenuItem miOpen, miSave, miExit,miJulia;
    JulPan imageSrc;
    JFileChooser fc = new JFileChooser();
    public Julia()
    {
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        JMenu menu = new JMenu("File");
        miOpen = new JMenuItem("Open Fractal");
        miOpen.addActionListener(this);
        menu.add(miOpen);
        miSave = new JMenuItem("Save Fractal");
        miSave.addActionListener(this);
        menu.add(miSave);
        menu.addSeparator();
        miExit = new JMenuItem("Exit");
        miExit.addActionListener(this);
        menu.add(miExit);
        mb.add(menu);
        JMenu mSelect = new JMenu("Constant");
        JMenuItem mi = new JMenuItem("Random");
        mi.addActionListener(this);
        mSelect.add(mi);
        JMenuItem mManual = new JMenuItem("Manual");
        mManual.addActionListener(this);mSelect.add(mManual);
        mb.add(mSelect);
        JMenu mFractal = new JMenu("Fractal");
        mb.add(mFractal);
        JMenuItem mJulia = new JMenuItem("Julia");
        mJulia.addActionListener(this);
        mFractal.add(mJulia);
        //JMenuItem mLSys = new JMenuItem("L-Sys");
        //mLSys.addActionListener(this);
        //mFractal.add(mLSys);
        cp = this.getContentPane();
        cp.setLayout(new FlowLayout());
        imageSrc = new JulPan();
        cp.add(imageSrc);
        WindowListener l = new WindowAdapter()
            {
                public void windowClosing(WindowEvent ev)
                {
                    System.exit(0);
                }

                public void windowActivated(WindowEvent ev)
                {
                    repaint();
                }

                public void windowStateChanged(WindowEvent ev)
                {
                    repaint();
                }
            };
        ComponentListener k = new ComponentAdapter()
            {
                public void componentResized(ComponentEvent e)
                {
                    repaint();
                }
            };
        MouseListener mouseListener = new MouseAdapter()
            {
                public void mouseClicked(MouseEvent ev)
                {
                    return;
                }
            };
        this.addWindowListener(l);
        this.addComponentListener(k);
        this.addMouseListener(mouseListener);
    }

    public void actionPerformed(ActionEvent ev)
    {
        cmd = ev.getActionCommand();
        if("Open Fractal".equals(cmd))
        {int retval = fc.showOpenDialog(this);
            if (retval == JFileChooser.APPROVE_OPTION)
            {
                imageSrc.setImage(null);
                try
                {
                    BufferedImage bi = ImageIO.read(fc.getSelectedFile());
                    imageSrc.SetTitle("Unknown", "Unknown");
                    imageSrc.setImage(bi);
                    setSize(600,480);
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        else if("Save Fractal".equals(cmd))
        {
            if (imageSrc == null)
            {
                imageSrc.SetTitle(Double.toString(cr), Double.toString(ci));
                imageSrc.createImage(cr,ci,cmd,p);
                imageSrc.setImage(imageSrc.getImage());
            }
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int retval = fc.showSaveDialog(this);
            if (retval == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    File s = fc.getSelectedFile();
                    String path = s.getPath();
                    double r = cr;
                    double i = ci;
                    String fileName = path + "\\" + fractalName + " Fractal- CR=" +
                        Double.toString(r) + "- CI="+ Double.toString(i) + ".bmp";
                    File output = new File(fileName);
                    ImageIO.write(imageSrc.getImage(), "bmp", output);
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        else if ("Exit".equals(cmd))
            System.exit(0);
        else if ("Random".equals(cmd))
        {
            cr=(float)Math.random();
            ci=(float)Math.random();if(cr<Math.random())
                cr=-cr;
            if(ci<Math.random())
                ci=-ci;
        }
        if ("Manual".equals(cmd))
        {
            cr = Float.parseFloat(JOptionPane.showInputDialog(null,"Enter Real Component"));
            ci = Float.parseFloat(JOptionPane.showInputDialog(null,"Enter Imaginary Component"));
            p = Float.parseFloat(JOptionPane.showInputDialog(null,"Enter the Power"));
        }
        if ("Julia".equals(cmd)) //||"L-Sys".equals(cmd))
        {
            fractalName = cmd;
            imageSrc.SetTitle(Double.toString(cr), Double.toString(ci));
            imageSrc.createImage(cr,ci,cmd,p);
            imageSrc.setImage(imageSrc.getImage());
            setSize(600,480);
        }
    }

    public static void main()
    {
        JFrame frame = new Julia();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,480);
        frame.setVisible(true);
    }
}
