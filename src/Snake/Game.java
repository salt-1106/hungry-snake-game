package Snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
public class Game extends JFrame{
	private Snake snk;    //蛇
    private JPanel jp;//游戏棋盘
    private Timer tm;//定时器,在指定的时间调用蛇移动的方法
    private Node food;//食物
    public Game()throws HeadlessException{
    	 //初始化窗口参数
        initFrame();
        //初始化游戏棋盘
        initGamePanel();
        //初始化蛇
        initSnk();
        //初始化食物
        initFood();
        //初始化定时器
        initTimer();
        //设置键盘监听，让蛇随着上下左右移动
        setKeyListener();
    }
    
    private void initFood()
    {
        food=new Node();
        food.random();
    }
    
    private void setKeyListener()        //设置键盘监听
    {
        addKeyListener(new KeyAdapter()
            //当键盘按下会自动调用此方法
    {
        @Override
        public void keyPressed(KeyEvent e)
                //键盘中的每个键都有编号
        {        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if(snk.getDirection()!=Direction.DOWN) {
                	snk.setDirection(Direction.UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(snk.getDirection()!=Direction.UP) {
                	snk.setDirection(Direction.DOWN);
                }
                break;
            case KeyEvent.VK_LEFT:
                if(snk.getDirection()!=Direction.RIGHT) {
                	snk.setDirection(Direction.LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(snk.getDirection()!=Direction.LEFT) {
                	snk.setDirection(Direction.RIGHT);
                }
                break;
        }
        }
    });
}
    
  //初始化定时器
    private void initTimer()
    {
        //创建定时器对象
        tm=new Timer();
        //初始化定时任务
        TimerTask timerTask=new TimerTask()
        {
            @Override
            public void run()
            {
                snk.move();
                //判断蛇头是否与食物重合
                Node head=snk.getBody().getFirst();
                if(head.getx()==food.getx()&&head.gety()==food.gety())
                {
                    snk.eat(food);
                    food.random();//蛇头与食物重合一次时，食物位置随机变换一次
                }
                //重绘游戏棋盘
                jp.repaint();
            }
        };
        //每100毫秒，执行一次定时任务
        tm.scheduleAtFixedRate(timerTask,0,100);
    }
    
    private void initSnk()
    {
        snk=new Snake();
    }
    
  //初始化游戏棋盘
    public void initGamePanel()
    {
    	jp=new JPanel()
        {
            //绘制
            @Override
            public void paint(Graphics g)       //Graphics g可以理解为一个画笔
            {
                //清空棋盘
                g.clearRect(0,0,600,600);
                //super.paint(g);
                //绘制横线
                for(int i=0;i<=35;i++)
                {
                    g.drawLine(0,i*15,600,i*15);
                }
                //绘制竖线
                for(int i=0;i<=36;i++)
                {
                    g.drawLine(i*15,0,i*15,600);
                }
                //绘制蛇
                LinkedList<Node> body=snk.getBody();
                for(Node node:body)
                {
                    g.fillRect(node.getx()*15,node.gety()*15,15,15);
                }
                //绘制食物
                g.fillRect(food.getx()*15,food.gety()*15,15,15);//将食物绘制到棋盘上
            }
        };
        add(jp);
    }
    
  //初始化窗口参数
    public void initFrame()
    {   //设置窗体长宽高
        setSize(540,575);
        //设置窗口位置
        setLocation(240,50);
        //设置关闭按钮
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //规定窗口大小不可变
        setResizable(false);
    }
    public static void main(String[]args)
    {
        new Game().setVisible(true);
    }
}
