#Author Matteo Barbieri

Feature: Assign Project to a Category

As a User I would like to assing a project to a category
so that I can organize related projects in one group.

  Background: The Todo Manager service is running and category exists

Given the Todo Manager service is running
  And the following category exists:
    | id | title  | description |
    | 2  | Office |             |

Scenario: Project successfully assigned to category (Success Flow)

    Given the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |
     When I assign project with id '1' to the category with id '2'
     Then the system will not show an error message
      And the system will have successfully assigned the project to the category

Scenario: Project was already assigned to the category (Alternate Flow)

    Given the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |
      And this project is already assigned to the category with id '2'
     When I assign project with id '1' to the category with id '2'
     Then the system will not show an error message
      And the system will have not added a duplicate project to the category

Scenario: Project does not exist (Error Flow)
   
    Given no project exists with id '12'
     When I assign project with id '12' to the category with id '2'
     Then the system will show the error message "Could not find thing matching value for id"