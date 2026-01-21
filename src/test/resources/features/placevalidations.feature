Feature: validating place API's
@addplace @Regression
  Scenario Outline: verify if place is being successfully added using AddplaceAPI
    Given add place payload with "<name>" "<language>" "<address>"
    When user calls "addplaceapi" with "post" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And  verify place_id created maps to "<name>" using "getplaceapi"
    Examples:
     | name    | |language | |  address   |
     |youssef  | | arabic  | | giza egypt |
    # |ibrahim  | |english  | |cairo egypt |
@deleteplace @Regression
  Scenario: verify if delete place funcationality is working
    Given delete place payload
    When user calls "deleteplaceapi" with "post" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"

