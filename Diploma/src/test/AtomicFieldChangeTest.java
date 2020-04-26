package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import schema.AtomicFieldChange;

class AtomicFieldChangeTest {
	
	private AtomicFieldChange field;
	
	@BeforeAll
	public static void setUp() {
		System.out.println("Testing AtomicFieldChange class...\n");
	}
	
	@BeforeEach
	public void init() {
		field = new AtomicFieldChange();
	}
	
	@After
	public void tearDown() {
		field = new AtomicFieldChange();
	}

	@Test
	void testAddedChange() {
		field.setKey("name");
		field.setValue("Georgia");
		field.setParent("root");
		field.setAct("+");
		assertEquals("+", field.getAct());
		System.out.println("----Field---- \n" +
				field.getKey() + " : " + field.getValue() +
				"\nParent : " +field.getParent() + "\n" +
				field.getAct() + " : Added\n");
	}
	
	@Test
	void testRemovedChange() {
		field.setKey("email");
		field.setValue("abc@gmail.com");
		field.setParent("root");
		field.setAct("-");
		assertEquals("-", field.getAct());
		System.out.println("---Field----\n" +
				field.getKey() + " : " + field.getValue() +
				"\nParent : " +field.getParent() + "\n" +
				field.getAct() + " : Removed");
	}
}
