# Author - Sami
Feature: Update a Category

  As a User
  I would like to update a category
  So that I can update its description

  Background: The Todo Manager service is running and todo exists

    Given the Todo Manager service is running
    And the following category exists:
      | id | title  | description |
      | 1  | Office |             |

  Scenario: Update category with all fields set (Success Flow)

    When I update the category with the following fields:
      | id | title           | description      |
      | 1  | Office Category | Some description |
    Then the system will not show an error message
    And the system will have successfully updated the category

  Scenario: Update category with "description" missing (Alternate Flow)

    When I update the category with id "1" with only a new title field "new todo title"
    Then the system will not show an error message
    And the system will have set the new category title and set the optional fields to default values

  Scenario: Update category's ID (Error Flow)

    When I update the category with id "1" with a new id "55"
    Then the system will show an error message
    And the category will have remained unchanged