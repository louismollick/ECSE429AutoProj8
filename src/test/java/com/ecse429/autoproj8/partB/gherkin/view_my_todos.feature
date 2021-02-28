# Author - Louis
Feature: View my Todos

  As a User
  I would like to view all my todos
  So I can see the tasks I have left to do and what I've already done

  Background: The Todo Manager service is running and todo exists

    Given the Todo Manager serivce is running
    And the following todos exist in the todo manager:
      | id | title           | doneStatus | description |
      | 1  | scan paperwork  | false      |             |
      | 2  | file paperwork  | false      |             |
      | 3  | finish homework | true       | asap        |
      | 4  | call parents    | true       |             |
      | 5  | make dinner     | false      | asap        |

  Scenario: I view all the todos (Success Flow)

    When I request to see all my todos
    Then the system will not show an error message
    And the system will successfully show all the todos

  Scenario: I view all the todos with a certain description (Alternate Flow)

    When I request to see all my todos with description 'asap'
    Then the system will not show an error message
    And the system will successfully show the following todos:
      | id | title           | doneStatus | description |
      | 3  | finish homework | true       | asap        |
      | 5  | make dinner     | false      | asap        |

  Scenario: I request to see a todo with an invalid id (Error Flow)

    When I request to see a todo with id '6'
    Then the system will show the error message 'Could not find an instance with todos/6'
    And the system will not return a todo