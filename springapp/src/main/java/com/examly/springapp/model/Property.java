package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity  //Maps the class with database
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
@Id  //Defines the primary Key
@GeneratedValue(strategy= GenerationType.IDENTITY)  //Auto-Generated ID
private long propertyId;
private String title;
private String description;
private String location;
private Double price;
private String type;
private String status;
private int deleted=0;
public String toString(){
    return this.propertyId+" "+this.title+ " "+this.deleted;
}

}
