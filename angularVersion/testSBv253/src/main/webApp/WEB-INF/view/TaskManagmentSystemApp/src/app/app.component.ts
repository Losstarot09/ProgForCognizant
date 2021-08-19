import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Task } from './task';
import { TaskService } from './task.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public tasks: Task[] = [] ;
  public editTask: Task = <Task>{};
  public deleteTask: Task= <Task>{};
  

  constructor(private taskService: TaskService){}

  ngOnInit() {
    this.getTasks();
  }

  public getTasks(): void {
    this.taskService.getTasks().subscribe(
      (response: Task[]) => {this.tasks = response;},
      (error: HttpErrorResponse) => {alert(error.message);}
    );
  }

  public onAddTask(addForm: NgForm): void {
    document.getElementById('add-task-form-close')?.click();
    this.taskService.addTask(addForm.value).subscribe(
      (response: Task) => {
        console.log(response);
        this.getTasks();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {alert(error.message);}
    )
  }

  public onEditTask(task: Task): void {
    this.taskService.editTask(task).subscribe(
      (response: Task) => {
        console.log(response);
        this.getTasks();
      },
      (error: HttpErrorResponse) => {alert(error.message);}
    )
  }

  public onDeleteTask(taskId: number): void {
    this.taskService.deleteTask(taskId).subscribe(
      (response: void) => {
        console.log(response);
        this.getTasks();
      },
      (error: HttpErrorResponse) => {alert(error.message);}
    )
  }

  

  public onOpenModal(task: Task, mode: string): void{
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toogle', 'modal');
    if (mode === 'edit') {
      this.editTask = task;
      button.setAttribute('data-target', '#editTaskModal');
    }
    if (mode === 'delete') {
      button.setAttribute('data-target', '#deleteTaskModal');
    }
    container?.appendChild(button);
    button.click();
  }

}
