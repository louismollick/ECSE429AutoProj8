# Author - Matt Langshur
Feature: View Categories Associated with A Todo

  As a User I would like to view which categories are associated with a given todo so that I can 
  better categorize that todo item.

  Background: The Todo Manager service is running and the following todos exists

    Given the Todo Manager service is running
    And the following todos exist in the todo manager:
      | id | title           | doneStatus | description |
      | 1  | scan paperwork  | false      |  asap       |
      | 2  | file paperwork  | false      |  asap       |

    And the following category exists:
      | id | title  | description        |
      | 2  | Office | work at the office |

    And I assign the todo with id '1' to the category with id '2'

  Scenario: I view all the categories associated with a todo (Success Flow)

    When I request to see all the categories associated with a todo with id '1'
    Then the system will not show an error message
    And the system will successfully show all the category with id '2' associated with the todo of id '1'

  Scenario: The todo has no categories associated with it (Alternate Flow)

    When I request to see all the categories associated with a todo with id '2'
    Then the system will not show an error message
    And the system will successfully show that there are no categories associated with todo of id '2'

  Scenario: I request to see a category with an invalid id (Error Flow)

    When I request to see all the categories associated with a todo with id '20'
    Then the system will show the error message 'Could not find an instance with categories/5'