package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Department {

    public Department(Long dId2, String deptName2) {
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_id")
    @JsonProperty("dId")   // JSON se dId map kari
    private Long dId;


    @NotNull
    @Size(min = 2,max = 20,message = "Department Name should be minimum 2-20")
    private String deptName;

//    @JsonIgnore
    @JsonManagedReference 
    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Faculty> faculty;  
    
    
}   
    



//Doubt 1:-->
/*
 * // @JsonManagedReference aur @JsonBackReference ka Use Kariye // Circular
 * reference problem se bachne ke liye aapko:
 * 
 * // ---@JsonManagedReference → Parent side (Department) //
 * ---@JsonBackReference → Child side (Faculty) lagana chahiye.
 */







//Doubt 2:-->    
/*
 * //    ---Aapka Department aur Faculty me bidirectional relationship hai:
//    		-Department → Faculty: @OneToMany
//    		-Faculty → Department: @ManyToOne

//    ---Jab aap @JsonIgnore hatate hain, to circular reference start ho jata hai:
//    		-Department se Faculty fetch hoti hai.
//    		-Faculty se Department wapas fetch hota hai.
//    		-Yehi cycle recursively chalte rehta hai, jiski wajah se large data serialization hoti hai.
*/


/*
 * //	Aapko @JsonIgnore lagane ki jarurat nahi hoti, kyunki @JsonBackReference
 * 		child side ko automatically ignore kar leta hai.
 */



//Doubt 3:-->

/*
 * 
 * //@OneToMany in Department
 * 
 * //mappedBy = "department" → Ye Faculty entity ke department field ko
 * reference kar raha hai.
 * 
 * //cascade = CascadeType.ALL → Iska matlab agar ek department delete ho jaye,
 * toh uske saare faculties bhi delete ho jayenge.
 * 
 * //orphanRemoval = true → Agar department se kisi faculty ko hata diya jaye,
 * toh wo automatically database se delete ho jayega.
 */




//Doubt 4:-->

/*
 * //Jab @JsonProperty("dId") iske bina use kar rahe the to dId null aa raha tha
 * kyonki?
 * 
 * //Tere JSON mapping me null aane ka reason yeh tha:
 * 
 * //Jackson mapping problem:
 * 
 * //Jackson tere JSON body ko Java object me convert nahi kar pa raha tha.
 * 
 * 
 * //Tere Department entity me dId ko map nahi kar pa raha tha → Is wajah se
 * null aa raha tha. //No @JsonProperty annotation: //Jackson JSON mapping ke
 * liye field name ka strict matching karta hai. //Tere Department.java me field
 * name dId tha, lekin JSON me d_id aa raha tha. //Mismatch hone se mapping fail
 * ho gayi → null aa raha tha.
 */