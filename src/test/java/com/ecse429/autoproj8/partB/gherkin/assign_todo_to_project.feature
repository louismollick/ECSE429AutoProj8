#Author Matteo Barbieri
Feature: Assign Task to a Project

As a User I would like to assign a todo to a project
so that I can indicate that the todo is part of that project

Background: The Todo Manager service is running and project exists

Given the Todo Manager service is running
  And the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |

Scenario: Task successfully assigned to a project (Success Flow)

    Given the following todo exists:
      | id | title        | doneStatus | description      |
      | 3  | Prank Dwight | false      | office reference |
    When I assign the todo with id '3' to the project with id '1'
    Then the system will not show an error message
    And the system will have successfully assigned the todo with the project

Scenario: Task was already assigned to the category (Alternate Flow)

    Given the following todo exists:
      | id | title        | doneStatus | description      |
      | 3  | Prank Dwight | false      | office reference |

      And this todo is already assigned to the project wit id '1'
     When I assign the todo with id '3' to the project with id '1'
     Then the system will not show an error message
     And the system will not have added a duplicate task to the project

Scenario: Todo does not exist (Error Flow)

    Given no todo exists with id '4'
     When I assign the todo with id '4' to the project with id '1'
     Then the system will show the error message "Could not find thing matching value for id"