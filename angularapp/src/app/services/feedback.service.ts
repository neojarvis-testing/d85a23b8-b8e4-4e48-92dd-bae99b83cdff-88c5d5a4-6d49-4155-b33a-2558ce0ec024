import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';
import { ApiUrl } from '../constant/ApiUrl';

@Injectable({
  providedIn: 'root' // Singleton service across the application
})
export class FeedbackService {
  
  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = `${ApiUrl.apiUrl}/feedback`; // Base API endpoint
  }

  /**
   * Submits user feedback to the server.
   * @param feedback - The feedback object
   * @returns Observable<Feedback> - The created feedback response
   */
  sendFeedback(feedback: Feedback): Observable<Feedback> {
    return this.http.post<Feedback>(this.apiUrl, feedback);
  }

  /**
   * Retrieves all feedback submitted by a specific user.
   * @param userId - The user's unique identifier
   * @returns Observable<Feedback[]> - List of feedback submitted by the user
   */
  getAllFeedbacksByUserId(userId: number): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.apiUrl}/user/${userId}`);
  }

  /**
   * Retrieves all feedback entries from the system.
   * @returns Observable<Feedback[]> - List of all feedback
   */
  getFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.apiUrl);
  }

  /**
   * Deletes a specific feedback entry based on its ID.
   * @param feedbackId - The unique identifier of the feedback entry
   * @returns Observable<void> - Observable indicating completion (no returned data)
   */
  deleteFeedback(feedbackId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${feedbackId}`);
  }
}
