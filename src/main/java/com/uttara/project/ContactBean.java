package com.uttara.project;

import java.io.Serializable;

public class ContactBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name , email , phoneNum , tags , gender , dob , created_date;
	int sl_no;
	
	public ContactBean() {
		
		System.out.println( "In CB no-arg constructor " );
	
	}
	
	public ContactBean(int sl_no , String name, String email, String phoneNum, String tags, String gender, String dob, String created_date) {
		
		super();
		this.sl_no = sl_no; 
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
		this.tags = tags;
		this.gender = gender;
		this.dob = dob;
		this.created_date = created_date;
		
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getCreated_date() {
		return created_date;
	}


	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_date == null) ? 0 : created_date.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
		result = prime * result + sl_no;
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		ContactBean other = (ContactBean) obj;
		if (created_date == null) {
			if (other.created_date != null)
				return false;
		} else if (!created_date.equals(other.created_date))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		if (sl_no != other.sl_no)
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactBean [name=" + name + ", email=" + email + ", phoneNum=" + phoneNum + ", tags=" + tags
				+ ", gender=" + gender + ", dob=" + dob + ", created_date=" + created_date + ", sl_no=" + sl_no + "]";
	}

	public String validate() {
		
		StringBuilder sb = new StringBuilder();
		
		if( name == null || name.trim().equals("") )
			sb.append( "Name is mandatory!Enter it<br/>" );
		
		if( email == null || email.trim().equals("") )
			sb.append( "Emails are mandatory!Enter it<br/>" );
		
		if( phoneNum == null || phoneNum.trim().equals("") )
			sb.append( "phoneNumbers are mandatory!Enter it<br/>" );
		
		if( tags == null || tags.trim().equals("") )
			sb.append( "Tags are mandatory!Enter it<br/>" );
		
		if( gender == null || gender.trim().equals("") )
			sb.append( "Gender is mandatory!Choose it<br/>" );
		
		if( dob == null || dob.trim().equals("") )
			sb.append( "Date of birth is mandatory!Enter it<br/>" );
		
		String str = sb.toString();
		
		if( str.equals("") )
			return Constants.SUCCESS;
		else
			return str;
	}
}
