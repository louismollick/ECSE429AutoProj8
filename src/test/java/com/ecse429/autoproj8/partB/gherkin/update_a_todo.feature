# Author - Louis
Feature: Update a Todo

  As a User
  I would like to update a todo
  So that I can update its details or set its status to done

  Background: The Todo Manager service is running and todo exists

    Given the Todo Manager serivce is running
    And the following todo exists:
      | id | title          | done  | description |
      | 1  | scan paperwork | false |             |

  Scenario: Update todo with all fields set (Success Flow)

    When I update the todo with id "1" to the following fields:
      | title                        | doneStatus | description     |
      | scan very important document | true       | need to do asap |
    Then the system will not show an error message
    And the system will have successfully updated the todo

  Scenario: Update the todo with doneStatus and description missing (Alternate Flow)

    When I update the todo with id "1" with only a new title field "new todo title"
    Then the system will not show an error message
    And the system will have set the new todo title and set the optional fields to default values

  Scenario: I try to update the todo's ID (Error Flow)

    When I update the todo with id "1" with a new id "55"
    Then the system will show an error message
    And the todo will have remained unchanged