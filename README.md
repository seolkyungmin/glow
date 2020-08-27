# glow
glow

1. 사진 업로드 서비스

다음과 같은 기능이 요구되는 이미지 서비스가 있습니다.
유저는 자신만의 폴더를 생성할 수 있다.
생성시 폴더에 대한 이름을 정할 수 있다.
폴더의 생성 수는 제한이 없다.
유저는 자신의 특정 폴더에 업로드된 사진을 저장할 수 있다.
사진은 어딘가의 이미지 서버에 저장이 되고 이에 대한 URL이 저장된다고 가정한다.(실제 이미지 업로드 기능은 필요 없음)
N개의 사진을 동시에 저장할 수 있다.
유저는 자신의 폴더를 생성 순서대로 조회할 수 있으며, 이 때에 각 폴더에 저장된 이미지 갯수를 알 수 있다.
유저는 특정 폴더에서 최근 저장한 순서대로 사진을 조회할 수 있다.

이 요구사항에 따른 DB와 REST API를 구성해주세요.

[request] [POST]
/shop/glow/pictures/create
{
    "name":"folder",
    "userId":1
}

[response]
{
    "data": {
        "id": 30
    },
    "status": {
        "message": "정상처리되었습니다.",
        "errors": null,
        "code": "0000"
    }
}

유저는 자신의 폴더를 생성 순서대로 조회할 수 있으며, 이 때에 각 폴더에 저장된 이미지 갯수를 알 수 있다.

[request] [GET]

/shop/glow/pictures?userId=1

[response]

{
    "data": {
        "list": [
            {
                "id": 27,
                "name": "1_folder1",
                "userId": 1,
                "pictureChildList": [
                    {
                        "id": 27,
                        "name": null,
                        "url": null,
                        "pictureId": 0,
                        "createdAt": null,
                        "updatedAt": null,
                        "tagList": null
                    }
                ],
                "pictureCounts": 0,
                "createdAt": "2020-08-26 16:08:01.0",
                "updatedAt": "2020-08-26 16:25:13.0"
            },
            {
                "id": 28,
                "name": "1_folder2",
                "userId": 1,
                "pictureChildList": [
                    {
                        "id": 28,
                        "name": "사진1004",
                        "url": "www.test1004.com",
                        "pictureId": 28,
                        "createdAt": "2020-08-26 16:41:16.0",
                        "updatedAt": "2020-08-26 16:41:16.0",
                        "tagList": null
                    },
                    {
                        "id": 28,
                        "name": "사진1005",
                        "url": "www.test1005.com",
                        "pictureId": 28,
                        "createdAt": "2020-08-26 16:41:16.0",
                        "updatedAt": "2020-08-26 16:41:16.0",
                        "tagList": null
                    },
                    {
                        "id": 28,
                        "name": "사진1002",
                        "url": "www.test1002.com",
                        "pictureId": 28,
                        "createdAt": "2020-08-26 16:09:10.0",
                        "updatedAt": "2020-08-26 16:09:10.0",
                        "tagList": null
                    },
                    {
                        "id": 28,
                        "name": "사진1002",
                        "url": "www.test1002.com",
                        "pictureId": 28,
                        "createdAt": "2020-08-26 16:09:10.0",
                        "updatedAt": "2020-08-26 16:09:10.0",
                        "tagList": null
                    }
                ],
                "pictureCounts": 4,
                "createdAt": "2020-08-26 16:08:32.0",
                "updatedAt": "2020-08-26 16:25:18.0"
            },
            {
                "id": 29,
                "name": "1_folder!!!",
                "userId": 1,
                "pictureChildList": [
                    {
                        "id": 29,
                        "name": null,
                        "url": null,
                        "pictureId": 0,
                        "createdAt": null,
                        "updatedAt": null,
                        "tagList": null
                    }
                ],
                "pictureCounts": 0,
                "createdAt": "2020-08-26 16:42:11.0",
                "updatedAt": "2020-08-26 16:42:11.0"
            }
        ]
    },
    "status": {
        "message": "정상처리되었습니다.",
        "errors": null,
        "code": "0000"
    }
}

----------------------------------------------------------------------------------------------------------------------------------------------

2. 사진 태그 서비스

위에서 개발된 사진 업로드 서비스에 다음과 같은 기능이 추가됩니다.
사진 저장시 N개의 문자 태그를 추가로 전달받아 저장해야 한다.
[response] [POST]
/shop/glow/pictures/child/create  [사진저장, 태그저장]

[request]

{
    "id": 28,
    "userId" : 1,
    "pictureChildList":[
    {
        "name":"사진1004",
        "pictureId": 28,
        "url":"www.test1004.com",
        "tagList":[
        {
            "tagName" : "test@@"
        }
      
        ]
    },
     {
        "name":"사진1005",
        "pictureId": 28,
        "url":"www.test1005.com",
        "tagList":[
        {
            "tagName" : "test@@"
        }
      
        ]
    }
  ]
}

[response]

{
    "data": {
        "id": 28
    },
    "status": {
        "message": "정상처리되었습니다.",
        "errors": null,
        "code": "0000"
    }
}

통계를 위해 전체 사진에서 가장 많이 달린 태그에 대한 TOP 10 을 추출할 수 있어야 한다.

[request] [GET]

/shop/glow/pictures/tags/rankings [태그 TOP10]

[response]

{
    "data": {
        "list": [
            {
                "id": 4,
                "tagName": "test@@",
                "pictureChildId": 13,
                "tagCount": 11,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 5,
                "tagName": "test1",
                "pictureChildId": 13,
                "tagCount": 10,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 6,
                "tagName": "test2",
                "pictureChildId": 13,
                "tagCount": 9,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 7,
                "tagName": "test3",
                "pictureChildId": 13,
                "tagCount": 8,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 8,
                "tagName": "test4",
                "pictureChildId": 13,
                "tagCount": 7,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 9,
                "tagName": "test5",
                "pictureChildId": 13,
                "tagCount": 6,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 10,
                "tagName": "test6",
                "pictureChildId": 13,
                "tagCount": 5,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 11,
                "tagName": "test7",
                "pictureChildId": 13,
                "tagCount": 4,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 12,
                "tagName": "test8",
                "pictureChildId": 13,
                "tagCount": 3,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            },
            {
                "id": 13,
                "tagName": "test9",
                "pictureChildId": 13,
                "tagCount": 2,
                "createdAt": "2020-08-25 17:36:42.0",
                "updatedAt": "2020-08-25 17:36:42.0"
            }
        ]
    },
    "status": {
        "message": "정상처리되었습니다.",
        "errors": null,
        "code": "0000"
    }
}

----------------------------------------------------------------------------------------------------------------------------------------------

3. 포인트 서비스

위에서 개발된 사진 업로드 서비스에 다음과 같은 기능이 추가됩니다.
유저는 포인트를 갖게된다.
폴더 생성시마다 해당 유저는 1,000 포인트를 획득한다.
사진 업로드시 해당 유저는 사진 1개당 100 포인트를 소모한다.
포인트가 모자란 경우 사진을 업로드 할 수 없다.

이 추가 요구사항에 따른 수정 내용을 적용해주세요.

/shop/glow/pictures/create        1000 포인트 획득

/shop/glow/pictures/child/create  100  포인트 감소

----------------------------------------------------------------------------------------------------------------------------------------------

4. 포인트에 대한 선입/선출

유저의 사진 등록을 활성화 하기 위해서 개발된 포인트 서비스 기능에 다음과 같은 기능이 추가됩니다.
포인트 획득과 소모에 대한 선입/선출을 추적할 수 있어야 한다. 예를 들면,
유저가 폴더를 2개(생성순서대로 폴더1, 폴더2라 하자) 생성하여 2,000 포인트를 획득한다.
유저가 1개의 사진을 폴더2에 저장하여 100 포인트를 소모한다.(사진이 저장되는 폴더와 선입/선출은 관계가 없다.)
이 때, 폴더1은 생성으로 인한 포인트 획득량 1000 포인트에서, 100 포인트가 소모됐음을 알 수 있어야 한다.
폴더2는 생성으로 인한 포인트 획득량 1000 포인트에서, 포인트 소모가 없었음을 알 수 있어야 한다.
사진을 추가로 10장을 저장하면, 다음과 같은 상태가 됨을 알 수 있어야 한다.
폴더1
포인트획득: 1000
포인트소모: 1000
폴더2
포인트획득: 1000
포인트소모: 100

[request] [GET]

/shop/glow/pictures/stats?userId=2

[response]

{
    "data": {
        "list": [
            {
                "pointLogList": [
                    {
                        "plusPoint": 1000,
                        "minusPoint": 0,
                        "id": 8
                    }
                ],
                "name": "2_folder!!!",
                "id": 30,
                "userId": 2
            }
        ]
    },
    "status": {
        "message": "정상처리되었습니다.",
        "errors": null,
        "code": "0000"
    }
}


통계를 위해 전체 폴더 중에 획득한 포인트에서 소모가 없는 폴더 목록을 추출한다

[response] [GET]

/shop/glow/pictures/unused

[request]

{
    "data": {
        "list": [
            {
                "id": 1,
                "userId": 1,
                "pictureId": 27,
                "logData": "PICTURE_CREATE_POINT",
                "points": 1000,
                "createdAt": "2020-08-26 16:08:01.0",
                "updatedAt": "2020-08-26 16:08:01.0"
            },
            {
                "id": 7,
                "userId": 1,
                "pictureId": 29,
                "logData": "PICTURE_CREATE_POINT",
                "points": 1000,
                "createdAt": "2020-08-26 16:42:11.0",
                "updatedAt": "2020-08-26 16:42:11.0"
            }
        ]
    },
    "status": {
        "message": "정상처리되었습니다.",
        "errors": null,
        "code": "0000"
    }
}

