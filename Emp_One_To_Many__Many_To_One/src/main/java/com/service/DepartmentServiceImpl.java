package com.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.model.Department;
import com.model.DepartmentDTO;
import com.repository.DeptRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DeptServiceInterface
{

	@Autowired
	private DeptRepository dr;
	
	@Transactional
	@Override
	public ResponseEntity<?> saveDepartment(Department d) 
	{
		
		System.out.println("Service = "+d);
		 Department byName = dr.getByName(d.getDeptName());
		 System.out.println(byName);
		if ((byName== null)) 
		    {
		        Department saveDepartment = dr.save(d);
		        return ResponseEntity.status(HttpStatus.CREATED)
		                .body(Map.of("Data Save", saveDepartment));
		    } else {
		        return ResponseEntity.ok().body("Data Already inserted: " + d.getDeptName());
		    }
		
	}

//	@Transactional
//	@Override
//	public ResponseEntity<?> saveDepartment(Department d) {
//
//	    System.out.println("Service = " + d);
//	    Department byName = dr.getByName(d.getDeptName().trim());
//
//	    if (byName != null) {
//	        return ResponseEntity.ok().body("Data Already inserted: " + d.getDeptName());
//	    }
//
//	    Department saveDepartment = dr.save(d);
//	    
//	    return ResponseEntity.status(HttpStatus.CREATED)
//	            .body(Map.of("Data Saved", saveDepartment));
//	}

	
	
	@Transactional
	@Override
	public List<?> getAllDepartmentDetail() 
	{
		List<Department> getAllDepartmentDetail = dr.findAll();
		if(getAllDepartmentDetail.isEmpty())
		{
			return List.of(Map.of("Message","DataBase is Empty"));
		}
		return getAllDepartmentDetail;
	}

	@Override
	public ResponseEntity<?> getById(Long id) throws ResourceNotFoundException 
	{
		
		Department deptById = dr.findById(id).orElseThrow(()->new ResourceNotFoundException("Department not available = "+ id));
		return  ResponseEntity.ok(Map.of("Message","Faculty Data available\n","Data",deptById));
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) throws ResourceNotFoundException 
	{
		Department dept = dr.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Data not availble with id "+ id));
		
		dr.deleteById(id);
		return ResponseEntity.ok(Map.of("Message","Data is present\n","Data",dept));
	}

	
	@Override
	public ResponseEntity<?> updateDepartment(Long id, Department d) throws ResourceNotFoundException 
	{
		Department updateData = dr.findById(id).orElseThrow(()->new ResourceNotFoundException("Id not available = "+id));
		
		updateData.setDeptName(d.getDeptName());
		updateData.setDId(d.getDId());
		
		Department updatedData = dr.save(updateData);
		
		return ResponseEntity.ok(Map.of("message", "Data successfully updated", "data", updatedData));
	}

	@Transactional
    @Override
    public List<DepartmentDTO> getDepartmentDetail() {

        // ✅ Department ki list fetch kar rahe hain
        List<Department> detail = dr.findAll();

        // ✅ DTO me map kar rahe hain taaki sirf `dId` aur `deptName` fetch ho
        List<DepartmentDTO> dtoList = detail.stream()
                .map(d -> new DepartmentDTO(d.getDId(), d.getDeptName()))
                .collect(Collectors.toList());

        // ✅ Console me print karne ke liye loop
        dtoList.forEach(d -> {
            System.out.println("ID: " + d.getDId() + ", Name: " + d.getDeptName());
        });

        // ✅ Database empty hone par message
        if (dtoList.isEmpty()) {
            return List.of(new DepartmentDTO(0L, "Database is Empty"));
        }

        return dtoList;
    }

}
