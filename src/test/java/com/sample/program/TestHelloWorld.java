package com.sample.program;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sample.program.HelloWorld;

public class TestHelloWorld {

	private HelloWorld h;

	@Before
	public void setUp() throws Exception {
		h = new HelloWorld();
	}

	@Test
	public void getMessageTest() {
		String msg = h.getMessage();
		assertEquals(msg, "Hello World!");
	}
}
