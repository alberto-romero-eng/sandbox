package localhost.sandbox.InheritancePolymorphismInterface;

import localhost.sandbox.InheritancePolymorphismInterface.pojo.PersonDetailFour;

public class InheritancePolymorphismMain {

	public static void main() {
		
		System.out.println("Hello from InheritancePolymorphismMain!");
		
		PersonDetailFour personDetailFour = new PersonDetailFour();
		personDetailFour.name = "Alberto";
		personDetailFour.age = 40;
		personDetailFour.height = 1.73;
		personDetailFour.didService = false;
		
		System.out.println("personDetailFour: " + personDetailFour.toString());
		
	}
	

}