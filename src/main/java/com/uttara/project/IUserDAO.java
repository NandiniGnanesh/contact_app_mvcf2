package com.uttara.project;

import java.util.List;

public interface IUserDAO {

	public String create( RegBean bean );
	public boolean checkIfEmailExists( String email );
	public String authenticate( LoginBean bean );
	public String saveEditAccounntDetails(RegBean regBean);
	public String addContact(ContactBean contactBean);
	public boolean checkIfContactNameExists(String name);
	public List<ContactBean> getContacts(String searchString , int orderBy);
	
}
