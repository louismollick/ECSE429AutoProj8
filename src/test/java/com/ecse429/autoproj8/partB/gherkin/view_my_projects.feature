# Author - Matt Langshur
Feature: View My Projects

  As a User I would like to view existing projects that are in the system
  so that I can get an overview of what different projects I am working on currently. 

  Background: 

    Given the Todo Manager service is running
    And the following projects exist in the todo manager:
      | id | title           | active | completed | description                    |
      | 1  | Job             | false  | false     |  Office job work               |
      | 2  | Homework        | false  | true      |                                |
      | 3  | Home            | true   | true      | Work around the house          |
      | 4  | Repaint Garage  | true   | false     | Need to paint the garage white |

  Scenario: I view all the projects (Success Flow)

    When I request to see all my projects
    Then the system will not show an error message
    And the system will successfully show all the projects existing in the system

  Scenario: I view all the projects with an active status (Alternate Flow)

    When I request to see all my projects with active status 'true'
    Then the system will not show an error message
    And the system will successfully show the following projects:
      | id | title           | active | completed | description                    |
      | 3  | Home            | true   | true      | Work around the house          |
      | 4  | Repaint Garage  | true   | false     | Need to paint the garage white |

  Scenario: I request to see a project with an invalid id (Error Flow)

    When I request to see a project with id '5'
    Then the system will show the error message 'Could not find an instance with projects/5'
    And the system will not return a project