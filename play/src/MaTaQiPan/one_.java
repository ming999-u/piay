package MaTaQiPan;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class one_ {
    private static int x =6;//lie//col
    private static int y =6;//hang//row
    private static int[][] chessBoard =new int[y][x];//棋盘

    private static boolean[] visited = new boolean[x*y];//记录某个位置是否走过
    private static boolean finished =false;//记录是否完成

    public static void main(String[] args) {


        int row =2;
        int col =2;

        long start =System.currentTimeMillis();
        traversalChessBoard(chessBoard,row-1,col-1,1);
        long end =System.currentTimeMillis();

        System.out.println("="+(end-start));

        for(int[] rows:chessBoard){

            for (int step :rows){//step表示马该走的第几步
                System.out.print(step + "\t");
            }
            System.out.println();
            
        }
    }

    //遍历棋盘，成功就finished = true,并记录过程
    public static void traversalChessBoard(int [][] chessBoard,int row, int col ,int step){
        //记录step chessboard
        chessBoard[row][col] =step;
        //把位置设置已经访问
        visited[row*x+col]=true;

        ArrayList<Point> ps = next(new Point(col, row));
        sort(ps);


        while (!ps.isEmpty()){
            Point p = ps.remove(0);

            if (!visited[p.y*x+p.x]){
                traversalChessBoard(chessBoard,p.y,p.x,step+1);
            }

        }
        //没成功就回溯
        if (step<x*y && !finished){
            //重置
            chessBoard[row][col]=0;
            visited[row*x+col]=false;
        }else{
           finished=true;
        }




    }

    public static ArrayList<Point> next(Point curPoint){

        ArrayList<Point> points = new ArrayList<>();

        Point p1 = new Point();

        //判断curPoint是否可以走如下位置
        //5
        if((p1.x = curPoint.x-2)>= 0 && (p1.y =curPoint.y-1)>=0){
            points.add(new Point(p1));
        }
        //6
        if ((p1.x = curPoint.x-1)>= 0 && (p1.y =curPoint.y-2)>=0){
            points.add(new Point(p1));
        }
        //7
        if ((p1.x = curPoint.x+1)<x && (p1.y =curPoint.y-2)>=0){
            points.add(new Point(p1));
        }
        //0
        if ((p1.x = curPoint.x+2)<x && (p1.y =curPoint.y-1)>=0){
            points.add(new Point(p1));
        }
        //1
        if ((p1.x = curPoint.x+2)<x && (p1.y =curPoint.y+1)<y){
            points.add(new Point(p1));
        }
        //2
        if ((p1.x = curPoint.x+1)<x && (p1.y =curPoint.y+2)<y){
            points.add(new Point(p1));

        }
        //3
        if ((p1.x = curPoint.x-1)>=0 && (p1.y =curPoint.y+2)<y){
            points.add(new Point(p1));
        }

        //4
        if ((p1.x = curPoint.x-2)>=0 && (p1.y =curPoint.y+1)<y){
            points.add(new Point(p1));
        }

        return points;

    }

    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size()-next(o2).size();
            }
        });
    }
}
