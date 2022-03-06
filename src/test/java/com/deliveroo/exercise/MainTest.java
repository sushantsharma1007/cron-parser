package com.deliveroo.exercise;

import org.junit.Test;

public class MainTest {
	@Test
	public void testMainWithValidInput() {
	    String[] args = new String[] {"*/15 1-5 1-15 * 1,5 /usr/bin/find"};
	    Main.main(args);
	}
	@Test
	public void testMainWithMultipleInput() {
	    String[] args = new String[] {"*/15 1-5 1-15 * 1,5 /usr/bin/find","find"};
	    Main.main(args);
	}
	@Test
	public void testMainWithInvalidInput() {
	    String[] args = new String[] {"*/15 1-5 1-15 A 1,5 /usr/bin/find"};
	    Main.main(args);
	}
}
