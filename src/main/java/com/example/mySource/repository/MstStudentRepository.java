package com.example.mySource.repository;

import com.example.mySource.entity.MstStudent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  MstStudentRepository extends JpaRepository<MstStudent, Integer> {
	public List<MstStudent> findByMailAddress(String mailAddress);
}
