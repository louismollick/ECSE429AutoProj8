# Author - Matt Langshur
Feature: Delete A Todo

  As a User I would like to delete todo items that I am finished with or no longer want/need to do.
  This should remove them so they are no longer on my todo list. 

  Background: The Todo Manager service is running and todo exists

    Given the Todo Manager service is running
    And the following todos exist in the todo manager:
      | id | title           | doneStatus | description |
      | 1  | scan paperwork  | false      |             |
      | 2  | file paperwork  | false      |             |

  Scenario: I want to delete a todo (Success Flow)

    When I request to delete a todo with id '1'
    Then the system will not show an error message
    And the todo with id '1' will not exist in the todo manager

  Scenario: I want to delete the last todo on the list (Alternate Flow)

    When I request to delete a todo with id '1'
    And  I request to delete a todo with id '2'
    Then the system will not show an error message
    And there will not be any todos in the list

  Scenario: I request to delete a todo with an invalid id (Error Flow)

    When I request to delete a todo with id '6'
    Then the system will show the error message 'Could not find any instances with todos/6'