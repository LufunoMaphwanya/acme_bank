* Start up application

./gradlew bootRun


* To create or open an account

post /accounts

body: { "type": "current | savings", "deposit" : "<intValue>" }


* To see all accounts

get/accounts


* To withdraw - enter negative value in deposit

put/accounts

body: { "id": 587676476732, "deposit" : "<negativeIntValue>" }



* To deposit- enter positive value in deposit

put/accounts

body: { "id": 673656536536, "deposit" : "<positiveIntValue>" }