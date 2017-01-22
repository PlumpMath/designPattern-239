package com.panda;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singleton implements Serializable ,Cloneable{
	/**
	 * volatile give guarantee that will not give half bake(initilized ) object
	 * Write happed before the read for volatile varible (in case of two thread)
	 */
	private static volatile  Singleton singletonInstance=null;
	private Singleton(){
		if(singletonInstance!=null)//This will block the reflection
		{
			throw new RuntimeException("can not create please use getInstance() method");
		}
		System.out.println("Creating...");
	}
	
	/*public static Singleton getInstance(){
		if(singletonInstance==null)
		{
			synchronized(Singleton.class)
			{
				
//				 double checking becouse while getting lock some other thred might initilized the thread.
//				  double check locking is broken becouse java specification allow half initilised instanse to be published
//				  half initilized veriable can see by other thread
				 
				if(singletonInstance==null)
					
				{
			singletonInstance=new Singleton();
				}
			}
		}
		return singletonInstance;//may get half initilized variable
	}*/
	
	//another apporch to handale multiple thread
	 static class Holder{
		 static final  Singleton INSTANCE=new Singleton();
	}
	
	 public static Singleton getInstance(){
		return Holder.INSTANCE;
	 }
	
	/**
	 * To change the deserilization behavior 
	 * @return
	 * @throws ObjectStreamException
	 */
	public Object readResolve() throws ObjectStreamException
	{
		return singletonInstance;
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		throw new RuntimeException("clone not supported");
		//return super.clone();
	}
	
}
