# Author: Matteo Barbieri

Feature: Assign Project to Todo

As a User I would like to assign a project to a todo 
so that I can then see what bigger project the todo is a part of

  Background: The Todo Manager service is running and todo exists

  Given the Todo Manager service is running
    And the following todo exists:
      | id | title          | doneStatus | description |
      | 1  | scan paperwork | false      |             |

  Scenario: Project successfully assigned to todo (Success Flow)

  Given the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |
   When I assign the project with id '1' to the todo with id '1'
   Then the system will not show an error message
    And the system will have successfully assigned the project to the todo


   Scenario: Project was already assigned to the todo (Alternate Flow)

    Given the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |
      And this project is already assigned to the todo with id '1'
     When I assign the project with id '1' to the todo with id '1'
     Then the system will not show an error message
    And the system will have not added a duplicate project assignment to the todo

    
    Scenario: Project does not exist (Error Flow)

        Given no project exists with id '4'
         When I assign the project with id '4' to the todo with id '1'
         Then the system will show the error message "Could not find thing matching value for id"
