# ProductController

ProductController


---
## addProduct

> BASIC

**Path:** /product

**Method:** POST

> REQUEST

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**Request Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| id | integer |  |
| name | string |  |
| description | string |  |
| imageUrl | string |  |
| price | string |  |
| status | integer |  |

**Request Demo:**

```json
{
  "id": 0,
  "name": "",
  "description": "",
  "imageUrl": "",
  "price": "",
  "status": 0
}
```



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer | 状态码 |
| message | string | 消息 |
| data | object | 数据 |
| &ensp;&ensp;&#124;─id | integer |  |
| &ensp;&ensp;&#124;─name | string |  |
| &ensp;&ensp;&#124;─description | string |  |
| &ensp;&ensp;&#124;─imageUrl | string |  |
| &ensp;&ensp;&#124;─price | string |  |
| &ensp;&ensp;&#124;─status | integer |  |

**Response Demo:**

```json
{
  "code": 0,
  "message": "",
  "data": {
    "id": 0,
    "name": "",
    "description": "",
    "imageUrl": "",
    "price": "",
    "status": 0
  }
}
```




---
## getAllProducts

> BASIC

**Path:** /product/history

**Method:** GET

> REQUEST



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer | 状态码 |
| message | string | 消息 |
| data | array | 数据 |
| &ensp;&ensp;&#124;─ | object |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─id | integer |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─name | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─description | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─imageUrl | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─price | string |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─status | integer |  |

**Response Demo:**

```json
{
  "code": 0,
  "message": "",
  "data": [
    {
      "id": 0,
      "name": "",
      "description": "",
      "imageUrl": "",
      "price": "",
      "status": 0
    }
  ]
}
```




---
## freezeProduct

> BASIC

**Path:** /product/freeze/{id}

**Method:** POST

> REQUEST

**Path Params:**

| name | value | desc |
| ------------ | ------------ | ------------ |
| id |  |  |



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer | 状态码 |
| message | string | 消息 |
| data | string | 数据 |

**Response Demo:**

```json
{
  "code": 0,
  "message": "",
  "data": ""
}
```




---
## unfreezeProduct

> BASIC

**Path:** /product/unfreeze/{id}

**Method:** POST

> REQUEST

**Path Params:**

| name | value | desc |
| ------------ | ------------ | ------------ |
| id |  |  |



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer | 状态码 |
| message | string | 消息 |
| data | string | 数据 |

**Response Demo:**

```json
{
  "code": 0,
  "message": "",
  "data": ""
}
```




---
## sellProduct

> BASIC

**Path:** /product/sell/{id}

**Method:** POST

> REQUEST

**Path Params:**

| name | value | desc |
| ------------ | ------------ | ------------ |
| id |  |  |



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer | 状态码 |
| message | string | 消息 |
| data | string | 数据 |

**Response Demo:**

```json
{
  "code": 0,
  "message": "",
  "data": ""
}
```



