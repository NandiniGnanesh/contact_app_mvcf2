package com.uttara.project;

import java.io.Serializable;

public class RegBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name , email , pwd , rpwd;

	public RegBean() {
		
		System.out.println( "In RB no-arg constructor " );
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("In RB setName() name = " +name);
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		System.out.println("In RB setEmail() email = " +email);
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		System.out.println("In RB setPwd() password = " +pwd);
		this.pwd = pwd;
	}

	public String getRpwd() {
		return rpwd;
	}

	public void setRpwd(String rpwd) {
		System.out.println("In RB setRpwd() repeat password = " +rpwd);
		this.rpwd = rpwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((rpwd == null) ? 0 : rpwd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegBean other = (RegBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (rpwd == null) {
			if (other.rpwd != null)
				return false;
		} else if (!rpwd.equals(other.rpwd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegBean [name=" + name + ", email=" + email + ", pwd=" + pwd + ", rpwd=" + rpwd + "]";
	}
	
	public String validate() {
		
		StringBuilder sb = new StringBuilder();
		
		if( name == null || name.trim().equals("") )
			sb.append( "Name is mandatory!Enter it<br/>" );
		
		if( email == null || email.trim().equals("") )
			sb.append( "Email is mandatory!Enter it<br/>" );
		
		if( pwd == null || pwd.trim().equals("") )
			sb.append( "Password is mandatory!Enter it<br/>" );
		else
			if( !pwd.equals( rpwd ) )
				sb.append( "Your password is not equal to repeat password.<br/>" );
		
		String str = sb.toString();
		if( str.equals("") )
			return Constants.SUCCESS;
		else
			return str;
	}

}
