<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
        <title>TMS</title>
        <link href="static/css/bootstrap.min.css" rel="stylesheet">
        <link href="static/css/style.css" rel="stylesheet">

    </head>
    <body>
	    <div role="navigation">
		    <div class="navbar navbar-inverse">
			    <div class="navbar-collapse collapse">
				    <ul class="nav navbar-nav">
				        <li><a href="/show-tasks">All Tasks</a></li>
					    <li><a href="/add-task-form">New Task</a></li>
				    </ul>
			    </div>
		    </div>
	    </div>

        <c:choose>
            <c:when test="${mode=='MODE_SHOW-TASKS' }">
                <div class="container text-center">
                    <h3>All Tasks</h3>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Task Name</th>
                                    <th>Time Spent</th>
                                    <th>Task Group</th>
                                    <th>Assignee</th>
                                    <th>Completion Status</th>
                                    <th>Show Sub-Tasks</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="task" items="${allTasks }">
                                    <tr>
                                        <td>${task.taskId}</td>
                                        <td>${task.taskName}</td>
                                        <td>${task.timeSpent}</td>
                                        <td>${task.taskGroup}</td>
                                        <td>${task.assignee}</td>
                                        <td>${task.completeStatus}</td>
                                        <td>
                                            <a class="btn btn-primary" href="/show-subtasks?taskId=${task.taskId}"
                                                role="button" > Show Subtasks </a></td>
                                        <td><a href="/edit-task-form?taskId=${task.taskId }"><span
                                            class="glyphicon glyphicon-pencil"></span></a></td>
                                        <td><a href="/delete-task?taskId=${task.taskId }"><span
                                            class="glyphicon glyphicon-trash"></span></a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:when>

            <c:when test="${mode=='MODE_ADD-TASK-FORM' }">
                <div class="container text-center">
                    <h3>New Task</h3>
                    <hr>
                    <form class="form-horizontal" method="POST" action="save-task">
                        <div class="form-group">
                            <label class="control-label col-md-3">Task name</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="taskName"
                                    value="${task.taskName }" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Group Name</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="taskGroup"
                                    value="${task.taskGroup }" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Assignee</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="assignee"
                                    value="${task.assignee }" />
                            </div>
                        </div>
                        <div class="form-group ">
                            <input type="submit" class="btn btn-primary" value="Add task" />
                        </div>
                    </form>
                </div>
            </c:when>

            <c:when test="${mode=='MODE_EDIT-TASK-FORM' }">
                <div class="container text-center">
                    <h3>Update Task</h3>
                    <hr>
                    <form class="form-horizontal" method="POST" action="save-existing-task">
                        <input type="hidden" name="taskId" value="${task.taskId }" />
                        <div class="form-group">
                            <label class="control-label col-md-3">Task name</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="taskName"
                                    value="${task.taskName }" />
                            </div>
                        </div>
			            <div class="form-group">
                            <label class="control-label col-md-3">Time spent</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="timeSpent"
                                    value="${task.timeSpent }" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Group Name</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="taskGroup"
                                    value="${task.taskGroup }" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Assignee</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="assignee"
                                    value="${task.assignee }" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Complete status</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="completeStatus"
                                    value="${task.completeStatus }" />
                            </div>
                        </div>
                        <div class="form-group ">
                            <input type="submit" class="btn btn-primary" value="Update task" />
                        </div>
                    </form>
                </div>
            </c:when>

            <c:when test="${mode=='MODE_CANT-FINISH-TASK' }">
                <div class="container" id="homediv">
                    <div class="jumbotron text-center">
                        <h3>Task completion can not be set as completed,</h3>
                        <h3>because its subtasks are not completed!</h3>
                        <a class="btn btn-primary" href="/edit-task-form?taskId=${taskId }""
                            role="button" > Return </a></td>
                    </div>
                </div>
            </c:when>

            <c:when test="${mode=='MODE_SHOW-SUBTASKS' }">
                <div class="container text-center">
                    <h3>Subtasks for Task: ${taskName}</h3>
                    <hr>
                    <div>
                        <a class="btn btn-primary" href="/add-subtask-form?taskId=${taskId}"
                            role="button" > Add Subtasks </a>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Subtask Name</th>
                                    <th>Task Id</th>
                                    <th>Time spent</th>
                                    <th>Task Group</th>
                                    <th>Assignee</th>
                                    <th>Completion status</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="subTask" items="${allSubtasks }">
                                    <tr>
                                        <td>${subTask.subTaskId}</td>
                                        <td>${subTask.subTaskName}</td>
                                        <td>${subTask.taskId}</td>
                                        <td>${subTask.timeSpent}</td>
                                        <td>${subTask.taskGroup}</td>
                                        <td>${subTask.assignee}</td>
                                        <td>${subTask.completeStatus}</td>
                                        <td><a href="/edit-subtask-form?subTaskId=${subTask.subTaskId }"><span
                                            class="glyphicon glyphicon-pencil"></span></a></td>
                                        <td><a href="/delete-subtask?subTaskId=${subTask.subTaskId }"><span
                                            class="glyphicon glyphicon-trash"></span></a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:when>

            <c:when test="${mode=='MODE_ADD-SUBTASK-FORM' }">
                <div class="container text-center">
                    <h3>New Subtask for Task: ${taskName}</h3>
                    <hr>
                    <form class="form-horizontal" method="POST" action="save-subtask">
                        <div class="form-group">
                            <label class="control-label col-md-3">Subtask name</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="subTaskName"
                                    value="${subTask.subTaskName }" />
                            </div>
                        </div>
                        <input type="hidden" name="taskId" value="${taskId }" />
                        <input type="hidden" name="taskGroup" value="${taskGroup }" />
                        <div class="form-group">
                            <label class="control-label col-md-3">Assignee</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="assignee"
                                    value="${subTask.assignee }" />
                            </div>
                        </div>
                        <div class="form-group ">
                            <input type="submit" class="btn btn-primary" value="Add Subtask" />
                        </div>
                    </form>
                </div>
            </c:when>

            <c:when test="${mode=='MODE_EDIT-SUBTASK-FORM' }">
                <div class="container text-center">
                    <h3>Update Subtask (${subTask.subTaskName }) for Task: ${taskName}</h3>
                    <hr>
                    <form class="form-horizontal" method="POST" action="save-existing-subtask">
                        <input type="hidden" name="subTaskId" value="${subTask.subTaskId }" />
                        <div class="form-group">
                            <label class="control-label col-md-3">Subtask name</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="subTaskName"
                                    value="${subTask.subTaskName }" />
                            </div>
                        </div>
                        <input type="hidden" name="taskId" value="${subTask.taskId }" />
			            <div class="form-group">
                            <label class="control-label col-md-3">Time spent</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="timeSpent"
                                    value="${subTask.timeSpent }" />
                            </div>
                        </div>
			            <div class="form-group">
                            <label class="control-label col-md-3">Group</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="taskGroup"
                                    value="${subTask.taskGroup }" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Assignee</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="assignee"
                                    value="${subTask.assignee }" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Complete status</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="completeStatus"
                                    value="${subTask.completeStatus }" />
                            </div>
                        </div>
                        <div class="form-group ">
                            <input type="submit" class="btn btn-primary" value="Update Subtask" />
                        </div>
                    </form>
                </div>
            </c:when>
        </c:choose>



        <script src="static/js/jquery-1.11.1.min.js"></script>
        <script src="static/js/bootstrap.min.js"></script>
    </body>
</html>