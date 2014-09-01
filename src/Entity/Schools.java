package Entity;

public class Schools {
	private String School_Name; //required
	private String T_In_Charge; //required
	private int Contact; //required
	private String Email; //optional,constraints not checked
	private int Num_Blitz;//if null, 0 is filled in
	private int Num_Team;//checked >0
	private int Total_Cost;//computed
	public Schools(String school_Name, String t_In_Charge, int contact,
			String email, int num_Blitz, int num_Team, int total_Cost) {
		super();
		School_Name = school_Name;
		T_In_Charge = t_In_Charge;
		Contact = contact;
		Email = email;
		Num_Blitz = num_Blitz;
		Num_Team = num_Team;
		Total_Cost = total_Cost;
	}
	public Schools(){
		
	}
	public String getSchool_Name() {
		return School_Name;
	}
	public void setSchool_Name(String school_Name) {
		School_Name = school_Name;
	}
	public String getT_In_Charge() {
		return T_In_Charge;
	}
	public void setT_In_Charge(String t_In_Charge) {
		T_In_Charge = t_In_Charge;
	}
	public int getContact() {
		return Contact;
	}
	public void setContact(int contact) {
		Contact = contact;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getNum_Blitz() {
		return Num_Blitz;
	}
	public void setNum_Blitz(int num_Blitz) {
		Num_Blitz = num_Blitz;
	}
	public int getNum_Team() {
		return Num_Team;
	}
	public void setNum_Team(int num_Team) {
		Num_Team = num_Team;
	}
	public int getTotal_Cost() {
		return Total_Cost;
	}
	public void setTotal_Cost(int total_Cost) {
		Total_Cost = total_Cost;
	}
	
}
