
package Notes;

/*
 * You need to start the application, initialize semesters (inside Application), add notes by the application 
 * 
 * Note is able to:
 * 		- Create each semesters/blocs/subjects of the student
 * 		- Add marks to subjects
 * 		- Calculate the TD/TP/DS/main average even with marks missing
 * 		- Save marks
 * 		- Initialize marks from xml
 * 		
 */

import Application.Application;

public class NoteMain {

	public static void main(String[] args) {
		Application app = new Application();
		app.initSemesters();
		//app.addNote(1, "A", "anglais", "ds", 20);
	}

}
