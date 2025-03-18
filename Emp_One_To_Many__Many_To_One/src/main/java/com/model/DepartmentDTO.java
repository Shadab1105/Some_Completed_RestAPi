package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO 
{
    
    private Long dId;
    private String deptName;
}

/*
 * //DTO ke bina kya dikkat hoti hai? 
 * 
 * //👉 A) Redundant Data Fetching:
 * 		//Lazy Loading hone ke bawajood bhi unnecessary fields fetch ho sakti hain.
 * 		//DTO se sirf required fields hi fetch hoti hain. 
 * 
 * //👉 B) Security Risk:
 * 		//Entity me sensitive fields hone par wo bhi expose hoti hain. 
 * 		//DTO se aap sirf safe fields expose karte ho. 
 * 
 * //👉 C) Maintainability Issues:
 *     //DB schema change hone par poora code refactor karna padta hai. 
 *     //DTO me schema independent hota hai, isliye code easily maintain hota hai.
 */





//DTO vs. Lazy Loading + Serialization (Comparison)
//Feature	                        DTO	       								Lazy Loading + Serialization
//Data Fetching	   	 	Sirf required fields fetch karta hai				Pure entity fetch hoti hai
//Performance	        	Optimized (fewer fields fetch hoti hain)			Slow (more data load hota hai)
//Security	        	Sensitive fields hide kar sakte ho			Entity me sensitive fields expose hoti hain
//Loose Coupling	    	Backend aur frontend loosely coupled				Tightly coupled
//Code Cleanliness		Clean architecture	 								Mixed data logic
//Custom Transformation	Easy (DTO me easily customize kar sakte ho)	     	 Difficult





