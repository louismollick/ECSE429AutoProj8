# Author - Matt Langshur
Feature: Delete A Project

  As a User I would like to delete projects that I have completed or no longer want to complete.

  Background: 

    Given the Todo Manager service is running
    And the following projects exist in the todo manager:
      | id | title           | active | completed | description                    |
      | 1  | Job             | false  | false     |  Office job work               |
      | 2  | Homework        | false  | true      |  Some Description for homework |

  Scenario: I want to delete a project (Success Flow)

    When I request to delete a project with id '1'
    Then the system will not show an error message
    And the project with id '1' will not exist in the todo manager

  Scenario: I want to delete all the projects (Alternate Flow)

    When I request to delete a project with id '1'
    And  I request to delete a project with id '2'
    Then the system will not show an error message
    And there will not be any projects in the todo manager

  Scenario: I request to delete a project with an invalid id (Error Flow)

    When I request to delete a project with id '6'
    Then the system will show the error message 'Could not find any instances with projects/6'