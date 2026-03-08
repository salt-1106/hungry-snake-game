package Snake;
import java.util.Random;
public class Node {
	private int x;
    private int y;
 
    public Node(){}
    public Node(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int getx()
    {
        return x;
    }
    public void setx(int x)
    {
        this.x=x;
    }
    public int gety()
    {
        return y;
    }
    public void sety(int y)
    {
        this.y=y;
    }
  //随机位置方法
    public void random()
    {
        Random r=new Random();
        //随机生成横坐标
        this.x=r.nextInt(35);
        //随机生成纵坐标
        this.y=r.nextInt(36);
    }
}
