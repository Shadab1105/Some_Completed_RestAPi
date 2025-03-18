package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.model.Department;
import com.model.Faculty;
import com.repository.DeptRepository;
import com.repository.FacultyRepository;

import jakarta.transaction.Transactional;

@Service
public class FacultyServiceImpl implements FacultyServiceInterface
{
	@Autowired
	private FacultyRepository fr;

	@Autowired
	private DeptRepository dr;

	
	
	
	@Transactional
	@Override
	public ResponseEntity<?> savefaculty(Faculty f) throws ResourceNotFoundException 
	{

	    Department dept;

	    if (f.getDept() != null && f.getDept().getDId() != null) 
		    {
		        dept = dr.findById(f.getDept().getDId()).orElseThrow(() -> 
		        new ResourceNotFoundException("Department not found with ID: " + f.getDept().getDId()));
		    } 
	    else 
		    {
		        dept = dr.save(f.getDept());
		    }

	    f.setDept(dept);

	    Faculty savedFaculty = fr.save(f);

	    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
	            "message", "Data saved successfully",
	            "data", savedFaculty
	    ));
	}

	
	

	@Override
	public List<?> getAllfaculty() {
		List<Faculty> getAllFacultyDetail = fr.findAll();
		if(getAllFacultyDetail.isEmpty())
		{
			return List.of(Map.of("Message","DataBase is Empty"));
		}
		return getAllFacultyDetail;
	}

	@Override
	public ResponseEntity<?> getByFId(Long id) throws ResourceNotFoundException {
		Faculty deptById = fr.findById(id).orElseThrow(()->new ResourceNotFoundException("Faculty not available = "+ id));
		return  ResponseEntity.ok(Map.of("Message","Faculty Data available\n","Data",deptById));
	}

	@Override
	public ResponseEntity<?> deleteByFId(Long id) throws ResourceNotFoundException {
		Faculty dept = fr.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Data not availble with id "+ id));
		
		fr.deleteById(id);
		return ResponseEntity.ok(Map.of("Message","Data is present\n","Data",dept));
	}

	@Override
	public ResponseEntity<?> updatefaculty(Long id, Faculty d) throws ResourceNotFoundException {
		Faculty updateData = fr.findById(id).orElseThrow(()->new ResourceNotFoundException("Id not available = "+id));
		
		updateData.setFacultyName(d.getFacultyName());
		updateData.setFacultyNumber(d.getFacultyNumber());
		updateData.setSalary(d.getSalary());
		updateData.setFacultyNumber(d.getFacultyNumber());
	//	updateData.setDept(d.);
		
		Faculty updatedData = fr.save(updateData);
		
		return ResponseEntity.ok(Map.of("message", "Data successfully updated", "data", updatedData));
	}

}



//@Transactional ka fayda:
//Consistency:
//	-Agar Department save hone ke baad Faculty save nahi hoti to Department bhi rollback ho jayega.
//	-Isse data inconsistency se bachte hain.


//	-Bina @Transactional ke:
//		-Department save ho jata, lekin Faculty save nahi hoti
//		-DB me inconsistent data hota.


//Rollback on exception:
//	-Agar koi exception aata hai (jaise department ID nahi mili) to poora transaction rollback ho jata hai.

//	-Bina @Transactional ke:
//				-Department save ho jata, par Faculty nahi hoti
//				-Data inconsistency hoti.
