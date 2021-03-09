# Author Matteo Barbieri
Feature: Delete A Category

As a User I would like to delete categories that are no longer relevant
so that I can have a clean to do list.

Background: The Todo Manager service is running and category exists

    Given the Todo Manager service is running
      And the following categories exist in the todo manager:
        | id | title  | description |
        | 1  | Office |             |
        | 2  | Home   |             |

Scenario: I want to delete a category (Success Flow)

    When I request to delete a category with id '1'
    Then the system will not show an error message
     And the category with id '1' will not exist in the todo manager

Scenario: I want to delete the last category on the list (Alternate Flow)

    When I request to delete a category with id '1'
     And I request to delete a category with id '2'
    Then the system will not show an error message
     And there will not be any categories in the list

Scenario: I request to delete a category with invalid id (Error Flow)

    When I request to delete a category with id '12'
    Then the system will show the error message 'Could not find any instances with categories/12'