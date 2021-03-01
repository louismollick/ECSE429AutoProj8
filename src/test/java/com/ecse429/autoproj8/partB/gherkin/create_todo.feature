# Author - Sami
Feature: Create a todo

  As a User
  I would like to create a todo
  So that I can remember to get it done

  Background: The Todo Manager service is running and project exists

    Given the Todo Manager service is running
    And the following project exists:
      | id | title       | completed | active | description |
      | 1  | Office Work | false     | false  |             |

  Scenario: Todo created (Success Flow)

    Given the following todo does not exist:
      | id | title    | doneStatus | description |
      | 3  | homework | false      |             |
    When I create the following todo:
      | title    | doneStatus | description |
      | homework | false      |             |
    Then the system will have successfully created the todo

  Scenario: Todo title already exists (Alternate Flow)

    Given the following todo exists:
      | id | title          | doneStatus | description |
      | 1  | scan paperwork | false      |             |
    When I create the following todo:
      | title          | doneStatus | description |
      | scan paperwork | false      |             |
    Then the system will have successfully created the todo

  Scenario: Create a todo with no title (Error Flow)

    When I create the following todo:
      | title | doneStatus | description |
      |       | false      |             |
    Then the system will show the error message 'title : field is mandatory'