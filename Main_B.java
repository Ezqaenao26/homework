package iss.java;

import iss.java.list.MyList;
import iss.java.list.Node;

/**
 * Created by wenke on 2016/9/16.
 */

public class Main_B {
    public static void main(String[] args) throws InterruptedException {
        // TODO: Write a multithreaded testcase against requirement B.
        // NOTE: Testcase for requirement C is not required.
    	final Object obj = new Object();
    	
    	 MyList list = new MyList();
         list.insert(list.getHead(), 5);
         list.insert(list.getHead(), 4);
         list.insert(list.getHead(), 4);
         list.insert(list.getHead(), 2);
         list.insert(list.getHead(), 2);
         list.insert(list.getHead(), 2);
         //����	requirement C
        /* MyList listA=new MyList();
         list.insert(listA.getHead(), 3);
         list.remove(listA.getHead());*/
         
         //Thread to insert
         Thread insert = new Thread(){
        	 @Override
        	 public void run(){
        		 for (int i =0;i<4;i++){
        			 synchronized(obj){
        				 list.insert(list.getHead(), 0);
        			 }
        		 }
        	 }
         };
         //Thread to delete
         Thread delete=new Thread(){
             @Override
             public  void run(){
                 for(int i=0;i<4;i++){
                     synchronized (obj){
                         list.remove(list.getHead().getNext());
                     }
                 }
             }
         };
         //�������
         Thread Traverse=new Thread(){
             @Override
             public void run(){
                 synchronized (obj){
                     for (Node n = list.getHead().getNext(); n!=list.getTail(); n=n.getNext()) {
                         System.out.printf("%d ", n.getData());
                     }
                     System.out.println();
                 }
             }
         };
         //�������
         Thread back_Traverse=new Thread(){
             @Override
             public void run(){
                 synchronized (obj){
                     for (Node n = list.getTail().getPrev(); n!=list.getHead(); n=n.getPrev()) {
                         System.out.printf("%d ", n.getData());
                     }
                     System.out.println();
                 }
             }
         };
         insert.start();
         delete.start();
         
         Thread.sleep(1000);
         Traverse.start();
         Thread.sleep(1000);
         back_Traverse.start();
         int a = list.getSize();
         System.out.println(a);
         
         
    }
}
