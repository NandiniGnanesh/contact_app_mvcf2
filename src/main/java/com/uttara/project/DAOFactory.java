package com.uttara.project;

public class DAOFactory {

	public static IUserDAO getDAO() {
		
		if( Constants.DAOCHOICE == 1 ) {
			
			return new HSQLDbDAO();
			
		}
		return null;
		
	}

}

