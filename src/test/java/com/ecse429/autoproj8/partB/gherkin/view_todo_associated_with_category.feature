#Author Matteo Barbieri

Feature: View Todos Associated with a Category

As a User I would like to view which todos belong to a certain Category
so that I can see what needs to be done for a certain category.

  Background: The Todo Manager service is running 

    Given the Todo Manager service is running
    And the following categories exist in the todo manager:
      | id | title           | description |
      | 1  | ECSE 429        |             |
      | 2  | ECSE 428        |             |

    And the following todos exist in the todo manager:
      | id | title           | doneStatus | description |
      | 1  | scan paperwork  | false      |  asap       |
      | 2  | file paperwork  | false      |  asap       |

      And I assign the todo with id '1' to the category with id '2'


    Scenario: I view all the todos associated with a category (Success Flow)

        When I request to see all the todos associated with a category with id '2'
        Then the system will not show an error message
         And the system will successfully show the todo with id '1' associated with the category with id '2'

    Scenario: The category has no todos associated with it (Alternate Flow)

        When I request to see all the todos associated with a category with id '1'
        Then the system will not show an error message
        And the system will successfully show that there are no todos associated with category of id '1'

    Scenario: I request to see a category with an invalid id (Error Flow)

         When I request to see all the todos associated with a category with id '4'
         Then the system will show the error message 'Could not find an instance with todos/21'


