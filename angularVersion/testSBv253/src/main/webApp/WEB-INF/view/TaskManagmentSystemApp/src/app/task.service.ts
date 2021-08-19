import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiServerUrl}/task/allTasks`)
  }

  public addTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${this.apiServerUrl}/task/addTask`, task)
  }

  public editTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.apiServerUrl}/task/editTask`, task)
  }

  public deleteTask(taskId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/task/deleteTask/${taskId}`)
  }
}
