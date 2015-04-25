/*
Name: Hong Ting
AdminNo: 130250D
Class: IS1301
Date Created: 19/12/2013
 */

package entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class EmailValidator 
{
 
	private Pattern pattern;
	private Matcher matcher;
	private String email;
 
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
	public EmailValidator(String email) 
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
 
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) 
	{
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}