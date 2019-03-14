import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestHelloWorld {

   private HelloWorld h;
  
   @Before
   public void setUp() throws Exception 
   {
      h = new HelloWorld();
   }

     
   @Test
   public void testHelloWorld() 
   {
      String msg = h.getMessage();
      assertEquals(msg,"Hello World!");
   }
} 
