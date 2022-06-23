
# 
## 
**URL:** `/v1/user/Register`

**Type:** `POST`


**Content-Type:** `application/json`

**Description:** 




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
name|string|No comments found.|false|-
openID|string|No comments found.|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json' -i /v1/user/Register --data '{
  "name": "laurence.wisozk",
  "openID": "46"
}'
```

**Response-example:**
```
Return void.
```

## 
**URL:** `/v1/user/register`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```
curl -X GET -i /v1/user/register
```

**Response-example:**
```
string
```

