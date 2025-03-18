//package com.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.ForeignKey;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.validation.constraints.DecimalMax;
//import jakarta.validation.constraints.DecimalMin;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//public class Faculty 
//{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long fId;
//	
//	@NotNull(message = "FacultyName can not be null")
//	@Size(min = 3,max = 20,message = "Name shouble be min 3 to 20")
//	private String facultyName;
//	
//	
//	@NotNull(message = "Number name can not be null")
//	@Min(value = 1000000000L, message = "Number should be 10 digits")
//    @Max(value = 9999999999L, message = "Number should be 10 digits")
//	private Long facultyNumber;
//	
//	
//	@NotNull(message = "Salary can not be null")
//	@DecimalMin(value = "15000")
//	@DecimalMax(value = "200000")
//	private double salary;
//	
//
//	@NotNull
//    @ManyToOne
//    @JoinColumn(name = "d_id",referencedColumnName = "d_id",foreignKey = @ForeignKey(name="fk_dId"))
//    public Department dept;
//
//
//	public Long getfId() {
//		return fId;
//	}
//
//
//	public void setfId(Long fId) {
//		this.fId = fId;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Faculty [fId=" + fId + ", facultyName=" + facultyName + ", facultyNumber=" + facultyNumber + ", salary="
//				+ salary + ", dept=" + dept + "]";
//	}
//	
//	
//	
//
//}
//


package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;   // ✅ Add this
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fId;

    @NotNull(message = "FacultyName can not be null")
    @Size(min = 3, max = 20, message = "Name should be min 3 to 20")
    private String facultyName;

    @NotNull(message = "Number cannot be null")
    @Min(value = 1000000000L, message = "Number should be 10 digits")
    @Max(value = 9999999999L, message = "Number should be 10 digits")
    private Long facultyNumber;

    @NotNull(message = "Salary cannot be null")
    @DecimalMin(value = "15000")
    @DecimalMax(value = "200000")
    private double salary;

    @JsonBackReference   // ✅ Circular dependency se bachne ke liye
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "d_id", referencedColumnName = "d_id", foreignKey = @ForeignKey(name = "fk_dId"))
    private Department dept;
}



//@ManyToOne in Faculty

//@JoinColumn(name = "department_id", nullable = false) → Iska matlab department_id column foreign key ke roop me Faculty table me store hoga.