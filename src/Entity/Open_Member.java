package Entity;

public class Open_Member {
	private String School_Name;//required
	private int Team_ID;//required
	private String Name;//required
	private String NRIC;//required
	private String Shirt_Size;//required
	private int Contact;//required
	private String Email;//optional not checked
	private String Player_Pos;//required
	private String Category;//required
	private String Chess;//required
	public Open_Member(String school_Name, int team_ID, String name,
			String nRIC, String shirt_Size, int contact, String email,
			String player_Pos, String category, String chess) {
		super();
		School_Name = school_Name;
		Team_ID = team_ID;
		Name = name;
		NRIC = nRIC;
		Shirt_Size = shirt_Size;
		Contact = contact;
		Email = email;
		Player_Pos = player_Pos;
		Category = category;
		Chess = chess;
	}
	public String getSchool_Name() {
		return School_Name;
	}
	public void setSchool_Name(String school_Name) {
		School_Name = school_Name;
	}
	public int getTeam_ID() {
		return Team_ID;
	}
	public void setTeam_ID(int team_ID) {
		Team_ID = team_ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNRIC() {
		return NRIC;
	}
	public void setNRIC(String nRIC) {
		NRIC = nRIC;
	}
	public String getShirt_Size() {
		return Shirt_Size;
	}
	public void setShirt_Size(String shirt_Size) {
		Shirt_Size = shirt_Size;
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
	public String getPlayer_Pos() {
		return Player_Pos;
	}
	public void setPlayer_Pos(String player_Pos) {
		Player_Pos = player_Pos;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getChess() {
		return Chess;
	}
	public void setChess(String chess) {
		Chess = chess;
	}
	
}
