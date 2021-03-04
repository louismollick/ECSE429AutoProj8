# Author - Matt Langshur
Feature: View My Categories

  As a User I would like to view existing categories that are in the system
  so that I can get an overview of what different categories I am working on currently. 

  Background: 

    Given the Todo Manager service is running
    And the following categories exist in the todo manager:
      | id | title           | description |
      | 1  | ECSE 429        |             |
      | 2  | ECSE 428        |             |
      | 3  | Urgent          | asap        |
      | 4  | Important       | asap        |

  Scenario: I view all the categories (Success Flow)

    When I request to see all my categories
    Then the system will not show an error message
    And the system will successfully show all the categories existing in the system

  Scenario: I view all the categories with the same description (Alternate Flow)

    When I request to see all my categories with description 'asap'
    Then the system will not show an error message
    And the system will successfully show the following categories:
      | id | title           | description |
      | 3  | Urgent          | asap        |
      | 4  | Important       | asap        |

  Scenario: I request to see a category with an invalid id (Error Flow)

    When I request to see a category with id '5'
    Then the system will show the error message 'Could not find an instance with categories/5'
    And the system will not return a category