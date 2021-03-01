# Author - Sami
Feature: Update a Project

  As a User
  I would like to update a project
  So that I can update its details or set its "completed" or "active" status to true or false

  Background: The Todo Manager service is running and todo exists

    Given the Todo Manager service is running
    And the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |

  Scenario: Update project with all fields set (Success Flow)

    When I update the project with the following fields:
      | id | title       | completed | active | description |
      | 1  | Office Work | true      | true   | Office work |
    Then the system will not show an error message
    And the system will have successfully updated the project

  Scenario: Update project with "completed" and "active" missing (Alternate Flow)

    When I update the project with id "1" with only a new title field "new todo title"
    Then the system will not show an error message
    And the system will have set the new project title and set the optional fields to default values

  Scenario: Update project's ID (Error Flow)

    When I update the project with id "1" with a new id "55"
    Then the system will show an error message
    And the project will have remained unchanged