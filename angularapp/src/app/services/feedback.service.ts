import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';
import { apiUrl } from '../constant/ApiUrl';
@Injectable({
  providedIn: 'root' // Singleton service across the application
})
export class FeedbackService {
  
  constructor(private readonly http: HttpClient) {
  }

  /**
   * Submits user feedback to the server.
   * @param feedback - The feedback object
   * @returns Observable<Feedback> - The created feedback response
   */
  sendFeedback(feedback: Feedback): Observable<Feedback> {
    console.log(feedback)
    return this.http.post<Feedback>(`${apiUrl}/feedback`, feedback);
  }

  /**
   * Retrieves all feedback submitted by a specific user.
   * @param userId - The user's unique identifier
   * @returns Observable<Feedback[]> - List of feedback submitted by the user
   */
  getAllFeedbacksByUserId(userId:any): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${apiUrl}/feedback/user/${userId}`);
  }

  /**
   * Retrieves all feedback entries from the system.
   * @returns Observable<Feedback[]> - List of all feedback
   */
  getFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${apiUrl}/feedback`);
  }

  /**
   * Deletes a specific feedback entry based on its ID.
   * @param feedbackId - The unique identifier of the feedback entry
   * @returns Observable<void> - Observable indicating completion (no returned data)
   */
  deleteFeedback(feedbackId: number): Observable<any> {
    return this.http.delete(`${apiUrl}/feedback/${feedbackId}`, { responseType: 'text' });
  }
}
