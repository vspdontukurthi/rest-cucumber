Feature: Tutorial part 1 - scenario's with simple REST calls

  Scenario: The two default books should always be returned
    When i call get "/books"
    Then the response status should be 200
    And response entity "_embedded.books" should contain at least 2 entities
    And the response should contain data:
    """
      _embedded.books:
        - title: Jurassic Park
          author: Michael Crichton
        - title: Schindler's List
          author: Thomas Keneally
    """

  Scenario: A newly created book should be added to the list
    Given i call post "/books" with data
      """
        title: "My book: #{id.scenario}"
        author: Myself
      """
    And the response status is 201
    When i call get "/books"
    Then the response entity "_embedded.books[]" should contain:
      | title  | My book: #{id.scenario} |
      | author | Myself                  |

  Scenario Outline: In descending order the S comes last
    When i call get "/books" with parameters:
      | sort | <sortField>,DESC |
    Then the response entity "_embedded.books[0]" should contain "title" with value "<expectedFirstBook>"
    When i call get "/books?sort=<sortField>"
    Then the response entity "_embedded.books[0]" should not contain "title" with value "<expectedFirstBook>"

    Examples:
      | sortField | expectedFirstBook         |
      | title     | Schindler's List          |
      | author    | Not So Great Expectations |
