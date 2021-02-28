# Author - Louis
Feature: Assign Category to Todo

  As a User
  I would like to assign a category to a todo
  So that I specify an extra bit of information about the todo

  Background: The Todo Manager service is running and todo exists

    Given the Todo Manager serivce is running
    And the following todo exists:
      | id | title          | doneStatus | description |
      | 1  | scan paperwork | false      |             |

  Scenario: Category successfully assigned to todo (Success Flow)

    Given the following category exists:
      | id | title | description                         |
      | 3  | Easy  | Tag to indicate that a todo is easy |
    When I assign the category with id '3' to the todo with id '1'
    Then the system will not show an error message
    And the system will have successfully assigned the category to the todo

  Scenario: Category was already assigned to the todo (Alternate Flow)

    Given the following category exists:
      | id | title | description                         |
      | 3  | Easy  | Tag to indicate that a todo is easy |
    And this category is already assigned to the todo with id '1'
    When I assign the category with id '3' to the todo with id '1'
    Then the system will not show an error message
    And the system will have not added a duplicate category assignment to the todo

  Scenario: Category does not exist (Error Flow)

    Given no category exists with id '3'
    When I assign the category with id '3' to the todo with id '1'
    Then the system will show the error message "Could not find thing matching value for id"