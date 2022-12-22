public class TurtlePos 
{
    int xPos,yPos;
    double angle;
    public TurtlePos(int xPos, int yPos, double angle) 
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
    }
    public int getxPos() 
    {
        return xPos;
    }

    public int getyPos() 
    {
        return yPos;
    }

    public double getAngle() 
    {
        return angle;
    }
}