# Author - Sami
Feature: Create a category

  As a User
  I would like to create a category
  So that I can assign todos and projects to it

  Background: The Todo Manager service is running and project exists

    Given the Todo Manager service is running

  Scenario: Category created (Success Flow)

    Given the following category does not exist:
      | id | title | description |
      | 3  | Home  |             |
    When I create the following category:
      | id | title | description |
      | 3  | Home  |             |
    Then the system will have successfully created the category

  Scenario: Category title already exists (Alternate Flow)

    Given the following category exists:
      | id | title | description |
      | 3  | Home  |             |
    When I create the following category:
      | id | title | description |
      | 3  | Home  |             |
    Then the system will have successfully created the category

  Scenario: Create a category with no title (Error Flow)

    When I create the following category:
      | id | title | description |
      | 3  |       |             |
    Then the system will show the error message 'title : field is mandatory'