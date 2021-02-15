package iss.java;
import iss.java.list.MyList;
import iss.java.list.Node;
/**
 * Created by wenke on 2016/9/16.
 */
public class Main_A {
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement a multithreaded test case against requirement A.
    	MyList list = new MyList();
    	list.insert(list.getHead(),5);
    	list.insert(list.getHead(),4);
    	list.insert(list.getHead(),3);
    	list.insert(list.getHead(),2);

    	//
    	final Object obj = new Object();
    	Node n = list.getHead().getNext();
    	
    //¼Ó
    	Thread ta = new Thread(){
    		@Override
    		public void run(){
    			for(int i = 0; i<100;i++)
    			{			
    			synchronized(obj){
    				int a = n.getData() - 1;
    				n.setData(a);  				
    			}
    		}
    	}
    };
    
    //¼õ
    	Thread tb = new Thread(){
    		@Override
    		public void run(){
    			for(int i=0;i<100;i++){
    				synchronized(obj){
    				int a = n.getData()+1;
    				n.setData(a);	
    				}
    			}
    		}
    	};
    	ta.start();
		tb.start();
		//a second is enough for the two threads to finish
		Thread.sleep(1000);
		System.out.printf("%d ",n.getData());
    }
}
