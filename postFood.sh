curl --location --request POST 'localhost:8080/foods' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=-JnqrZupY7FtcJcodKJKSqjw.undefined; ServerID=stage' \
--data-raw '{
    "name": "Apple",
    "unitMappings": [
        {
            "unit": "PIECE",
            "gramsPerUnit": 200
        },
        {
            "unit": "PORTION",
            "gramsPerUnit": 100
        }
    ]
}'