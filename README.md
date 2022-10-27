# CryptoPrint

This project is all about understanding http requests and response in Java and the processing of JSON Objects.
Once you execute it, you will be asked to enter an asset id for a crypto currency. Then the program will send a get http request to an external API called coinAPI.io
and grab the following informations about it :
    - Current price in USD
    - 1 hour volume in USD
    - 1 day volume in USD
    - 1 month volume in USD
    - The name of the currency
If the given ID is correct it will display these informations.

BEFORE TRYING TO EXECUTE THE PROG :
You have to make sure that you modified the path to the gson library in the file ".classpath" otherwise the program won't recognize the calls to it.
You can find the library gson on the web, the version used there is gson 2.10.
