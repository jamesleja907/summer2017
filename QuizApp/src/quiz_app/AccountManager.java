package quiz_app;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the saving and loading of Accounts.
 */
public class AccountManager {
	
	/** The instance of AccountManager */
	private static final AccountManager instance = new AccountManager();
	
	/** A map from the login of the Account to the Account. */
	private Map<String, Account> accounts;
	
	/**
	 * Creates a new AccountManager.
	 */
	private AccountManager() {
		accounts = new HashMap<String, Account>();
		File file = new File("accounts.ser");
		if (file.exists()) {
			try {
				readFromFile(file.getPath());
			} catch (FileNotFoundException e) {
			} catch (ClassNotFoundException e) {
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * @return the instance of AccountManager.
	 */
	public static AccountManager getInstance() {
		return instance;
	}
	/**
	 * Populates the question map from the file at path filePath.
	 * 
	 * @param filePath
	 *            the path of the data file
	 * @throws FileNotFoundException
	 *             if filePath is not a valid path
	 */
	public void readFromFile(String filePath) throws FileNotFoundException, ClassNotFoundException {
		try {
			InputStream file = new FileInputStream(filePath);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);

			// deserialize the Map
			accounts = (Map<String, Account>) input.readObject();
			input.close();
		} catch (ClassNotFoundException ex) {
		} catch (IOException ex) {
		}
	}
	
	/**
	 * Writes the questions to file at filePath.
	 * 
	 * @param filePath
	 *            the file to write the records to
	 * @throws IOException
	 */
	public void saveToFile() throws IOException {
		File file = new File("accounts.ser");

		OutputStream filePath = new FileOutputStream(file.getPath());
		OutputStream buffer = new BufferedOutputStream(filePath);
		ObjectOutput output = new ObjectOutputStream(buffer);

		// serialize the Map
		output.writeObject(accounts);
		output.close();
	}
	
	/**
	 * Add an account to the map.
	 * 
	 * @param account
	 * 		The account to be added.
	 */
	public void addAccount(Account account) {
		if (!accounts.containsKey(account.getLogin())) {
			accounts.put(account.getLogin(), account);
		}
	}
	
	/**
	 * Remove an account from the map.
	 * 
	 * @param account
	 * 		The account to be removed.
	 */
	public void removeAccount(Account account) {
		if (accounts.containsKey(account.getLogin())) {
				accounts.remove(account.getLogin());
		}
	}
}