package com.edorasware.homework1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

import java.util.Date;

@Entity
@ApiModel(value="Expense entity", description="Each expenses entry consists of a description (String), a value (Double) and a date (Date)")
public class Expense {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "The id of the expense. It's automatically managed by the system and the end user has not control over it", required = true)
    private Integer id;
    @Column(name="DESCRIPTION", nullable=false)
    @ApiModelProperty(value = "Expense concept or description. It's minimum length is 3 characters", required = true)
    private String description;
    @Column(name="VALUE", nullable=false)
    @ApiModelProperty(value = "Value of the expense.", required = true)
    private Double value;
    @Column(name="DATE", nullable=false)
    @ApiModelProperty(value = "Date when the expense took place in format yyyy-MM-dd", required = true)
    private Date date;

    public Expense() {
    }

    public Expense(String description, Double value, Date date) {
        this.description = description;
        this.value = value;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
