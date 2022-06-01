package com.uttara.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HSQLDbDAO implements IUserDAO {

	public HSQLDbDAO() {

		System.out.println(" In HSQLDb no-arg constructor ");

	}

	public String create(RegBean bean) {

		Connection con = null;
		PreparedStatement ps_ins = null;

		try {

			con = JDBCHelper.getConnection();
			ps_ins = con.prepareStatement("insert into Register_users(name,email,password)values(?,?,?)");

			ps_ins.setString(1, bean.getName());
			ps_ins.setString(2, bean.getEmail());
			ps_ins.setString(3, bean.getPwd());
			ps_ins.execute();

			return Constants.SUCCESS;

		} catch (Exception e) {

			e.printStackTrace();
			return "Oops something bad happened , msg = " + e.getMessage();

		} finally {

			JDBCHelper.close(con);
			JDBCHelper.close(ps_ins);
		}
	}

	public boolean checkIfEmailExists(String email) {

		Connection con = null;
		PreparedStatement ps_sel = null;
		ResultSet rs = null;

		try {

			con = JDBCHelper.getConnection();

			ps_sel = con.prepareStatement("select * from Register_users where email = ?");
			ps_sel.setString(1, email);
			ps_sel.execute();

			rs = ps_sel.getResultSet();

			if (rs.next()) {

				// there is at least 1 row...user email is duplicate!!!
				return true;
			} else
				return false;

		} catch (Exception e) {

			e.printStackTrace();
			return false;

		} finally {

			JDBCHelper.close(rs);
			JDBCHelper.close(con);
			JDBCHelper.close(ps_sel);
		}
	}

	public String authenticate(LoginBean bean) {

		Connection con = null;
		PreparedStatement ps_sel = null, ps_sel1 = null;
		ResultSet rs = null, rs1 = null;

		try {

			con = JDBCHelper.getConnection();

			ps_sel = con.prepareStatement("select * from Register_users where email = ? and password = ?");
			ps_sel.setString(1, bean.getEmail());
			ps_sel.setString(2, bean.getPwd());
			ps_sel.execute();

			rs = ps_sel.getResultSet();

			if (rs.next()) {

				// there is at least 1 row...where email and password matched

				return Constants.SUCCESS;
			}

			ps_sel1 = con.prepareStatement("select * from Register_users where email = ?");
			ps_sel1.setString(1, bean.getEmail());
			ps_sel1.execute();

			rs1 = ps_sel1.getResultSet();

			if (rs1.next()) {

				// there is at least 1 row...where only email matched

				return "Entered password is wrong , re-enter correct password";
			}

			ps_sel1 = con.prepareStatement("select * from Register_users where password = ?");
			ps_sel1.setString(1, bean.getPwd());
			ps_sel1.execute();

			rs1 = ps_sel1.getResultSet();

			if (rs1.next()) {

				// there is at least 1 row...where only password matched

				return "Entered email is wrong . Please enter correct email ";

			} else {

				return "Both email id and password is wrong . If you not yet registered. Please register before you login";
			}

		} catch (Exception e) {

			e.printStackTrace();
			return "Oops something bad happened , message = " + e.getMessage();

		} finally {

			JDBCHelper.close(rs);
			JDBCHelper.close(con);
			JDBCHelper.close(ps_sel);
		}

	}

	public String saveEditAccounntDetails(RegBean regBean) {

		Connection con = null;
		PreparedStatement ps_upd = null;

		try {

			con = JDBCHelper.getConnection();

			ps_upd = con.prepareStatement(" update Register_users set name = ? , password = ? where email = ? ");
			ps_upd.setString(1, regBean.getName());
			ps_upd.setString(2, regBean.getPwd());
			ps_upd.setString(3, regBean.getEmail());
			ps_upd.execute();

			return Constants.SUCCESS;

		} catch (Exception e) {

			e.printStackTrace();
			return "Oops something bad happened , msg = " + e.getMessage();

		} finally {

			JDBCHelper.close(con);
			JDBCHelper.close(ps_upd);
		}
	}

	public String addContact(ContactBean contactBean) {

		PreparedStatement ps_contactIns = null, ps_contactEmailsIns = null, ps_contactTagsIns = null,
				ps_contactPhoneNumsIns = null;
		ResultSet rs = null;
		Connection con = null;

		try {

			con = JDBCHelper.getConnection();

			ps_contactIns = con.prepareStatement("insert into contact(name,dob,gender,created_date)values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps_contactIns.setString(1, contactBean.getName());
			ps_contactIns.setString(2, contactBean.getDob());
			ps_contactIns.setString(3, contactBean.getGender());

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();

			System.out.println("strDate " + formatter.format(date));

			String scr_dt = formatter.format(date);
			contactBean.setCreated_date(scr_dt);

			java.util.Date dt = formatter.parse(scr_dt);
			java.sql.Date sqlDate = new java.sql.Date(dt.getTime());

			ps_contactIns.setDate(4, sqlDate);

			ps_contactIns.execute();

			rs = ps_contactIns.getGeneratedKeys();
			rs.next();

			int contact_sl_no = rs.getInt("sl_no");

			String emails = contactBean.getEmail();
			String[] emailArr = emails.split(",");
			int i = 0;

			while (i < emailArr.length) {

				ps_contactEmailsIns = con.prepareStatement("insert into contact_emails(contact_sl,email)values(?,?)");

				ps_contactEmailsIns.setInt(1, contact_sl_no);
				ps_contactEmailsIns.setString(2, emailArr[i]);
				ps_contactEmailsIns.execute();
				i++;
			}

			String tags = contactBean.getTags();
			String[] tagsArr = tags.split(",");
			int j = 0;

			while (j < tagsArr.length) {

				ps_contactTagsIns = con.prepareStatement("insert into contact_tags(contact_sl,tag)values(?,?)");

				ps_contactTagsIns.setInt(1, contact_sl_no);
				ps_contactTagsIns.setString(2, tagsArr[j]);
				ps_contactTagsIns.execute();
				j++;

			}

			String phoneNum = contactBean.getPhoneNum();
			String[] phoneNumArr = phoneNum.split(",");
			int k = 0;

			while (k < phoneNumArr.length) {

				ps_contactPhoneNumsIns = con
						.prepareStatement("insert into contact_phoneNumbers(contact_sl,phoneNumber)values(?,?)");

				ps_contactPhoneNumsIns.setInt(1, contact_sl_no);
				ps_contactPhoneNumsIns.setString(2, phoneNumArr[k]);
				ps_contactPhoneNumsIns.execute();
				k++;

			}

			return Constants.SUCCESS;
		} catch (Exception e) {

			e.printStackTrace();
			return "Oops something bad happened , msg = " + e.getMessage();

		} finally {

			JDBCHelper.close(con);
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_contactIns);
			JDBCHelper.close(ps_contactEmailsIns);
			JDBCHelper.close(ps_contactTagsIns);
			JDBCHelper.close(ps_contactPhoneNumsIns);

		}
	}

	public boolean checkIfContactNameExists(String name , Integer sl_no) {

		Connection con = null;
		PreparedStatement ps_sel = null;
		ResultSet rs = null;

		try {

			con = JDBCHelper.getConnection();
			
			if( sl_no == null  ) {
				
				ps_sel = con.prepareStatement("select * from contact where name = ?");
				ps_sel.setString(1, name);
				ps_sel.execute();
				
			} else {
				
				ps_sel = con.prepareStatement("select * from contact where name = ? and sl_no <> ?");
				ps_sel.setString(1, name);
				ps_sel.setLong(2, sl_no);
				ps_sel.execute();
				
			}

			rs = ps_sel.getResultSet();

			if (rs.next()) {

				// there is at least 1 row...contact name is duplicate!!!
				return true;
			} else
				return false;

		} catch (Exception e) {

			e.printStackTrace();
			return false;

		} finally {

			JDBCHelper.close(rs);
			JDBCHelper.close(con);
			JDBCHelper.close(ps_sel);
		}
	}

	public List<ContactBean> getContacts(String searchString, int orderBy) {
		
		String s = null;
		String orderByColumn = "NAME";
		
		Connection con = null;

		PreparedStatement ps_sel = null, ps_emailsSel = null, ps_tagsSel = null, ps_phNumsSel = null;

		ResultSet rsSel = null, rsEmailsSel = null, rsTagsSel = null, rsPhNumsSel = null;

		List<ContactBean> al = new ArrayList<ContactBean>();
		
		switch (orderBy) {
		
		case 2 :
			orderByColumn="DOB";
			break;
			
		case 3:
			orderByColumn="CREATED_DATE";
			break;

		default:
			orderByColumn="NAME";
			break;
		}
		
		
		try {

			con = JDBCHelper.getConnection();
		
			if(searchString != null) {	
				
				s = "select * from contact where LOWER(name) like CONCAT( '%',?,'%')";	
				
				ps_sel = con.prepareStatement(s);
				ps_sel.setString(1, searchString);
				ps_sel.execute();
				
			} else {
				
				
				s = "select * from contact ORDER BY " + orderByColumn;
				
				ps_sel = con.prepareStatement(s);
				ps_sel.execute();
			}
			
			rsSel = ps_sel.getResultSet();

			String name, dob, gender, created_date;
			int sl_no;

			while (rsSel.next()) {

				sl_no = rsSel.getInt("sl_no");
				name = rsSel.getString("name");
				dob = rsSel.getString("dob");
				gender = rsSel.getString("gender");
				created_date = rsSel.getString("created_date");

				System.out.println("sl_no = " + sl_no + " name = " + name + " dob = " + dob + " gender = " + gender
						+ " created_date = " + created_date);

				ps_emailsSel = con.prepareStatement("select email from contact_emails where contact_sl = ?");
				ps_emailsSel.setInt(1, sl_no);
				ps_emailsSel.execute();

				rsEmailsSel = ps_emailsSel.getResultSet();
				String emails = "", tempEmail;

				while (rsEmailsSel.next()) {

					tempEmail = rsEmailsSel.getString("email");
					emails = emails + tempEmail + ",";
				}

				emails = emails.substring(0, emails.length() - 1);
				System.out.println("emails = " + emails);

				ps_tagsSel = con.prepareStatement("select tag from contact_tags where contact_sl = ?");
				ps_tagsSel.setInt(1, sl_no);
				ps_tagsSel.execute();

				rsTagsSel = ps_tagsSel.getResultSet();
				String tags = "", tempTag;

				while (rsTagsSel.next()) {

					tempTag = rsTagsSel.getString("tag");
					tags = tags + tempTag + ",";
				}

				tags = tags.substring(0, tags.length() - 1);
				System.out.println("tags = " + tags);

				ps_phNumsSel = con.prepareStatement("select phoneNumber from contact_phoneNumbers where contact_sl = ?");
				ps_phNumsSel.setInt(1, sl_no);
				ps_phNumsSel.execute();

				rsPhNumsSel = ps_phNumsSel.getResultSet();
				String phNums = "", tempPhNum;

				while (rsPhNumsSel.next()) {

					tempPhNum = rsPhNumsSel.getString("phoneNumber");
					phNums = phNums + tempPhNum + ",";
				}

				phNums = phNums.substring(0, phNums.length() - 1);
				System.out.println("phNums = " + phNums);

				// private String name , email , phoneNum , tags , gender , dob , created_date;
				ContactBean cBean = new ContactBean(sl_no , name, emails, phNums, tags, gender, dob, created_date);
				al.add(cBean);

			}

		
		}  catch (Exception e) {

			e.printStackTrace();

		} finally {

			JDBCHelper.close(con);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(ps_emailsSel);
			JDBCHelper.close(ps_tagsSel);
			JDBCHelper.close(ps_phNumsSel);
			JDBCHelper.close(rsSel);
			JDBCHelper.close(rsEmailsSel);
			JDBCHelper.close(rsTagsSel);
			JDBCHelper.close(ps_phNumsSel);

		}
		return al;

	}

	public String updateContact(ContactBean contactBean) {
		
		Connection con = null;

		PreparedStatement ps_contactUpdate = null , ps_contactEmailsUpdate = null , ps_contactTagsUpdate = null , ps_contactPhoneNumsUpdate = null;

		try {
			
			con = JDBCHelper.getConnection();
			
			ps_contactUpdate = con.prepareStatement("update contact set name = ? , dob = ? , gender = ? where sl_no = ?");
			
			ps_contactUpdate.setString(1, contactBean.getName());
			ps_contactUpdate.setString(2, contactBean.getDob());
			ps_contactUpdate.setString(3, contactBean.getGender());
			ps_contactUpdate.setLong(4, contactBean.getSl_no());
			ps_contactUpdate.execute();

			String emails = contactBean.getEmail();
			String[] emailArr = emails.split(",");
			int i = 0;

			while (i < emailArr.length) {

				ps_contactEmailsUpdate = con.prepareStatement("update contact_emails set email = ? where  contact_sl = ?");

				ps_contactEmailsUpdate.setString(1, emailArr[i]);
				ps_contactEmailsUpdate.setLong(2, contactBean.getSl_no());
				ps_contactEmailsUpdate.execute();
				i++;
			}

			String tags = contactBean.getTags();
			String[] tagsArr = tags.split(",");
			int j = 0;

			while (j < tagsArr.length) {

				ps_contactTagsUpdate = con.prepareStatement("update contact_tags set tag = ? where  contact_sl = ?");

				ps_contactTagsUpdate.setString(1, tagsArr[j]);
				ps_contactTagsUpdate.setLong(2, contactBean.getSl_no());
				ps_contactTagsUpdate.execute();
				j++;

			}

			String phoneNum = contactBean.getPhoneNum();
			String[] phoneNumArr = phoneNum.split(",");
			int k = 0;

			while (k < phoneNumArr.length) {

				ps_contactPhoneNumsUpdate = con.prepareStatement("update contact_phoneNumbers set phonenumber = ? where  contact_sl = ?");

				ps_contactPhoneNumsUpdate.setString(1, phoneNumArr[k]);
				ps_contactPhoneNumsUpdate.setLong(2, contactBean.getSl_no());
				ps_contactPhoneNumsUpdate.execute();
				k++;

			}

			return Constants.SUCCESS;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "Oops something bad happened , msg = " + e.getMessage();

		} finally {

			JDBCHelper.close(con);
			JDBCHelper.close(ps_contactUpdate);
			JDBCHelper.close(ps_contactEmailsUpdate);
			JDBCHelper.close(ps_contactTagsUpdate);
			JDBCHelper.close(ps_contactPhoneNumsUpdate);
			
		}
	}

	public String deleteContact(int sl_no) {
		
		PreparedStatement ps_contactDelete = null , ps_contactEmailsDelete = null , ps_contactTagsDelete = null , ps_contactPhoneNumsDelete = null;
		Connection con = null;
		
		try {
			
			con = JDBCHelper.getConnection();
			ps_contactDelete = con.prepareStatement("delete from contact where sl_no = ?");
			ps_contactDelete.setLong(1, sl_no);
			ps_contactDelete.execute();
			
			ps_contactEmailsDelete = con.prepareStatement("delete from contact_emails where contact_sl = ?");
			ps_contactEmailsDelete.setLong(1, sl_no);
			ps_contactEmailsDelete.execute();
			
			ps_contactTagsDelete = con.prepareStatement("delete from contact_tags where contact_sl = ?");
			ps_contactTagsDelete.setLong(1, sl_no);
			ps_contactTagsDelete.execute();
			
			ps_contactPhoneNumsDelete = con.prepareStatement("delete from contact_phoneNumbers where contact_sl = ?");
			ps_contactPhoneNumsDelete.setLong(1, sl_no);
			ps_contactPhoneNumsDelete.execute();
			
			return Constants.SUCCESS;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "Oops something bad happened , msg = " + e.getMessage();
			
		} finally {

			JDBCHelper.close(con);
			JDBCHelper.close(ps_contactDelete);
			JDBCHelper.close(ps_contactEmailsDelete);
			JDBCHelper.close(ps_contactTagsDelete);
			JDBCHelper.close(ps_contactPhoneNumsDelete);
			
		}
	}
}