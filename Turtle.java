import javax.swing.*;
import java.awt.*;
import java.util.Stack;
public class Turtle extends JFrame 
{
    int xPosInit = 100,yPosInit = 900;
    double angleInit = (180-55) * Math.PI/180;
    int xPos = xPosInit,yPos = yPosInit;
    double angle = angleInit,d = 25 * Math.PI/180;
    String w0 = "X";
    char[] wn;
    Stack<TurtlePos> positionStack = new Stack<>();
    int n=7;
    public Turtle() throws InterruptedException 
    {
        setTitle("L-system");
        setSize(1366, 768);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        wn = generateString(n);
    }

    public void paint(Graphics g)
    {
        for (char c:wn)
            moveTurtle(g, c);        
        xPos = xPosInit;
        yPos = yPosInit;
        angle = angleInit;
    }

    public void moveTurtle(Graphics g, char action)
    {
        if(action == 'F'){
            int endX = (int) (xPos + 4 * Math.sin(angle));
            int endY = (int) (yPos + 4 * Math.cos(angle));
            g.drawLine(xPos, yPos, endX, endY);
            xPos = endX;
            yPos = endY;
        }
        if(action == '+'){
            angle = angle + d;
        }
        if(action == '-'){
            angle = angle - d;
        }
        if(action == '['){
            TurtlePos tp = new TurtlePos(xPos, yPos, angle);
            positionStack.push(tp);
        }
        if(action == ']'){
            TurtlePos tp = positionStack.pop();
            xPos = tp.xPos;
            yPos = tp.yPos;
            angle = tp.angle;
        }
    }
    public char[] generateString(int n)
    {
        String tmp = w0;
        for(int i=0; i < n; i++){
            StringBuilder currentString = new StringBuilder();
            char[] chars = tmp.toCharArray();
            for (Character c:
            chars) {
                if(c.equals('X')) {
                    currentString.append("F+[[X]-X]-F[-FX]+X");
                } else if(c.equals('F')){
                    currentString.append("FF");
                } else {
                    currentString.append(c);
                }
            }
            tmp = currentString.toString();
        }
        return tmp.toCharArray();
    }
}
