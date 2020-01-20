package com.irshad.apps.filemanagement.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.irshad.apps.filemanagement.vo.User;

@Service
public class FileService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void addUser(User user) {
		try {
			byte[] bs = null;
			if (user.getPhoto() != null)
				bs = user.getPhoto().getBytes();
			int count = jdbcTemplate.update("INSERT INTO user_tbl(id, name, photo) VALUES (?,?,?)", user.getId(), user.getName(),
					bs);
			if(count==1)
				System.out.println("User inserted!");
			else
				System.out.println("User not inserted :( ");
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
