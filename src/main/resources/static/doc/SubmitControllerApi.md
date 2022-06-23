
# 
## 
**URL:** `/v1/sensor/submit`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
sensorid|string|No comments found.|false|-
humidity|float|No comments found.|false|-
temperature|float|No comments found.|false|-


**Request-example:**
```
curl -X GET -i /v1/sensor/submit?sensorid=46&humidity=41.91&temperature=30.34
```

**Response-example:**
```
Return void.
```

