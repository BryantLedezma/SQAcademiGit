Feature: add product to shopping carts
  As a user I want to add product to shopping cart
  to make a purchase

  @Successful
  Scenario Outline: Scenario: add product to shopping cart
    Given that the user selects the product
      | productos   |
      | <productos> |
    When that the user adds the product to the shopping cart
      | productos   |
      | <productos> |
    Then that the user views the product in the shopping cart
    Examples:
      | productos |
    ##@externaldata@.\src\test\resources\datadriven\data.xlsx@datos
   |Lavadora|
   |Balon|
   |Nevera|
   |Aguardiente|








