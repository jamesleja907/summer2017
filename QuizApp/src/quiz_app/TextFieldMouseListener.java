package quiz_app;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
	
/**
 * 
 *  A Mouse Listener to set text fields to empty strings when clicked on. Follows
 * 	
*/
public class TextFieldMouseListener extends MouseAdapter {

	/**
	* Sets a JTextField to the empty string when MouseEvent mouseClicked takes
	* place in a JTextField.
	*/
	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField txtArea = (JTextField) e.getSource();
		txtArea.setText("");
	}

}


