# Author - Matteo Barbieri
Feature: View Projects Associated with A Task

As a User I would like to view which project a todo belongs to 
so that I can see what bigger item this task belongs to.

Background: The Todo Manager service is running and a project exists

Given the Todo Manager service is running
And the following project exists:
| id | title       | completed | active | description |
| 1  | Office Work | false     | false  |             |

And the following todo exists:
     | id | title           | doneStatus | description |
     | 1  | scan paperwork  | false      |             |


Scenario: I view all the tasks associated with a project (Success Flow)

     When I request to see the project associated with a task with id '1'
     Then the system will not show an error message
      And the system will successfully show all the project with id '1' associated with the task with id '1'

Scenario: The todo has no project associated with it

     When I request to see the project associated with a task with id '2'
     Then the system will not show an error message
      And the system will successfully show that there is no project associated with todo of id '2'

Scenario: I request to see a project with an invalid todo id (Error Flow)

     When I request to see the project associated with a todo with id '47'
     Then the system will show the error message 'Could not find an instance with todos/47' 