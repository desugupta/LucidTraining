# LucidTraining
LIST OF API CALLS TO THE SERVICES: ATTENDANCE AND LEAVE MANAGEMENT

1. Creates an employee user
POST METHOD:
http://localhost:9442/subscription
REQUEST:
{
    "userName": "Pankaj",
    "password": "1234",
    "emailId": "desurajasekhargupta437@gmail.com",
    "pincode": "500081",
    "city":"Hyderabad",
    "country":"India",
    "mobileNo": "6281584089",
    "age": 29,
    "gender": "female",
    "roles":[
    	{
    		"roleName":"MANAGER"
    	}
    	],
    "department": "dev"
}
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3dcefb7811d631214f11d",
        "versionId": 0,
        "userName": "Pankaj",
        "password": "$2a$10$bqKhB7tIXc9dzGHYAwdrv.DRPo5DLRyxuaC/q1Q6XpPrK1vSsekT.",
        "employeeId": 26,
        "firstName": null,
        "lastName": null,
        "emailId": "desurajasekhargupta437@gmail.com",
        "age": 29,
        "gender": "female",
        "mobileNo": "+916281584089",
        "dob": null,
        "roles": [
            {
                "deleteFlag": false,
                "deletedOn": null,
                "activeFlag": false,
                "activeFromDate": null,
                "activeTillDate": null,
                "createdOn": null,
                "createdBy": null,
                "lastUpdatedOn": null,
                "lastUpdatedBy": null,
                "id": null,
                "versionId": null,
                "roleId": null,
                "roleName": "MANAGER",
                "roleStatus": null
            }
        ],
        "department": "dev",
        "designation": null,
        "country": "India",
        "city": "Hyderabad",
        "pincode": "500081",
        "employeeStatus": null,
        "permanentAddress": null,
        "bloodGroup": null,
        "joinDate": null,
        "endDate": null,
        "managerEmpId": null,
        "managerEmailId": null
    },
    "errorMessage": null,
    "success": true
}


2. Authentication of an employee which returns jwt token
POST METHOD:
http://localhost:9442/authentication
REQUEST:
{
    "userName": "Pankaj",
    "password": "1234"
}
RESPONSE:
{
    "response": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQcmFudXNoYSIsImF1dGgiOiJST0xFX01BTkFHRVIiLCJpYXQiOjE2MjIzOTcwNjUsImV4cCI6MTYyMjQzMzA2NX0.HxvfZ6hZgQHV7RQCf4csDrd7nVr2HJZu1XaQgpjvCpg"
}

3. To apply for a leave by the employee
POST METHOD:
http://localhost:9442/leave
REQUEST:
{
	"employeeId":24,
	"leaveStartDate":"2021-07-06",
	"leaveEndDate":"2021-07-09",
	"leaveType":"earnedLeave",
	"leaveDay":"full",
	"leaveReason":"festival"
}	
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3d48f36cc271117c307ce",
        "versionId": 0,
        "employeeId": 24,
        "approvalDate": null,
        "requestDate": "2021-05-30",
        "leaveStartDate": "2021-07-06",
        "leaveEndDate": "2021-07-09",
        "leaveStatus": "pending",
        "leaveType": "earnedLeave",
        "leaveDay": "full",
        "leaveReason": "festival",
        "managerEmpId": 25,
        "department": "dev"
    },
    "errorMessage": null,
    "success": true
}


4, TO APPROVE THE LEAVE REQUEST OF AN EMPLOYEE BY THE EMPLOYEE MANAGER 
PUT METHOD: http://localhost:9442/leave 
REQUEST:
{
    "employeeId": "24",
    "leaveStatus": "approved"
}
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3d48f36cc271117c307ce",
        "versionId": 1,
        "employeeId": 24,
        "approvalDate": "2021-05-30",
        "requestDate": "2021-05-30",
        "leaveStartDate": "2021-07-06",
        "leaveEndDate": "2021-07-09",
        "leaveStatus": "approved",
        "leaveType": "earnedLeave",
        "leaveDay": "full",
        "leaveReason": "festival",
        "managerEmpId": 25,
        "department": "dev"
    },
    "errorMessage": null,
    "success": true
}

5. TO DOWNLOAD THE ATTENDANCE REPORT OF AN EMPLOYEE BY GIVING START AND ENDDATE ,EMPLOYEEID WHICH GIVES TOTAL WORKING HOURS
GET METHOD:
http://localhost:9442/leave?employeeId=23&startDate=2021-06-02&endDate=2021-06-25
RESPONSE:
{
    "result": "File report downloaded successfully",
    "errorMessage": null,
    "success": true
}

6. TO CREATE A ROLE
POST METHOD:
http://localhost:9442/role
REQUEST:
{
	"roleName":"Manager",
	"roleStatus":"Active"
}
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3b5ba47e2346ae3443b1d",
        "versionId": 0,
        "roleId": "792",
        "roleName": "Manager",
        "roleStatus": "Active"
    },
    "errorMessage": null,
    "success": true
}

7.TO SAVE THE HOLIDAY (DEFINE COMPANY CALENDAR )
POST METHOD:
http://localhost:9442/holiday
REQUEST:
{
	"holidayId":10,
	"holidayYear":"2021", 
	"holiday":"2021-08-19",
	"reason":"Diwali"
}
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3e11db7811d631214f11e",
        "holidayId": 10,
        "holiday": "2021-08-19",
        "reason": "Diwali",
        "holidayYear": 2021
    },
    "errorMessage": null,
    "success": true
}

8. TO SAVE THE DAILY ATTTENDANCE
POST METHOD:
http://localhost:9442/attendance
REQUEST:
{
	"employeeId":"23",
	"timeIn":"9:15",
	"timeOut":"21:48",
	"normalDate":"2021-05-31",
	"location":"Ongole"
}
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3e3fbb7811d631214f11f",
        "versionId": 0,
        "sno": null,
        "employeeId": 23,
        "normalDate": "2021-05-31T00:00:00.000+00:00",
        "timeIn": "9:15",
        "timeOut": "21:48",
        "working": 12.55,
        "location": "Ongole"
    },
    "errorMessage": null,
    "success": true
}

9.TO GETALL THE EMPLOYEES
GET METHOD:
http://localhost:9442/employee/getAll
RESPONSE:
{
    "result": [
        {
            "deleteFlag": false,
            "deletedOn": null,
            "activeFlag": false,
            "activeFromDate": null,
            "activeTillDate": null,
            "createdOn": null,
            "createdBy": null,
            "lastUpdatedOn": null,
            "lastUpdatedBy": null,
            "id": "60a211fab5764042e7b5ff20",
            "versionId": 0,
            "userName": "Chathrapathi S",
            "password": "1234",
            "employeeId": null,
            "firstName": null,
            "lastName": null,
            "emailId": null,
            "age": 0,
            "gender": null,
            "mobileNo": null,
            "dob": null,
            "roles": null,
            "department": null,
            "designation": null,
            "country": null,
            "city": null,
            "pincode": "0",
            "employeeStatus": null,
            "permanentAddress": null,
            "bloodGroup": null,
            "joinDate": null,
            "endDate": null,
            "managerEmpId": null,
            "managerEmailId": null
        },
        {
            "deleteFlag": false,
            "deletedOn": null,
            "activeFlag": false,
            "activeFromDate": null,
            "activeTillDate": null,
            "createdOn": null,
            "createdBy": null,
            "lastUpdatedOn": null,
            "lastUpdatedBy": null,
            "id": "7",
            "versionId": 0,
            "userName": "Rahasjbsekhargupotab",
            "password": "1234",
            "employeeId": null,
            "firstName": null,
            "lastName": null,
            "emailId": null,
            "age": 0,
            "gender": null,
            "mobileNo": null,
            "dob": null,
            "roles": null,
            "department": null,
            "designation": null,
            "country": null,
            "city": null,
            "pincode": "0",
            "employeeStatus": null,
            "permanentAddress": null,
            "bloodGroup": null,
            "joinDate": null,
            "endDate": null,
            "managerEmpId": null,
            "managerEmailId": null
        },
        {
            "deleteFlag": false,
            "deletedOn": null,
            "activeFlag": false,
            "activeFromDate": null,
            "activeTillDate": null,
            "createdOn": null,
            "createdBy": null,
            "lastUpdatedOn": null,
            "lastUpdatedBy": null,
            "id": "60a4b37599b7d77f706d38f9",
            "versionId": 0,
            "userName": "sindhu",
            "password": "1234",
            "employeeId": 9,
            "firstName": null,
            "lastName": null,
            "emailId": null,
            "age": null,
            "gender": null,
            "mobileNo": null,
            "dob": null,
            "roles": null,
            "department": null,
            "designation": null,
            "country": null,
            "city": null,
            "pincode": null,
            "employeeStatus": null,
            "permanentAddress": null,
            "bloodGroup": null,
            "joinDate": null,
            "endDate": null,
            "managerEmpId": null,
            "managerEmailId": null
        },
        {
            "deleteFlag": false,
            "deletedOn": null,
            "activeFlag": false,
            "activeFromDate": null,
            "activeTillDate": null,
            "createdOn": null,
            "createdBy": null,
            "lastUpdatedOn": null,
            "lastUpdatedBy": null,
            "id": "60a7361611bf882201c7d56a",
            "versionId": 0,
            "userName": "Hari",
            "password": "1234",
            "employeeId": 11,
            "firstName": null,
            "lastName": null,
            "emailId": "desudhsdh@gmail.com",
            "age": 24,
            "gender": "male",
            "mobileNo": "+919840355954",
            "dob": null,
            "roles": [
                {
                    "deleteFlag": false,
                    "deletedOn": null,
                    "activeFlag": false,
                    "activeFromDate": null,
                    "activeTillDate": null,
                    "createdOn": null,
                    "createdBy": null,
                    "lastUpdatedOn": null,
                    "lastUpdatedBy": null,
                    "id": null,
                    "versionId": null,
                    "roleId": null,
                    "roleName": "USER",
                    "roleStatus": null
                }
            ],
            "department": "dev",
            "designation": null,
            "country": null,
            "city": null,
            "pincode": "523001",
            "employeeStatus": null,
            "permanentAddress": null,
            "bloodGroup": null,
            "joinDate": null,
            "endDate": null,
            "managerEmpId": null,
            "managerEmailId": null
        },
    ],
    "errorMessage": null,
    "success": true
}

10.TO GET AN EMPLOYEE BY EMPLOYEEID:
GET METHOD:
http://localhost:9442/employee/23
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60ad48f6b242a02eb130bcb9",
        "versionId": 0,
        "userName": "BalaKrishna",
        "password": "$2a$10$PQ1oyBOmqkS9NeYXEosAweNym0rhzsECfVynyj3C8ggG3fvoNO05y",
        "employeeId": 23,
        "firstName": null,
        "lastName": null,
        "emailId": "desudhsdh@gmail.com",
        "age": 24,
        "gender": "male",
        "mobileNo": "9840355954",
        "dob": null,
        "roles": [
            {
                "deleteFlag": false,
                "deletedOn": null,
                "activeFlag": false,
                "activeFromDate": null,
                "activeTillDate": null,
                "createdOn": null,
                "createdBy": null,
                "lastUpdatedOn": null,
                "lastUpdatedBy": null,
                "id": null,
                "versionId": null,
                "roleId": null,
                "roleName": "USER",
                "roleStatus": null
            }
        ],
        "department": "dev",
        "designation": null,
        "country": null,
        "city": null,
        "pincode": "523001",
        "employeeStatus": null,
        "permanentAddress": null,
        "bloodGroup": null,
        "joinDate": null,
        "endDate": null,
        "managerEmpId": null,
        "managerEmailId": null
    },
    "errorMessage": null,
    "success": true
}

11. TO UPDATE THE EMPLOYEE:
PUT METHOD:
http://localhost:9442/employee
REQUEST:
{
    "userName": "Desu Rajasekhar Gupta",
    "employeeId": 24,
    "managerEmailId": "desurajasekhargupta437@gmail.com",
    "managerEmpId": 25
}
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3b68c47e2346ae3443b1e",
        "versionId": 3,
        "userName": "Desu Rajasekhar Gupta",
        "password": "$2a$10$CUnEGEINajcO2ePRDIVHmeJV0cspu5idAIIEyHpXHyhaUC5TcmFqW",
        "employeeId": 24,
        "firstName": null,
        "lastName": null,
        "emailId": "desurajasekhargupta437@gmail.com",
        "age": 24,
        "gender": "male",
        "mobileNo": "+919840355954",
        "dob": null,
        "roles": [
            {
                "deleteFlag": false,
                "deletedOn": null,
                "activeFlag": false,
                "activeFromDate": null,
                "activeTillDate": null,
                "createdOn": null,
                "createdBy": null,
                "lastUpdatedOn": null,
                "lastUpdatedBy": null,
                "id": null,
                "versionId": null,
                "roleId": null,
                "roleName": "USER",
                "roleStatus": null
            },
            {
                "deleteFlag": false,
                "deletedOn": null,
                "activeFlag": false,
                "activeFromDate": null,
                "activeTillDate": null,
                "createdOn": null,
                "createdBy": null,
                "lastUpdatedOn": null,
                "lastUpdatedBy": null,
                "id": null,
                "versionId": null,
                "roleId": null,
                "roleName": "ADMIN",
                "roleStatus": null
            }
        ],
        "department": "dev",
        "designation": null,
        "country": "India",
        "city": "Ongole",
        "pincode": "523001",
        "employeeStatus": null,
        "permanentAddress": null,
        "bloodGroup": null,
        "joinDate": null,
        "endDate": null,
        "managerEmpId": 25,
        "managerEmailId": "desurajasekhargupta437@gmail.com"
    },
    "errorMessage": null,
    "success": true
}

12. TO GET THE ROLE BY ROLEID
GET METHOD
http://localhost:9442/role/792
RESPONSE
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3b5ba47e2346ae3443b1d",
        "versionId": 0,
        "roleId": "792",
        "roleName": "MANAGER",
        "roleStatus": "Active"
    },
    "errorMessage": null,
    "success": true
}

13.TO GET ALL THE ROLES AVAILABLE IN THE DATABASE:
GET METHOD:
http://localhost:9442/role/getAll 
RESPONSE:
{
    "result": [
        {
            "deleteFlag": false,
            "deletedOn": null,
            "activeFlag": false,
            "activeFromDate": null,
            "activeTillDate": null,
            "createdOn": null,
            "createdBy": null,
            "lastUpdatedOn": null,
            "lastUpdatedBy": null,
            "id": "60a276a72da0a957c512d1a0",
            "versionId": 1,
            "roleId": "806",
            "roleName": "USER",
            "roleStatus": "Active"
        },
        {
            "deleteFlag": false,
            "deletedOn": null,
            "activeFlag": false,
            "activeFromDate": null,
            "activeTillDate": null,
            "createdOn": null,
            "createdBy": null,
            "lastUpdatedOn": null,
            "lastUpdatedBy": null,
            "id": "60b3b5a247e2346ae3443b1c",
            "versionId": 0,
            "roleId": "41",
            "roleName": "ADMIN",
            "roleStatus": "Active"
        },
        {
            "deleteFlag": false,
            "deletedOn": null,
            "activeFlag": false,
            "activeFromDate": null,
            "activeTillDate": null,
            "createdOn": null,
            "createdBy": null,
            "lastUpdatedOn": null,
            "lastUpdatedBy": null,
            "id": "60b3b5ba47e2346ae3443b1d",
            "versionId": 0,
            "roleId": "792",
            "roleName": "MANAGER",
            "roleStatus": "Active"
        }
    ],
    "errorMessage": null,
    "success": true
}
14.TO UPDATE THE ROLE
PUT METHOD
http://localhost:9442/role/792
REQUEST:
{
    "roleId": "706",
    "roleName": "MANAGER",
    "roleStatus": "Inactive"
}
RESPONSE:
{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3b5ba47e2346ae3443b1d",
        "versionId": 1,
        "roleId": "706",
        "roleName": "MANAGER",
        "roleStatus": "Inactive"
    },
    "errorMessage": null,
    "success": true
}

15.TO SAVE THE LEAVE BALANCE OF AN EMPLOYEE 

POST METHOD:
http://localhost:9442/leave/leaveBalance

REQUEST:
{
    "employeeId": 25,
    "leaveTypeBalance": [
        {
            "leaveId": 1,
            "leaveType": "earnedLeave",
            "noOfDays": 10
        },
        {
            "leaveId": 2,
            "leaveType": "sickLeave",
            "noOfDays": 5
        },
        {
            "leaveId": 3,
            "leaveType": "lossOfPay",
            "noOfDays": 0
        }
    ],
    "annualAllotment": 30
}

RESPONSE:

{
    "result": {
        "deleteFlag": false,
        "deletedOn": null,
        "activeFlag": false,
        "activeFromDate": null,
        "activeTillDate": null,
        "createdOn": null,
        "createdBy": null,
        "lastUpdatedOn": null,
        "lastUpdatedBy": null,
        "id": "60b3f3c8e4271e570eea0765",
        "versionId": 0,
        "employeeId": 25,
        "leaveTypeBalance": [
            {
                "leaveId": 1,
                "leaveType": "earnedLeave",
                "noOfDays": 10.0
            },
            {
                "leaveId": 2,
                "leaveType": "sickLeave",
                "noOfDays": 5.0
            },
            {
                "leaveId": 3,
                "leaveType": "lossOfPay",
                "noOfDays": 0.0
            }
        ],
        "annualAllotment": 30
    },
    "errorMessage": null,
    "success": true
}



