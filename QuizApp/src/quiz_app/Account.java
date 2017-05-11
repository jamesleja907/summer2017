package quiz_app;

import java.io.Serializable;

/**
 * The superclass for Players and Admins.
 */
public class Account implements Serializable {

	/** To serialize accounts. */
	private static final long serialVersionUID = -5495483695798467352L;
	/** The login to the account. */
	private String login;
	/** The password of the account. */
	private String password;

	/**
	 * 
	 * @param login
	 *            The login to the account.
	 * @param password
	 *            The password of the account.
	 */
	public Account(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/**
	 * 
	 * @return the login of the account.
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * 
	 * @param login
	 *            The new login of the account.
	 */
	public void setLogin(String login) {
		// Do we want them to be able to change their login?
		// Check all other login to make sure the String new_login is unique.
		this.login = login;
	}

	/**
	 * 
	 * @return the password of the account.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 *            The new password of the account.
	 */
	public void setPassword(String old_password, String new_password) {
		// A user can only change the password if he/she can correctly provide
		// the old password.
		if (this.password == old_password) {
			this.password = new_password;
		}
	}
}
