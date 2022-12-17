Feature: BackEnd Task 1 and 2

@Test
Scenario: Retrieve the ID of bitcoin (BTC), usd tether (USDT), and Ethereum (ETH)
	Given API Key is provided in header
	When retrieve the ID of currency "BTC,USDT,ETH"
	And ID is retieved
	Then convert to "BOLI"

@Test	
Scenario: Retrieve the Ethereum (ID 1027) technical documentation
	Given API Key is provided in header
	When API executed for assertions on Ethereum currency
	Then Assertions are verified
	
