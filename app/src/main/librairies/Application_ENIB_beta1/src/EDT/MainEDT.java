
package EDT;

/*
 * You need to start the application, create a user, create a calendar with those. Create an EDT with the user calendar.
 * You can add,change,delete homework and DS FROM the Application or you'll need to call EDT.edtData(SchoolClass) to save
 * the information in the XML.
 * 
 * EDT is able to:
 * 		- Create the user calendar (ical) from the edt.enib.fr
 * 		- create an EDT with it
 * 		- Add/change homework
 * 		- Able/disable DS
 * 		- Initialization of previous settings for SchoolClass (from XML)
 * 		- While initializing compare if location's event or date's event move or if the event is deleted.
 *  Need to know if the UID change when event moves.
 */

import Application.Application;
import Profil.User;

public class MainEDT {

	public static void main(String[] args) {
		
		User user = new User("a2guilli");
		Application app = new Application();
		app.wantsEDT(user.getLogin());
	}

}
