package Logic;

import java.util.ArrayList;

import Entity.Open_Member;
import Entity.Schools;
import Entity.Team_Details;

public class EntityCreation {
	public ArrayList<Object> obj;
	Schools school_details;
	Team_Details teamDetails;
	String state;
	String school;
	String teamID;
	String chess;
	String teamCategory;
	public EntityCreation(ArrayList<Object> object){
		obj = object;
	}
	public void sort(){
		for(int i=0; obj.size()>i;i++){
			
			String head = (((Object[])obj.get(i))[0]).toString();
			if(head.equals("Header")){
				Object[] details = ((Object[])((Object[])obj.get(i))[1]);
					school_details = new Schools();
					teamDetails = new Team_Details();
					school = details[2].toString();
					school_details.setT_In_Charge(details[0].toString());
					school_details.setContact(Integer.valueOf(details[1].toString()));
					school_details.setSchool_Name(school);
					school_details.setEmail(details[3].toString());
					teamID =(details[4].toString());
					teamCategory =(details[5].toString());
					chess = (details[6].toString());
			}else if(head.equals("Open")){
				String[][] openTeam = ((String[][])((Object[])obj.get(i))[1]);
				ArrayList<Open_Member> members = new ArrayList<Open_Member>(); 
				//starts from row 1 
				for(int j=1;openTeam.length>j;j++){
					Open_Member member = new Open_Member();
					for(int k=0; openTeam[j].length>k;k++){
						if(openTeam[j][k].equals("")){
							break;
						}
						String player_Pos = openTeam[j][0];
						String player_name = openTeam[j][1];
						String player_NRIC = openTeam[j][2];
						String player_Size = openTeam[j][3];
						String player_Contact = openTeam[j][4];
						String player_Email = openTeam[j][5];
						member.setSchool_Name(school);
						member.setTeam_ID(teamID);
						member.setName(player_name);
						member.setNRIC(player_NRIC);
						member.setShirt_Size(player_Size);
						member.setContact(Integer.valueOf(player_Contact));
						member.setEmail(player_Email);
						member.setPlayer_Pos(player_Pos);
						member.setCategory(teamCategory);
						member.setChess(chess);
					}
					members.add(member);
				}
			}else if(head.equals("Blitz")){
				
			}
		}
	}
	

}
