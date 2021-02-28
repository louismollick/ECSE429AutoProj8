# Author - Louis
Feature: Assign Todo to a Category

  As a User
  I would like to assign a todo to a category
  So that I can indicate that the todo is a member of that category

  Background: The Todo Manager service is running and category exists

    Given the Todo Manager serivce is running
    And the following category exists:
      | id | title  | description |
      | 2  | Office |             |

  Scenario: Todo successfully assigned to category (Success Flow)

    Given the following todo exists:
      | id | title        | doneStatus | description                              |
      | 3  | Prank Dwight | false      | office reference wow i clapped i clapped |
    When I assign the todo with id '3' to the category with id '2'
    Then the system will not show an error message
    And the system will have successfully assigned the todo to the category

  Scenario: Todo was already assigned to the category (Alternate Flow)

    Given the following todo exists:
      | id | title        | doneStatus | description                              |
      | 3  | Prank Dwight | false      | office reference wow i clapped i clapped |
    And this todo is already assigned to the category with id '2'
    When I assign the todo with id '3' to the category with id '2'
    Then the system will not show an error message
    And the system will have not added a duplicate todo assignment to the category

  Scenario: Todo does not exist (Error Flow)

    Given no todo exists with id '3'
    When I assign the todo with id '3' to the category with id '2'
    Then the system will show the error message "Could not find thing matching value for id"