package helper;

public class RegexPattern {
	public final static String NAME = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,15}[a-zA-Z0-9]$";
	public final static String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{7,20}$";
	public final static String NUMBER = "^[0-9]*$";
}
