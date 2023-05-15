package hw3_package;

public class lock
{
	 
	  protected boolean locked = true;
	 
	  public synchronized boolean isLocked()
	  {
	    return locked;
	  }
	 
	  public synchronized void lock ()
	  {
	    locked = true;  
	  }
	 
	  public synchronized void unlock ()
	  {
	    locked = false;  
	  }
	}
