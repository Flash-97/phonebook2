  
package com.javaex.dao;

import com.javaex.vo.PersonVo;

public class PhoneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PhoneDao phoneDao = new PhoneDao();
		PersonVo phonevo;
//		PersonVo personVo = new PersonVo("이현준", "010-2222-3333", "010-4444-5555");
//		phoneDao.personInsert(personVo);
		
		phonevo = phoneDao.getPerson(1);
		System.out.println(phonevo.toString());
	}

}