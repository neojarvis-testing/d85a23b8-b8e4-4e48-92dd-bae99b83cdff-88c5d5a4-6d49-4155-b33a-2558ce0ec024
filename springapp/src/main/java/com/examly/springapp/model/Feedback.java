package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedback {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private long feedbackId;
private String feedbackText;
private LocalDate date;
@ManyToOne
@JoinColumn(name = "userId",nullable=false)
private User user;
@ManyToOne
@JoinColumn(name = "propertyId",nullable=false)
private Property property;
private String category;
public long getFeedbackId() {
    return feedbackId;
}
public void setFeedbackId(long feedbackId) {
    this.feedbackId = feedbackId;
}
public String getFeedbackText() {
    return feedbackText;
}
public void setFeedbackText(String feedbackText) {
    this.feedbackText = feedbackText;
}
public LocalDate getDate() {
    return date;
}
public void setDate(LocalDate date) {
    this.date = date;
}
public User getUser() {
    return user;
}
public void setUser(User user) {
    this.user = user;
}
public Property getProperty() {
    return property;
}
public void setProperty(Property property) {
    this.property = property;
}
public String getCategory() {
    return category;
}
public void setCategory(String category) {
    this.category = category;
}


}
