# Author - Louis
Feature: Assign Category to Project

  As a User
  I would like to assign a category to a project
  So that I specify an extra bit of information about the project

  Background: The Todo Manager service is running and project exists

    Given the Todo Manager serivce is running
    And the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |

  Scenario: Category successfully assigned to project (Success Flow)

    Given the following category exists:
      | id | title      | description                                     |
      | 3  | Main focus | Tag to indicate that a project is my main focus |
    When I assign the category with id '3' to the project with id '1'
    Then the system will not show an error message
    And the system will have successfully assigned the category to the project

  Scenario: Category was already assigned to the project (Alternate Flow)

    Given the following category exists:
      | id | title      | description                                     |
      | 3  | Main focus | Tag to indicate that a project is my main focus |
    And this category is already assigned to the project with id '1'
    When I assign the category with id '3' to the project with id '1'
    Then the system will not show an error message
    And the system will have not added a duplicate category assignment to the project

  Scenario: Category does not exist (Error Flow)

    Given no category exists with id '3'
    When I assign the category with id '3' to the project with id '1'
    Then the system will show the error message "Could not find thing matching value for id"