# Author - Sami
Feature: Create a project

  As a User
  I would like to create a project
  So that I can assign todos to it

  Background: The Todo Manager service is running and project exists

    Given the Todo Manager service is running
    And the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |

  Scenario: Project created (Success Flow)

    Given the following project does not exist:
      | id | title     | completed | active | description |
      | 2  | Home Work | false     | false  |             |
    When I create the following project:
      | title     | completed | active | description |
      | Home Work | false     | false  |             |
    Then the system will have successfully created the project

  Scenario: Project title already exists (Alternate Flow)

    Given the following project exists:
      | id | title     | completed | active | description |
      | 2  | Home Work | false     | false  |             |
    When I create the following project:
      | title     | completed | active | description |
      | Home Work | false     | false  |             |
    Then the system will have successfully created the project

  Scenario: Create a project with no title (Error Flow)

    When I create the following project:
      | title | completed | active | description |
      |       | false     | false  |             |
    Then the system will show the error message 'title : field is mandatory'