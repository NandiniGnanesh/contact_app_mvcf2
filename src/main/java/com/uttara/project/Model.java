package com.uttara.project;

import java.util.List;

public class Model {

	public String register(RegBean bean) {
		
		System.out.println("in Model->register() bean = "+bean);
		
		// perform user input validations
		
		String msg = bean.validate();
				
		if( !msg.equals(Constants.SUCCESS) ) {
					
			System.out.println("Model -> register() , registration has failed! msg = " +msg);
			return msg;
			
		}
				
		//perform business validations
				
		IUserDAO dao = DAOFactory.getDAO();			
		
		if ( dao.checkIfEmailExists( bean.getEmail()) ) {
			
			return "Your email id already exists...";
			
		} else {
			
			//save the data
			
			msg = dao.create(bean);
			
			if( msg.equals( Constants.SUCCESS ) ) {
				
				return Constants.SUCCESS;
				
			} else {
				
				return msg;
			}
			
		}
	}

	public String authenticate(LoginBean loginBean) {
		
		System.out.println("In model -> authenticate() -> bean = " +loginBean);
		
		// perform user input validations
		
		String msg = loginBean.validate();
		
		if( !msg.equals( Constants.SUCCESS ) ) {
			
			System.out.println("Model -> authenticate() , authentication has failed! msg = " +msg);
			return msg;
			
		}
		
		//perform business validations
		
				IUserDAO dao = DAOFactory.getDAO();	
				
				String message = dao.authenticate(loginBean);
				
				if( !message.equals( Constants.SUCCESS ) ) {
					
					return message;
					
				} else {
					
					return Constants.SUCCESS;
				}
				
	}

	public String editAccountAuthenticate(RegBean regBean , LoginBean loginBean) {
		 
		System.out.println("In model -> editAccountAuthenticate() -> regBean = " +regBean);
		
		    	// perform user input validations
		
				String msg = regBean.validate();
				
				if( !msg.equals( Constants.SUCCESS ) ) {
					
					System.out.println("Model -> authenticate() , authentication has failed! msg = " +msg);
					return msg;
					
				}
				
				//perform business validations
				
				if( !regBean.getEmail().equals(loginBean.getEmail()) ) {
					
					return "Email-id can't be edited , please enter your email id to edit other data";
					
				} else {
					
					//save edited data
					
					IUserDAO dao = DAOFactory.getDAO();
					String message = dao.saveEditAccounntDetails(regBean);
					
					if( message.equals( Constants.SUCCESS ) ) {
						
						return Constants.SUCCESS;
						
					} else {
						
						return msg;
						
					}
				}
				
	}

	public String addContact(ContactBean contactBean) {
		 
		System.out.println("In model -> addContact() -> contactBean = " +contactBean);
		
		// perform user input validations
		
		String msg = contactBean.validate();
		
		if( !msg.equals( Constants.SUCCESS ) ) {
			
			System.out.println("Model -> addContact() , addContact has failed! msg = " +msg);
			return msg;
			
		}
		
		//perform business validations
		
		// check unique name
		
		IUserDAO dao = DAOFactory.getDAO();			
		
		if ( dao.checkIfContactNameExists( contactBean.getName() , null ) ) {
			
			return "Your contact name already exists...";
			
		} 
		
		//check unique email
		
		String emails = contactBean.getEmail();
		
		String[] arr = emails.split(",");
		int count = 0;
		
		for( int i = 0 ; i < arr.length; i++ ) {
			
			for( int j = i+1 ; j < arr.length; j++ ) {
				
				if( arr[i].equals(arr[j]) ) {
					
					count++;
					
				}
			}
		}
		
		if( count > 0 ) {
			
			//contains duplicate email
			return "Enter unique email ids";
		}
		
		//check unique phoneNumber
			
		String phoneNumbers = contactBean.getPhoneNum();
		
		String[] phNumArr = phoneNumbers.split(",");
		int count1 = 0;
		
		for( int i = 0 ; i < phNumArr.length; i++ ) {
			
			for( int j = i+1 ; j < phNumArr.length; j++ ) {
				
				if( phNumArr[i].equals(phNumArr[j]) ) {
					
					System.out.println(phNumArr[i]+" "+phNumArr[j]);
					count1++;
					
				}
			}
		}
		if( count1 > 0 ) {
				
			//contains duplicate phoneNumbers
			return "Enter unique phone numbers";
		}
		
		//add contacts
		IUserDAO dao1 = DAOFactory.getDAO();
		String message = dao1.addContact(contactBean);
		
		if( message.equals( Constants.SUCCESS ) ) {
			
			return Constants.SUCCESS;
			
		} else {
			
			return message;
			
		}
	}

	public List<ContactBean> getContacts(String searchString , String orderBy) {
		
		System.out.println("In model -> getContacts() -> ");
		
		int orderByInt = 0 ;
		
		if(orderBy == null) {
			
			orderByInt = 0;
			
		} else if( orderBy.equals("dob") ) {
			
			orderByInt = 2;
		
		} else if(orderBy.equals("created_date") ) {
		
			orderByInt = 3;
		
		} else if(orderBy.equals("name") ){
			
			orderByInt = 1;
			
		}
		//get contacts 
		
		IUserDAO dao = DAOFactory.getDAO();
		List<ContactBean> beanList = dao.getContacts(searchString , orderByInt);
		
		System.out.println("beanList = " +beanList);
		return beanList;
		
		
	}
	
	public String updateContact(ContactBean contactBean) {
		 
		System.out.println("In model -> updateContact() -> contactBean = " +contactBean);
		
		// perform user input validations
		
		String msg = contactBean.validate();
		
		if( !msg.equals( Constants.SUCCESS ) ) {
			
			System.out.println("Model -> addContact() , addContact has failed! msg = " +msg);
			return msg;
			
		}
		
		//perform business validations
		
		// check unique name
		
		IUserDAO dao = DAOFactory.getDAO();			
		
		if ( dao.checkIfContactNameExists( contactBean.getName() , contactBean.getSl_no())) {
			
			return "Your contact name already exists...";
			
		} 
		
		//check unique email
		
		String emails = contactBean.getEmail();
		
		String[] arr = emails.split(",");
		int count = 0;
		
		for( int i = 0 ; i < arr.length; i++ ) {
			
			for( int j = i+1 ; j < arr.length; j++ ) {
				
				if( arr[i].equals(arr[j]) ) {
					
					count++;
					
				}
			}
		}
		
		if( count > 0 ) {
			
			//contains duplicate email
			return "Enter unique email ids";
		}
		
		//check unique phoneNumber
			
		String phoneNumbers = contactBean.getPhoneNum();
		
		String[] phNumArr = phoneNumbers.split(",");
		int count1 = 0;
		
		for( int i = 0 ; i < phNumArr.length; i++ ) {
			
			for( int j = i+1 ; j < phNumArr.length; j++ ) {
				
				if( phNumArr[i].equals(phNumArr[j]) ) {
					
					System.out.println(phNumArr[i]+" "+phNumArr[j]);
					count1++;
					
				}
			}
		}
		if( count1 > 0 ) {
				
			//contains duplicate phoneNumbers
			return "Enter unique phone numbers";
		}
		
		//update contacts
		IUserDAO dao1 = DAOFactory.getDAO();
		String message = dao1.updateContact(contactBean);
		
		if( message.equals( Constants.SUCCESS ) ) {
			
			return Constants.SUCCESS;
			
		} else {
			
			return message;
			
		}
	}

	public String deleteContact(int sl_no) {
		
		IUserDAO dao = DAOFactory.getDAO();
		String message = dao.deleteContact(sl_no);
		
		if( message.equals( Constants.SUCCESS ) ) {
			
			return Constants.SUCCESS;
			
		} else {
			
			return message;
			
		}
	}

}
